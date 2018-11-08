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

package apio.architect.workshop.model;

import com.liferay.portal.kernel.model.Organization;
import com.liferay.recipes.workshop.helper.WorkshopHelper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Víctor Galán
 */

@SuppressWarnings("unused")
@XmlRootElement
public class OrganizationDTO {

    public OrganizationDTO() {
    }

    public OrganizationDTO(Organization organization, WorkshopHelper workshopHelper) {
        id = organization.getOrganizationId();
        name = organization.getName();
        logoURL = workshopHelper.getLogoURL(organization);
        address = new AddressDTO(organization.getAddress());
        chefId = workshopHelper.getChefId(organization);
    }

    @XmlElement(name = "chefId")
    public Long getChefId() {
        return chefId;
    }

    @XmlElement(name = "id")
    public long getId() {
        return id;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    @XmlElement(name = "logoURL")
    public String getLogoURL() {
        return logoURL;
    }

    @XmlElement(name = "address")
    public AddressDTO getAddress() {
        return address;
    }

    private long id;
    private String name;
    private String logoURL;
    private AddressDTO address;
    private Long chefId;

}
