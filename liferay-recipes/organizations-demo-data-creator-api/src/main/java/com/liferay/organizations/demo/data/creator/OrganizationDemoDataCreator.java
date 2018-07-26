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

package com.liferay.organizations.demo.data.creator;

import com.liferay.portal.kernel.model.Organization;

/**
 * Creates random organizations.
 *
 * @author Alejandro Hernández
 */
public interface OrganizationDemoDataCreator {

	/**
	 * Creates a random {@link Organization} and adds it to the internal
	 * database.
	 *
	 * @param  userId the ID of the user that creates the {@code Organization}
	 * @param  name the organization's name
	 * @param  imageURL the URL of the organization's logo
	 * @return the created organization
	 */
	public Organization create(long userId, String name, String imageURL);

	/**
	 * Deletes all the created organizations.
	 */
	public void delete();

}