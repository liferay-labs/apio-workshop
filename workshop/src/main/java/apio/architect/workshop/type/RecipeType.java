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
import com.liferay.apio.architect.annotation.Vocabulary.LinkedModel;
import com.liferay.apio.architect.annotation.Vocabulary.RelativeURL;
import com.liferay.apio.architect.annotation.Vocabulary.Type;
import com.liferay.apio.architect.identifier.Identifier;

import java.util.Date;
import java.util.List;

/**
 * @author Alejandro Hernández
 */
@Type("Recipe")
public interface RecipeType extends Identifier<Long> {

    @Id
    public long getId();

    @Field("creator")
    @LinkedModel(UserType.class)
    public long getUserId();

    @Field("dateCreated")
    public Date getCreateDate();

    @Field("dateModified")
    public Date getModifiedDate();

    @Field("datePublished")
    public Date getPublishedDate();

    @Field("recipeCuisine")
    public String getRegion();

    @Field("recipeIngredient")
    public List<String> getIngredients();

    @Field("recipeInstructions")
    public List<String> getSteps();

    @Field("video")
    public String getVideoURL();

    @Field("cookTime")
    public String getCookTime();

    @Field("recipeCategory")
    public String getCategory();

    @Field("keywords")
    public List<String> getRecipeAssetTags();

    @Field("image")
    @RelativeURL
    public String getImageURL();

    @Field("name")
    public String getName();

}
