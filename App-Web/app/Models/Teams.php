<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Teams extends Model
{
    use HasFactory;
    protected $fillable = [
        "id",
        'name',
        'code',
        'country',
        'founded',
        'national',
        'logo',
        'address',
        'city',
        'capacity',
        'surface',
        'image',
        'stadium',
        'league_id'
    ];

}
