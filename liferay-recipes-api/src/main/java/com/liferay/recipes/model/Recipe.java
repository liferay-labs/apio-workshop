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

package com.liferay.recipes.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Recipe service. Represents a row in the &quot;APIO_Recipe&quot; database table, with each column mapped to a property of this class.
 *
 * @author Alejandro Hernández
 * @see RecipeModel
 * @see com.liferay.recipes.model.impl.RecipeImpl
 * @see com.liferay.recipes.model.impl.RecipeModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.recipes.model.impl.RecipeImpl")
@ProviderType
public interface Recipe extends RecipeModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.recipes.model.impl.RecipeImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Recipe, Long> RECIPE_ID_ACCESSOR = new Accessor<Recipe, Long>() {
			@Override
			public Long get(Recipe recipe) {
				return recipe.getRecipeId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Recipe> getTypeClass() {
				return Recipe.class;
			}
		};

	/**
	* Returns the list of the recipe's ingredients.
	*
	* @return the list of the recipe's ingredients
	*/
	public java.util.List<String> getIngredients();

	/**
	* Returns the list of the recipe's steps.
	*
	* @return the list of the recipe's steps
	*/
	public java.util.List<String> getSteps();
}