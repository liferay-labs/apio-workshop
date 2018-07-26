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
import static com.liferay.recipes.gogo.shell.internal.util.CommandsUtil.printAssistantInformation;
import static com.liferay.recipes.gogo.shell.internal.util.CommandsUtil.printTwoDashes;

import com.liferay.apio.architect.functional.Try;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.recipes.gogo.shell.internal.model.KitchenAssistantInformation;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Exposes a command for obtaining the list of kitchen assistants and the list
 * of their assigned restaurants.
 *
 * @author Alejandro Hernández
 */
@Component(
	property = {"osgi.command.function=assistants", "osgi.command.scope=apio"},
	service = Object.class
)
@SuppressWarnings("unused")
public class AssistantsCommand {

	/**
	 * Outputs the list of available kitchen assistants with the following
	 * information: assistant's email address and assistant's restaurants.
	 */
	public void assistants() {
		print("Kitchen Assistant Email", "Organizations");
		printTwoDashes();

		Role kitchenAssistantRole = Try.fromFallible(
			() -> _userLocalService.getUsers(0, 1)
		).map(
			list -> list.get(0)
		).map(
			User::getCompanyId
		).map(
			companyId -> _roleLocalService.getRole(
				companyId, "Kitchen Assistant")
		).orElse(
			null
		);

		if (kitchenAssistantRole == null) {
			System.out.println("Unable to find Kitchen Assistant role");

			return;
		}

		Stream.of(
			_userLocalService.getUsers(-1, -1)
		).flatMap(
			List::stream
		).map(
			getAssistantInformation(kitchenAssistantRole)
		).filter(
			Validator::isNotNull
		).forEach(
			printAssistantInformation().andThen(__ -> printTwoDashes())
		);
	}

	/**
	 * Returns a function that gets the restaurants where the user is a kitchen
	 * assistant (if any) and returns them along with the user. Returns {@code
	 * null} if the user isn't a kitchen assistant.
	 *
	 * @param  assistantRole the kitchen assistant role
	 * @return a function that gets the restaurants where the user is a kitchen
	 *         assistant and returns them along with the user, if it is a
	 *         kitchen assistant; returns {@code null} otherwise
	 */
	protected Function<User, KitchenAssistantInformation>
		getAssistantInformation(Role assistantRole) {

		return user -> {
			String restaurants = getRestaurants(user, assistantRole);

			if (Validator.isNull(restaurants)) {
				return null;
			}

			return new KitchenAssistantInformation(user, restaurants);
		};
	}

	/**
	 * Returns the list of restaurants (as a comma separated string) where the
	 * user is a kitchen assistant. Returns empty string if the user isn't a
	 * kitchen assistant.
	 *
	 * @param  user the user
	 * @param  assistantRole the kitchen assistant role
	 * @return the list of restaurants where the user is a kitchen assistant;
	 *         empty string if the user isn't a kitchen assistant
	 */
	protected String getRestaurants(User user, Role assistantRole) {
		return Stream.of(
			_organizationLocalService.getUserOrganizations(user.getUserId())
		).flatMap(
			List::stream
		).filter(
			organization -> _userGroupRoleLocalService.hasUserGroupRole(
				user.getUserId(), organization.getGroupId(),
				assistantRole.getRoleId())
		).map(
			Organization::getName
		).collect(
			Collectors.joining(", ")
		);
	}

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private UserGroupRoleLocalService _userGroupRoleLocalService;

	@Reference
	private UserLocalService _userLocalService;

}