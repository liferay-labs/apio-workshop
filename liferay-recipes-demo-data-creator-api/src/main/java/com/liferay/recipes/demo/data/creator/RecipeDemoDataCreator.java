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

package com.liferay.recipes.demo.data.creator;

import com.liferay.recipes.model.Recipe;

/**
 * Creates random recipes.
 *
 * @author Alejandro Hernández
 */
public interface RecipeDemoDataCreator {

	/**
	 * Creates a random {@link Recipe} and adds it to the internal database.
	 *
	 * @param  userId the ID of the user that creates the {@code Recipe}
	 * @param  groupId the ID of the group where the {@code Recipe} is created
	 * @return the created recipe or {@code null} if something went wrong
	 */
	public Recipe create(long userId, long groupId);

	/**
	 * Deletes all the created recipes.
	 */
	public void delete();

}