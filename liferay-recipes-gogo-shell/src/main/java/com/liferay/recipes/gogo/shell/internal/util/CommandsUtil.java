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

package com.liferay.recipes.gogo.shell.internal.util;

import com.liferay.recipes.gogo.shell.internal.model.KitchenAssistantInformation;
import com.liferay.recipes.gogo.shell.internal.model.RestaurantInformation;

import java.util.Collections;
import java.util.function.Consumer;

/**
 * Contains utility methods for command classes.
 *
 * @author Alejandro Hernández
 */
public class CommandsUtil {

	/**
	 * Prints the provided strings with a fixed column size.
	 *
	 * @param string1 the first string to print
	 * @param string2 the second string to print
	 */
	public static void print(String string1, String string2) {
		System.out.format("%30s %100s\n", string1, string2);
	}

	/**
	 * Prints the provided strings with a fixed column size.
	 *
	 * @param string1 the first string to print
	 * @param string2 the second string to print
	 * @param string3 the thirds string to print
	 */
	public static void print(String string1, String string2, String string3) {
		System.out.format("%7s %25s %35s\n", string1, string2, string3);
	}

	/**
	 * Returns a consumer that prints the assistant information.
	 *
	 * @return a consumer that prints the assistant information
	 */
	public static Consumer<KitchenAssistantInformation>
		printAssistantInformation() {

		return kitchenAssistantInformation -> print(
			kitchenAssistantInformation.getAssistantEmailAddress(),
			kitchenAssistantInformation.getRestaurants());
	}

	/**
	 * Returns a consumer that prints the restaurant information.
	 *
	 * @return a consumer that prints the restaurant information
	 */
	public static Consumer<RestaurantInformation> printRestaurantInformation() {
		return restaurantInformation -> print(
			restaurantInformation.getRestaurantId(),
			restaurantInformation.getRestaurantName(),
			restaurantInformation.getChefEmailAddress());
	}

	/**
	 * Prints dashes for separating information rows.
	 */
	public static void printThreeDashes() {
		print(repeat("-", 7), repeat("-", 25), repeat("-", 35));
	}

	/**
	 * Prints dashes for separating information rows.
	 */
	public static void printTwoDashes() {
		print(repeat("-", 30), repeat("-", 100));
	}

	/**
	 * Repeats the provided string n-times.
	 *
	 * @param  string the string to repeat
	 * @param  times the number of times to repeat the string
	 * @return the provided string repeated n-times
	 */
	public static String repeat(String string, int times) {
		return String.join("", Collections.nCopies(times, string));
	}

}