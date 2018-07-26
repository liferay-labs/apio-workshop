# Steps for the workshop

---


8 - Inject `OrganizationService`:

```java
@Reference
private OrganizationService _organizationService;
```

---

9 - Add method to retrieve an `Organization` to `itemRoutes`:

```java
return builder.addGetter(
	_organizationService::getOrganization
).build();
```

---

10 - Add minimum information to the `representor` method:

```java
return builder.types(
	"Organization"
).identifier(
	Organization::getOrganizationId
).build();
```

---

11 - Add method to retrieve a page of `Organization`:

```java
return builder.addGetter(
	this::_getPageItems
).build();
```

---

12 - Create `_getPageItems` method:

```java
private PageItems<Organization> _getPageItems(Pagination pagination) {
	List<Organization> organizations =
		_organizationService.getOrganizations(
			20099, 0, pagination.getStartPosition(),
			pagination.getEndPosition());

	int count = _organizationService.getOrganizationsCount(20099, 0);

	return new PageItems<>(organizations, count);
}
```

---

13 - Inject `OrganizationLocalService`:

```java
@Reference
private OrganizationLocalService _organizationLocalService;
```

---

14 - Get user specific organizations:

```java
List<Organization> sites =
	_organizationLocalService.getUserOrganizations(
		userId, pagination.getStartPosition(),
		pagination.getEndPosition());

int count = _organizationLocalService.getUserOrganizationsCount(
	userId);

return new PageItems<>(sites, count);
```

---

15 - Create `CurrentUser` class:

```java
public class CurrentUser extends UserWrapper {

	public CurrentUser(User user) {
		super(user);
	}

}
```

---

16 - Update gradle dependencies:

```gradle
compileOnly group: "javax.portlet", name: "portlet-api", version: "3.0.0"
compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"
```

---

17 - Create `Provider` for `CurrentUser`:

```java
@Component
public class CurrentUserProvider implements Provider<CurrentUser> {

	@Override
	public CurrentUser createContext(HttpServletRequest httpServletRequest) {
		return Try.fromFallible(
			() -> _portal.getUser(httpServletRequest)
		).filter(
			user -> !user.isDefaultUser()
		).map(
			CurrentUser::new
		).orElse(
			null
		);
	}

	@Reference
	private Portal _portal;

}
```

---

18 - Update `_getPageItems` signature:

```java
private PageItems<Organization> _getPageItems(
	Pagination pagination, Long userId) {
```

---

19 - Create second `_getPageItems` that receives `CurrentUser`:

```java
private PageItems<Organization> _getPageItems(
	Pagination pagination, CurrentUser currentUser) {

	return Optional.ofNullable(
		currentUser
	).map(
		CurrentUser::getUserId
	).map(
		userId -> _getPageItems(pagination, userId)
	).orElseGet(
		() -> new PageItems<>(Collections.emptyList(), 0)
	);
}
```

---

20 - In order to use the second `_getPageItems` in the `collectionRoutes` we need to add the provided class:

```java
this::_getPageItems, CurrentUser.class
```

---

21 - Complete `Representor` so we can see something on the response:

```java
).addString(
    "name", Organization::getName
```

---

22 - Add `Organization` logo URL with `addRelativeURL`:

```java
).addRelativeURL(
    "logo", this::_getLogoURL
```

---

23 - Create method for obtaining an `Organization` logo URL:

```java
private String _getLogoURL(Organization organization) {
	return Try.success(
		organization.getLogoId()
	).filter(
		logoId -> logoId != 0
	).map(
		logoId -> String.format(
			"%s/organization_logo?img_id=%s&t=%s",
			_portal.getPathImage(), String.valueOf(logoId),
			WebServerServletTokenUtil.getToken(logoId))
	).orElse(
		null
	);
}

@Reference
private Portal _portal;
```

---

24 - Let's model restaurants instead of organizations:

```java
return builder.types(
	"Restaurant"
```

---

25 - Don't forget to change the API name:

```java
public String getName() {
	return "restaurant";
}
```

---

26 - And rename `OrganizationIdentifier` to `RestaurantIdentifier`:

```java
public interface RestaurantIdentifier extends Identifier<Long> {
}
```

---

27 - Create `PersonIdentifier`:

```java
public interface PersonIdentifier extends Identifier<Long> {
}
```

---

28 - Create `PersonItemResource`:

```java
@Component
public class PersonItemResource
	implements ItemResource<User, Long, PersonIdentifier> {
}
```

---

29 - Implement interface:

```java
@Override
public String getName() {
	return null;
}

@Override
public ItemRoutes<User, Long> itemRoutes(
	ItemRoutes.Builder<User, Long> builder) {

	return null;
}

@Override
public Representor<User> representor(
	Representor.Builder<User, Long> builder) {

	return null;
}
```

---

30 - Inject `UserService`:

```java
@Reference
private UserService _userService;
```

---

31 - Add method for obtaining persons:

```java
return builder.addGetter(
	_userService::getUserById
).build();
```

---

32 - Update `Person` representor:

```java
return builder.types(
	"Person"
).identifier(
	User::getUserId
).addString(
	"alternateName", User::getScreenName
).addString(
	"email", User::getEmailAddress
).addString(
	"familyName", User::getLastName
).addString(
	"givenName", User::getFirstName
).addString(
	"jobTitle", User::getJobTitle
).addString(
	"name", User::getFullName
).build();
```

---

33 - Add person gender to the representor:

```java
).addString(
	"gender", PersonItemResource::_getGender
```

---

34 - Create method `_getGender`:

```java
private static String _getGender(User user) {
	return Try.fromFallible(
		user::isMale
	).map(
		male -> male ? "male" : "female"
	).orElse(
		null
	);
}
```

---

35 - Add person image to the representor:

```java
).addRelativeURL(
	"image", this::_getPortraitURL
```

---

36 - Add method `_getPortraitURL`:

```java
private String _getPortraitURL(User user) {
	return Try.fromFallible(
		() -> UserConstants.getPortraitURL(
			_portal.getPathImage(), user.isMale(), user.getPortraitId(),
			user.getUserUuid())
	).orElse(
		null
	);
}

@Reference
private Portal _portal;
```

---

37 - Add linked model to `Restaurant` employee:

```java
).addLinkedModel(
	"employee", PersonIdentifier.class, this::_getChefId
```

---

38 - Create `_getChefId` method:

```java
private Long _getChefId(Organization organization) {
    Role role = _roleLocalService.fetchRole(
        organization.getCompanyId(), "Chef");

    if (role == null) {
        return null;
    }

    return Try.fromFallible(
        organization::getGroupId
    ).map(
        groupId ->
            _userGroupRoleLocalService.getUserGroupRolesByGroupAndRole(
                groupId, role.getRoleId())
    ).filter(
        userGroupRoles -> userGroupRoles.size() > 0
    ).map(
        userGroupRoles -> userGroupRoles.get(0)
    ).map(
        UserGroupRoleModel::getUserId
    ).orElse(
        null
    );
}

@Reference
private RoleLocalService _roleLocalService;

@Reference
private UserGroupRoleLocalService _userGroupRoleLocalService;
```

---

39 - Add nested field `location` to add the restaurant's address:

```java
).addNested(
    "location", Organization::getAddress,
    nestedBuilder -> nestedBuilder.types(
        "PostalAddress"
    ).addString(
        "streetAddress", Address::getStreet1
    ).addString(
        "postalCode", Address::getZip
    ).build()
```

---

40 - Add field for exposing address region:

```java
).addString(
	"addressRegion", this::_getRegion
```

---

41 - Create `_getRegion` method:

```java
private String _getRegion(Address address) {
	return Try.success(
		address.getRegion()
	).map(
		Region::getName
	).orElse(
		null
	);
}
```

---

42 - Add field for exposing address country:

```java
).addString(
	"addressCountry", this::_getCountry
```

---

43 - Create `_getCountry` method:

```java
private String _getCountry(Address address) {
	return Try.success(
		address.getCountry()
	).map(
		Country::getName
	).orElse(
		null
	);
}
```

---

44 - We can make this field locatable:

```java
).addLocalizedStringByLocale(
	"addressCountry", this::_getCountry
```

---

45 - And then update `_getCountry` method:

```java
private String _getCountry(Address address, Locale locale) {
	return Try.success(
		address.getCountry()
	).map(
		country -> country.getName(locale)
	).orElse(
		null
	);
}
```

---

46 - Create `RecipeIdentifier`:

```java
public interface RecipeIdentifier extends Identifier<Long> {
}
```

---

47 - Create `RecipesItemResource`:

```java
@Component
public class RecipesItemResource
	implements ItemResource<Recipe, Long, RecipeIdentifier> {

	@Override
	public String getName() {
		return null;
	}

	@Override
	public ItemRoutes<Recipe, Long> itemRoutes(
		ItemRoutes.Builder<Recipe, Long> builder) {

		return null;
	}

	@Override
	public Representor<Recipe> representor(
		Representor.Builder<Recipe, Long> builder) {

		return null;
	}

}
```

---

48 - Give it a name:

```java
return "recipe";
```

---

49 - Inject `RecipeService`:

```java
@Reference
private RecipeService _recipeService;
```

---

50 - Add method for obtaining a single recipe to `itemRoutes`:

```java
return builder.addGetter(
	_recipeService::getRecipe
).build();
```

---

51 - Complete the minimum `Representor`:

```java
return builder.types(
	"Recipe"
).identifier(
	Recipe::getRecipeId
).build();
```

---

52 - Add a bidirectional relation between restaurants and recipes:

```java
).addBidirectionalModel(
	"restaurant", "recipes", RestaurantIdentifier.class,
	this::_getOrganizationId
```

---

53 - Create `_getOrganizationId` method:

```java
private Long _getOrganizationId(Recipe recipe) {
	return Try.fromFallible(
		() -> _groupLocalService.getGroup(recipe.getGroupId())
	).map(
		Group::getOrganizationId
	).orElse(
		null
	);
}

@Reference
private GroupLocalService _groupLocalService;
```

---

54 - Create `RecipeNestedCollectionRouter`:

```java
@Component
public class RecipeNestedCollectionRouter implements
	NestedCollectionRouter
		<Recipe, Long, RecipeIdentifier, Long, RestaurantIdentifier> {
```

---

55 - Implement interface:

```java
@Component
public class RecipeNestedCollectionRouter implements
	NestedCollectionRouter
		<Recipe, Long, RecipeIdentifier, Long, RestaurantIdentifier> {

	@Override
	public NestedCollectionRoutes<Recipe, Long, Long> collectionRoutes(
		Builder<Recipe, Long, Long> builder) {

		return null;
	}

}
```

---

56 - Add method for obtaining a page of a restaurant recipes:

```java
return builder.addGetter(
	this::_getPageItems
).build();
```

---

57 - Implement `_getPageItems` method:

```java
private PageItems<Recipe> _getPageItems(
	Pagination pagination, long organizationId)
	throws PortalException {

	Organization organization = _organizationService.getOrganization(
		organizationId);

	long groupId = organization.getGroupId();

	List<Recipe> recipes = _recipeService.getRecipesByGroupId(
		groupId, pagination.getStartPosition(),
		pagination.getEndPosition());

	int count = _recipeService.getRecipesCountByGroupId(groupId);

	return new PageItems<>(recipes, count);
}
```

---

58 - Merge `RecipeItemResource` and `RecipeNestedCollectionRouter` into `RecipeNestedCollectionResource`:

```java
@Component
public class RecipeNestedCollectionResource
	implements NestedCollectionResource
		<Recipe, Long, RecipeIdentifier, Long, RestaurantIdentifier> {

}
```

---

59 - Add "easy" fields to the `Recipe` representor:

```java
).addDate(
	"dateCreated", Recipe::getCreateDate
).addDate(
	"dateModified", Recipe::getModifiedDate
).addDate(
	"datePublished", Recipe::getPublishedDate
).addString(
	"name", Recipe::getName
).addString(
	"recipeCuisine", Recipe::getRegion
).addStringList(
	"recipeIngredient", Recipe::getIngredients
).addStringList(
	"recipeInstructions", Recipe::getSteps
).addLinkedModel(
	"creator", PersonIdentifier.class, Recipe::getUserId
```

---

60 - Add nested field for exposing youtube video information:

```java
).addNested(
    "video", Recipe::getVideoURL,
    nestedBuilder -> nestedBuilder.types(
        "VideoObject"
    ).addString(
        "caption", __ -> "Recipe's Youtube video"
    ).addString(
        "url", Function.identity()
    ).build()
```

---

61 - Add information about the recipe's category:

```java
).addString(
	"recipeCategory", this::_getCategory
```

---

62 - Create `_getCategory` method:

```java
private String _getCategory(Recipe recipe) {
	List<AssetCategory> categories =
		_assetCategoryLocalService.getCategories(
			Recipe.class.getName(), recipe.getRecipeId());

	if (categories.isEmpty()) {
		return null;
	}

	AssetCategory assetCategory = categories.get(0);

	return assetCategory.getName();
}

@Reference
private AssetCategoryLocalService _assetCategoryLocalService;
```

---

63 - Add information about the recipe's cook time:

```java
).addString(
    "cookTime", this::_getCookTime
```

---

64 - Create `_getCookTime` method:

```java
private String _getCookTime(Recipe recipe) {
	return String.format(
		"PT%dH%dM", recipe.getHoursToMake(), recipe.getMinutesToMake());
}
```

---

65 - Add information about the recipe's tags:

```java
).addStringList(
	"keywords", this::_getRecipeAssetTags
```

---

66 - Create `_getRecipeAssetTags` method:

```java
private List<String> _getRecipeAssetTags(Recipe recipe) {
	List<AssetTag> assetTags = _assetTagLocalService.getTags(
		Recipe.class.getName(), recipe.getRecipeId());

	return ListUtil.toList(assetTags, AssetTag::getName);
}

@Reference
private AssetTagLocalService _assetTagLocalService;
```

---

67 - Add recipe's image to the representor:

```java
).addBinary(
	"image", this::_getImageBinaryFile
```

---

68 - Create `_getImageBinaryFile`:

```java
private BinaryFile _getImageBinaryFile(Recipe recipe) {
	return Try.fromFallible(
		() -> _dlAppLocalService.getFileEntry(recipe.getImageFileEntryId())
	).map(
		fileEntry -> new BinaryFile(
			fileEntry.getContentStream(), fileEntry.getSize(),
			fileEntry.getMimeType())
	).orElse(
		null
	);
}

@Reference
private DLAppLocalService _dlAppLocalService;
```