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

package com.liferay.recipes.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.liferay.recipes.model.Recipe;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the recipe service. This utility wraps {@link com.liferay.recipes.service.persistence.impl.RecipePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Alejandro Hernández
 * @see RecipePersistence
 * @see com.liferay.recipes.service.persistence.impl.RecipePersistenceImpl
 * @generated
 */
@ProviderType
public class RecipeUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(Recipe recipe) {
		getPersistence().clearCache(recipe);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Recipe> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Recipe> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Recipe> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<Recipe> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Recipe update(Recipe recipe) {
		return getPersistence().update(recipe);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Recipe update(Recipe recipe, ServiceContext serviceContext) {
		return getPersistence().update(recipe, serviceContext);
	}

	/**
	* Returns all the recipes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching recipes
	*/
	public static List<Recipe> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the recipes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecipeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of recipes
	* @param end the upper bound of the range of recipes (not inclusive)
	* @return the range of matching recipes
	*/
	public static List<Recipe> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the recipes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecipeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of recipes
	* @param end the upper bound of the range of recipes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching recipes
	*/
	public static List<Recipe> findByUuid(String uuid, int start, int end,
		OrderByComparator<Recipe> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the recipes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecipeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of recipes
	* @param end the upper bound of the range of recipes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching recipes
	*/
	public static List<Recipe> findByUuid(String uuid, int start, int end,
		OrderByComparator<Recipe> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first recipe in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recipe
	* @throws NoSuchRecipeException if a matching recipe could not be found
	*/
	public static Recipe findByUuid_First(String uuid,
		OrderByComparator<Recipe> orderByComparator)
		throws com.liferay.recipes.exception.NoSuchRecipeException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first recipe in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recipe, or <code>null</code> if a matching recipe could not be found
	*/
	public static Recipe fetchByUuid_First(String uuid,
		OrderByComparator<Recipe> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last recipe in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recipe
	* @throws NoSuchRecipeException if a matching recipe could not be found
	*/
	public static Recipe findByUuid_Last(String uuid,
		OrderByComparator<Recipe> orderByComparator)
		throws com.liferay.recipes.exception.NoSuchRecipeException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last recipe in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recipe, or <code>null</code> if a matching recipe could not be found
	*/
	public static Recipe fetchByUuid_Last(String uuid,
		OrderByComparator<Recipe> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the recipes before and after the current recipe in the ordered set where uuid = &#63;.
	*
	* @param recipeId the primary key of the current recipe
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next recipe
	* @throws NoSuchRecipeException if a recipe with the primary key could not be found
	*/
	public static Recipe[] findByUuid_PrevAndNext(long recipeId, String uuid,
		OrderByComparator<Recipe> orderByComparator)
		throws com.liferay.recipes.exception.NoSuchRecipeException {
		return getPersistence()
				   .findByUuid_PrevAndNext(recipeId, uuid, orderByComparator);
	}

	/**
	* Removes all the recipes where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of recipes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching recipes
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the recipe where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchRecipeException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching recipe
	* @throws NoSuchRecipeException if a matching recipe could not be found
	*/
	public static Recipe findByUUID_G(String uuid, long groupId)
		throws com.liferay.recipes.exception.NoSuchRecipeException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the recipe where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching recipe, or <code>null</code> if a matching recipe could not be found
	*/
	public static Recipe fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the recipe where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching recipe, or <code>null</code> if a matching recipe could not be found
	*/
	public static Recipe fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the recipe where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the recipe that was removed
	*/
	public static Recipe removeByUUID_G(String uuid, long groupId)
		throws com.liferay.recipes.exception.NoSuchRecipeException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of recipes where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching recipes
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the recipes where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching recipes
	*/
	public static List<Recipe> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the recipes where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecipeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of recipes
	* @param end the upper bound of the range of recipes (not inclusive)
	* @return the range of matching recipes
	*/
	public static List<Recipe> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the recipes where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecipeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of recipes
	* @param end the upper bound of the range of recipes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching recipes
	*/
	public static List<Recipe> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<Recipe> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the recipes where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecipeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of recipes
	* @param end the upper bound of the range of recipes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching recipes
	*/
	public static List<Recipe> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<Recipe> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first recipe in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recipe
	* @throws NoSuchRecipeException if a matching recipe could not be found
	*/
	public static Recipe findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Recipe> orderByComparator)
		throws com.liferay.recipes.exception.NoSuchRecipeException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first recipe in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recipe, or <code>null</code> if a matching recipe could not be found
	*/
	public static Recipe fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Recipe> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last recipe in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recipe
	* @throws NoSuchRecipeException if a matching recipe could not be found
	*/
	public static Recipe findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Recipe> orderByComparator)
		throws com.liferay.recipes.exception.NoSuchRecipeException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last recipe in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recipe, or <code>null</code> if a matching recipe could not be found
	*/
	public static Recipe fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Recipe> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the recipes before and after the current recipe in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param recipeId the primary key of the current recipe
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next recipe
	* @throws NoSuchRecipeException if a recipe with the primary key could not be found
	*/
	public static Recipe[] findByUuid_C_PrevAndNext(long recipeId, String uuid,
		long companyId, OrderByComparator<Recipe> orderByComparator)
		throws com.liferay.recipes.exception.NoSuchRecipeException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(recipeId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the recipes where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of recipes where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching recipes
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the recipes where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching recipes
	*/
	public static List<Recipe> findByCompanyId(long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the recipes where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecipeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of recipes
	* @param end the upper bound of the range of recipes (not inclusive)
	* @return the range of matching recipes
	*/
	public static List<Recipe> findByCompanyId(long companyId, int start,
		int end) {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the recipes where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecipeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of recipes
	* @param end the upper bound of the range of recipes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching recipes
	*/
	public static List<Recipe> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<Recipe> orderByComparator) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the recipes where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecipeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of recipes
	* @param end the upper bound of the range of recipes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching recipes
	*/
	public static List<Recipe> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<Recipe> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first recipe in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recipe
	* @throws NoSuchRecipeException if a matching recipe could not be found
	*/
	public static Recipe findByCompanyId_First(long companyId,
		OrderByComparator<Recipe> orderByComparator)
		throws com.liferay.recipes.exception.NoSuchRecipeException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first recipe in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recipe, or <code>null</code> if a matching recipe could not be found
	*/
	public static Recipe fetchByCompanyId_First(long companyId,
		OrderByComparator<Recipe> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last recipe in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recipe
	* @throws NoSuchRecipeException if a matching recipe could not be found
	*/
	public static Recipe findByCompanyId_Last(long companyId,
		OrderByComparator<Recipe> orderByComparator)
		throws com.liferay.recipes.exception.NoSuchRecipeException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last recipe in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recipe, or <code>null</code> if a matching recipe could not be found
	*/
	public static Recipe fetchByCompanyId_Last(long companyId,
		OrderByComparator<Recipe> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the recipes before and after the current recipe in the ordered set where companyId = &#63;.
	*
	* @param recipeId the primary key of the current recipe
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next recipe
	* @throws NoSuchRecipeException if a recipe with the primary key could not be found
	*/
	public static Recipe[] findByCompanyId_PrevAndNext(long recipeId,
		long companyId, OrderByComparator<Recipe> orderByComparator)
		throws com.liferay.recipes.exception.NoSuchRecipeException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(recipeId, companyId,
			orderByComparator);
	}

	/**
	* Removes all the recipes where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Returns the number of recipes where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching recipes
	*/
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns all the recipes where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching recipes
	*/
	public static List<Recipe> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the recipes where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecipeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of recipes
	* @param end the upper bound of the range of recipes (not inclusive)
	* @return the range of matching recipes
	*/
	public static List<Recipe> findByGroupId(long groupId, int start, int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the recipes where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecipeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of recipes
	* @param end the upper bound of the range of recipes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching recipes
	*/
	public static List<Recipe> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Recipe> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the recipes where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecipeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of recipes
	* @param end the upper bound of the range of recipes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching recipes
	*/
	public static List<Recipe> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Recipe> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first recipe in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recipe
	* @throws NoSuchRecipeException if a matching recipe could not be found
	*/
	public static Recipe findByGroupId_First(long groupId,
		OrderByComparator<Recipe> orderByComparator)
		throws com.liferay.recipes.exception.NoSuchRecipeException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first recipe in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recipe, or <code>null</code> if a matching recipe could not be found
	*/
	public static Recipe fetchByGroupId_First(long groupId,
		OrderByComparator<Recipe> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last recipe in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recipe
	* @throws NoSuchRecipeException if a matching recipe could not be found
	*/
	public static Recipe findByGroupId_Last(long groupId,
		OrderByComparator<Recipe> orderByComparator)
		throws com.liferay.recipes.exception.NoSuchRecipeException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last recipe in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recipe, or <code>null</code> if a matching recipe could not be found
	*/
	public static Recipe fetchByGroupId_Last(long groupId,
		OrderByComparator<Recipe> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the recipes before and after the current recipe in the ordered set where groupId = &#63;.
	*
	* @param recipeId the primary key of the current recipe
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next recipe
	* @throws NoSuchRecipeException if a recipe with the primary key could not be found
	*/
	public static Recipe[] findByGroupId_PrevAndNext(long recipeId,
		long groupId, OrderByComparator<Recipe> orderByComparator)
		throws com.liferay.recipes.exception.NoSuchRecipeException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(recipeId, groupId,
			orderByComparator);
	}

	/**
	* Returns all the recipes that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching recipes that the user has permission to view
	*/
	public static List<Recipe> filterFindByGroupId(long groupId) {
		return getPersistence().filterFindByGroupId(groupId);
	}

	/**
	* Returns a range of all the recipes that the user has permission to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecipeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of recipes
	* @param end the upper bound of the range of recipes (not inclusive)
	* @return the range of matching recipes that the user has permission to view
	*/
	public static List<Recipe> filterFindByGroupId(long groupId, int start,
		int end) {
		return getPersistence().filterFindByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the recipes that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecipeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of recipes
	* @param end the upper bound of the range of recipes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching recipes that the user has permission to view
	*/
	public static List<Recipe> filterFindByGroupId(long groupId, int start,
		int end, OrderByComparator<Recipe> orderByComparator) {
		return getPersistence()
				   .filterFindByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the recipes before and after the current recipe in the ordered set of recipes that the user has permission to view where groupId = &#63;.
	*
	* @param recipeId the primary key of the current recipe
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next recipe
	* @throws NoSuchRecipeException if a recipe with the primary key could not be found
	*/
	public static Recipe[] filterFindByGroupId_PrevAndNext(long recipeId,
		long groupId, OrderByComparator<Recipe> orderByComparator)
		throws com.liferay.recipes.exception.NoSuchRecipeException {
		return getPersistence()
				   .filterFindByGroupId_PrevAndNext(recipeId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the recipes where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of recipes where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching recipes
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns the number of recipes that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching recipes that the user has permission to view
	*/
	public static int filterCountByGroupId(long groupId) {
		return getPersistence().filterCountByGroupId(groupId);
	}

	/**
	* Caches the recipe in the entity cache if it is enabled.
	*
	* @param recipe the recipe
	*/
	public static void cacheResult(Recipe recipe) {
		getPersistence().cacheResult(recipe);
	}

	/**
	* Caches the recipes in the entity cache if it is enabled.
	*
	* @param recipes the recipes
	*/
	public static void cacheResult(List<Recipe> recipes) {
		getPersistence().cacheResult(recipes);
	}

	/**
	* Creates a new recipe with the primary key. Does not add the recipe to the database.
	*
	* @param recipeId the primary key for the new recipe
	* @return the new recipe
	*/
	public static Recipe create(long recipeId) {
		return getPersistence().create(recipeId);
	}

	/**
	* Removes the recipe with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param recipeId the primary key of the recipe
	* @return the recipe that was removed
	* @throws NoSuchRecipeException if a recipe with the primary key could not be found
	*/
	public static Recipe remove(long recipeId)
		throws com.liferay.recipes.exception.NoSuchRecipeException {
		return getPersistence().remove(recipeId);
	}

	public static Recipe updateImpl(Recipe recipe) {
		return getPersistence().updateImpl(recipe);
	}

	/**
	* Returns the recipe with the primary key or throws a {@link NoSuchRecipeException} if it could not be found.
	*
	* @param recipeId the primary key of the recipe
	* @return the recipe
	* @throws NoSuchRecipeException if a recipe with the primary key could not be found
	*/
	public static Recipe findByPrimaryKey(long recipeId)
		throws com.liferay.recipes.exception.NoSuchRecipeException {
		return getPersistence().findByPrimaryKey(recipeId);
	}

	/**
	* Returns the recipe with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param recipeId the primary key of the recipe
	* @return the recipe, or <code>null</code> if a recipe with the primary key could not be found
	*/
	public static Recipe fetchByPrimaryKey(long recipeId) {
		return getPersistence().fetchByPrimaryKey(recipeId);
	}

	public static java.util.Map<java.io.Serializable, Recipe> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the recipes.
	*
	* @return the recipes
	*/
	public static List<Recipe> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the recipes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecipeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of recipes
	* @param end the upper bound of the range of recipes (not inclusive)
	* @return the range of recipes
	*/
	public static List<Recipe> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the recipes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecipeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of recipes
	* @param end the upper bound of the range of recipes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of recipes
	*/
	public static List<Recipe> findAll(int start, int end,
		OrderByComparator<Recipe> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the recipes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecipeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of recipes
	* @param end the upper bound of the range of recipes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of recipes
	*/
	public static List<Recipe> findAll(int start, int end,
		OrderByComparator<Recipe> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the recipes from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of recipes.
	*
	* @return the number of recipes
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static RecipePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<RecipePersistence, RecipePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(RecipePersistence.class);

		ServiceTracker<RecipePersistence, RecipePersistence> serviceTracker = new ServiceTracker<RecipePersistence, RecipePersistence>(bundle.getBundleContext(),
				RecipePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}