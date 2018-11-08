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

import com.liferay.portal.kernel.model.Address;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Víctor Galán
 */
@SuppressWarnings({"unused", "WeakerAccess"})
@XmlRootElement
public class AddressDTO {

    public AddressDTO() {

    }

    public AddressDTO(Address address) {
        countryName = address.getCountry().getName();
        zipCode = address.getZip();
        regionName = address.getRegion().getName();
        street = address.getStreet1();
    }

    @XmlElement(name = "street")
    public String getStreet() {
        return street;
    }

    @XmlElement(name = "zipCode")
    public String getZipCode() {
        return zipCode;
    }

    @XmlElement(name = "regionName")
    public String getRegionName() {
        return regionName;
    }

    @XmlElement(name = "countryName")
    public String getCountryName() {
        return countryName;
    }

    private String street;
    private String zipCode;
    private String regionName;
    private String countryName;

}
