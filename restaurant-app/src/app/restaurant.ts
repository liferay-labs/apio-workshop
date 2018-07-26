export class Restaurant {
id: string;
name: string;
logo: string;
recipes: string;
location: object;

constructor(id, name, logo, recipes, location) {
	this.id = encodeURIComponent(id);
	this.name = name;
	this.logo = logo;
	this.recipes = encodeURIComponent(recipes.id);
	this.location = location;
}
}