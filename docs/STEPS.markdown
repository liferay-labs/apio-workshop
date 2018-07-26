# Steps for the workshop

Add missing gradle dependencies to `liferay-recipes-rest` module:

```gradle
compileOnly group: "biz.aQute.bnd", name: "biz.aQute.bndlib", version: "3.1.0"
compileOnly group: "com.liferay", name: "com.liferay.apio.architect.api", version: "1.3.0"
compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "3.0.0"
compileOnly group: "org.osgi", name: "org.osgi.core", version: "6.0.0"
compileOnly project(":liferay-recipes-api")
```

Rename resource to `RestaurantCollectionResource` and implement `CollectionResource`:

```java
public class RestaurantCollectionResource implements CollectionResource<> {
}
```

Fill up the `CollectionResource`'s type params:

```java
public class RestaurantCollectionResource implements 
	CollectionResource<Organization, Long, ?> {
}
```

We need an `OrganizationIdentifier`:

```java
public interface OrganizationIdentifier extends Identifier<Long> {
}
```

Use `OrganizationIdentifier` and implement interface:

```java
public class RestaurantCollectionResource implements
	CollectionResource<Organization, Long, OrganizationIdentifier> {
	
	@Override
	public String getName() {
		return null;
	}

	@Override
	public Representor<Organization> representor(
		Representor.Builder<Organization, Long> builder) {
		
		return null;
	}

	@Override
	public CollectionRoutes<Organization, Long> collectionRoutes(
		CollectionRoutes.Builder<Organization, Long> builder) {
		
		return null;
	}

	@Override
	public ItemRoutes<Organization, Long> itemRoutes(
		ItemRoutes.Builder<Organization, Long> builder) {
		
		return null;
	}
}
```

Return a name in `getName`:

```java
return "organization";
```

Expose the class as a an OSGi `Component`:

```java
@Component
public class RestaurantCollectionResource
```

Inject `OrganizationService`:

```java
@Reference
private OrganizationService _organizationService;
```

Add method to retrieve an `Organization` to `itemRoutes`:

```java
return builder.addGetter(
	_organizationService::getOrganization
).build();
```

Add minimum information to the `representor` method:

```java
return builder.types(
	"Organization"
).identifier(
	Organization::getOrganizationId
).build();
```

Add method to retrieve a page of `Organization`:

```java
return builder.addGetter(
	this::_getPageItems
).build();
```

Create `_getPageItems` method:

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

Inject `OrganizationLocalService`:

```java
@Reference
private OrganizationLocalService _organizationLocalService;
```

Get user specific organizations:

```java
List<Organization> sites =
	_organizationLocalService.getUserOrganizations(
		userId, pagination.getStartPosition(),
		pagination.getEndPosition());

int count = _organizationLocalService.getUserOrganizationsCount(
	userId);

return new PageItems<>(sites, count);
```

Create `CurrentUser` class:

```java
public class CurrentUser extends UserWrapper {

	public CurrentUser(User user) {
		super(user);
	}

}
```

Update gradle dependencies:

```gradle
compileOnly group: "javax.portlet", name: "portlet-api", version: "3.0.0"
compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"
```

Create `Provider` for `CurrentUser`:

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

Update `_getPageItems` signature:

```java
private PageItems<Organization> _getPageItems(
	Pagination pagination, Long userId) {
```

Create second `_getPageItems` that receives `CurrentUser`:

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

In order to use the second `_getPageItems` in the `collectionRoutes` we need to add the provided class:

```java
this::_getPageItems, CurrentUser.class
```

Complete `Representor` so we can see something on the response:

```java
).addString(
    "name", Organization::getName
```

Add `Organization` logo URL with `addRelativeURL`:

```java
).addRelativeURL(
    "logo", this::_getLogoURL
```

Create method for obtaining an `Organization` logo URL:

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

Let's model restaurants instead of organizations:

```java
return builder.types(
	"Restaurant"
```

Don't forget to change the API name:

```java
public String getName() {
	return "restaurant";
}
```

And rename `OrganizationIdentifier` to `RestaurantIdentifier`:

```java
public interface RestaurantIdentifier extends Identifier<Long> {
}
```

Create `PersonIdentifier`:

```java
public interface PersonIdentifier extends Identifier<Long> {
}
```

Create `PersonItemResource`:

```java
@Component
public class PersonItemResource
	implements ItemResource<User, Long, PersonIdentifier> {
}
```

Implement interface:

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

Inject `UserService`:

```java
@Reference
private UserService _userService;
```

Add method for obtaining persons:

```java
return builder.addGetter(
	_userService::getUserById
).build();
```

Update `Person` representor:

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

Add person gender to the representor:

```java
).addString(
	"gender", PersonItemResource::_getGender
```

Create method `_getGender`:

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

Add person image to the representor:

```java
).addRelativeURL(
	"image", this::_getPortraitURL
```

Add method `_getPortraitURL`:

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

Add linked model to `Restaurant` employee:

```java
).addLinkedModel(
	"employee", PersonIdentifier.class, this::_getChefId
```

Create `_getChefId` method:

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

Add nested field `location` to add the restaurant's address:

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

Add field for exposing address region:

```java
).addString(
	"addressRegion", this::_getRegion
```

Create `_getRegion` method:

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

Add field for exposing address country:

```java
).addString(
	"addressCountry", this::_getCountry
```

Create `_getCountry` method:

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

We can make this field locatable:

```java
).addLocalizedStringByLocale(
	"addressCountry", this::_getCountry
```

And then update `_getCountry` method:

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

Create `RecipeIdentifier`:

```java
public interface RecipeIdentifier extends Identifier<Long> {
}
```

Create `RecipesItemResource`:

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

Give it a name:

```java
return "recipe";
```

Inject `RecipeService`:

```java
@Reference
private RecipeService _recipeService;
```

Add method for obtaining a single recipe to `itemRoutes`:

```java
return builder.addGetter(
	_recipeService::getRecipe
).build();
```

Complete the minimum `Representor`:

```java
return builder.types(
	"Recipe"
).identifier(
	Recipe::getRecipeId
).build();
```

Add a bidirectional relation between restaurants and recipes:

```java
).addBidirectionalModel(
	"restaurant", "recipes", RestaurantIdentifier.class,
	this::_getOrganizationId
```

Create `_getOrganizationId` method:

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

Create `RecipeNestedCollectionRouter`:

```java
@Component
public class RecipeNestedCollectionRouter implements 
	NestedCollectionRouter
		<Recipe, Long, RecipeIdentifier, Long, RestaurantIdentifier> {
```

Implement interface:

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

Add method for obtaining a page of a restaurant recipes:

```java
return builder.addGetter(
	this::_getPageItems
).build();
```

Implement `_getPageItems` method:

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

Merge `RecipeItemResource` and `RecipeNestedCollectionRouter` into `RecipeNestedCollectionResource`:

```java
@Component
public class RecipeNestedCollectionResource
	implements NestedCollectionResource
		<Recipe, Long, RecipeIdentifier, Long, RestaurantIdentifier> {

	@Override
	public String getName() {
		return "recipe";
	}

	@Override
	public ItemRoutes<Recipe, Long> itemRoutes(
		ItemRoutes.Builder<Recipe, Long> builder) {

		return builder.addGetter(
			_recipeService::getRecipe
		).build();
	}

	@Override
	public Representor<Recipe> representor(
		Representor.Builder<Recipe, Long> builder) {

		return builder.types(
			"Recipe"
		).identifier(
			Recipe::getRecipeId
		).addBidirectionalModel(
			"restaurant", "recipes", RestaurantIdentifier.class,
			this::_getOrganizationId
		).build();
	}

	@Override
	public NestedCollectionRoutes<Recipe, Long, Long> collectionRoutes(
		NestedCollectionRoutes.Builder<Recipe, Long, Long> builder) {

		return builder.addGetter(
			this::_getPageItems
		).build();
	}

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

	@Reference
	private RecipeService _recipeService;

	@Reference
	private OrganizationService _organizationService;

}
```

Add "easy" fields to the `Recipe` representor:

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

Add nested field for exposing youtube video information:

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

Add information about the recipe's category:

```java
).addString(
	"recipeCategory", this::_getCategory
```

Create `_getCategory` method:

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

Add information about the recipe's cook time:

```java
).addString(
    "cookTime", this::_getCookTime
```

Create `_getCookTime` method:

```java
private String _getCookTime(Recipe recipe) {
	return String.format(
		"PT%dH%dM", recipe.getHoursToMake(), recipe.getMinutesToMake());
}
```

Add information about the recipe's tags:

```java
).addStringList(
	"keywords", this::_getRecipeAssetTags
```

Create `_getRecipeAssetTags` method:

```java
private List<String> _getRecipeAssetTags(Recipe recipe) {
	List<AssetTag> assetTags = _assetTagLocalService.getTags(
		Recipe.class.getName(), recipe.getRecipeId());

	return ListUtil.toList(assetTags, AssetTag::getName);
}

@Reference
private AssetTagLocalService _assetTagLocalService;
```

Add recipe's image to the representor:

```java
).addBinary(
	"image", this::_getImageBinaryFile
```

Create `_getImageBinaryFile`:

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