import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RestaurantComponent} from '../restaurant/restaurant.component';
import { RecipesComponent } from '../recipes/recipes.component';
import { AuthGuard } from './auth-guard';
import { LoginGuard } from './login-guard';
import { LoginComponent } from '../login/login.component';
import { RecipeDetailComponent } from '../recipe-detail/recipe-detail.component';

const routes: Routes = [
{ path: 'restaurants', component: RestaurantComponent, canActivate: [AuthGuard]},
{ path: '', redirectTo: '/restaurants', pathMatch: 'full'},
{ path: 'recipes/:id', component:  RecipesComponent, canActivate: [AuthGuard]},
{ path: 'recipe/:id', component:  RecipeDetailComponent, canActivate: [AuthGuard]},
{ path: 'login', component:  LoginComponent, canActivate: [LoginGuard]}
];

@NgModule({
imports: [ RouterModule.forRoot(routes)],
exports: [ RouterModule ]
})
export class AppRoutingModule { }