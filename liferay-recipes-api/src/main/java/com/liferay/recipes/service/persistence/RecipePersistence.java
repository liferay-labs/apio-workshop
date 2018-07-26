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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.liferay.recipes.exception.NoSuchRecipeException;
import com.liferay.recipes.model.Recipe;

/**
 * The persistence interface for the recipe service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Alejandro Hernández
 * @see com.liferay.recipes.service.persistence.impl.RecipePersistenceImpl
 * @see RecipeUtil
 * @generated
 */
@ProviderType
public interface RecipePersistence extends BasePersistence<Recipe> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RecipeUtil} to access the recipe persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the recipes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching recipes
	*/
	public java.util.List<Recipe> findByUuid(String uuid);

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
	public java.util.List<Recipe> findByUuid(String uuid, int start, int end);

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
	public java.util.List<Recipe> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Recipe> orderByComparator);

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
	public java.util.List<Recipe> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Recipe> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first recipe in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recipe
	* @throws NoSuchRecipeException if a matching recipe could not be found
	*/
	public Recipe findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Recipe> orderByComparator)
		throws NoSuchRecipeException;

	/**
	* Returns the first recipe in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recipe, or <code>null</code> if a matching recipe could not be found
	*/
	public Recipe fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Recipe> orderByComparator);

	/**
	* Returns the last recipe in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recipe
	* @throws NoSuchRecipeException if a matching recipe could not be found
	*/
	public Recipe findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Recipe> orderByComparator)
		throws NoSuchRecipeException;

	/**
	* Returns the last recipe in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recipe, or <code>null</code> if a matching recipe could not be found
	*/
	public Recipe fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Recipe> orderByComparator);

	/**
	* Returns the recipes before and after the current recipe in the ordered set where uuid = &#63;.
	*
	* @param recipeId the primary key of the current recipe
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next recipe
	* @throws NoSuchRecipeException if a recipe with the primary key could not be found
	*/
	public Recipe[] findByUuid_PrevAndNext(long recipeId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Recipe> orderByComparator)
		throws NoSuchRecipeException;

	/**
	* Removes all the recipes where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of recipes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching recipes
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the recipe where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchRecipeException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching recipe
	* @throws NoSuchRecipeException if a matching recipe could not be found
	*/
	public Recipe findByUUID_G(String uuid, long groupId)
		throws NoSuchRecipeException;

	/**
	* Returns the recipe where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching recipe, or <code>null</code> if a matching recipe could not be found
	*/
	public Recipe fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the recipe where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching recipe, or <code>null</code> if a matching recipe could not be found
	*/
	public Recipe fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the recipe where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the recipe that was removed
	*/
	public Recipe removeByUUID_G(String uuid, long groupId)
		throws NoSuchRecipeException;

	/**
	* Returns the number of recipes where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching recipes
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the recipes where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching recipes
	*/
	public java.util.List<Recipe> findByUuid_C(String uuid, long companyId);

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
	public java.util.List<Recipe> findByUuid_C(String uuid, long companyId,
		int start, int end);

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
	public java.util.List<Recipe> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Recipe> orderByComparator);

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
	public java.util.List<Recipe> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Recipe> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first recipe in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recipe
	* @throws NoSuchRecipeException if a matching recipe could not be found
	*/
	public Recipe findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Recipe> orderByComparator)
		throws NoSuchRecipeException;

	/**
	* Returns the first recipe in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recipe, or <code>null</code> if a matching recipe could not be found
	*/
	public Recipe fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Recipe> orderByComparator);

	/**
	* Returns the last recipe in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recipe
	* @throws NoSuchRecipeException if a matching recipe could not be found
	*/
	public Recipe findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Recipe> orderByComparator)
		throws NoSuchRecipeException;

	/**
	* Returns the last recipe in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recipe, or <code>null</code> if a matching recipe could not be found
	*/
	public Recipe fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Recipe> orderByComparator);

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
	public Recipe[] findByUuid_C_PrevAndNext(long recipeId, String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Recipe> orderByComparator)
		throws NoSuchRecipeException;

	/**
	* Removes all the recipes where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of recipes where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching recipes
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the recipes where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching recipes
	*/
	public java.util.List<Recipe> findByCompanyId(long companyId);

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
	public java.util.List<Recipe> findByCompanyId(long companyId, int start,
		int end);

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
	public java.util.List<Recipe> findByCompanyId(long companyId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Recipe> orderByComparator);

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
	public java.util.List<Recipe> findByCompanyId(long companyId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Recipe> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first recipe in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recipe
	* @throws NoSuchRecipeException if a matching recipe could not be found
	*/
	public Recipe findByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Recipe> orderByComparator)
		throws NoSuchRecipeException;

	/**
	* Returns the first recipe in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recipe, or <code>null</code> if a matching recipe could not be found
	*/
	public Recipe fetchByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Recipe> orderByComparator);

	/**
	* Returns the last recipe in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recipe
	* @throws NoSuchRecipeException if a matching recipe could not be found
	*/
	public Recipe findByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Recipe> orderByComparator)
		throws NoSuchRecipeException;

	/**
	* Returns the last recipe in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recipe, or <code>null</code> if a matching recipe could not be found
	*/
	public Recipe fetchByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Recipe> orderByComparator);

	/**
	* Returns the recipes before and after the current recipe in the ordered set where companyId = &#63;.
	*
	* @param recipeId the primary key of the current recipe
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next recipe
	* @throws NoSuchRecipeException if a recipe with the primary key could not be found
	*/
	public Recipe[] findByCompanyId_PrevAndNext(long recipeId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Recipe> orderByComparator)
		throws NoSuchRecipeException;

	/**
	* Removes all the recipes where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public void removeByCompanyId(long companyId);

	/**
	* Returns the number of recipes where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching recipes
	*/
	public int countByCompanyId(long companyId);

	/**
	* Returns all the recipes where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching recipes
	*/
	public java.util.List<Recipe> findByGroupId(long groupId);

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
	public java.util.List<Recipe> findByGroupId(long groupId, int start, int end);

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
	public java.util.List<Recipe> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Recipe> orderByComparator);

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
	public java.util.List<Recipe> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Recipe> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first recipe in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recipe
	* @throws NoSuchRecipeException if a matching recipe could not be found
	*/
	public Recipe findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Recipe> orderByComparator)
		throws NoSuchRecipeException;

	/**
	* Returns the first recipe in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recipe, or <code>null</code> if a matching recipe could not be found
	*/
	public Recipe fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Recipe> orderByComparator);

	/**
	* Returns the last recipe in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recipe
	* @throws NoSuchRecipeException if a matching recipe could not be found
	*/
	public Recipe findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Recipe> orderByComparator)
		throws NoSuchRecipeException;

	/**
	* Returns the last recipe in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recipe, or <code>null</code> if a matching recipe could not be found
	*/
	public Recipe fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Recipe> orderByComparator);

	/**
	* Returns the recipes before and after the current recipe in the ordered set where groupId = &#63;.
	*
	* @param recipeId the primary key of the current recipe
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next recipe
	* @throws NoSuchRecipeException if a recipe with the primary key could not be found
	*/
	public Recipe[] findByGroupId_PrevAndNext(long recipeId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Recipe> orderByComparator)
		throws NoSuchRecipeException;

	/**
	* Returns all the recipes that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching recipes that the user has permission to view
	*/
	public java.util.List<Recipe> filterFindByGroupId(long groupId);

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
	public java.util.List<Recipe> filterFindByGroupId(long groupId, int start,
		int end);

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
	public java.util.List<Recipe> filterFindByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Recipe> orderByComparator);

	/**
	* Returns the recipes before and after the current recipe in the ordered set of recipes that the user has permission to view where groupId = &#63;.
	*
	* @param recipeId the primary key of the current recipe
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next recipe
	* @throws NoSuchRecipeException if a recipe with the primary key could not be found
	*/
	public Recipe[] filterFindByGroupId_PrevAndNext(long recipeId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Recipe> orderByComparator)
		throws NoSuchRecipeException;

	/**
	* Removes all the recipes where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of recipes where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching recipes
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns the number of recipes that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching recipes that the user has permission to view
	*/
	public int filterCountByGroupId(long groupId);

	/**
	* Caches the recipe in the entity cache if it is enabled.
	*
	* @param recipe the recipe
	*/
	public void cacheResult(Recipe recipe);

	/**
	* Caches the recipes in the entity cache if it is enabled.
	*
	* @param recipes the recipes
	*/
	public void cacheResult(java.util.List<Recipe> recipes);

	/**
	* Creates a new recipe with the primary key. Does not add the recipe to the database.
	*
	* @param recipeId the primary key for the new recipe
	* @return the new recipe
	*/
	public Recipe create(long recipeId);

	/**
	* Removes the recipe with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param recipeId the primary key of the recipe
	* @return the recipe that was removed
	* @throws NoSuchRecipeException if a recipe with the primary key could not be found
	*/
	public Recipe remove(long recipeId) throws NoSuchRecipeException;

	public Recipe updateImpl(Recipe recipe);

	/**
	* Returns the recipe with the primary key or throws a {@link NoSuchRecipeException} if it could not be found.
	*
	* @param recipeId the primary key of the recipe
	* @return the recipe
	* @throws NoSuchRecipeException if a recipe with the primary key could not be found
	*/
	public Recipe findByPrimaryKey(long recipeId) throws NoSuchRecipeException;

	/**
	* Returns the recipe with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param recipeId the primary key of the recipe
	* @return the recipe, or <code>null</code> if a recipe with the primary key could not be found
	*/
	public Recipe fetchByPrimaryKey(long recipeId);

	@Override
	public java.util.Map<java.io.Serializable, Recipe> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the recipes.
	*
	* @return the recipes
	*/
	public java.util.List<Recipe> findAll();

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
	public java.util.List<Recipe> findAll(int start, int end);

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
	public java.util.List<Recipe> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Recipe> orderByComparator);

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
	public java.util.List<Recipe> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Recipe> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the recipes from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of recipes.
	*
	* @return the number of recipes
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}