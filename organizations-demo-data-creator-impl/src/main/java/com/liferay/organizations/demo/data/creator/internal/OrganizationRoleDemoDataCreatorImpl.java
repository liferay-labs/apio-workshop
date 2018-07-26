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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.roles.admin.demo.data.creator.RoleDemoDataCreator;
import com.liferay.roles.admin.demo.data.creator.internal.BaseRoleDemoDataCreator;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alejandro Hernández
 */
@Component(property = "role.type=organization")
public class OrganizationRoleDemoDataCreatorImpl
	extends BaseRoleDemoDataCreator implements RoleDemoDataCreator {

	@Override
	public Role create(long companyId, String permissionsXML)
		throws PortalException {

		return create(companyId, StringUtil.randomString(), permissionsXML);
	}

	@Override
	public Role create(long companyId, String roleName, String permissionsXML)
		throws PortalException {

		Role role = createRole(
			companyId, roleName, RoleConstants.TYPE_ORGANIZATION);

		if (Validator.isNotNull(permissionsXML)) {
			addPermissions(
				role, permissionsXML, ResourceConstants.SCOPE_GROUP_TEMPLATE,
				String.valueOf(
					OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID));
		}

		return role;
	}

}