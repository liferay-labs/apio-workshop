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

import com.liferay.portal.kernel.model.User;
import com.liferay.recipes.workshop.helper.WorkshopHelper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Víctor Galán
 */
@SuppressWarnings("unused")
@XmlRootElement
public class UserDTO {

    public UserDTO() {
    }

    public UserDTO(User user, WorkshopHelper workshopHelper) {
        id = user.getUserId();
        screenName = user.getScreenName();
        email = user.getEmailAddress();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        jobTitle = user.getJobTitle();
        fullName = user.getFullName();
        portraitURL = workshopHelper.getPortraitURL(user);
    }

    @XmlElement(name = "portraitURL")
    public String getPortraitURL() {
        return portraitURL;
    }

    @XmlElement(name = "id")
    public long getId() {
        return id;
    }

    @XmlElement(name = "screenName")
    public String getScreenName() {
        return screenName;
    }

    @XmlElement(name = "email")
    public String getEmail() {
        return email;
    }

    @XmlElement(name = "firstName")
    public String getFirstName() {
        return firstName;
    }

    @XmlElement(name = "lastName")
    public String getLastName() {
        return lastName;
    }

    @XmlElement(name = "jobTitle")
    public String getJobTitle() {
        return jobTitle;
    }

    @XmlElement(name = "fullName")
    public String getFullName() {
        return fullName;
    }

    private long id;
    private String screenName;
    private String email;
    private String firstName;
    private String lastName;
    private String jobTitle;
    private String fullName;
    private String portraitURL;

}
