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

package apio.architect.workshop.resource;

import apio.architect.workshop.model.RecipeDTO;
import apio.architect.workshop.type.OrganizationType;
import apio.architect.workshop.type.RecipeType;
import com.liferay.apio.architect.annotation.Actions.Retrieve;
import com.liferay.apio.architect.annotation.Id;
import com.liferay.apio.architect.annotation.ParentId;
import com.liferay.apio.architect.pagination.PageItems;
import com.liferay.apio.architect.pagination.Pagination;
import com.liferay.apio.architect.router.ActionRouter;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.recipes.exception.NoSuchRecipeException;
import com.liferay.recipes.model.Recipe;
import com.liferay.recipes.service.RecipeService;
import com.liferay.recipes.workshop.helper.WorkshopHelper;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alejandro Hernández
 * @author Victor Galán
 */
@Component(immediate = true, property = "osgi.jaxrs.resource=true", service = {Object.class, ActionRouter.class})
public class RecipeResource implements ActionRouter<RecipeType> {

    @Retrieve
    public PageItems<RecipeType> retrieveRecipes(
        @ParentId(OrganizationType.class) long organizationId, User user, Pagination pagination) throws PortalException {

        Group organizationGroup = _groupService.getOrganizationGroup(user.getCompanyId(), organizationId);

        List<RecipeType> recipes = _recipeService.getRecipesByGroupId(organizationGroup.getGroupId(), -1, -1)
            .stream()
            .map(recipe -> new RecipeDTO(recipe, _workshopHelper))
            .collect(Collectors.toList());

        return new PageItems<>(
            recipes.subList(pagination.getStartPosition(), pagination.getEndPosition()), recipes.size());
    }

    @Retrieve
    public RecipeType retrieve(@Id long recipeId) throws PortalException {
        Recipe recipe = _recipeService.getRecipe(recipeId);

        return new RecipeDTO(recipe, _workshopHelper);
    }

    @DELETE
    @Path("recipes/{id}")
    public void delete(@PathParam("id") long recipeId) throws PortalException {
        try {
            _recipeService.deleteRecipe(recipeId);
        } catch (NoSuchRecipeException ignore) {

        }
    }

    @Reference
    private GroupLocalService _groupService;

    @Reference
    private RecipeService _recipeService;

    @Reference
    private WorkshopHelper _workshopHelper;

}