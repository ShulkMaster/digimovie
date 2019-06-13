package com.sovize.labomovie.client.models

import com.sovize.labomovie.database.entities.Movie

data class ServerResponse(
    val Search: List<Movie>,
    val totalResults: String,
    val Response: String
)