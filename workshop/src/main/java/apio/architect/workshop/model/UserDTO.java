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

import apio.architect.workshop.type.UserType;
import com.liferay.portal.kernel.model.User;
import com.liferay.recipes.workshop.helper.WorkshopHelper;

/**
 * @author Víctor Galán
 */
public class UserDTO implements UserType {

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

    @Override
    public String getPortraitURL() {
        return portraitURL;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getScreenName() {
        return screenName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getJobTitle() {
        return jobTitle;
    }

    @Override
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
