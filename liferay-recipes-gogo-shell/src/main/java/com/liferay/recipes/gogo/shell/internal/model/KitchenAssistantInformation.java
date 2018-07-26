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

package com.liferay.recipes.gogo.shell.internal.model;

import com.liferay.portal.kernel.model.User;

/**
 * Instances of this class model the union of a restaurant and its chef.
 *
 * @author Alejandro Hernández
 */
public class KitchenAssistantInformation {

	public KitchenAssistantInformation(
		User kitchenAssistantUser, String restaurants) {

		_kitchenAssistantUser = kitchenAssistantUser;
		_restaurants = restaurants;
	}

	/**
	 * Returns the kitchen assistant's email address.
	 *
	 * @return the kitchen assistant's email address
	 */
	public String getAssistantEmailAddress() {
		return _kitchenAssistantUser.getEmailAddress();
	}

	/**
	 * Returns the kitchen assistant's restaurants.
	 *
	 * @return the kitchen assistant's restaurants
	 */
	public String getRestaurants() {
		return _restaurants;
	}

	private final User _kitchenAssistantUser;
	private final String _restaurants;

}