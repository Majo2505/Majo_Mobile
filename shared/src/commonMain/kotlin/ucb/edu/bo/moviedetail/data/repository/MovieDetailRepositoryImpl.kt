package ucb.edu.bo.moviedetail.data.repository

import ucb.edu.bo.moviedetail.domain.model.MovieDetailModel
import ucb.edu.bo.moviedetail.domain.repository.MovieDetailRepository
import kotlinx.coroutines.delay

class MovieDetailRepositoryImpl : MovieDetailRepository {
    override suspend fun getMovieDetail(id: String): MovieDetailModel {
        delay(500)
        return MovieDetailModel(
            id = id,
            title = "Película $id",
            description = "Descripción de la película $id",
            posterUrl = "poster_$id.jpg"
        )
    }
}
