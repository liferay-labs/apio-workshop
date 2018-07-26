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
 * Provides the remote service utility for Recipe. This utility wraps
 * {@link com.liferay.recipes.service.impl.RecipeServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Alejandro Hernández
 * @see RecipeService
 * @see com.liferay.recipes.service.base.RecipeServiceBaseImpl
 * @see com.liferay.recipes.service.impl.RecipeServiceImpl
 * @generated
 */
@ProviderType
public class RecipeServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.recipes.service.impl.RecipeServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Returns the recipe with the primary key.
	*
	* @param recipeId the primary key of the recipe
	* @return the recipe
	* @throws PortalException if a recipe with the primary key could not be
	found
	*/
	public static com.liferay.recipes.model.Recipe getRecipe(long recipeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getRecipe(recipeId);
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
	public static java.util.List<com.liferay.recipes.model.Recipe> getRecipesByGroupId(
		long groupId, int start, int end) {
		return getService().getRecipesByGroupId(groupId, start, end);
	}

	/**
	* Returns the number of recipes that the user has permission to view where
	* groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching recipes that the user has permission to
	view
	*/
	public static int getRecipesCountByGroupId(long groupId) {
		return getService().getRecipesCountByGroupId(groupId);
	}

	public static RecipeService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<RecipeService, RecipeService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(RecipeService.class);

		ServiceTracker<RecipeService, RecipeService> serviceTracker = new ServiceTracker<RecipeService, RecipeService>(bundle.getBundleContext(),
				RecipeService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}