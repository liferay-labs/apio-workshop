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
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.category.demo.data.creator.AssetVocabularyDemoDataCreator;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alejandro Hernández
 */
@Component
public class AssetVocabularyDemoDataCreatorImpl
	implements AssetVocabularyDemoDataCreator {

	@Override
	public AssetVocabulary create(long userId, long groupId, String name) {
		return Try.fromFallible(
			() -> _assetVocabularyLocalService.getGroupVocabulary(groupId, name)
		).recoverWith(
			__ -> Try.fromFallible(
				() -> _assetVocabularyLocalService.addVocabulary(
					userId, groupId, name, getServiceContext()))
		).fold(
			__ -> null, this::storeAssetVocabularyId
		);
	}

	@Override
	public void delete() {
		_ids.forEach(
			id -> Try.fromFallible(
				() -> _assetVocabularyLocalService.deleteAssetVocabulary(id)));
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
	 * Stores the vocabulary ID in the internal list so it gets deleted when
	 * called {@link #delete()}.
	 *
	 * @param  vocabulary the vocabulary to store
	 * @return the stored vocabulary
	 */
	protected AssetVocabulary storeAssetVocabularyId(
		AssetVocabulary vocabulary) {

		_ids.add(vocabulary.getVocabularyId());

		return vocabulary;
	}

	@Reference
	private AssetVocabularyLocalService _assetVocabularyLocalService;

	private List<Long> _ids = new ArrayList<>();

}