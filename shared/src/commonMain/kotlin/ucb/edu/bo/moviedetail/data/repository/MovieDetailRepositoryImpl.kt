package ucb.edu.bo.moviedetail.data.repository

import org.jetbrains.compose.resources.getString
import majo.shared.generated.resources.*
import ucb.edu.bo.moviedetail.domain.model.MovieDetailModel
import ucb.edu.bo.moviedetail.domain.repository.MovieDetailRepository
import kotlinx.coroutines.delay

class MovieDetailRepositoryImpl : MovieDetailRepository {
    override suspend fun getMovieDetail(id: String): MovieDetailModel {
        delay(500)
        return MovieDetailModel(
            id = id,
            title = getString(Res.string.movie_detail_title, id),
            description = getString(Res.string.movie_detail_description, id),
            posterUrl = "poster_$id.jpg"
        )
    }
}
