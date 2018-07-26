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

package com.liferay.recipes.gogo.shell.internal.command;

import static com.liferay.recipes.gogo.shell.internal.util.CommandsUtil.print;
import static com.liferay.recipes.gogo.shell.internal.util.CommandsUtil.printRestaurantInformation;
import static com.liferay.recipes.gogo.shell.internal.util.CommandsUtil.printThreeDashes;

import com.liferay.apio.architect.functional.Try;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.recipes.gogo.shell.internal.model.RestaurantInformation;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Exposes a command for obtaining the list of restaurants and the email
 * addresses of their chefs.
 *
 * @author Alejandro Hernández
 */
@Component(
	property = {"osgi.command.function=restaurants", "osgi.command.scope=apio"},
	service = Object.class
)
@SuppressWarnings("unused")
public class RestaurantsCommand {

	/**
	 * Outputs the list of available restaurants with the following information:
	 * restaurant's ID, restaurant's name and restaurant chef's name
	 */
	public void restaurants() {
		print("ID", "Name", "Chef Email");
		printThreeDashes();

		Stream.of(
			_organizationLocalService.getOrganizations(-1, -1)
		).flatMap(
			List::stream
		).map(
			this::getRestaurantInformation
		).forEach(
			printRestaurantInformation().andThen(__ -> printThreeDashes())
		);
	}

	/**
	 * Gets the restaurant's chef (if any) and returns it along with the
	 * restaurant.
	 *
	 * @param  organization the restaurant
	 * @return the restaurant along with its chef
	 */
	protected RestaurantInformation getRestaurantInformation(
		Organization organization) {

		User chef = Stream.of(
			_userLocalService.getOrganizationUsers(
				organization.getOrganizationId())
		).flatMap(
			List::stream
		).filter(
			isChef(organization.getGroupId())
		).findFirst(
		).orElse(
			null
		);

		return new RestaurantInformation(organization, chef);
	}

	/**
	 * Returns a predicate that returns {@code true} if the evaluated {@link
	 * User} is a chef in the provided group.
	 *
	 * @param  groupId the ID of the group
	 * @return a predicate that returns {@code true} if the user is a chef in
	 *         the group; {@code false} otherwise
	 */
	protected Predicate<User> isChef(long groupId) {
		return user -> Try.fromFallible(
			() -> _userGroupRoleLocalService.hasUserGroupRole(
				user.getUserId(), groupId, "Chef")
		).orElse(
			false
		);
	}

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference
	private UserGroupRoleLocalService _userGroupRoleLocalService;

	@Reference
	private UserLocalService _userLocalService;

}