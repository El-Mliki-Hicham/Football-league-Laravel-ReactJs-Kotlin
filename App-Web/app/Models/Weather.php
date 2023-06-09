<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Weather extends Model
{
    use HasFactory;
    protected $fillable=[
        "id",
        'name',
        "country",
        "coulds",
        "temp",
        "feels_Like",
        "humudity",
        "wind"
    ];
    public $timestamps = false;
}
