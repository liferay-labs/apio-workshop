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

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.liferay.recipes.model.Recipe;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

/**
 * Provides the local service interface for Recipe. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Alejandro Hernández
 * @see RecipeLocalServiceUtil
 * @see com.liferay.recipes.service.base.RecipeLocalServiceBaseImpl
 * @see com.liferay.recipes.service.impl.RecipeLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface RecipeLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RecipeLocalServiceUtil} to access the recipe local service. Add custom service methods to {@link com.liferay.recipes.service.impl.RecipeLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
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
	public Recipe addRecipe(long userId, String name, String region,
		Long imageFileEntryId, Date publishedDate, int hoursToMake,
		int minutesToMake, List<String> steps, List<String> ingredients,
		String videoURL, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Adds the recipe to the database. Also notifies the appropriate model listeners.
	*
	* @param recipe the recipe
	* @return the recipe that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Recipe addRecipe(Recipe recipe);

	/**
	* Creates a new recipe with the primary key. Does not add the recipe to the database.
	*
	* @param recipeId the primary key for the new recipe
	* @return the new recipe
	*/
	@Transactional(enabled = false)
	public Recipe createRecipe(long recipeId);

	public void deleteAsset(Recipe recipe) throws PortalException;

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	* Deletes the recipe with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param recipeId the primary key of the recipe
	* @return the recipe that was removed
	* @throws PortalException if a recipe with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public Recipe deleteRecipe(long recipeId) throws PortalException;

	/**
	* Deletes the recipe from the database. Also notifies the appropriate model listeners.
	*
	* @param recipe the recipe
	* @return the recipe that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public Recipe deleteRecipe(Recipe recipe);

	public DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Recipe fetchRecipe(long recipeId);

	/**
	* Returns the recipe matching the UUID and group.
	*
	* @param uuid the recipe's UUID
	* @param groupId the primary key of the group
	* @return the matching recipe, or <code>null</code> if a matching recipe could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Recipe fetchRecipeByUuidAndGroupId(String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns the recipe with the primary key.
	*
	* @param recipeId the primary key of the recipe
	* @return the recipe
	* @throws PortalException if a recipe with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Recipe getRecipe(long recipeId) throws PortalException;

	/**
	* Returns the recipe matching the UUID and group.
	*
	* @param uuid the recipe's UUID
	* @param groupId the primary key of the group
	* @return the matching recipe
	* @throws PortalException if a matching recipe could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Recipe getRecipeByUuidAndGroupId(String uuid, long groupId)
		throws PortalException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Recipe> getRecipes(int start, int end);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Recipe> getRecipesByCompanyId(long companyId, int start, int end);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Recipe> getRecipesByGroupId(long groupId, int start, int end);

	/**
	* Returns all the recipes matching the UUID and company.
	*
	* @param uuid the UUID of the recipes
	* @param companyId the primary key of the company
	* @return the matching recipes, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Recipe> getRecipesByUuidAndCompanyId(String uuid, long companyId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Recipe> getRecipesByUuidAndCompanyId(String uuid,
		long companyId, int start, int end,
		OrderByComparator<Recipe> orderByComparator);

	/**
	* Returns the number of recipes.
	*
	* @return the number of recipes
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getRecipesCount();

	/**
	* Returns the number of recipes of the company.
	*
	* @param companyId the company ID
	* @return the number of recipes of the company
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getRecipesCountByCompanyId(long companyId);

	/**
	* Returns the number of recipes of the group.
	*
	* @param groupId the group ID
	* @return the number of recipes of the group
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getRecipesCountByGroupId(long groupId);

	public void updateAsset(long userId, Recipe recipe,
		long[] assetCategoryIds, String[] assetTagNames)
		throws PortalException;

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
	public Recipe updateRecipe(long userId, long recipeId, String name,
		String region, long imageFileEntryId, Date publishedDate,
		int hoursToMake, int minutesToMake, List<String> steps,
		List<String> ingredients, String videoURL, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Updates the recipe in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param recipe the recipe
	* @return the recipe that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Recipe updateRecipe(Recipe recipe);
}