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

package com.liferay.recipes.demo.data.creator.internal;

import com.liferay.apio.architect.functional.Try;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.net.URL;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Utility class that contains method to create {@link RecipeInformation} with
 * information obtained from <a href="https://www.themealdb.com">The MealDB</a>.
 *
 * @author Alejandro Hernández
 */
public class MealDBJSONRecipeProvider {

	/**
	 * Creates a {@link RecipeInformation} instance with information obtained
	 * from <a href="https://www.themealdb.com">The MealDB</a> or returns a
	 * random one created with {@link RecipeInformation#randomRecipe()} if
	 * something went wrong.
	 *
	 * @return the {@code RecipeInformation}
	 */
	public static RecipeInformation createMealDBRecipeInformation() {
		return Try.fromFallibleWithResources(
			() -> new URL(MEAL_DB_RANDOM_RECIPE_URL).openStream(),
			StringUtil::read
		).map(
			JSONFactoryUtil::createJSONObject
		).map(
			jsonObject -> jsonObject.getJSONArray("meals")
		).filter(
			jsonArray -> jsonArray.length() == 1
		).map(
			jsonArray -> jsonArray.getJSONObject(0)
		).map(
			MealDBJSONRecipeProvider::parseJsonObject
		).orElseGet(
			RecipeInformation::randomRecipe
		);
	}

	/**
	 * Parses de provided {@code JSONObject} into a {@link RecipeInformation}.
	 * If any of the json fields aren't found, the {@code Recipe#default*()}
	 * methods are going to be used to populate those fields.
	 *
	 * @param  jsonObject the JSON object with the server's response
	 * @return the recipe information
	 */
	protected static RecipeInformation parseJsonObject(JSONObject jsonObject) {
		String name = Try.fromFallible(
			() -> jsonObject.getString("strMeal")
		).orElseGet(
			RecipeInformation::randomName
		);

		String region = Try.fromFallible(
			() -> jsonObject.getString("strArea")
		).orElseGet(
			RecipeInformation::randomRegion
		);

		String category = Try.fromFallible(
			() -> jsonObject.getString("strCategory")
		).orElseGet(
			RecipeInformation::randomCategory
		);

		List<String> tags = Try.fromFallible(
			() -> jsonObject.getString("strTags")
		).map(
			string -> string.split(",")
		).map(
			Arrays::asList
		).orElseGet(
			RecipeInformation::randomTags
		);

		String imageURL = Try.fromFallible(
			() -> jsonObject.getString("strMealThumb")
		).orElse(
			null
		);

		String videoURL = Try.fromFallible(
			() -> jsonObject.getString("strYoutube")
		).orElse(
			null
		);

		List<String> steps = Try.fromFallible(
			() -> jsonObject.getString("strInstructions")
		).map(
			string -> string.split("\r\n")
		).filter(
			Validator::isNotNull
		).map(
			Arrays::asList
		).orElseGet(
			RecipeInformation::randomSteps
		);

		List<String> ingredients = Try.fromFallible(
			() -> IntStream.range(
				1, 21
			).mapToObj(
				processIngredient(jsonObject)
			).filter(
				Validator::isNotNull
			).collect(
				Collectors.toList()
			)
		).orElseGet(
			RecipeInformation::randomIngredients
		);

		return new RecipeInformation(
			name, region, category, tags, imageURL, steps, ingredients,
			videoURL);
	}

	/**
	 * Returns a function that receives an index and returns the definitive
	 * information of a recipe's ingredient if valid values are found. Returns
	 * {@code null} otherwise.
	 *
	 * @param  jsonObject the JSON object with the server's response
	 * @return a function that transform an index into a recipe's ingredient, if
	 *         valid values are found; {@code null} otherwise
	 */
	protected static IntFunction<String> processIngredient(
		JSONObject jsonObject) {

		return index -> {
			String measure = jsonObject.getString("strMeasure" + index);

			String ingredient = jsonObject.getString("strIngredient" + index);

			if (Validator.isNotNull(measure) &&
				Validator.isNotNull(ingredient)) {

				return measure + " " + ingredient;
			}

			return null;
		};
	}

	protected static final String MEAL_DB_RANDOM_RECIPE_URL =
		"https://www.themealdb.com/api/json/v1/1/random.php";

}