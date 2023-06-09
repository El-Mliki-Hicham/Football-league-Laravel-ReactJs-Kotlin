<?php

namespace App\Http\Controllers;

use App\Models\FavoriteLeagues;
use App\Models\FavoriteTeam;
use App\Models\Leagues;
use App\Models\Teams;
use App\Models\User;
use App\Models\Weather;
use Illuminate\Http\Request;
use Mockery\Undefined;

class LeaguesController extends Controller
{
    function  listLeague(){
        $list = FavoriteLeagues::all();
        return $list;
    }

  function  League($id){
        $list = FavoriteLeagues::where("id",$id)->get();
        return $list;
    }
  function  LeagueExist($id){
        $list = FavoriteLeagues::where("id",$id)->get();
        if(!empty($list[0])){
            return ['message'=>"true"];
        }else{
            return ['message'=>"false"];

        }
        // dd($list);
        // return $list;
    }

   function addToFavorite(Request $request){

        $league = new FavoriteLeagues();
        $league->id   = $request->id  ;
        $league->name = $request->name;
        $league->type = $request->type;
        $league->logo = $request->logo;
        $league->user_id = 1;

        $league->save();
   }


    function Login(Request $request)
    {

        $user = User::select("id", "name", "password", "email")->where([
            ["email", $request->email],
            ["password", $request->password]
        ])->first();

        if ($user) {

            return $user;
        } else {
            return false;
        }
    }


    function ListTeams($id)
    {
        $teamTable =  Teams::where("league_id", $id)->get();
        if ($teamTable->isNotEmpty()) {

            return $teamTable;
        } else {

            $client = new \GuzzleHttp\Client([
                'verify' => false,
                'headers' => [
                    'x-rapidapi-host' => 'v3.football.api-sports.io',
                    'x-rapidapi-key' => '8cc7b490ea845f35b524aef59d7e1782'
                ]
            ]);

            $response = $client->request('GET', 'https://v3.football.api-sports.io/teams', [
                'query' => [
                    'season' => '2022',
                    'league' => $id
                ]
            ]);
            $data = json_decode($response->getBody(), true);

            foreach ($data["response"] as $value) {
                # code...
                $team = $value["team"];
                $team += $value["venue"];
                $team['stadium'] = $value["venue"]["name"];
                $team['league_id'] = $id;

                Teams::create($team);
            }

            $teams =  Teams::where("league_id", $id)->get();

            return  $teams;
        }
    }



    function Standing($id)
    {
        // $teamTable =  Teams::where("league_id",$id)->get();
        // if($teamTable->isNotEmpty()){

        //     return $teamTable;

        // }else{

        $client = new \GuzzleHttp\Client([
            'verify' => false,
            'headers' => [
                'x-rapidapi-host' => 'v3.football.api-sports.io',
                'x-rapidapi-key' => '8cc7b490ea845f35b524aef59d7e1782'
            ]
        ]);

        $response = $client->request('GET', 'https://v3.football.api-sports.io/standings', [
            'query' => [
                'season' => '2022',
                'league' => $id
            ]
        ]);

        $array = array();
        $data = json_decode($response->getBody(), true);
        foreach ($data["response"] as $value) {
            $standings = $value["league"]["standings"];
            foreach ($standings as $listStanding) {
                # code...
                foreach ($listStanding as $standingValue) {

                    $standingsData["rank"] =  $standingValue["rank"];
                    $standingsData["team_name"] =  $standingValue["team"]['name'];
                    $standingsData["team_logo"] =  $standingValue["team"]['logo'];
                    $standingsData["team_id"] =  $standingValue["team"]['id'];
                    $standingsData["points"] =  $standingValue["points"];
                    $standingsData["played"] =  $standingValue["all"]["played"];
                    $standingsData["win"] =  $standingValue["all"]["win"];
                    $standingsData["draw"] =  $standingValue["all"]["draw"];
                    $standingsData["lose"] =  $standingValue["all"]["lose"];
                    $standingsData["for"] =  $standingValue["all"]["goals"]["for"];
                    $standingsData["against"] =  $standingValue["all"]["goals"]["against"];
                    array_push($array, $standingsData);
                }
            }
        }
        return $array;
    }

    // players
    function PlayersList($id)
    {

        $client = new \GuzzleHttp\Client([
            'verify' => false,
            'headers' => [
                'x-rapidapi-host' => 'v3.football.api-sports.io',
                'x-rapidapi-key' => '8cc7b490ea845f35b524aef59d7e1782'
            ]
        ]);

        $response = $client->request('GET', 'https://v3.football.api-sports.io/players/squads', [
            'query' => [
                'team' => $id
            ]
        ]);

        $array = array();
        $data = json_decode($response->getBody(), true);
        foreach ($data["response"] as $value) {

            array_push($array, $value["players"]);
        }

        return $array[0];
    }




    function ListLeagues()
    {
        $Leagues =  Leagues::where("type", "league")
            ->get();

        if ($Leagues->isNotEmpty()) {

            return $Leagues;
        } else {

            $client = new \GuzzleHttp\Client([
                'verify' => false,
                'headers' => [
                    'x-rapidapi-host' => 'v3.football.api-sports.io',
                    'x-rapidapi-key' => '8cc7b490ea845f35b524aef59d7e1782'
                ]
            ]);

            $response = $client->request('GET', 'https://v3.football.api-sports.io/leagues');
            $data = json_decode($response->getBody(), true);
            $firstTwenty = array_slice($data["response"], 0, 20);
            foreach ($firstTwenty as $value) {
                Leagues::create($value["league"]);
            }
            $listLeague  = Leagues::select("*")
                ->where("type", "league")
                ->get();
            // dd($listLeague);

            return  $listLeague;
        }
    }
    function SearshLeagues($name)
    {

        $client = new \GuzzleHttp\Client([
            'verify' => false,
            'headers' => [
                'x-rapidapi-host' => 'v3.football.api-sports.io',
                'x-rapidapi-key' => '8cc7b490ea845f35b524aef59d7e1782'
            ]
        ]);

        $response = $client->request('GET', "https://v3.football.api-sports.io/leagues?name=" . $name . "&season=2022");
        $data = json_decode($response->getBody(), true);
        $array = array();
        foreach ($data['response'] as $value) {
            array_push($array, $value["league"]);
        }

        return $array;
    }

    function  ListFavoriteLeague($id)
    {
        $FavoriteLeagues = FavoriteLeagues::where('user_id', $id)->get();
        return $FavoriteLeagues;
    }
    function  FavoriteLeague($id)
    {
        $list = Leagues::where("id", $id)->get();
        return $list;
    }
    function  CheckFavoriteTeam($id)
    {
        $favoriteLeague = FavoriteTeam::where('id', $id)->exists();
        return $favoriteLeague;
    }
    function  CheckFavorite($id, $userId)
    {
        $favoriteLeague = FavoriteLeagues::where([['id', $id], ["user_id", $userId]])->exists();
        return $favoriteLeague;
    }
    function  RemoveFavoriteLeague($id, $userId)
    {
        FavoriteLeagues::where([['id', $id], ["user_id", $userId]])->delete();
        $listAll = FavoriteLeagues::all();
        return $listAll;
    }
    function  RemoveLeague($id)
    {
        FavoriteLeagues::where('id', $id)->delete();
        $listAll = FavoriteLeagues::all();
        return $listAll;
    }

    function AddToFavoriteLeague(Request $request)
    {

        $league = new FavoriteLeagues();
        $league->id   = $request->id;
        $league->name = $request->name;
        $league->type = $request->type;
        $league->logo = $request->logo;
        $league->user_id = $request->user_id;
        $league->save();
        return $league;
    }

    function Team($id)
    {
        $team = Teams::find($id);
        return $team;
    }

    function AddToFavoriteTeam(Request $request)
    {

        $teamData = Teams::find($request->id);

        $team = FavoriteTeam::create($teamData->toArray());

        return 1;
    }

//::::::::::::::::::::::::::::::::::::::::::///

    function witherApi($name)
    {

        $client = new \GuzzleHttp\Client([
            'verify' => false,
            'headers' => [
                'x-rapidapi-host' => 'v3.football.api-sports.io',
                'x-rapidapi-key' => '8cc7b490ea845f35b524aef59d7e1782'
            ]
        ]);

        $response = $client->request('GET', 'https://api.openweathermap.org/data/2.5/weather?q=' . $name . '&appid=1253309e30b4fb953c136c1426565be0&units=metric');

        $data = json_decode($response->getBody(), true);

        $weather = $data["weather"];
        $id = $data['id'];


        $humidity =  $data["main"]["humidity"];
        $temp =  $data["main"]["temp"];
        $feels_like =  $data["main"]["feels_like"];
        $city = $data["name"];
        $country = $data["sys"]["country"];

        $weather[0] += array(

            "id_city" => $id,
            "humidity" => $humidity,
            "temp" => $temp,
            "feels_like" => $feels_like,
            "city" => $city,
            "country" => $country
        );


        return $weather[0];
    }

    function SaveCity(Request $request)
    {

        $league = new Weather();

        $league->id = $request->id;
        $league->name = $request->city;

        $league->save();
        return true;
    }


    function SaveList(){
        $weather = Weather::select("id","name as city")->get();
        return $weather;
   }
   function delete($id){
    Weather::where("id", $id)->delete();
        $listAll = Weather::all();
        return true;
   }
}
