import { Component, OnInit } from '@angular/core';
import { Restaurant } from '../restaurant';
import { ConsumerService } from '../consumer/consumer.service';

@Component({
selector: 'app-restaurant',
templateUrl: './restaurant.component.html',
styleUrls: ['./restaurant.component.css']
})
export class RestaurantComponent implements OnInit {

restaurants: Array<Restaurant> = [];
loading = true;

constructor(private consumer: ConsumerService) {
}

parseThings(members) {
	console.log(members[0].thing);
}

ngOnInit() {
	this.consumer.fetchResource('http://localhost:8080/o/api/p/restaurant')
	  .then(collection => {
		this.loading = false;
		this.restaurants = collection.items;
	  });
}

}