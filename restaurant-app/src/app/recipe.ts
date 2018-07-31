export class Recipe {
id: string;
name: string;
category: string;
origin: string;
cookTime: string;
instructions: Array<string>;
ingredients: Array<string>;
keywords: Array<string>;
video: string;
logo: string;

constructor(id, name, category, origin, cookTime,
	instructions, ingredients, keywords, video, logo) {

	this.id = encodeURIComponent(id);
	this.name = name;
	this.category = category;
	this.origin = origin;
	this.cookTime = cookTime;
	this.instructions = instructions;
	this.ingredients = ingredients;
	this.keywords = keywords;
	this.video = video;
	this.logo = logo;
}
}