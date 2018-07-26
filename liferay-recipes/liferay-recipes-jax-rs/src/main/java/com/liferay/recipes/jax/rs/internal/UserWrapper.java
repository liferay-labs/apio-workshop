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

package com.liferay.recipes.jax.rs.internal;

import com.liferay.apio.architect.functional.Try;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserConstants;

import java.util.Optional;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * User wrapper for serving Liferay Portal users as a JSON element.
 *
 * @author Victor Galan
 */
@SuppressWarnings("unused")
@XmlRootElement
public class UserWrapper {

	public UserWrapper() {
		_user = null;
		_imagePath = null;
	}

	public UserWrapper(User user, String imagePath) {
		_user = user;
		_imagePath = imagePath;
	}

	@XmlElement
	public String getImage() {
		if (_user == null) {
			return null;
		}

		boolean male = Try.fromFallible(
			_user::isMale
		).orElse(
			true
		);

		return UserConstants.getPortraitURL(
			_imagePath, male, _user.getPortraitId(), _user.getUuid());
	}

	@XmlElement
	public String getName() {
		return Optional.ofNullable(
			_user
		).map(
			User::getFullName
		).orElse(
			null
		);
	}

	private final String _imagePath;
	private final User _user;

}