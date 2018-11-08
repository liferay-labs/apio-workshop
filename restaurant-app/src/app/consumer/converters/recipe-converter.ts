import { Recipe } from '../../recipe';

export function recipeConverter(thing: any): Recipe {
const {name, image, video, recipeCuisine, recipeCategory, cookTime,
	recipeIngredient, keywords, recipeInstructions} = thing.attributes;

const [_, hours, minutes] = /PT(\d+)H(\d*)M/g.exec(cookTime);

const videoUrl = new URL(video.url);

const videoEmbeddedUrl = 'https://www.youtube.com/embed/' + videoUrl.searchParams.get('v');

const cookTimeString = `${hours} hours ${minutes} minutes`;

return new Recipe(thing.id, name, recipeCategory, recipeCuisine, cookTimeString, recipeInstructions,
	recipeIngredient, keywords, videoEmbeddedUrl, image);
}