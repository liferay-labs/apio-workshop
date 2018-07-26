import { Component, OnInit } from '@angular/core';
import { Recipe } from '../recipe';
import { ActivatedRoute, Router } from '../../../node_modules/@angular/router';
import { ConsumerService } from '../consumer/consumer.service';

@Component({
selector: 'app-recipes',
templateUrl: './recipes.component.html',
styleUrls: ['./recipes.component.css']
})
export class RecipesComponent implements OnInit {

recipes: Array<Recipe> = [];
loading = true;

constructor(private activatedRoute: ActivatedRoute, private consumer: ConsumerService, private router: Router) { }

ngOnInit() {
	const id = this.activatedRoute.snapshot.paramMap.get('id');

	this.consumer.fetchResource(id).then(collection => {
	  this.loading = false;
	  this.recipes = collection.items;
	});
}

onClick(recipe) {
	this.router.navigate([`recipe/${recipe.id}`]);
}

}