package agency.five.codebase.android.movieapp.ui.favorites.mapper

import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.ui.component.MovieCardViewState
import agency.five.codebase.android.movieapp.ui.favorites.FavoritesMovieViewState
import agency.five.codebase.android.movieapp.ui.favorites.FavoritesViewState
import agency.five.codebase.android.movieapp.ui.moviedetails.movieDetailsViewState

class FavoritesMapperImpl : FavoritesMapper {

    override fun toFavoritesViewState(favoriteMovies: List<Movie>): FavoritesViewState =
        FavoritesViewState(
            favoriteMoviesList = favoriteMovies.map(::toFavoritesMovieViewState)
        )

    private fun toFavoritesMovieViewState(favoriteMovie: Movie) =
        FavoritesMovieViewState(
            id = favoriteMovie.id,
            movieCardViewState = MovieCardViewState(movieDetailsViewState.imageUrl, movieDetailsViewState.isFavorite)
    )

}
