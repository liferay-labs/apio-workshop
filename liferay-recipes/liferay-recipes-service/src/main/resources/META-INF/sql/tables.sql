create table APIO_Recipe (
	uuid_ VARCHAR(75) null,
	recipeId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(255) null,
	region VARCHAR(255) null,
	imageFileEntryId LONG,
	publishedDate DATE null,
	hoursToMake INTEGER,
	minutesToMake INTEGER,
	stepsString TEXT null,
	ingredientsString TEXT null,
	videoURL VARCHAR(75) null
);