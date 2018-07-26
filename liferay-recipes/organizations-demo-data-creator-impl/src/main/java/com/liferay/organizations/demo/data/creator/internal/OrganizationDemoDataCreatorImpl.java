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

package com.liferay.organizations.demo.data.creator.internal;

import static com.liferay.portal.kernel.model.OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;

import com.liferay.apio.architect.functional.Try;
import com.liferay.organizations.demo.data.creator.OrganizationDemoDataCreator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.ListTypeConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Region;
import com.liferay.portal.kernel.security.RandomUtil;
import com.liferay.portal.kernel.service.AddressLocalService;
import com.liferay.portal.kernel.service.CountryService;
import com.liferay.portal.kernel.service.ListTypeLocalService;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.RegionService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.TransactionConfig;
import com.liferay.portal.kernel.transaction.TransactionInvokerUtil;
import com.liferay.portal.kernel.util.FileUtil;

import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alejandro Hernández
 */
@Component
public class OrganizationDemoDataCreatorImpl
	implements OrganizationDemoDataCreator {

	@Override
	public Organization create(long userId, String name, String imageURL) {
		try {
			return TransactionInvokerUtil.invoke(
				_transactionConfig,
				() -> createOrganization(userId, name, imageURL));
		}
		catch (Throwable throwable) {
			_log.error(
				"There was an error creating the Organization", throwable);

			return null;
		}
	}

	@Override
	public void delete() {
		_ids.forEach(
			id -> Try.fromFallible(
				() -> _organizationLocalService.deleteOrganization(id)));
	}

	/**
	 * Adds a random address to the provided organization.
	 *
	 * @param userId the user ID that creates the address
	 * @param organization the organization being updated
	 */
	protected void addOrganizationAddress(
			long userId, Organization organization)
		throws PortalException {

		Country country = getCountry("US");

		Region region = getRandomRegion(country);

		ListType listType = getAddressListType();

		Address address = new Faker().address();

		_addressLocalService.addAddress(
			userId, Organization.class.getName(),
			organization.getOrganizationId(), address.streetAddress(), "", "",
			address.city(), address.zipCode(), region.getRegionId(),
			country.getCountryId(), listType.getListTypeId(), false, true,
			new ServiceContext());
	}

	/**
	 * Creates an organization with the provided name and stores it in the
	 * internal database.
	 *
	 * @param  userId the user that create the organization
	 * @param  name the name of the new organization
	 * @return the created organization
	 */
	protected Organization createOrganization(long userId, String name)
		throws PortalException {

		String organizationName = Optional.ofNullable(
			name
		).orElseGet(
			new Faker().company()::name
		);

		return _organizationLocalService.addOrganization(
			userId, DEFAULT_PARENT_ORGANIZATION_ID, organizationName, true);
	}

	/**
	 * Creates an organization with the provided name and logo and stores it in
	 * the internal database.
	 *
	 * @param  userId the user that create the organization
	 * @param  name the name of the new organization
	 * @param  imageURL the URL of the organization's logo
	 * @return the created organization
	 */
	protected Organization createOrganization(
			long userId, String name, String imageURL)
		throws PortalException {

		Organization organization = createOrganization(userId, name);

		addOrganizationAddress(userId, organization);

		updateOrganizationLogo(imageURL, organization);

		_ids.add(organization.getOrganizationId());

		return _organizationLocalService.getOrganization(
			organization.getOrganizationId());
	}

	/**
	 * Returns the first address list type for organizations that can be found.
	 *
	 * @return the first address list type for organizations that can be found.
	 */
	protected ListType getAddressListType() {
		List<ListType> listTypes = _listTypeLocalService.getListTypes(
			Organization.class.getName() + ListTypeConstants.ADDRESS);

		return listTypes.get(0);
	}

	/**
	 * Try to obtains a specific country by its a2 code. If getting the country
	 * fails, returns the first country the service returns.
	 *
	 * @param  a2 the A2 code for the country
	 * @return the country with the provided a2 code, if found; the first
	 *         country the service returns otherwise
	 */
	protected Country getCountry(String a2) {
		return Try.fromFallible(
			() -> _countryService.getCountryByA2(a2)
		).orElseGet(
			() -> _countryService.getCountries(true).get(0)
		);
	}

	/**
	 * Returns a random region from the provided country.
	 *
	 * @param  country the country
	 * @return a random region from the country
	 */
	protected Region getRandomRegion(Country country) {
		List<Region> regions = _regionService.getRegions(
			country.getCountryId());

		return regions.get(RandomUtil.nextInt(regions.size()));
	}

	/**
	 * Updates and organization logo with the image obtained from the provided
	 * URL.
	 *
	 * @param imageURL the image URL
	 * @param organization the organization being updated
	 */
	protected void updateOrganizationLogo(
			String imageURL, Organization organization)
		throws PortalException {

		String url = Optional.ofNullable(
			imageURL
		).orElse(
			BUSINESS_UNSPLASH_URL
		);

		byte[] logoBytes = Try.fromFallibleWithResources(
			() -> new URL(url).openStream(), FileUtil::getBytes
		).orElse(
			null
		);

		_organizationLocalService.updateOrganization(
			organization.getCompanyId(), organization.getOrganizationId(),
			DEFAULT_PARENT_ORGANIZATION_ID, organization.getName(),
			organization.getType(), 0, 0,
			ListTypeConstants.ORGANIZATION_STATUS_DEFAULT, null,
			logoBytes != null, logoBytes, true, null);
	}

	protected static final String BUSINESS_UNSPLASH_URL =
		"https://source.unsplash.com/600x600/?business";

	private static final TransactionConfig _transactionConfig =
		TransactionConfig.Factory.create(
			Propagation.REQUIRED, new Class<?>[] {Exception.class});

	@Reference
	private AddressLocalService _addressLocalService;

	@Reference
	private CountryService _countryService;

	private List<Long> _ids = new ArrayList<>();

	@Reference
	private ListTypeLocalService _listTypeLocalService;

	private Log _log = LogFactoryUtil.getLog(getClass());

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference
	private RegionService _regionService;

}