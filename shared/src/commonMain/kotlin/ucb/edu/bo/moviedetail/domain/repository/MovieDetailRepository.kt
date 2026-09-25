package ucb.edu.bo.moviedetail.domain.repository

import ucb.edu.bo.moviedetail.domain.model.MovieDetailModel

interface MovieDetailRepository {
    suspend fun getMovieDetail(id: String): MovieDetailModel
}
