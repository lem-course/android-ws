package ep.ws

import java.io.Serializable

data class SearchResponse(
    val Response: Boolean = false,
    val Search: List<Result>? = emptyList(),
    val totalResults: Int = 0
) : Serializable

data class Result(
    val Poster: String = "",
    val Title: String = "",
    val Type: String = "",
    val Year: String = "",
    val imdbID: String = ""
) : Serializable