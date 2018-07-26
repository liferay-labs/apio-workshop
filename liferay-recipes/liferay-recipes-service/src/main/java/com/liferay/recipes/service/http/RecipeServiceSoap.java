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

package com.liferay.recipes.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.liferay.recipes.service.RecipeServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link RecipeServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.recipes.model.RecipeSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.recipes.model.Recipe}, that is translated to a
 * {@link com.liferay.recipes.model.RecipeSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Alejandro Hernández
 * @see RecipeServiceHttp
 * @see com.liferay.recipes.model.RecipeSoap
 * @see RecipeServiceUtil
 * @generated
 */
@ProviderType
public class RecipeServiceSoap {
	/**
	* Returns the recipe with the primary key.
	*
	* @param recipeId the primary key of the recipe
	* @return the recipe
	* @throws PortalException if a recipe with the primary key could not be
	found
	*/
	public static com.liferay.recipes.model.RecipeSoap getRecipe(long recipeId)
		throws RemoteException {
		try {
			com.liferay.recipes.model.Recipe returnValue = RecipeServiceUtil.getRecipe(recipeId);

			return com.liferay.recipes.model.RecipeSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

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
	public static com.liferay.recipes.model.RecipeSoap[] getRecipesByGroupId(
		long groupId, int start, int end) throws RemoteException {
		try {
			java.util.List<com.liferay.recipes.model.Recipe> returnValue = RecipeServiceUtil.getRecipesByGroupId(groupId,
					start, end);

			return com.liferay.recipes.model.RecipeSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Returns the number of recipes that the user has permission to view where
	* groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching recipes that the user has permission to
	view
	*/
	public static int getRecipesCountByGroupId(long groupId)
		throws RemoteException {
		try {
			int returnValue = RecipeServiceUtil.getRecipesCountByGroupId(groupId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(RecipeServiceSoap.class);
}