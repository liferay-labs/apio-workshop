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

import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;

import java.util.Optional;

/**
 * Instances of this class model the union of a restaurant and its chef.
 *
 * @author Alejandro Hernández
 */
public class RestaurantInformation {

	public RestaurantInformation(Organization organization, User chef) {
		_organization = organization;
		_chef = chef;
	}

	/**
	 * Returns the restaurant chef's email address or the string {@code "No
	 * chef"} if the restaurant doesn't have an assigned chef.
	 *
	 * @return the restaurant chef's email address, if the restaurant has an
	 *         assigned chef; {@code "No chef"} otherwise
	 */
	public String getChefEmailAddress() {
		return Optional.ofNullable(
			_chef
		).map(
			User::getEmailAddress
		).orElse(
			"No chef"
		);
	}

	/**
	 * Returns the restaurant's ID.
	 *
	 * @return the restaurant's ID
	 */
	public String getRestaurantId() {
		return String.valueOf(_organization.getOrganizationId());
	}

	/**
	 * Returns the restaurant's name.
	 *
	 * @return the restaurant's name
	 */
	public String getRestaurantName() {
		return _organization.getName();
	}

	private final User _chef;
	private final Organization _organization;

}