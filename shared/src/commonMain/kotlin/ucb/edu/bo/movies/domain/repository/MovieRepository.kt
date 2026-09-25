package ucb.edu.bo.movies.domain.repository

import ucb.edu.bo.movies.domain.model.MovieModel

interface MovieRepository {
    suspend fun getMovies(): List<MovieModel>
}
