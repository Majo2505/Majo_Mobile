package ucb.edu.bo.movies.data.repository

import ucb.edu.bo.movies.domain.model.MovieModel
import ucb.edu.bo.movies.domain.repository.MovieRepository

class MovieRepositoryImpl : MovieRepository {
    override suspend fun getMovies(): List<MovieModel> {
        // Datos hardcodeados para probar el flujo
        return listOf(
            MovieModel(id = "1", title = "Inception", posterUrl = "https://tse4.mm.bing.net/th/id/OIP.XF0ORZuqzuXz1QKBw4JN1wHaLj?r=0&rs=1&pid=ImgDetMain&o=7&rm=3"),
            MovieModel(id = "2", title = "Interstellar", posterUrl = ""),
            MovieModel(id = "3", title = "The Dark Knight", posterUrl = "")
        )
    }
}