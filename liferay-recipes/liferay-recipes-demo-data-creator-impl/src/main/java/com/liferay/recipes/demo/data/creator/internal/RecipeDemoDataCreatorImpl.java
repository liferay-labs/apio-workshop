/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.recipes.demo.data.creator.internal;

import static com.liferay.recipes.demo.data.creator.internal.MealDBJSONRecipeProvider.createMealDBRecipeInformation;

import static java.util.concurrent.TimeUnit.DAYS;

import com.github.javafaker.Faker;

import com.liferay.apio.architect.functional.Try;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.category.demo.data.creator.AssetCategoryDemoDataCreator;
import com.liferay.category.demo.data.creator.AssetVocabularyDemoDataCreator;
import com.liferay.document.library.demo.data.creator.FileEntryDemoDataCreator;
import com.liferay.document.library.demo.data.creator.RootFolderDemoDataCreator;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.RandomUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.recipes.demo.data.creator.RecipeDemoDataCreator;
import com.liferay.recipes.model.Recipe;
import com.liferay.recipes.service.RecipeLocalService;

import java.net.URL;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alejandro Hernández
 */
@Component(immediate = true)
public class RecipeDemoDataCreatorImpl implements RecipeDemoDataCreator {

	@Override
	public Recipe create(long userId, long groupId) {
		return Try.fromFallible(
			() -> _rootFolderDemoDataCreator.create(userId, groupId, "Recipes")
		).map(
			Folder::getFolderId
		).map(
			folderId -> createRecipe(userId, folderId, groupId)
		).fold(
			__ -> null, this::storeRecipeId
		);
	}

	@Override
	public void delete() {
		_ids.forEach(
			id -> Try.fromFallible(() -> _recipeLocalService.deleteRecipe(id)));

		ignoreException(_fileEntryDemoDataCreator::delete);
		ignoreException(_rootFolderDemoDataCreator::delete);
		_assetCategoryDemoDataCreator.delete();
		_assetVocabularyDemoDataCreator.delete();
	}

	/**
	 * Creates a new recipe and stores it in the database.
	 *
	 * @param  userId the user that creates the recipe
	 * @param  folderId the ID of the folder where the recipe image is stored
	 * @param  groupId the ID of the group where the recipe is created
	 * @return a new recipe
	 */
	protected Recipe createRecipe(long userId, long folderId, long groupId)
		throws Exception {

		RecipeInformation recipeInformation = createMealDBRecipeInformation();
		Date createAndModifiedDate = new Faker().date().past(90, DAYS);

		ServiceContext serviceContext = getServiceContext(
			userId, groupId, recipeInformation, createAndModifiedDate);

		FileEntry fileEntry = createRecipeImage(
			userId, folderId, recipeInformation.getImageURL());

		int hoursToMake = RandomUtil.nextInt(3);

		int minutesToMake = RandomUtil.nextInt(59);

		Date publishedDate = new Faker().date().between(
			createAndModifiedDate, new Date());

		return _recipeLocalService.addRecipe(
			userId, recipeInformation.getName(), recipeInformation.getRegion(),
			fileEntry.getFileEntryId(), publishedDate, hoursToMake,
			minutesToMake, recipeInformation.getSteps(),
			recipeInformation.getIngredients(), recipeInformation.getVideoURL(),
			serviceContext);
	}

	/**
	 * Tries to create an image for a recipe using the provided URL to obtain
	 * the image bytes. If it fails, tries to create it using a {@link
	 * FileEntryDemoDataCreator}.
	 *
	 * @param  userId the user that creates the recipe
	 * @param  folderId the ID of the folder where the recipe image is stored
	 * @param  imageURL the URL of the image to obtain
	 * @return an image for a recipe
	 */
	protected FileEntry createRecipeImage(
		long userId, long folderId, String imageURL) {

		Folder folder = Try.fromFallible(
			() -> _dlAppLocalService.getFolder(folderId)
		).orElse(
			null
		);

		UUID uuid = UUID.randomUUID();

		String sourceFileName = uuid.toString() + ".jpeg";

		return Try.fromFallibleWithResources(
			() -> new URL(imageURL).openStream(), FileUtil::getBytes
		).map(
			bytes -> _dlAppLocalService.addFileEntry(
				userId, folder.getGroupId(), folderId, sourceFileName,
				"image/jpeg", bytes, new ServiceContext())
		).recoverWith(
			__ -> Try.fromFallible(
				() -> _fileEntryDemoDataCreator.create(userId, folderId))
		).orElse(
			null
		);
	}

	/**
	 * Returns the recipe category's ID, if the category is found or can be
	 * created. Returns {@code Optional#empty()} otherwise.
	 *
	 * @param  userId the user who creates the category
	 * @param  groupId the ID of the group where the category is created
	 * @param  name the name of the category
	 * @return the recipe category's ID, if the category is found or can be
	 *         created; {@code Optional#empty()} otherwise
	 */
	protected Optional<Long> getCategoryIdOptional(
		long userId, long groupId, String name) {

		return Optional.ofNullable(
			_assetVocabularyDemoDataCreator.create(userId, groupId, "Meals")
		).map(
			AssetVocabulary::getVocabularyId
		).map(
			id -> _assetCategoryDemoDataCreator.create(userId, id, name)
		).map(
			AssetCategory::getCategoryId
		);
	}

	/**
	 * Returns a valid {@link ServiceContext} for creating Recipes.
	 *
	 * @param  userId the ID of the user who creates the {@link Recipe}
	 * @param  groupId the recipe's group ID
	 * @param  recipeInformation the recipe's information
	 * @param  createAndModifiedDate the create and modified date
	 * @return a valid {@code ServiceContext} for creating Recipes
	 */
	protected ServiceContext getServiceContext(
		long userId, long groupId, RecipeInformation recipeInformation,
		Date createAndModifiedDate) {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setScopeGroupId(groupId);

		serviceContext.setCreateDate(createAndModifiedDate);
		serviceContext.setModifiedDate(createAndModifiedDate);

		getCategoryIdOptional(
			userId, groupId, recipeInformation.getCategory()
		).ifPresent(
			id -> serviceContext.setAssetCategoryIds(new long[] {id})
		);

		serviceContext.setAssetTagNames(
			recipeInformation.getTags().toArray(new String[0]));

		return serviceContext;
	}

	/**
	 * Runs a void method (a method that neither receive parameters nor returns
	 * output) and ignores any exceptions throws by it.
	 *
	 * @param voidMethod the method being executed
	 */
	protected void ignoreException(VoidMethod voidMethod) {
		try {
			voidMethod.run();
		}
		catch (Exception ignore) {
		}
	}

	/**
	 * Stores the recipe ID in the internal list so it gets deleted when called
	 * {@link #delete()}.
	 *
	 * @param  recipe the recipe to store
	 * @return the stored recipe
	 */
	protected Recipe storeRecipeId(Recipe recipe) {
		_ids.add(recipe.getRecipeId());

		return recipe;
	}

	@Reference
	private AssetCategoryDemoDataCreator _assetCategoryDemoDataCreator;

	@Reference
	private AssetVocabularyDemoDataCreator _assetVocabularyDemoDataCreator;

	@Reference
	private DLAppLocalService _dlAppLocalService;

	@Reference
	private FileEntryDemoDataCreator _fileEntryDemoDataCreator;

	private List<Long> _ids = new ArrayList<>();

	@Reference
	private RecipeLocalService _recipeLocalService;

	@Reference
	private RootFolderDemoDataCreator _rootFolderDemoDataCreator;

	/**
	 * Instances of this interface represent a method that neither receive
	 * parameters nor returns output but can throw an exception.
	 */
	@FunctionalInterface
	private interface VoidMethod {

		/**
		 * Runs an action.
		 */
		public void run() throws Exception;

	}

}