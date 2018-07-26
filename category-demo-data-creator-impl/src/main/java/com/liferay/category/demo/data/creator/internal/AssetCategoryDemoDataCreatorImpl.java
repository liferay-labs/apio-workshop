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

package com.liferay.category.demo.data.creator.internal;

import com.liferay.apio.architect.functional.Try;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.category.demo.data.creator.AssetCategoryDemoDataCreator;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alejandro Hernández
 */
@Component
public class AssetCategoryDemoDataCreatorImpl
	implements AssetCategoryDemoDataCreator {

	@Override
	public AssetCategory create(long userId, long vocabularyId, String name) {
		AssetVocabulary vocabulary =
			_assetVocabularyLocalService.fetchAssetVocabulary(vocabularyId);

		if (vocabulary == null) {
			return null;
		}

		return Try.fromFallible(
			() -> _assetCategoryLocalService.fetchCategory(
				vocabulary.getGroupId(), 0, name, vocabularyId)
		).filter(
			Validator::isNotNull
		).recoverWith(
			__ -> Try.fromFallible(
				() -> _assetCategoryLocalService.addCategory(
					userId, vocabulary.getGroupId(), name, vocabularyId,
					getServiceContext()))
		).fold(
			__ -> null, this::storeAssetCategoryId
		);
	}

	@Override
	public void delete() {
		_ids.forEach(
			id -> Try.fromFallible(
				() -> _assetCategoryLocalService.deleteAssetCategory(id)));
	}

	/**
	 * Returns a valid {@link ServiceContext} for creating AssetVocabularies.
	 *
	 * @return a valid {@code ServiceContext} for creating AssetVocabularies
	 */
	protected ServiceContext getServiceContext() {
		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		return serviceContext;
	}

	/**
	 * Stores the category ID in the internal list so it gets deleted when
	 * called {@link #delete()}.
	 *
	 * @param  category the category to store
	 * @return the stored category
	 */
	protected AssetCategory storeAssetCategoryId(AssetCategory category) {
		_ids.add(category.getCategoryId());

		return category;
	}

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Reference
	private AssetVocabularyLocalService _assetVocabularyLocalService;

	private List<Long> _ids = new ArrayList<>();

}