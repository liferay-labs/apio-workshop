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

package apio.architect.workshop.type;

import com.liferay.apio.architect.annotation.Id;
import com.liferay.apio.architect.annotation.Vocabulary.Field;
import com.liferay.apio.architect.annotation.Vocabulary.RelativeURL;
import com.liferay.apio.architect.annotation.Vocabulary.Type;
import com.liferay.apio.architect.identifier.Identifier;

/**
 * @author Alejandro Hernández
 */
@Type("User")
public interface UserType extends Identifier<Long> {

    @Field("portraitURL")
    @RelativeURL
    public String getPortraitURL();

    @Id
    public long getId();

    @Field("screenName")
    public String getScreenName();

    @Field("email")
    public String getEmail();

    @Field("firstName")
    public String getFirstName();

    @Field("lastName")
    public String getLastName();

    @Field("jobTitle")
    public String getJobTitle();

    @Field("fullName")
    public String getFullName();

}
