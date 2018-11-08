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

import com.liferay.recipes.model.Recipe;
import com.liferay.recipes.workshop.helper.WorkshopHelper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

/**
 * @author Víctor Galán
 */

@SuppressWarnings("unused")
@XmlRootElement
public class RecipeDTO {

    public RecipeDTO() {

    }

    public RecipeDTO(Recipe recipe, WorkshopHelper workshopHelper) {
        id = recipe.getRecipeId();
        createDate = recipe.getCreateDate();
        modifiedDate = recipe.getModifiedDate();
        publishedDate = recipe.getPublishedDate();
        region = recipe.getRegion();
        ingredients = recipe.getIngredients();
        steps = recipe.getSteps();
        name = recipe.getName();
        userId = recipe.getUserId();
        videoURL = recipe.getVideoURL();
        cookTime = workshopHelper.getCookTime(recipe);
        category = workshopHelper.getCategory(recipe);
        recipeAssetTags = workshopHelper.getRecipeAssetTags(recipe);
        imageURL = workshopHelper.getImageURL(recipe);
    }

    @XmlElement(name = "createDate")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @XmlElement(name = "modifiedDate")
    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @XmlElement(name = "publishedDate")
    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    @XmlElement(name = "region")
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @XmlElement(name = "ingredients")
    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    @XmlElement(name = "steps")
    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    @XmlElement(name = "userId")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @XmlElement(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @XmlElement(name = "videoURL")
    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    @XmlElement(name = "cookTime")
    public String getCookTime() {
        return cookTime;
    }

    public void setCookTime(String cookTime) {
        this.cookTime = cookTime;
    }

    @XmlElement(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @XmlElement(name = "recipeAssetTags")
    public List<String> getRecipeAssetTags() {
        return recipeAssetTags;
    }

    public void setRecipeAssetTags(List<String> recipeAssetTags) {
        this.recipeAssetTags = recipeAssetTags;
    }

    @XmlElement(name = "imageURL")
    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    private String name;
    private Date createDate;
    private Date modifiedDate;
    private Date publishedDate;
    private String region;
    private List<String> ingredients;
    private List<String> steps;
    private long userId;
    private long id;
    private String videoURL;
    private String cookTime;
    private String category;
    private List<String> recipeAssetTags;
    private String imageURL;

}
