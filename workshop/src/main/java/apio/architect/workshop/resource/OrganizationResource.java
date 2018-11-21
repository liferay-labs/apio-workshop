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

import apio.architect.workshop.model.OrganizationDTO;
import apio.architect.workshop.type.OrganizationType;
import com.liferay.apio.architect.annotation.Actions.EntryPoint;
import com.liferay.apio.architect.annotation.Actions.Retrieve;
import com.liferay.apio.architect.annotation.Id;
import com.liferay.apio.architect.pagination.PageItems;
import com.liferay.apio.architect.pagination.Pagination;
import com.liferay.apio.architect.router.ActionRouter;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.OrganizationService;
import com.liferay.recipes.workshop.helper.WorkshopHelper;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alejandro Hernández
 * @author Victor Galán
 */
@Component
public class OrganizationResource implements ActionRouter<OrganizationType> {

    @EntryPoint
    @Retrieve
    public PageItems<OrganizationType> retrieve(User user, Pagination pagination) throws PortalException {
        List<OrganizationType> organizations = _organizationService.getUserOrganizations(user.getUserId())
            .stream()
            .map(organization -> new OrganizationDTO(organization, _workshopHelper))
            .collect(Collectors.toList());

        return new PageItems<>(
            organizations.subList(pagination.getStartPosition(), pagination.getEndPosition()), organizations.size());
    }

    @Retrieve
    public OrganizationType retrieve(@Id long id) throws PortalException {
        Organization organization = _organizationService.getOrganization(id);

        return new OrganizationDTO(organization, _workshopHelper);
    }

    @Reference
    private OrganizationService _organizationService;

    @Reference
    private WorkshopHelper _workshopHelper;

}