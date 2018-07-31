import { Collection } from '../../collection';
import { restaurantConverter } from './restaurant-converter';
import { recipeConverter } from './recipe-converter';

export function collectionConverter(thing: any): Collection<any> {
const {member = [], numberOfItems, totalItems} = thing.attributes;
let items = [];

console.log(thing);
if (member.length > 0) {
	if (member[0].thing.types.includes('Restaurant')) {
	  items = member.map(relation => relation.thing).map(restaurantConverter);
	} else if (member[0].thing.types.includes('Recipe')) {
	  items = member.map(relation => relation.thing).map(recipeConverter);
	}
}

return new Collection(thing.id, items, numberOfItems, totalItems);
}