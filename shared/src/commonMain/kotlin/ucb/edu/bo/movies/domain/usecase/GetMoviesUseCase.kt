package ucb.edu.bo.movies.domain.usecase

import ucb.edu.bo.movies.domain.model.MovieModel
import ucb.edu.bo.movies.domain.repository.MovieRepository

class GetMoviesUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(): List<MovieModel> = repository.getMovies()
}
