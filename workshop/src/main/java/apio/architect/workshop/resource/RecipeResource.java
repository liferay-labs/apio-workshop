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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.recipes.exception.NoSuchRecipeException;
import com.liferay.recipes.model.Recipe;
import com.liferay.recipes.service.RecipeService;
import com.liferay.recipes.workshop.helper.WorkshopHelper;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * @author Alejandro Hernández
 * @author Victor Galán
 */
@Component(immediate = true, property = "osgi.jaxrs.resource=true", service = Object.class)
public class RecipeResource {

    @GET
    @Path("recipes/{id}")
    @Produces(APPLICATION_JSON)
    public RecipeDTO retrieve(@PathParam("id") long recipeId) throws PortalException {
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
    private RecipeService _recipeService;

    @Reference
    private WorkshopHelper _workshopHelper;

}