package ucb.edu.bo.moviedetail.domain.usecase

import ucb.edu.bo.moviedetail.domain.model.MovieDetailModel
import ucb.edu.bo.moviedetail.domain.repository.MovieDetailRepository

class GetMovieDetailUseCase(private val repository: MovieDetailRepository) {
    suspend operator fun invoke(id: String): MovieDetailModel = repository.getMovieDetail(id)
}
