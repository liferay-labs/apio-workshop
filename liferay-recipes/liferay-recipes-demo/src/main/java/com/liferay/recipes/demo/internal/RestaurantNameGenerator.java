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

package com.liferay.recipes.demo.internal;

import com.liferay.portal.kernel.security.RandomUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Provide methods for generating random restaurant names.
 *
 * @author Alejandro Hernández
 */
public class RestaurantNameGenerator {

	/**
	 * Generates a random restaurant name.
	 *
	 * @return a random restaurant name
	 */
	public static String generate() {
		List<String> names1 = Arrays.asList(
			"Abacus", "Amber", "Aqua", "Arctic", "Aroma", "Autumn", "Baking",
			"Bamboo", "Beach", "Bengal", "Big", "Big City", "Bitter", "Boiling",
			"Bright", "Saffron", "Brimstone", "Bronze", "Caramel", "Caribbean",
			"Caviar", "Central", "Champagne", "Charming", "Chili", "Chocolate",
			"Chopping", "Cinnamon", "City", "Clear", "Coastal", "Cocoa",
			"Coffee", "Comet", "Cool", "Cool Cat", "Copper", "Coriander",
			"Corner", "Court", "Crimson", "Crystal", "Curry", "Dairy", "Dapper",
			"Delhi", "Dwarf", "Eastern", "Electric", "Emerald", "Enigma",
			"Fable", "Fantasy", "Fiery", "Fire", "First", "Forest", "French",
			"Fresh", "Friendly", "Gallery", "Glass", "Golden", "Grand", "Greek",
			"Harborview", "Harlequin", "Harmonic", "Holy", "Honey", "Honor",
			"Hot", "Hungry", "Imperial", "Incredible", "Indian", "Island",
			"Italian", "Jade", "Jamming", "Japanese", "Jazz", "Jungle",
			"Juniper", "Karma", "King's", "Lakeside", "Lavender", "Light",
			"Little", "Lunar", "Mad", "Magical", "Malt", "Mammoth", "Meadow",
			"Mellow", "Minty", "Modern", "Moroccan", "Mountain", "Mountainview",
			"Narrow", "New", "Nifty", "Noodle", "Northern", "Old", "Olive",
			"Orange", "Original", "Oval", "Painted", "Palm", "Paradise",
			"Pearl", "Pepper", "Pink", "Polar", "Private", "Proud", "Queen's",
			"Rare", "River", "Royal", "Ruby", "Sailing", "Salty", "Sapphire",
			"Savory", "Seaside", "Shining", "Silent", "Silk", "Silver", "Solar",
			"Sour", "Southern", "Spaghetti", "Spiced", "Spicy", "Spring",
			"Square", "Streetwise", "Sugar", "Summer", "Sumo", "Sushi", "Sweet",
			"Tandoori", "Thai", "Tropical", "Underwater", "Urban", "Vanilla",
			"Village", "Vintage", "Violet", "Warm", "Waterfront", "Waterway",
			"Western", "Whiskey", "Wild", "Winter");

		List<String> names2 = Arrays.asList(
			"After Dark", "Afternoon", "Ambience", "Angel", "Apple", "Archive",
			"Avenue", "Badger", "Balcony", "Barbecue", "Bass", "Bay", "Bear",
			"Beehive", "Bistro", "Bite", "Bites", "Blanket", "Blend", "Block",
			"Blossom", "Boar", "Bond", "Boulevard", "Brewery", "Brothers",
			"Canteen", "Castle", "Catch", "Chamber", "Chef", "Chicken",
			"Chimney", "Clam", "Cloud", "Clove", "Club", "Commander", "Cottage",
			"Court", "Courtyard", "Cow", "Crown", "Cuisine", "Dairy", "Deer",
			"Demon", "Devil", "Diner", "Docks", "Dome", "Door", "Dragon",
			"Dream", "Drum", "Duke", "Eats", "Elephant", "Emperor", "Empress",
			"Exchange", "Exhibit", "Fable", "Factory", "Faire", "Farmer",
			"Fence", "Fiddler", "Fish", "Flavour", "Flower", "Fox", "Fridge",
			"Front", "Fusion", "Garden", "Gate", "Genie", "Goat", "God",
			"Goddess", "Grill", "Grove", "Harvest", "Heart", "Heaven", "Hog",
			"Hook", "Horse", "Hound", "House", "Jewel", "Junction", "Kitchen",
			"Knight", "Laguna", "Lane", "Lantern", "Leaf", "Lighthouse", "Lily",
			"Lion", "Lobster", "Lounge", "Maple", "Market", "Merchant",
			"Mirror", "Mission", "Mockingbird", "Moment", "Moments", "Monkey",
			"Monument", "Morning", "Night", "Nights", "Oak", "Orchard",
			"Orchid", "Oriental", "Oyster", "Palace", "Panda", "Pantry",
			"Paradise", "Parlour", "Peasant", "Persia", "Petal", "Pig", "Pipe",
			"Pizzeria", "Place", "Pond", "Port", "Ranch", "Road", "Rooftop",
			"Room", "Rose", "Salmon", "Saloon", "Scarf", "Shack", "Shark",
			"Ship", "Shrimp", "Sisters", "Smith", "Spice", "Spices", "Star",
			"Stranger", "Street", "Street Kitchen", "Table", "Taste", "Tiger",
			"Tower", "Tree", "Trumpet", "Tulip", "Valley", "Vaults", "Victory",
			"View", "Vine", "Violin", "Walk", "Way", "Well", "Whale", "Wharf",
			"Willow", "Windmill", "Window", "Wok", "Word", "Yard");

		int random1 = RandomUtil.nextInt(names1.size());
		int random2 = RandomUtil.nextInt(names2.size());

		String restaurantName = names1.get(random1) + " " + names2.get(random2);

		if (new Random().nextBoolean()) {
			return "The " + restaurantName;
		}

		return restaurantName;
	}

}