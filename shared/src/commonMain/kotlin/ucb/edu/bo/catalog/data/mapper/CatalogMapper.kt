package ucb.edu.bo.catalog.data.mapper

import ucb.edu.bo.catalog.data.dto.MovieDto
import ucb.edu.bo.catalog.domain.model.CatalogMovieModel

fun MovieDto.toDomain(): CatalogMovieModel {
    val fullPosterUrl = if (!posterPath.isNullOrBlank()) {
        "https://image.tmdb.org/t/p/w500$posterPath"
    } else {
        ""
    }
    return CatalogMovieModel(
        id = id.toString(),
        title = title,
        posterUrl = fullPosterUrl
    )
}
