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

package com.liferay.recipes.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Recipe. This utility wraps
 * {@link com.liferay.recipes.service.impl.RecipeLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Alejandro Hernández
 * @see RecipeLocalService
 * @see com.liferay.recipes.service.base.RecipeLocalServiceBaseImpl
 * @see com.liferay.recipes.service.impl.RecipeLocalServiceImpl
 * @generated
 */
@ProviderType
public class RecipeLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.recipes.service.impl.RecipeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds a new Recipe to the database.
	*
	* @param userId the recipe's creator
	* @param name the recipe's name
	* @param region the recipe's region
	* @param imageFileEntryId the recipe's image fileEntryId (can be {@code
	null}
	* @param publishedDate the published date
	* @param hoursToMake the number of hours needed to make the recipe
	* @param minutesToMake the number of minutes needed to make the recipe
	* @param steps the recipe's list of steps
	* @param ingredients the recipe's list of ingredients
	* @param videoURL the recipe's video URL
	* @param serviceContext the service context
	* @return the new recipe
	*/
	public static com.liferay.recipes.model.Recipe addRecipe(long userId,
		String name, String region, Long imageFileEntryId,
		java.util.Date publishedDate, int hoursToMake, int minutesToMake,
		java.util.List<String> steps, java.util.List<String> ingredients,
		String videoURL,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addRecipe(userId, name, region, imageFileEntryId,
			publishedDate, hoursToMake, minutesToMake, steps, ingredients,
			videoURL, serviceContext);
	}

	/**
	* Adds the recipe to the database. Also notifies the appropriate model listeners.
	*
	* @param recipe the recipe
	* @return the recipe that was added
	*/
	public static com.liferay.recipes.model.Recipe addRecipe(
		com.liferay.recipes.model.Recipe recipe) {
		return getService().addRecipe(recipe);
	}

	/**
	* Creates a new recipe with the primary key. Does not add the recipe to the database.
	*
	* @param recipeId the primary key for the new recipe
	* @return the new recipe
	*/
	public static com.liferay.recipes.model.Recipe createRecipe(long recipeId) {
		return getService().createRecipe(recipeId);
	}

	public static void deleteAsset(com.liferay.recipes.model.Recipe recipe)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteAsset(recipe);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the recipe with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param recipeId the primary key of the recipe
	* @return the recipe that was removed
	* @throws PortalException if a recipe with the primary key could not be found
	*/
	public static com.liferay.recipes.model.Recipe deleteRecipe(long recipeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteRecipe(recipeId);
	}

	/**
	* Deletes the recipe from the database. Also notifies the appropriate model listeners.
	*
	* @param recipe the recipe
	* @return the recipe that was removed
	*/
	public static com.liferay.recipes.model.Recipe deleteRecipe(
		com.liferay.recipes.model.Recipe recipe) {
		return getService().deleteRecipe(recipe);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.recipes.model.impl.RecipeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.recipes.model.impl.RecipeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.recipes.model.Recipe fetchRecipe(long recipeId) {
		return getService().fetchRecipe(recipeId);
	}

	/**
	* Returns the recipe matching the UUID and group.
	*
	* @param uuid the recipe's UUID
	* @param groupId the primary key of the group
	* @return the matching recipe, or <code>null</code> if a matching recipe could not be found
	*/
	public static com.liferay.recipes.model.Recipe fetchRecipeByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchRecipeByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the recipe with the primary key.
	*
	* @param recipeId the primary key of the recipe
	* @return the recipe
	* @throws PortalException if a recipe with the primary key could not be found
	*/
	public static com.liferay.recipes.model.Recipe getRecipe(long recipeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getRecipe(recipeId);
	}

	/**
	* Returns the recipe matching the UUID and group.
	*
	* @param uuid the recipe's UUID
	* @param groupId the primary key of the group
	* @return the matching recipe
	* @throws PortalException if a matching recipe could not be found
	*/
	public static com.liferay.recipes.model.Recipe getRecipeByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getRecipeByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the recipes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.recipes.model.impl.RecipeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of recipes
	* @param end the upper bound of the range of recipes (not inclusive)
	* @return the range of recipes
	*/
	public static java.util.List<com.liferay.recipes.model.Recipe> getRecipes(
		int start, int end) {
		return getService().getRecipes(start, end);
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
	* @param companyId the company ID
	* @param start the lower bound of the range of recipes
	* @param end the upper bound of the range of recipes (not inclusive)
	* @return the range of recipes of the company
	*/
	public static java.util.List<com.liferay.recipes.model.Recipe> getRecipesByCompanyId(
		long companyId, int start, int end) {
		return getService().getRecipesByCompanyId(companyId, start, end);
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
	* @param groupId the group ID
	* @param start the lower bound of the range of recipes
	* @param end the upper bound of the range of recipes (not inclusive)
	* @return the range of recipes of the group
	*/
	public static java.util.List<com.liferay.recipes.model.Recipe> getRecipesByGroupId(
		long groupId, int start, int end) {
		return getService().getRecipesByGroupId(groupId, start, end);
	}

	/**
	* Returns all the recipes matching the UUID and company.
	*
	* @param uuid the UUID of the recipes
	* @param companyId the primary key of the company
	* @return the matching recipes, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.recipes.model.Recipe> getRecipesByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getRecipesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of recipes matching the UUID and company.
	*
	* @param uuid the UUID of the recipes
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of recipes
	* @param end the upper bound of the range of recipes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching recipes, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.recipes.model.Recipe> getRecipesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.recipes.model.Recipe> orderByComparator) {
		return getService()
				   .getRecipesByUuidAndCompanyId(uuid, companyId, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of recipes.
	*
	* @return the number of recipes
	*/
	public static int getRecipesCount() {
		return getService().getRecipesCount();
	}

	/**
	* Returns the number of recipes of the company.
	*
	* @param companyId the company ID
	* @return the number of recipes of the company
	*/
	public static int getRecipesCountByCompanyId(long companyId) {
		return getService().getRecipesCountByCompanyId(companyId);
	}

	/**
	* Returns the number of recipes of the group.
	*
	* @param groupId the group ID
	* @return the number of recipes of the group
	*/
	public static int getRecipesCountByGroupId(long groupId) {
		return getService().getRecipesCountByGroupId(groupId);
	}

	public static void updateAsset(long userId,
		com.liferay.recipes.model.Recipe recipe, long[] assetCategoryIds,
		String[] assetTagNames)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().updateAsset(userId, recipe, assetCategoryIds, assetTagNames);
	}

	/**
	* Updates a Recipe in the database.
	*
	* @param userId the recipe's creator
	* @param recipeId the recipe's ID
	* @param name the recipe's name
	* @param region the recipe's region
	* @param imageFileEntryId the recipe's image fileEntryId (can be {@code
	null}
	* @param publishedDate the published date
	* @param hoursToMake the number of hours needed to make the recipe
	* @param minutesToMake the number of minutes needed to make the recipe
	* @param steps the recipe's list of steps
	* @param ingredients the recipe's list of ingredients
	* @param videoURL the recipe's video URL
	* @param serviceContext the service context
	* @return the updated recipe
	*/
	public static com.liferay.recipes.model.Recipe updateRecipe(long userId,
		long recipeId, String name, String region, long imageFileEntryId,
		java.util.Date publishedDate, int hoursToMake, int minutesToMake,
		java.util.List<String> steps, java.util.List<String> ingredients,
		String videoURL,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateRecipe(userId, recipeId, name, region,
			imageFileEntryId, publishedDate, hoursToMake, minutesToMake, steps,
			ingredients, videoURL, serviceContext);
	}

	/**
	* Updates the recipe in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param recipe the recipe
	* @return the recipe that was updated
	*/
	public static com.liferay.recipes.model.Recipe updateRecipe(
		com.liferay.recipes.model.Recipe recipe) {
		return getService().updateRecipe(recipe);
	}

	public static RecipeLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<RecipeLocalService, RecipeLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(RecipeLocalService.class);

		ServiceTracker<RecipeLocalService, RecipeLocalService> serviceTracker = new ServiceTracker<RecipeLocalService, RecipeLocalService>(bundle.getBundleContext(),
				RecipeLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}