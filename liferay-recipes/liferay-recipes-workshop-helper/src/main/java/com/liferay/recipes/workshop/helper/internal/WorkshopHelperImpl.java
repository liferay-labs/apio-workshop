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

package com.liferay.recipes.workshop.helper.internal;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetTagLocalService;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.portal.kernel.model.*;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.webserver.WebServerServletTokenUtil;
import com.liferay.recipes.model.Recipe;
import com.liferay.recipes.workshop.helper.WorkshopHelper;
import io.vavr.Tuple;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.liferay.document.library.kernel.util.DLUtil.getDownloadURL;

/**
 * @author Alejandro Hernández
 */
@Component(immediate = true)
public class WorkshopHelperImpl implements WorkshopHelper {

    @Override
    public String getLogoURL(Organization organization) {
        return String.format(
            "%s/organization_logo?img_id=%s&t=%s", _portal.getPathImage(), String.valueOf(organization.getLogoId()),
            WebServerServletTokenUtil.getToken(organization.getLogoId()));
    }

    @Override
    public ServiceContext getRecipeServiceContext(long userId, long groupId, String category, List<String> assetTags) {
        ServiceContext serviceContext = new ServiceContext();

        serviceContext.setScopeGroupId(groupId);

        serviceContext.setCreateDate(new Date());
        serviceContext.setModifiedDate(new Date());

        AssetVocabulary mealsVocabulary = Try.of(() -> _assetVocabularyLocalService.getGroupVocabulary(groupId, "Meals"))
            .getOrNull();

        if (mealsVocabulary != null) {
            Try.of(
                () -> _assetCategoryLocalService.fetchCategory(
                    groupId, 0, category, mealsVocabulary.getVocabularyId())
            ).filter(
                Validator::isNotNull
            ).recoverWith(
                __ -> Try.of(
                    () -> _assetCategoryLocalService.addCategory(
                        userId, groupId, category, mealsVocabulary.getVocabularyId(),
                        new ServiceContext()))
            ).onSuccess(
                assetCategory -> serviceContext.setAssetCategoryIds(new long[]{assetCategory.getCategoryId()})
            );
        }

        serviceContext.setAssetTagNames(assetTags.toArray(new String[0]));

        return serviceContext;
    }

    @Override
    public FileEntry createRecipeImage(
        long userId, long groupId, String imageURL) {

        Folder folder = Try.of(() -> _dlAppLocalService.getFolder(groupId, 0, "Recipes"))
            .getOrNull();

        UUID uuid = UUID.randomUUID();

        String sourceFileName = uuid.toString() + ".jpeg";

        return Try.withResources(() -> new URL(imageURL).openStream())
            .of(FileUtil::getBytes)
            .mapTry(bytes -> _dlAppLocalService.addFileEntry(
                userId, folder.getGroupId(), folder.getFolderId(), sourceFileName, "image/jpeg", bytes,
                new ServiceContext()))
            .getOrNull();
    }

    @Override
    public Long getChefId(Organization organization) {
        return Option.of(_roleLocalService.fetchRole(organization.getCompanyId(), "Chef"))
            .map(Role::getRoleId)
            .map(roleId -> _userGroupRoleLocalService.getUserGroupRolesByGroupAndRole(organization.getGroupId(), roleId))
            .filter(userGroupRoles -> userGroupRoles.size() > 0)
            .map(list -> list.get(0))
            .map(UserGroupRole::getUserId)
            .getOrNull();
    }

    @Override
    public String getPortraitURL(User user) {
        String pathImage = _portal.getPathImage();

        return Try.of(() -> Tuple.of(user.isMale(), user.getPortraitId(), user.getUserUuid()))
            .mapTry(tuple -> UserConstants.getPortraitURL(pathImage, tuple._1, tuple._2, tuple._3))
            .getOrNull();
    }

    @Override
    public String getCookTime(Recipe recipe) {
        return String.format(
            "PT%dH%dM", recipe.getHoursToMake(), recipe.getMinutesToMake());
    }

    @Override
    public List<String> getRecipeAssetTags(Recipe recipe) {
        return _assetTagLocalService.getTags(Recipe.class.getName(), recipe.getRecipeId())
            .stream()
            .map(AssetTag::getName)
            .collect(Collectors.toList());
    }

    @Override
    public String getCategory(Recipe recipe) {
        return _assetCategoryLocalService.getCategories(Recipe.class.getName(), recipe.getRecipeId())
            .stream()
            .findFirst()
            .map(AssetCategory::getName)
            .orElse(null);
    }

    @Override
    public String getImageURL(Recipe recipe) {
        return Try.of(() -> _dlAppLocalService.getFileEntry(recipe.getImageFileEntryId()))
            .mapTry(fileEntry -> getDownloadURL(fileEntry, fileEntry.getFileVersion(), null, "", false, false))
            .getOrNull();
    }

    @Reference
    private UserGroupRoleLocalService _userGroupRoleLocalService;
    @Reference
    private Portal _portal;
    @Reference
    private RoleLocalService _roleLocalService;
    @Reference
    private AssetTagLocalService _assetTagLocalService;
    @Reference
    private AssetCategoryLocalService _assetCategoryLocalService;
    @Reference
    private DLAppLocalService _dlAppLocalService;
    @Reference
    private AssetVocabularyLocalService _assetVocabularyLocalService;

}