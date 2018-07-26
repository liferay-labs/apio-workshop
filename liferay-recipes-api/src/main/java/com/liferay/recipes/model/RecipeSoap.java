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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.recipes.service.http.RecipeServiceSoap}.
 *
 * @author Alejandro Hernández
 * @see com.liferay.recipes.service.http.RecipeServiceSoap
 * @generated
 */
@ProviderType
public class RecipeSoap implements Serializable {
	public static RecipeSoap toSoapModel(Recipe model) {
		RecipeSoap soapModel = new RecipeSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setRecipeId(model.getRecipeId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setRegion(model.getRegion());
		soapModel.setImageFileEntryId(model.getImageFileEntryId());
		soapModel.setPublishedDate(model.getPublishedDate());
		soapModel.setHoursToMake(model.getHoursToMake());
		soapModel.setMinutesToMake(model.getMinutesToMake());
		soapModel.setStepsString(model.getStepsString());
		soapModel.setIngredientsString(model.getIngredientsString());
		soapModel.setVideoURL(model.getVideoURL());

		return soapModel;
	}

	public static RecipeSoap[] toSoapModels(Recipe[] models) {
		RecipeSoap[] soapModels = new RecipeSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RecipeSoap[][] toSoapModels(Recipe[][] models) {
		RecipeSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new RecipeSoap[models.length][models[0].length];
		}
		else {
			soapModels = new RecipeSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RecipeSoap[] toSoapModels(List<Recipe> models) {
		List<RecipeSoap> soapModels = new ArrayList<RecipeSoap>(models.size());

		for (Recipe model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new RecipeSoap[soapModels.size()]);
	}

	public RecipeSoap() {
	}

	public long getPrimaryKey() {
		return _recipeId;
	}

	public void setPrimaryKey(long pk) {
		setRecipeId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getRecipeId() {
		return _recipeId;
	}

	public void setRecipeId(long recipeId) {
		_recipeId = recipeId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getRegion() {
		return _region;
	}

	public void setRegion(String region) {
		_region = region;
	}

	public long getImageFileEntryId() {
		return _imageFileEntryId;
	}

	public void setImageFileEntryId(long imageFileEntryId) {
		_imageFileEntryId = imageFileEntryId;
	}

	public Date getPublishedDate() {
		return _publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		_publishedDate = publishedDate;
	}

	public int getHoursToMake() {
		return _hoursToMake;
	}

	public void setHoursToMake(int hoursToMake) {
		_hoursToMake = hoursToMake;
	}

	public int getMinutesToMake() {
		return _minutesToMake;
	}

	public void setMinutesToMake(int minutesToMake) {
		_minutesToMake = minutesToMake;
	}

	public String getStepsString() {
		return _stepsString;
	}

	public void setStepsString(String stepsString) {
		_stepsString = stepsString;
	}

	public String getIngredientsString() {
		return _ingredientsString;
	}

	public void setIngredientsString(String ingredientsString) {
		_ingredientsString = ingredientsString;
	}

	public String getVideoURL() {
		return _videoURL;
	}

	public void setVideoURL(String videoURL) {
		_videoURL = videoURL;
	}

	private String _uuid;
	private long _recipeId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _region;
	private long _imageFileEntryId;
	private Date _publishedDate;
	private int _hoursToMake;
	private int _minutesToMake;
	private String _stepsString;
	private String _ingredientsString;
	private String _videoURL;
}