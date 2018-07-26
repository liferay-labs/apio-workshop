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

package com.liferay.recipes.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.apio.architect.functional.Try;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The extended model implementation for the Recipe service. Represents a row in
 * the &quot;APIO_Recipe&quot; database table, with each column mapped to a
 * property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class.
 * Whenever methods are added, rerun ServiceBuilder to copy their definitions
 * into the {@link com.liferay.recipes.model.Recipe} interface.
 * </p>
 *
 * @author Alejandro Hernández
 */
@ProviderType
public class RecipeImpl extends RecipeBaseImpl {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a recipe
	 * model instance should use the {@link com.liferay.recipes.model.Recipe}
	 * interface instead.
	 */
	public RecipeImpl() {
	}

	/**
	 * Returns the list of the recipe's ingredients.
	 *
	 * @return the list of the recipe's ingredients
	 */
	@Override
	public List<String> getIngredients() {
		return Try.success(
			getIngredientsString()
		).filter(
			Validator::isNotNull
		).map(
			JSONFactoryUtil::createJSONArray
		).map(
			jsonArray -> IntStream.range(
				0, jsonArray.length()
			).mapToObj(
				jsonArray::getString
			).collect(
				Collectors.toList()
			)
		).orElseGet(
			Collections::emptyList
		);
	}

	/**
	 * Returns the list of the recipe's steps.
	 *
	 * @return the list of the recipe's steps
	 */
	@Override
	public List<String> getSteps() {
		return Try.success(
			getStepsString()
		).filter(
			Validator::isNotNull
		).map(
			JSONFactoryUtil::createJSONArray
		).map(
			jsonArray -> IntStream.range(
				0, jsonArray.length()
			).mapToObj(
				jsonArray::getString
			).collect(
				Collectors.toList()
			)
		).orElseGet(
			Collections::emptyList
		);
	}

}