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

import com.github.javafaker.Faker;

import com.liferay.portal.kernel.security.RandomUtil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Instances of this class represent the information of a new {@code Recipe}.
 *
 * @author Alejandro Hernández
 */
public class RecipeInformation {

	/**
	 * Returns a random category for a recipe.
	 *
	 * @return a random category for a recipe
	 */
	public static String randomCategory() {
		return new Faker().food().spice();
	}

	/**
	 * Returns a random ingredient list for a recipe.
	 *
	 * @return a random ingredient list for a recipe
	 */
	public static List<String> randomIngredients() {
		return randomList("Ingredient");
	}

	/**
	 * Returns a random name for a recipe.
	 *
	 * @return a random name for a recipe
	 */
	public static String randomName() {
		return new Faker().food().ingredient();
	}

	/**
	 * Returns a random recipe.
	 *
	 * @return a random recipe
	 */
	public static RecipeInformation randomRecipe() {
		return new RecipeInformation(
			randomName(), randomRegion(), randomCategory(), randomTags(), null,
			randomSteps(), randomIngredients(), null);
	}

	/**
	 * Returns a random region for a recipe.
	 *
	 * @return a random region for a recipe
	 */
	public static String randomRegion() {
		return new Faker().demographic().demonym();
	}

	/**
	 * Returns a random step list for a recipe.
	 *
	 * @return a random step list for a recipe
	 */
	public static List<String> randomSteps() {
		return randomList("Step");
	}

	/**
	 * Returns a random tag list for a recipe.
	 *
	 * @return a random tag list for a recipe
	 */
	public static List<String> randomTags() {
		return IntStream.range(
			0, RandomUtil.nextInt(3) + 1
		).mapToObj(
			__ -> new Faker().food().ingredient()
		).collect(
			Collectors.toList()
		);
	}

	public RecipeInformation(
		String name, String region, String category, List<String> tags,
		String imageURL, List<String> steps, List<String> ingredients,
		String videoURL) {

		_name = name;
		_region = region;
		_category = category;
		_tags = tags;
		_imageURL = imageURL;
		_steps = steps;
		_ingredients = ingredients;
		_videoURL = videoURL;
	}

	/**
	 * The recipe category.
	 *
	 * @return the recipe category
	 */
	public String getCategory() {
		return _category;
	}

	/**
	 * The recipe image URL.
	 *
	 * @return the recipe image URL
	 */
	public String getImageURL() {
		return _imageURL;
	}

	/**
	 * The recipe ingredients.
	 *
	 * @return the recipe ingredients
	 */
	public List<String> getIngredients() {
		return _ingredients;
	}

	/**
	 * The recipe name.
	 *
	 * @return the recipe name
	 */
	public String getName() {
		return _name;
	}

	/**
	 * The recipe region.
	 *
	 * @return the recipe region
	 */
	public String getRegion() {
		return _region;
	}

	/**
	 * The recipe steps.
	 *
	 * @return the recipe steps
	 */
	public List<String> getSteps() {
		return _steps;
	}

	/**
	 * The recipe tags.
	 *
	 * @return the recipe tags
	 */
	public List<String> getTags() {
		return _tags;
	}

	/**
	 * The recipe video URL.
	 *
	 * @return the recipe video URL
	 */
	public String getVideoURL() {
		return _videoURL;
	}

	/**
	 * Returns a random list of strings prefixed with the provided string and
	 * ending with the element index in the list.
	 *
	 * @param  string the string to use as prefix for every element
	 * @return a random list of strings
	 */
	protected static List<String> randomList(String string) {
		return IntStream.range(
			0, RandomUtil.nextInt(10)
		).mapToObj(
			integer -> string + " " + integer
		).collect(
			Collectors.toList()
		);
	}

	private String _category;
	private String _imageURL;
	private List<String> _ingredients;
	private String _name;
	private String _region;
	private List<String> _steps;
	private List<String> _tags;
	private String _videoURL;

}