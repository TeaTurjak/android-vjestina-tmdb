package agency.five.codebase.android.movieapp.ui.home.mapper

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabelTextViewState
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabelViewState
import agency.five.codebase.android.movieapp.ui.home.HomeMovieCategoryViewState
import agency.five.codebase.android.movieapp.ui.home.HomeMovieViewState

class HomeScreenMapperImpl : HomeScreenMapper {
    override fun toHomeMovieCategoryViewState(
        movieCategories: List<MovieCategory>,
        selectedMovieCategory: MovieCategory,
        movies: List<Movie>
    ): HomeMovieCategoryViewState {
        val movieViewState = mutableListOf<HomeMovieViewState>()
        val category = mutableListOf<MovieCategoryLabelViewState>()

        for (eachMovieCategory in movieCategories) {
            category.add(
                MovieCategoryLabelViewState(
                    itemId = eachMovieCategory.ordinal,
                    isSelected = selectedMovieCategory == eachMovieCategory,
                    categoryText = MovieCategoryLabelTextViewState.TextRes(
                        resourceText(
                            eachMovieCategory
                        )
                    )
                )
            )
        }

        for (eachMovie in movies) {
            movieViewState.add(
                HomeMovieViewState(
                    id = eachMovie.id,
                    imageUrl = eachMovie.imageUrl,
                    isFavorite = eachMovie.isFavorite
                )
            )
        }

        return HomeMovieCategoryViewState(category, movieViewState)
    }

}

fun resourceText(
    movieCategoryMatches: MovieCategory
): Int {
    return when (movieCategoryMatches) {
        MovieCategory.POPULAR_STREAMING -> R.string.POPULAR_STREAMING
        MovieCategory.POPULAR_ON_TV -> R.string.POPULAR_ON_TV
        MovieCategory.POPULAR_FOR_RENT -> R.string.POPULAR_FOR_RENT
        MovieCategory.POPULAR_IN_THEATRES -> R.string.POPULAR_IN_THEATRES
        MovieCategory.NOW_PLAYING_MOVIES -> R.string.NOW_PLAYING_MOVIES
        MovieCategory.NOW_PLAYING_TV -> R.string.NOW_PLAYING_TV
        MovieCategory.UPCOMING_TODAY -> R.string.UPCOMING_TODAY
        MovieCategory.UPCOMING_THIS_WEEK -> R.string.UPCOMING_THISS_WEEK
    }
}


