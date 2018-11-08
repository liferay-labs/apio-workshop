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

package com.liferay.recipes.jax.rs.internal;

import org.osgi.service.component.annotations.Component;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * @author Alejandro Hernández
 */
@Component(immediate = true, property = "osgi.jaxrs.extension=true")
public class GeneralExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        return Response.serverError().build();
    }

}
