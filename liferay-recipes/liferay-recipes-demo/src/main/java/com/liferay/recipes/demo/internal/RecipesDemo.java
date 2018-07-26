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

import com.github.javafaker.Faker;

import com.liferay.apio.architect.functional.Try;
import com.liferay.organizations.demo.data.creator.OrganizationDemoDataCreator;
import com.liferay.portal.instance.lifecycle.BasePortalInstanceLifecycleListener;
import com.liferay.portal.instance.lifecycle.PortalInstanceLifecycleListener;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.RandomUtil;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.recipes.demo.data.creator.RecipeDemoDataCreator;
import com.liferay.roles.admin.demo.data.creator.RoleDemoDataCreator;
import com.liferay.users.admin.demo.data.creator.SiteMemberUserDemoDataCreator;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Creates demo data for trying the recipes services.
 *
 * @author Alejandro Hernández
 */
@Component(
	immediate = true,
	property = {
		"liferay.recipes.max.assistants=25", "liferay.recipes.max.recipes=25",
		"liferay.recipes.max.restaurants=20",
		"liferay.recipes.max.restaurants.per.assistant=4"
	},
	service = PortalInstanceLifecycleListener.class
)
public class RecipesDemo extends BasePortalInstanceLifecycleListener {

	@Deactivate
	public void deactivate() throws PortalException {
		_recipeDemoDataCreator.delete();
		_siteMemberUserDemoDataCreator.delete();
		_siteRoleDemoDataCreator.delete();
		_organizationRoleDemoDataCreator.delete();
		_organizationDemoDataCreator.delete();
	}

	@Override
	public void portalInstanceRegistered(Company company) throws Exception {
		User user = _userLocalService.getUserByEmailAddress(
			company.getCompanyId(), "test@liferay.com");

		List<Organization> organizations = createRestaurants(user);

		_log.info("Restaurants created");

		Role memberRole = createMemberRole(company);

		Map<Long, Organization> chefs = addChefs(organizations, memberRole);

		_log.info("Chefs created");

		addKitchenAssistants(organizations, memberRole);

		_log.info("Kitchen Assistants created");

		createRecipes(chefs);

		_log.info("Recipes created");

		_log.info(
			"All data has been created. You can use the commands " +
				"`apio:restaurants` and `apio:assistants` to retrieve the " +
					"list of restaurants and assistants respectively.");
	}

	/**
	 * Creates a role using the xml with the allowed actions for it.
	 *
	 * @param  companyId the company ID
	 * @param  fileNameSuffix the XML permission filename suffix
	 * @param  roleDemoDataCreator the {@link RoleDemoDataCreator} used to
	 *         create the role
	 * @param  roleName the name of the created role
	 * @return the created role
	 */
	protected static Role createRole(
			long companyId, String fileNameSuffix,
			RoleDemoDataCreator roleDemoDataCreator, String roleName)
		throws PortalException {

		String permissionsXML = StringUtil.read(
			RecipesDemo.class,
			"/dependencies/permissions-" + fileNameSuffix + ".xml");

		return roleDemoDataCreator.create(companyId, roleName, permissionsXML);
	}

	/**
	 * Returns an integer value from the component's properties.
	 *
	 * @param  properties the component's properties
	 * @param  type the type of property to obtain
	 * @param  defaultValue the default value in case the property cannot be
	 *         found
	 * @return an integer value from the component's properties
	 */
	protected static Integer getProperty(
		Map<String, Object> properties, String type, int defaultValue) {

		return Try.fromFallible(
			() -> properties.get("liferay.recipes.max." + type)
		).map(
			String.class::cast
		).map(
			Integer::parseInt
		).orElse(
			defaultValue
		);
	}

	/**
	 * Returns a restaurant image URL for the current index.
	 *
	 * @param index the restaurant index
	 * @return a restaurant image URL
	 */
	protected static String getRestaurantURL(int index) {
		if ((index & 1) == 0) {
			return UNSPLASH_RESTAURANT_URL;
		}

		return UNSPLASH_FOOD_URL;
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_maxAssistants = getProperty(properties, "assistants", 25);
		_maxRecipes = getProperty(properties, "recipes", 25);
		_maxRestaurants = getProperty(properties, "restaurants", 20);
		_maxRestaurantsPerAssistant = getProperty(
			properties, "restaurants.per.assistant", 4);
	}

	/**
	 * Adds a chef to each of the provided restaurants and returns them along
	 * with their new chef ID.
	 *
	 * @param  organizations the restaurants
	 * @param  memberRole the organization member role
	 * @return a map containing chef IDs as keys and organizations as values
	 */
	protected Map<Long, Organization> addChefs(
			List<Organization> organizations, Role memberRole)
		throws PortalException {

		Role chefRole = createChefRole(memberRole.getCompanyId());

		return Stream.of(
			organizations
		).flatMap(
			List::stream
		).collect(
			Collectors.toMap(
				addMember(
					"Chef", null, chefRole.getRoleId(), memberRole.getRoleId()),
				Function.identity())
		);
	}

	/**
	 * Adds a handful of kitchen assistants to each of the provided restaurants.
	 *
	 * @param organizations the restaurants
	 * @param memberRole the organization member role
	 */
	protected void addKitchenAssistants(
			List<Organization> organizations, Role memberRole)
		throws PortalException {

		Role kitchenAssistantRole = createKitchenAssistantRole(
			memberRole.getCompanyId());

		IntStream.range(
			0, _maxAssistants
		).mapToObj(
			__ -> new Faker().internet().emailAddress()
		).forEach(
			email -> IntStream.range(
				0, RandomUtil.nextInt(_maxRestaurantsPerAssistant) + 1
			).map(
				__ -> RandomUtil.nextInt(organizations.size())
			).mapToObj(
				organizations::get
			).distinct(
			).forEach(
				addMember(
					"Kitchen Assistant", email, memberRole.getRoleId(),
					kitchenAssistantRole.getRoleId())::apply
			)
		);
	}

	/**
	 * Returns a function that receives an organization and returns the ID of a
	 * new user added to it.
	 *
	 * @param  jobTitle the job title for the new member
	 * @param  emailAddress the email address for the new member (can be {@code
	 *         null})
	 * @param  roles the list of roles to apply to the new user
	 * @return a function that receives an organization and returns the ID of a
	 *         new user added to it
	 */
	protected Function<Organization, Long> addMember(
		String jobTitle, String emailAddress, long... roles) {

		return organization -> Try.fromFallible(
			() -> _siteMemberUserDemoDataCreator.create(
				organization.getGroupId(),
				emailAddress == null ?
					new Faker().internet().emailAddress() : emailAddress)
		).map(
			user -> _userLocalService.updateJobTitle(user.getUserId(), jobTitle)
		).map(
			user -> _userLocalService.updateAgreedToTermsOfUse(
				user.getUserId(), true)
		).map(
			user -> _userLocalService.updateReminderQuery(
				user.getUserId(), "A", "B")
		).map(
			user -> _userLocalService.updatePassword(
				user.getUserId(), "apio", "apio", false, true)
		).map(
			user -> {
				_userGroupRoleLocalService.addUserGroupRoles(
					user.getUserId(), organization.getGroupId(), roles);

				return user;
			}
		).map(
			user -> {
				_organizationLocalService.addUserOrganization(
					user.getUserId(), organization);

				return user;
			}
		).map(
			User::getUserId
		).orElse(
			null
		);
	}

	/**
	 * Creates a role for Chefs using the xml with the allowed actions for it.
	 *
	 * @param  companyId the company ID
	 * @return the chef role
	 */
	protected Role createChefRole(long companyId) throws PortalException {
		return createRole(companyId, "chef", _siteRoleDemoDataCreator, "Chef");
	}

	/**
	 * Creates a role for Kitchen Assistants using the xml with the allowed
	 * actions for it.
	 *
	 * @param  companyId the company ID
	 * @return the kitchen assistant role
	 */
	protected Role createKitchenAssistantRole(long companyId)
		throws PortalException {

		return createRole(
			companyId, "kitchen-assistant", _siteRoleDemoDataCreator,
			"Kitchen Assistant");
	}

	/**
	 * Creates a role for Organization Members using the xml with the allowed
	 * actions for it.
	 *
	 * @param  company the company
	 * @return the organization member role
	 */
	protected Role createMemberRole(Company company) throws PortalException {
		return createRole(
			company.getCompanyId(), "member", _organizationRoleDemoDataCreator,
			"Member");
	}

	/**
	 * Creates a handful of recipes in the groups of the provided organizations
	 * using its chefs as creators.
	 *
	 * @param chefs the map containing the organizations and its chefs.
	 */
	protected void createRecipes(Map<Long, Organization> chefs) {
		chefs.forEach(
			(chefId, organization) -> IntStream.range(
				0, RandomUtil.nextInt(_maxRecipes) + 1
			).forEach(
				__ -> _recipeDemoDataCreator.create(
					chefId, organization.getGroupId())
			)
		);
	}

	/**
	 * Creates a handful of restaurants and stores them in the database.
	 *
	 * @param  user the user that create the restaurants
	 * @return the list of created restaurants
	 */
	protected List<Organization> createRestaurants(User user) {
		return IntStream.range(
			0, _maxRestaurants
		).mapToObj(
			index -> new Tuple(RestaurantNameGenerator.generate(), index)
		).map(
			tuple -> _organizationDemoDataCreator.create(
				user.getUserId(), (String)tuple.getObject(0),
				getRestaurantURL((int)tuple.getObject(1)))
		).filter(
			Validator::isNotNull
		).collect(
			Collectors.toList()
		);
	}

	protected static final String UNSPLASH_FOOD_URL =
		"https://source.unsplash.com/600x600/?food";

	protected static final String UNSPLASH_RESTAURANT_URL =
		"https://source.unsplash.com/600x600/?restaurant";

	private Log _log = LogFactoryUtil.getLog(getClass());
	private Integer _maxAssistants;
	private Integer _maxRecipes;
	private Integer _maxRestaurants;
	private Integer _maxRestaurantsPerAssistant;

	@Reference
	private OrganizationDemoDataCreator _organizationDemoDataCreator;

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference(target = "(role.type=organization)")
	private RoleDemoDataCreator _organizationRoleDemoDataCreator;

	@Reference
	private RecipeDemoDataCreator _recipeDemoDataCreator;

	@Reference
	private SiteMemberUserDemoDataCreator _siteMemberUserDemoDataCreator;

	@Reference(target = "(role.type=site)")
	private RoleDemoDataCreator _siteRoleDemoDataCreator;

	@Reference
	private UserGroupRoleLocalService _userGroupRoleLocalService;

	@Reference
	private UserLocalService _userLocalService;

}