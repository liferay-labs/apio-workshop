import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '../../../node_modules/@angular/router';
import { ConsumerService } from '../consumer/consumer.service';
import { Recipe } from '../recipe';
import { DomSanitizer, SafeResourceUrl } from '../../../node_modules/@angular/platform-browser';

@Component({
selector: 'app-recipe-detail',
templateUrl: './recipe-detail.component.html',
styleUrls: ['./recipe-detail.component.css']
})
export class RecipeDetailComponent implements OnInit {

recipe: Recipe;
videoUrl: SafeResourceUrl;

constructor(private activatedRoute: ActivatedRoute, private consumer: ConsumerService, private domSanitizer: DomSanitizer) {

}

ngOnInit() {
	const id = this.activatedRoute.snapshot.paramMap.get('id');

	this.consumer.fetchResource(id).then(recipe => {
	  console.log(recipe);
	  this.videoUrl = this.domSanitizer.bypassSecurityTrustResourceUrl(recipe.video);

	  console.log(this.videoUrl);
	  this.recipe = recipe;
	});
}

}