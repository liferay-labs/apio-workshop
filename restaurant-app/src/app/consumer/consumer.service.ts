import { Injectable } from '@angular/core';
import ApioConsumer from 'apio-consumer';
import {restaurantConverter} from './converters/restaurant-converter';
import {collectionConverter} from './converters/collection-converter';
import {recipeConverter} from './converters/recipe-converter';
import { AuthService } from '../auth-service/auth.service';

@Injectable({
providedIn: 'root'
})
export class ConsumerService {

apioConsumer: any;

constructor(private authService: AuthService) {
	this.apioConsumer = new ApioConsumer({'Authorization': authService.getAuth()});

	this.apioConsumer.addConverter('Restaurant', restaurantConverter);
	this.apioConsumer.addConverter('Recipe', recipeConverter);
	this.apioConsumer.addConverter('Collection', collectionConverter);
}

fetchResource(id, embedded = [], fields = {}): Promise<any> {
	const decodedId = decodeURIComponent(id);
	console.log(decodedId);
	return new Promise((resolve, reject) => {
	  return this.apioConsumer.fetchResource(decodedId, embedded, fields)
		.then(resolve)
		.catch(reject);
	});
}
}