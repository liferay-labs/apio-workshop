import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { RestaurantComponent } from './restaurant/restaurant.component';
import { AppRoutingModule } from './routing/app-routing.module';
import { ClarityModule } from '@clr/angular';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RecipesComponent } from './recipes/recipes.component';
import { LoginComponent } from './login/login.component';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { RecipeDetailComponent } from './recipe-detail/recipe-detail.component';
import { HeaderComponent } from './header/header.component';

@NgModule({
declarations: [
	AppComponent,
	RestaurantComponent,
	RecipesComponent,
	LoginComponent,
	RecipeDetailComponent,
	HeaderComponent
],
imports: [
	BrowserModule,
	AppRoutingModule,
	ClarityModule,
	BrowserAnimationsModule,
	HttpModule,
	FormsModule
],
providers: [],
bootstrap: [AppComponent]
})
export class AppModule { }