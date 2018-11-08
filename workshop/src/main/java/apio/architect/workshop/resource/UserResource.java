/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 * <p>
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * <p>
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package apio.architect.workshop.resource;

import apio.architect.workshop.model.UserDTO;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserService;
import com.liferay.recipes.workshop.helper.WorkshopHelper;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * @author Víctor Galán
 */
@Component(property = "osgi.jaxrs.resource=true", service = Object.class, immediate = true)
public class UserResource {

    @GET
    @Path("users/{id}")
    @Produces(APPLICATION_JSON)
    public UserDTO retrieve(@PathParam("id") long userId) throws PortalException {
        User user = _userService.getUserById(userId);

        return new UserDTO(user, _workshopHelper);
    }

    @Reference
    private UserService _userService;

    @Reference
    private WorkshopHelper _workshopHelper;

}
