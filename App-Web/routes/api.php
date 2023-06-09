<?php

use App\Http\Controllers\LeaguesController;
use App\Http\Controllers\StagiaireController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});

Route::get('witherApi/{name}',[LeaguesController::class,'witherApi']);
Route::post('SaveCity',[LeaguesController::class,'SaveCity']);
Route::get('SaveList',[LeaguesController::class,'SaveList']);
Route::delete('delete/{id}',[LeaguesController::class,'delete']);
Route::post('Login',[LeaguesController::class,'Login']);


Route::get('PlayersList/{id}',[LeaguesController::class,'PlayersList']);
Route::get('Team/{id}',[LeaguesController::class,'Team']);
Route::get('ListTeams/{id}',[LeaguesController::class,'ListTeams']);
Route::delete('RemoveFavoriteLeague/{id}/{userId}',[LeaguesController::class,'RemoveFavoriteLeague']);
Route::get('CheckFavorite/{id}/{userId}',[LeaguesController::class,'CheckFavorite']);
Route::get('CheckFavoriteTeam/{id}',[LeaguesController::class,'CheckFavoriteTeam']);
Route::get('ListLeagues',[LeaguesController::class,'ListLeagues']);
Route::get('SearshLeagues/{name}',[LeaguesController::class,'SearshLeagues']);
Route::post('AddToFavoriteLeague',[LeaguesController::class,'AddToFavoriteLeague']);
Route::post('AddToFavoriteTeam',[LeaguesController::class,'AddToFavoriteTeam']);
Route::get('ListFavoriteLeague/{id}',[LeaguesController::class,'ListFavoriteLeague']);
Route::get('Standing/{id}',[LeaguesController::class,'Standing']);



Route::get('listLeague',[LeaguesController::class,'listLeague']);
Route::get('League/{id}',[LeaguesController::class,'League']);
Route::get('LeagueExist/{id}',[LeaguesController::class,'LeagueExist']);
Route::get('RemoveLeague/{id}',[LeaguesController::class,'RemoveLeague']);
Route::get('Delete/{id}',[LeaguesController::class,'Delete']);
Route::post('addToFavorite',[LeaguesController::class,'addToFavorite']);
