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

package apio.architect.workshop.exception.mapper;

import com.liferay.apio.architect.error.APIError;
import com.liferay.apio.architect.exception.mapper.ExceptionMapper;
import com.liferay.portal.kernel.exception.NoSuchModelException;
import org.osgi.service.component.annotations.Component;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

/**
 * @author Alejandro Hernández
 */
@Component
public class NoSuchModelExceptionMapper implements ExceptionMapper<NoSuchModelException> {

    @Override
    public APIError map(NoSuchModelException noSuchModelException) {
        return new APIError(
            noSuchModelException, NOT_FOUND.getReasonPhrase(), noSuchModelException.getMessage(), "not-found", 404);
    }

}
