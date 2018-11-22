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

import apio.architect.workshop.model.AddressDTO;
import com.liferay.apio.architect.annotation.Id;
import com.liferay.apio.architect.annotation.Vocabulary.*;
import com.liferay.apio.architect.identifier.Identifier;

/**
 * @author Alejandro Hernández
 */
@Type("Restaurant")
public interface OrganizationType extends Identifier<Long> {

    @Id
    public long getId();

    @Field(value = "chef", description = "The restaurant's chef")
    @LinkedModel(UserType.class)
    public Long getChefId();

    @Field("recipes")
    @RelatedCollection(RecipeType.class)
    public default long getRecipeCollection() {
        return getId();
    }

    @Field("logo")
    @RelativeURL
    public String getLogoURL();

    @Field("name")
    public String getName();

    @Field("location")
    public AddressDTO getAddress();

}
