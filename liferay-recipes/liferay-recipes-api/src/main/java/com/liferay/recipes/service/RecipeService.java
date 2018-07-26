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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import com.liferay.recipes.model.Recipe;

import java.util.List;

/**
 * Provides the remote service interface for Recipe. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Alejandro Hernández
 * @see RecipeServiceUtil
 * @see com.liferay.recipes.service.base.RecipeServiceBaseImpl
 * @see com.liferay.recipes.service.impl.RecipeServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=apio", "json.web.service.context.path=Recipe"}, service = RecipeService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface RecipeService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RecipeServiceUtil} to access the recipe remote service. Add custom service methods to {@link com.liferay.recipes.service.impl.RecipeServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	/**
	* Returns the recipe with the primary key.
	*
	* @param recipeId the primary key of the recipe
	* @return the recipe
	* @throws PortalException if a recipe with the primary key could not be
	found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Recipe getRecipe(long recipeId) throws PortalException;

	/**
	* Returns a range of all the recipes that the user has permission to view
	* where groupId = &#63;.
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
	* will include the default ORDER BY logic from {@code RecipeModelImpl}. If
	* both <code>orderByComparator</code> and pagination are absent, for
	* performance reasons, the query will not have an ORDER BY clause and the
	* returned result set will be sorted on by the primary key in an ascending
	* order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of recipes
	* @param end the upper bound of the range of recipes (not inclusive)
	* @return the range of matching recipes that the user has permission to
	view
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Recipe> getRecipesByGroupId(long groupId, int start, int end);

	/**
	* Returns the number of recipes that the user has permission to view where
	* groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching recipes that the user has permission to
	view
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getRecipesCountByGroupId(long groupId);
}