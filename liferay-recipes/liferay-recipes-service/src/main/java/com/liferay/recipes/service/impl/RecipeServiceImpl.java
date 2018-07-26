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

import static com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory.getInstance;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.recipes.model.Recipe;
import com.liferay.recipes.service.base.RecipeServiceBaseImpl;

import java.util.List;

/**
 * The implementation of the recipe remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the {@link
 * com.liferay.recipes.service.RecipeService} interface. <p> This is a remote
 * service. Methods of this service are expected to have security checks based
 * on the propagated JAAS credentials because this service can be accessed
 * remotely.
 * </p>
 *
 * @author Alejandro Hernández
 * @see    RecipeServiceBaseImpl
 * @see    com.liferay.recipes.service.RecipeServiceUtil
 */
public class RecipeServiceImpl extends RecipeServiceBaseImpl {

	/**
	 * Returns the recipe with the primary key.
	 *
	 * @param  recipeId the primary key of the recipe
	 * @return the recipe
	 * @throws PortalException if a recipe with the primary key could not be
	 *         found
	 */
	@Override
	public Recipe getRecipe(long recipeId) throws PortalException {
		_recipeModelResourcePermission.check(
			getPermissionChecker(), recipeId, "VIEW");

		return recipeLocalService.getRecipe(recipeId);
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
	 * @param  groupId the group ID
	 * @param  start the lower bound of the range of recipes
	 * @param  end the upper bound of the range of recipes (not inclusive)
	 * @return the range of matching recipes that the user has permission to
	 *         view
	 */
	@Override
	public List<Recipe> getRecipesByGroupId(long groupId, int start, int end) {
		return recipePersistence.filterFindByGroupId(groupId, start, end);
	}

	/**
	 * Returns the number of recipes that the user has permission to view where
	 * groupId = &#63;.
	 *
	 * @param  groupId the group ID
	 * @return the number of matching recipes that the user has permission to
	 *         view
	 */
	@Override
	public int getRecipesCountByGroupId(long groupId) {
		return recipePersistence.filterCountByGroupId(groupId);
	}

	private static volatile ModelResourcePermission<Recipe>
		_recipeModelResourcePermission = getInstance(
			RecipeServiceImpl.class, "_recipeModelResourcePermission",
			Recipe.class);

}