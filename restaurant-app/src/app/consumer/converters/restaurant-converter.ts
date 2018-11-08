import { Restaurant } from '../../restaurant';

export function restaurantConverter(thing: any): Restaurant {
const {name, logo, recipes, location} = thing.attributes;
return new Restaurant(thing.id, name, logo, recipes, location);
}