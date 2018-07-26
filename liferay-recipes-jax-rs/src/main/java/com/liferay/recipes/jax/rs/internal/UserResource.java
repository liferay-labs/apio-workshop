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

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import com.liferay.apio.architect.functional.Try;
import com.liferay.portal.kernel.util.Portal;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Serves and endpoint for validating users credentials.
 *
 * @author Victor Galan
 */
@Component(
	immediate = true,
	property = {
		"osgi.jaxrs.application.select=(liferay.apio.architect.application=true)",
		"osgi.jaxrs.resource=true"
	},
	service = Object.class
)
@Path("/login")
public class UserResource {

	@GET
	@Path("/")
	@Produces(APPLICATION_JSON)
	public UserWrapper getCurrentUser(
		@Context HttpServletRequest httpServletRequest) {

		return Try.fromFallible(
			() -> _portal.getUser(httpServletRequest)
		).filter(
			user -> !user.isDefaultUser()
		).map(
			user -> new UserWrapper(user, _portal.getPathImage())
		).orElseThrow(
			ForbiddenException::new
		);
	}

	@Reference
	private Portal _portal;

}