package ucb.edu.bo.movies.domain.usecase

import ucb.edu.bo.movies.domain.model.MovieModel
import ucb.edu.bo.movies.domain.repository.MovieRepository

class GetPopularMovies(
    private val repository: MovieRepository
) {
    suspend fun invoke(): List<MovieModel> {
        return repository.getMovies()
    }
}
