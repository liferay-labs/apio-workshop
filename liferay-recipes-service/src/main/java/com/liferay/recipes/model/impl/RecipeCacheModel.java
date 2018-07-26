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

package com.liferay.recipes.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.recipes.model.Recipe;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Recipe in entity cache.
 *
 * @author Alejandro Hernández
 * @see Recipe
 * @generated
 */
@ProviderType
public class RecipeCacheModel implements CacheModel<Recipe>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RecipeCacheModel)) {
			return false;
		}

		RecipeCacheModel recipeCacheModel = (RecipeCacheModel)obj;

		if (recipeId == recipeCacheModel.recipeId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, recipeId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", recipeId=");
		sb.append(recipeId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", name=");
		sb.append(name);
		sb.append(", region=");
		sb.append(region);
		sb.append(", imageFileEntryId=");
		sb.append(imageFileEntryId);
		sb.append(", publishedDate=");
		sb.append(publishedDate);
		sb.append(", hoursToMake=");
		sb.append(hoursToMake);
		sb.append(", minutesToMake=");
		sb.append(minutesToMake);
		sb.append(", stepsString=");
		sb.append(stepsString);
		sb.append(", ingredientsString=");
		sb.append(ingredientsString);
		sb.append(", videoURL=");
		sb.append(videoURL);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Recipe toEntityModel() {
		RecipeImpl recipeImpl = new RecipeImpl();

		if (uuid == null) {
			recipeImpl.setUuid("");
		}
		else {
			recipeImpl.setUuid(uuid);
		}

		recipeImpl.setRecipeId(recipeId);
		recipeImpl.setCompanyId(companyId);
		recipeImpl.setGroupId(groupId);
		recipeImpl.setUserId(userId);

		if (userName == null) {
			recipeImpl.setUserName("");
		}
		else {
			recipeImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			recipeImpl.setCreateDate(null);
		}
		else {
			recipeImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			recipeImpl.setModifiedDate(null);
		}
		else {
			recipeImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			recipeImpl.setName("");
		}
		else {
			recipeImpl.setName(name);
		}

		if (region == null) {
			recipeImpl.setRegion("");
		}
		else {
			recipeImpl.setRegion(region);
		}

		recipeImpl.setImageFileEntryId(imageFileEntryId);

		if (publishedDate == Long.MIN_VALUE) {
			recipeImpl.setPublishedDate(null);
		}
		else {
			recipeImpl.setPublishedDate(new Date(publishedDate));
		}

		recipeImpl.setHoursToMake(hoursToMake);
		recipeImpl.setMinutesToMake(minutesToMake);

		if (stepsString == null) {
			recipeImpl.setStepsString("");
		}
		else {
			recipeImpl.setStepsString(stepsString);
		}

		if (ingredientsString == null) {
			recipeImpl.setIngredientsString("");
		}
		else {
			recipeImpl.setIngredientsString(ingredientsString);
		}

		if (videoURL == null) {
			recipeImpl.setVideoURL("");
		}
		else {
			recipeImpl.setVideoURL(videoURL);
		}

		recipeImpl.resetOriginalValues();

		return recipeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		recipeId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		region = objectInput.readUTF();

		imageFileEntryId = objectInput.readLong();
		publishedDate = objectInput.readLong();

		hoursToMake = objectInput.readInt();

		minutesToMake = objectInput.readInt();
		stepsString = objectInput.readUTF();
		ingredientsString = objectInput.readUTF();
		videoURL = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(recipeId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (region == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(region);
		}

		objectOutput.writeLong(imageFileEntryId);
		objectOutput.writeLong(publishedDate);

		objectOutput.writeInt(hoursToMake);

		objectOutput.writeInt(minutesToMake);

		if (stepsString == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(stepsString);
		}

		if (ingredientsString == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ingredientsString);
		}

		if (videoURL == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(videoURL);
		}
	}

	public String uuid;
	public long recipeId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String region;
	public long imageFileEntryId;
	public long publishedDate;
	public int hoursToMake;
	public int minutesToMake;
	public String stepsString;
	public String ingredientsString;
	public String videoURL;
}