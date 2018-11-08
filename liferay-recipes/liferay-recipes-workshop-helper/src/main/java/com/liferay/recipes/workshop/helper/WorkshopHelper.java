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

package com.liferay.recipes.workshop.helper;

import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.recipes.model.Recipe;

import java.util.List;

/**
 * Provides utility methods to be used during the Apio Architect Workshop.
 *
 * @author Alejandro Hernández
 */
@SuppressWarnings("UnnecessaryInterfaceModifier")
public interface WorkshopHelper {

    /**
     * Returns the URL of an organization's logo.
     */
    public String getLogoURL(Organization organization);

    /**
     * Returns the service context for creating a recipe.
     */
    public ServiceContext getRecipeServiceContext(long userId, long groupId, String category, List<String> assetTags);

    /**
     * Creates a file entry representing a recipe's image.
     */
    public FileEntry createRecipeImage(long userId, long groupId, String imageURL);

    /**
     * Returns the ID of an organization's chef.
     */
    public Long getChefId(Organization organization);

    /**
     * Returns the URL of an user's portrait.
     */
    public String getPortraitURL(User user);

    /**
     * Returns a recipe's cook time in 8601 ISO format.
     */
    public String getCookTime(Recipe recipe);

    /**
     * Returns the list of asset tags of the provided recipe.
     */
    public List<String> getRecipeAssetTags(Recipe recipe);

    /**
     * Returns the recipe's category name.
     */
    public String getCategory(Recipe recipe);

    /**
     * Returns the URL of a recipe's image.
     */
    public String getImageURL(Recipe recipe);

}