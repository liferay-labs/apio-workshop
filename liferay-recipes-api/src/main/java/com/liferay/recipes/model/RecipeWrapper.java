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

package com.liferay.recipes.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Recipe}.
 * </p>
 *
 * @author Alejandro Hernández
 * @see Recipe
 * @generated
 */
@ProviderType
public class RecipeWrapper implements Recipe, ModelWrapper<Recipe> {
	public RecipeWrapper(Recipe recipe) {
		_recipe = recipe;
	}

	@Override
	public Class<?> getModelClass() {
		return Recipe.class;
	}

	@Override
	public String getModelClassName() {
		return Recipe.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("recipeId", getRecipeId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("region", getRegion());
		attributes.put("imageFileEntryId", getImageFileEntryId());
		attributes.put("publishedDate", getPublishedDate());
		attributes.put("hoursToMake", getHoursToMake());
		attributes.put("minutesToMake", getMinutesToMake());
		attributes.put("stepsString", getStepsString());
		attributes.put("ingredientsString", getIngredientsString());
		attributes.put("videoURL", getVideoURL());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long recipeId = (Long)attributes.get("recipeId");

		if (recipeId != null) {
			setRecipeId(recipeId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String region = (String)attributes.get("region");

		if (region != null) {
			setRegion(region);
		}

		Long imageFileEntryId = (Long)attributes.get("imageFileEntryId");

		if (imageFileEntryId != null) {
			setImageFileEntryId(imageFileEntryId);
		}

		Date publishedDate = (Date)attributes.get("publishedDate");

		if (publishedDate != null) {
			setPublishedDate(publishedDate);
		}

		Integer hoursToMake = (Integer)attributes.get("hoursToMake");

		if (hoursToMake != null) {
			setHoursToMake(hoursToMake);
		}

		Integer minutesToMake = (Integer)attributes.get("minutesToMake");

		if (minutesToMake != null) {
			setMinutesToMake(minutesToMake);
		}

		String stepsString = (String)attributes.get("stepsString");

		if (stepsString != null) {
			setStepsString(stepsString);
		}

		String ingredientsString = (String)attributes.get("ingredientsString");

		if (ingredientsString != null) {
			setIngredientsString(ingredientsString);
		}

		String videoURL = (String)attributes.get("videoURL");

		if (videoURL != null) {
			setVideoURL(videoURL);
		}
	}

	@Override
	public Object clone() {
		return new RecipeWrapper((Recipe)_recipe.clone());
	}

	@Override
	public int compareTo(Recipe recipe) {
		return _recipe.compareTo(recipe);
	}

	/**
	* Returns the company ID of this recipe.
	*
	* @return the company ID of this recipe
	*/
	@Override
	public long getCompanyId() {
		return _recipe.getCompanyId();
	}

	/**
	* Returns the create date of this recipe.
	*
	* @return the create date of this recipe
	*/
	@Override
	public Date getCreateDate() {
		return _recipe.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _recipe.getExpandoBridge();
	}

	/**
	* Returns the group ID of this recipe.
	*
	* @return the group ID of this recipe
	*/
	@Override
	public long getGroupId() {
		return _recipe.getGroupId();
	}

	/**
	* Returns the hours to make of this recipe.
	*
	* @return the hours to make of this recipe
	*/
	@Override
	public int getHoursToMake() {
		return _recipe.getHoursToMake();
	}

	/**
	* Returns the image file entry ID of this recipe.
	*
	* @return the image file entry ID of this recipe
	*/
	@Override
	public long getImageFileEntryId() {
		return _recipe.getImageFileEntryId();
	}

	/**
	* Returns the list of the recipe's ingredients.
	*
	* @return the list of the recipe's ingredients
	*/
	@Override
	public java.util.List<String> getIngredients() {
		return _recipe.getIngredients();
	}

	/**
	* Returns the ingredients string of this recipe.
	*
	* @return the ingredients string of this recipe
	*/
	@Override
	public String getIngredientsString() {
		return _recipe.getIngredientsString();
	}

	/**
	* Returns the minutes to make of this recipe.
	*
	* @return the minutes to make of this recipe
	*/
	@Override
	public int getMinutesToMake() {
		return _recipe.getMinutesToMake();
	}

	/**
	* Returns the modified date of this recipe.
	*
	* @return the modified date of this recipe
	*/
	@Override
	public Date getModifiedDate() {
		return _recipe.getModifiedDate();
	}

	/**
	* Returns the name of this recipe.
	*
	* @return the name of this recipe
	*/
	@Override
	public String getName() {
		return _recipe.getName();
	}

	/**
	* Returns the primary key of this recipe.
	*
	* @return the primary key of this recipe
	*/
	@Override
	public long getPrimaryKey() {
		return _recipe.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _recipe.getPrimaryKeyObj();
	}

	/**
	* Returns the published date of this recipe.
	*
	* @return the published date of this recipe
	*/
	@Override
	public Date getPublishedDate() {
		return _recipe.getPublishedDate();
	}

	/**
	* Returns the recipe ID of this recipe.
	*
	* @return the recipe ID of this recipe
	*/
	@Override
	public long getRecipeId() {
		return _recipe.getRecipeId();
	}

	/**
	* Returns the region of this recipe.
	*
	* @return the region of this recipe
	*/
	@Override
	public String getRegion() {
		return _recipe.getRegion();
	}

	/**
	* Returns the list of the recipe's steps.
	*
	* @return the list of the recipe's steps
	*/
	@Override
	public java.util.List<String> getSteps() {
		return _recipe.getSteps();
	}

	/**
	* Returns the steps string of this recipe.
	*
	* @return the steps string of this recipe
	*/
	@Override
	public String getStepsString() {
		return _recipe.getStepsString();
	}

	/**
	* Returns the user ID of this recipe.
	*
	* @return the user ID of this recipe
	*/
	@Override
	public long getUserId() {
		return _recipe.getUserId();
	}

	/**
	* Returns the user name of this recipe.
	*
	* @return the user name of this recipe
	*/
	@Override
	public String getUserName() {
		return _recipe.getUserName();
	}

	/**
	* Returns the user uuid of this recipe.
	*
	* @return the user uuid of this recipe
	*/
	@Override
	public String getUserUuid() {
		return _recipe.getUserUuid();
	}

	/**
	* Returns the uuid of this recipe.
	*
	* @return the uuid of this recipe
	*/
	@Override
	public String getUuid() {
		return _recipe.getUuid();
	}

	/**
	* Returns the video url of this recipe.
	*
	* @return the video url of this recipe
	*/
	@Override
	public String getVideoURL() {
		return _recipe.getVideoURL();
	}

	@Override
	public int hashCode() {
		return _recipe.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _recipe.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _recipe.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _recipe.isNew();
	}

	@Override
	public void persist() {
		_recipe.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_recipe.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this recipe.
	*
	* @param companyId the company ID of this recipe
	*/
	@Override
	public void setCompanyId(long companyId) {
		_recipe.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this recipe.
	*
	* @param createDate the create date of this recipe
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_recipe.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_recipe.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_recipe.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_recipe.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this recipe.
	*
	* @param groupId the group ID of this recipe
	*/
	@Override
	public void setGroupId(long groupId) {
		_recipe.setGroupId(groupId);
	}

	/**
	* Sets the hours to make of this recipe.
	*
	* @param hoursToMake the hours to make of this recipe
	*/
	@Override
	public void setHoursToMake(int hoursToMake) {
		_recipe.setHoursToMake(hoursToMake);
	}

	/**
	* Sets the image file entry ID of this recipe.
	*
	* @param imageFileEntryId the image file entry ID of this recipe
	*/
	@Override
	public void setImageFileEntryId(long imageFileEntryId) {
		_recipe.setImageFileEntryId(imageFileEntryId);
	}

	/**
	* Sets the ingredients string of this recipe.
	*
	* @param ingredientsString the ingredients string of this recipe
	*/
	@Override
	public void setIngredientsString(String ingredientsString) {
		_recipe.setIngredientsString(ingredientsString);
	}

	/**
	* Sets the minutes to make of this recipe.
	*
	* @param minutesToMake the minutes to make of this recipe
	*/
	@Override
	public void setMinutesToMake(int minutesToMake) {
		_recipe.setMinutesToMake(minutesToMake);
	}

	/**
	* Sets the modified date of this recipe.
	*
	* @param modifiedDate the modified date of this recipe
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_recipe.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this recipe.
	*
	* @param name the name of this recipe
	*/
	@Override
	public void setName(String name) {
		_recipe.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_recipe.setNew(n);
	}

	/**
	* Sets the primary key of this recipe.
	*
	* @param primaryKey the primary key of this recipe
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_recipe.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_recipe.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the published date of this recipe.
	*
	* @param publishedDate the published date of this recipe
	*/
	@Override
	public void setPublishedDate(Date publishedDate) {
		_recipe.setPublishedDate(publishedDate);
	}

	/**
	* Sets the recipe ID of this recipe.
	*
	* @param recipeId the recipe ID of this recipe
	*/
	@Override
	public void setRecipeId(long recipeId) {
		_recipe.setRecipeId(recipeId);
	}

	/**
	* Sets the region of this recipe.
	*
	* @param region the region of this recipe
	*/
	@Override
	public void setRegion(String region) {
		_recipe.setRegion(region);
	}

	/**
	* Sets the steps string of this recipe.
	*
	* @param stepsString the steps string of this recipe
	*/
	@Override
	public void setStepsString(String stepsString) {
		_recipe.setStepsString(stepsString);
	}

	/**
	* Sets the user ID of this recipe.
	*
	* @param userId the user ID of this recipe
	*/
	@Override
	public void setUserId(long userId) {
		_recipe.setUserId(userId);
	}

	/**
	* Sets the user name of this recipe.
	*
	* @param userName the user name of this recipe
	*/
	@Override
	public void setUserName(String userName) {
		_recipe.setUserName(userName);
	}

	/**
	* Sets the user uuid of this recipe.
	*
	* @param userUuid the user uuid of this recipe
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_recipe.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this recipe.
	*
	* @param uuid the uuid of this recipe
	*/
	@Override
	public void setUuid(String uuid) {
		_recipe.setUuid(uuid);
	}

	/**
	* Sets the video url of this recipe.
	*
	* @param videoURL the video url of this recipe
	*/
	@Override
	public void setVideoURL(String videoURL) {
		_recipe.setVideoURL(videoURL);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Recipe> toCacheModel() {
		return _recipe.toCacheModel();
	}

	@Override
	public Recipe toEscapedModel() {
		return new RecipeWrapper(_recipe.toEscapedModel());
	}

	@Override
	public String toString() {
		return _recipe.toString();
	}

	@Override
	public Recipe toUnescapedModel() {
		return new RecipeWrapper(_recipe.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _recipe.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RecipeWrapper)) {
			return false;
		}

		RecipeWrapper recipeWrapper = (RecipeWrapper)obj;

		if (Objects.equals(_recipe, recipeWrapper._recipe)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _recipe.getStagedModelType();
	}

	@Override
	public Recipe getWrappedModel() {
		return _recipe;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _recipe.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _recipe.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_recipe.resetOriginalValues();
	}

	private final Recipe _recipe;
}