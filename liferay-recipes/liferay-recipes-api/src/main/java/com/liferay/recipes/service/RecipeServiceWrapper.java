/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 * <p>
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * <p>
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.recipes.service;

import aQute.bnd.annotation.ProviderType;
import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link RecipeService}.
 *
 * @author Alejandro Hernández
 * @see RecipeService
 * @generated
 */
@ProviderType
public class RecipeServiceWrapper implements RecipeService,
    ServiceWrapper<RecipeService> {

    public RecipeServiceWrapper(RecipeService recipeService) {
        _recipeService = recipeService;
    }

    /**
     * Adds a new Recipe to the database.
     *
     * @param name             the recipe's name
     * @param region           the recipe's region
     * @param imageFileEntryId the recipe's image fileEntryId (can be {@code
    null}
     * @param publishedDate    the published date
     * @param hoursToMake      the number of hours needed to make the recipe
     * @param minutesToMake    the number of minutes needed to make the recipe
     * @param steps            the recipe's list of steps
     * @param ingredients      the recipe's list of ingredients
     * @param videoURL         the recipe's video URL
     * @param serviceContext   the service context
     * @return the new recipe
     */
    @Override
    public com.liferay.recipes.model.Recipe addRecipe(String name,
                                                      String region, Long imageFileEntryId, java.util.Date publishedDate,
                                                      int hoursToMake, int minutesToMake, java.util.List<String> steps,
                                                      java.util.List<String> ingredients, String videoURL,
                                                      com.liferay.portal.kernel.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException {
        return _recipeService.addRecipe(name, region, imageFileEntryId,
            publishedDate, hoursToMake, minutesToMake, steps, ingredients,
            videoURL, serviceContext);
    }

    /**
     * Deletes the recipe with the primary key from the database. Also notifies
     * the appropriate model listeners.
     *
     * @param recipeId the primary key of the recipe
     * @return the recipe that was removed
     */
    @Override
    public com.liferay.recipes.model.Recipe deleteRecipe(long recipeId)
        throws com.liferay.portal.kernel.exception.PortalException {
        return _recipeService.deleteRecipe(recipeId);
    }

    /**
     * Returns the OSGi service identifier.
     *
     * @return the OSGi service identifier
     */
    @Override
    public String getOSGiServiceIdentifier() {
        return _recipeService.getOSGiServiceIdentifier();
    }

    /**
     * Returns the recipe with the primary key.
     *
     * @param recipeId the primary key of the recipe
     * @return the recipe
     * @throws PortalException if a recipe with the primary key could not be
    found
     */
    @Override
    public com.liferay.recipes.model.Recipe getRecipe(long recipeId)
        throws com.liferay.portal.kernel.exception.PortalException {
        return _recipeService.getRecipe(recipeId);
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
     * @param start   the lower bound of the range of recipes
     * @param end     the upper bound of the range of recipes (not inclusive)
     * @return the range of matching recipes that the user has permission to
    view
     */
    @Override
    public java.util.List<com.liferay.recipes.model.Recipe> getRecipesByGroupId(
        long groupId, int start, int end) {
        return _recipeService.getRecipesByGroupId(groupId, start, end);
    }

    /**
     * Returns the number of recipes that the user has permission to view where
     * groupId = &#63;.
     *
     * @param groupId the group ID
     * @return the number of matching recipes that the user has permission to
    view
     */
    @Override
    public int getRecipesCountByGroupId(long groupId) {
        return _recipeService.getRecipesCountByGroupId(groupId);
    }

    @Override
    public RecipeService getWrappedService() {
        return _recipeService;
    }

    @Override
    public void setWrappedService(RecipeService recipeService) {
        _recipeService = recipeService;
    }

    private RecipeService _recipeService;

}