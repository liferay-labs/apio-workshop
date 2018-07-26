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

package com.liferay.category.demo.data.creator;

import com.liferay.asset.kernel.model.AssetCategory;

/**
 * Creates random recipes.
 *
 * @author Alejandro Hernández
 */
public interface AssetCategoryDemoDataCreator {

	/**
	 * Creates a random {@link AssetCategory} and adds it to the internal
	 * database.
	 *
	 * @param  userId the ID of the user that creates the {@code Recipe}
	 * @param  vocabularyId the ID of the vocabulary where the {@code
	 *         AssetCategory} is created
	 * @return the created category or {@code null} if something went wrong
	 */
	public AssetCategory create(long userId, long vocabularyId, String name);

	/**
	 * Deletes all the created categories.
	 */
	public void delete();

}