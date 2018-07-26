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

package com.liferay.recipes.service.impl;

import static com.liferay.portal.kernel.util.ContentTypes.TEXT;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.recipes.exception.RecipeInvalidTimeToMakeException;
import com.liferay.recipes.exception.RecipeNameTooLongException;
import com.liferay.recipes.exception.RecipeNullPublishedDateException;
import com.liferay.recipes.exception.RecipeRegionTooLongException;
import com.liferay.recipes.exception.RecipeVideoURLInvalidException;
import com.liferay.recipes.model.Recipe;
import com.liferay.recipes.service.base.RecipeLocalServiceBaseImpl;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.Date;
import java.util.List;

/**
 * The implementation of the recipe local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the {@link
 * com.liferay.recipes.service.RecipeLocalService} interface. <p> This is a
 * local service. Methods of this service will not have security checks based on
 * the propagated JAAS credentials because this service can only be accessed
 * from within the same VM.
 * </p>
 *
 * @author Alejandro Hernández
 * @see    RecipeLocalServiceBaseImpl
 * @see    com.liferay.recipes.service.RecipeLocalServiceUtil
 */
@ProviderType
public class RecipeLocalServiceImpl extends RecipeLocalServiceBaseImpl {

	/**
	 * Adds a new Recipe to the database.
	 *
	 * @param  userId the recipe's creator
	 * @param  name the recipe's name
	 * @param  region the recipe's region
	 * @param  imageFileEntryId the recipe's image fileEntryId (can be {@code
	 *         null}
	 * @param  publishedDate the published date
	 * @param  hoursToMake the number of hours needed to make the recipe
	 * @param  minutesToMake the number of minutes needed to make the recipe
	 * @param  steps the recipe's list of steps
	 * @param  ingredients the recipe's list of ingredients
	 * @param  videoURL the recipe's video URL
	 * @param  serviceContext the service context
	 * @return the new recipe
	 */
	public Recipe addRecipe(
			long userId, String name, String region, Long imageFileEntryId,
			Date publishedDate, int hoursToMake, int minutesToMake,
			List<String> steps, List<String> ingredients, String videoURL,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		long groupId = serviceContext.getScopeGroupId();

		_validateNameAndRegion(name, region);

		_validateDate(publishedDate);

		_validateTime(hoursToMake, minutesToMake);

		_validateVideoURL(videoURL);

		long recipeId = counterLocalService.increment();

		Recipe recipe = recipePersistence.create(recipeId);

		recipe.setUuid(serviceContext.getUuid());
		recipe.setUserId(userId);
		recipe.setUserName(user.getFullName());
		recipe.setUserUuid(user.getUserUuid());
		recipe.setGroupId(groupId);

		if (Validator.isNotNull(imageFileEntryId)) {
			FileEntry fileEntry = dlAppLocalService.getFileEntry(
				imageFileEntryId);

			recipe.setImageFileEntryId(fileEntry.getFileEntryId());
		}

		recipe.setName(name);
		recipe.setRegion(region);
		recipe.setHoursToMake(hoursToMake);
		recipe.setMinutesToMake(minutesToMake);
		recipe.setStepsString(JSONFactoryUtil.looseSerialize(steps));
		recipe.setIngredientsString(
			JSONFactoryUtil.looseSerialize(ingredients));
		recipe.setVideoURL(videoURL);

		updateRecipe(recipe);

		resourceLocalService.addModelResources(recipe, serviceContext);

		resourceLocalService.addResources(
			recipe.getCompanyId(), groupId, userId, Recipe.class.getName(),
			recipeId, false, true, true);

		updateAsset(
			userId, recipe, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames());

		return recipe;
	}

	@Override
	public void deleteAsset(Recipe recipe) throws PortalException {
		assetEntryLocalService.deleteEntry(
			Recipe.class.getName(), recipe.getRecipeId());

		Indexer<Recipe> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			Recipe.class);

		indexer.delete(recipe);
	}

	/**
	 * Deletes the recipe with the primary key from the database. Also notifies
	 * the appropriate model listeners.
	 *
	 * @param  recipeId the primary key of the recipe
	 * @return the recipe that was removed
	 * @throws PortalException if a recipe with the primary key could not be
	 *         found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Recipe deleteRecipe(long recipeId) throws PortalException {
		Recipe recipe = recipePersistence.remove(recipeId);

		dlAppLocalService.deleteFileEntry(recipe.getImageFileEntryId());

		resourceLocalService.deleteResource(
			recipe.getCompanyId(), Recipe.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, recipeId);

		deleteAsset(recipe);

		return recipe;
	}

	/**
	 * Returns a range of all the recipes of the company.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link
	 * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	 * result set. If <code>orderByComparator</code> is specified, then the
	 * query will include the given ORDER BY logic. If
	 * <code>orderByComparator</code> is absent and pagination is required
	 * (<code>start</code> and <code>end</code> are not {@link
	 * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query
	 * will include the default ORDER BY logic from {@link
	 * com.liferay.recipes.model.impl.RecipeModelImpl}. If both
	 * <code>orderByComparator</code> and pagination are absent, for performance
	 * reasons, the query will not have an ORDER BY clause and the returned
	 * result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param  companyId the company ID
	 * @param  start the lower bound of the range of recipes
	 * @param  end the upper bound of the range of recipes (not inclusive)
	 * @return the range of recipes of the company
	 */
	@Override
	public List<Recipe> getRecipesByCompanyId(
		long companyId, int start, int end) {

		return recipePersistence.findByCompanyId(companyId, start, end);
	}

	/**
	 * Returns a range of all the recipes of the group.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link
	 * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	 * result set. If <code>orderByComparator</code> is specified, then the
	 * query will include the given ORDER BY logic. If
	 * <code>orderByComparator</code> is absent and pagination is required
	 * (<code>start</code> and <code>end</code> are not {@link
	 * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query
	 * will include the default ORDER BY logic from {@link
	 * com.liferay.recipes.model.impl.RecipeModelImpl}. If both
	 * <code>orderByComparator</code> and pagination are absent, for performance
	 * reasons, the query will not have an ORDER BY clause and the returned
	 * result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param  groupId the group ID
	 * @param  start the lower bound of the range of recipes
	 * @param  end the upper bound of the range of recipes (not inclusive)
	 * @return the range of recipes of the group
	 */
	@Override
	public List<Recipe> getRecipesByGroupId(long groupId, int start, int end) {
		return recipePersistence.findByGroupId(groupId, start, end);
	}

	/**
	 * Returns the number of recipes of the company.
	 *
	 * @param  companyId the company ID
	 * @return the number of recipes of the company
	 */
	@Override
	public int getRecipesCountByCompanyId(long companyId) {
		return recipePersistence.countByCompanyId(companyId);
	}

	/**
	 * Returns the number of recipes of the group.
	 *
	 * @param  groupId the group ID
	 * @return the number of recipes of the group
	 */
	@Override
	public int getRecipesCountByGroupId(long groupId) {
		return recipePersistence.countByGroupId(groupId);
	}

	@Override
	public void updateAsset(
			long userId, Recipe recipe, long[] assetCategoryIds,
			String[] assetTagNames)
		throws PortalException {

		assetEntryLocalService.updateEntry(
			userId, recipe.getGroupId(), recipe.getCreateDate(),
			recipe.getModifiedDate(), Recipe.class.getName(),
			recipe.getRecipeId(), recipe.getUuid(), 0, assetCategoryIds,
			assetTagNames, false, false, null, null, null, null, TEXT,
			recipe.getName(), recipe.getRegion(), "", null, null, 0, 0, null);

		Indexer<Recipe> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			Recipe.class);

		indexer.reindex(recipe);
	}

	/**
	 * Updates a Recipe in the database.
	 *
	 * @param  userId the recipe's creator
	 * @param  recipeId the recipe's ID
	 * @param  name the recipe's name
	 * @param  region the recipe's region
	 * @param  imageFileEntryId the recipe's image fileEntryId (can be {@code
	 *         null}
	 * @param  publishedDate the published date
	 * @param  hoursToMake the number of hours needed to make the recipe
	 * @param  minutesToMake the number of minutes needed to make the recipe
	 * @param  steps the recipe's list of steps
	 * @param  ingredients the recipe's list of ingredients
	 * @param  videoURL the recipe's video URL
	 * @param  serviceContext the service context
	 * @return the updated recipe
	 */
	@Override
	public Recipe updateRecipe(
			long userId, long recipeId, String name, String region,
			long imageFileEntryId, Date publishedDate, int hoursToMake,
			int minutesToMake, List<String> steps, List<String> ingredients,
			String videoURL, ServiceContext serviceContext)
		throws PortalException {

		Recipe recipe = recipePersistence.findByPrimaryKey(recipeId);

		User user = userLocalService.getUser(userId);

		long groupId = serviceContext.getScopeGroupId();

		_validateNameAndRegion(name, region);

		_validateDate(publishedDate);

		_validateTime(hoursToMake, minutesToMake);

		_validateVideoURL(videoURL);

		recipe.setUuid(serviceContext.getUuid());
		recipe.setUserId(userId);
		recipe.setUserName(user.getFullName());
		recipe.setUserUuid(user.getUserUuid());
		recipe.setGroupId(groupId);

		if (Validator.isNotNull(imageFileEntryId)) {
			FileEntry fileEntry = dlAppLocalService.getFileEntry(
				imageFileEntryId);

			recipe.setImageFileEntryId(fileEntry.getFileEntryId());
		}

		recipe.setName(name);
		recipe.setRegion(region);
		recipe.setHoursToMake(hoursToMake);
		recipe.setMinutesToMake(minutesToMake);
		recipe.setStepsString(JSONFactoryUtil.looseSerialize(steps));
		recipe.setIngredientsString(
			JSONFactoryUtil.looseSerialize(ingredients));
		recipe.setVideoURL(videoURL);

		updateRecipe(recipe);

		resourceLocalService.addModelResources(recipe, serviceContext);

		resourceLocalService.addResources(
			recipe.getCompanyId(), groupId, userId, Recipe.class.getName(),
			recipeId, false, true, true);

		updateAsset(
			userId, recipe, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames());

		return recipe;
	}

	private void _validateDate(Date publishedDate)
		throws RecipeNullPublishedDateException {

		if (Validator.isNull(publishedDate)) {
			throw new RecipeNullPublishedDateException();
		}
	}

	private void _validateNameAndRegion(String name, String region)
		throws PortalException {

		if (Validator.isNotNull(name)) {
			if (name.length() > 255) {
				throw new RecipeNameTooLongException();
			}
		}

		if (Validator.isNotNull(region)) {
			if (region.length() > 255) {
				throw new RecipeRegionTooLongException();
			}
		}
	}

	private void _validateTime(int hoursToMake, int minutesToMake)
		throws RecipeInvalidTimeToMakeException {

		if (hoursToMake < 0) {
			throw new RecipeInvalidTimeToMakeException(
				"Hours to make cannot be less than 0");
		}

		if ((minutesToMake < 0) || (minutesToMake > 59)) {
			throw new RecipeInvalidTimeToMakeException(
				"Minutes to make must be a number between 0 and 59");
		}
	}

	private void _validateVideoURL(String videoURL)
		throws RecipeVideoURLInvalidException {

		if (Validator.isNotNull(videoURL)) {
			try {
				new URL(videoURL);
			}
			catch (MalformedURLException murle) {
				throw new RecipeVideoURLInvalidException(murle);
			}
		}
	}

}