package ep.ws

import java.io.Serializable

data class SearchResponse(
    val Response: Boolean = true,
    val Search: List<Hit> = emptyList(),
    val totalResults: Int = 0
) : Serializable

data class Hit(
    val Poster: String = "",
    val Title: String = "",
    val Type: String = "",
    val Year: String = "",
    val imdbID: String = "",
) : Serializable
