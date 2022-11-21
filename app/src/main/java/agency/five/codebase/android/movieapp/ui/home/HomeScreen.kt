package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.mock.MoviesMock.getMoviesList
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.component.MovieCard
import agency.five.codebase.android.movieapp.ui.component.MovieCardViewState
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabel
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabelViewState
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapper
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapperImpl
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


private val homeScreenMapper: HomeScreenMapper = HomeScreenMapperImpl()

val popularCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
    listOf(
        MovieCategory.POPULAR_STREAMING,
        MovieCategory.POPULAR_FOR_RENT,
        MovieCategory.POPULAR_IN_THEATRES,
        MovieCategory.POPULAR_ON_TV
    ),
    MovieCategory.POPULAR_STREAMING,
    getMoviesList()
)

val nowPlayingCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
    listOf(
        MovieCategory.NOW_PLAYING_TV,
        MovieCategory.NOW_PLAYING_MOVIES
    ),
    MovieCategory.NOW_PLAYING_MOVIES,
    getMoviesList()
)

val upcomingCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
    listOf(
        MovieCategory.UPCOMING_TODAY,
        MovieCategory.UPCOMING_THIS_WEEK
    ),
    MovieCategory.UPCOMING_TODAY,
    getMoviesList()
)

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    onMovieClick: () -> Unit,
    onLikeButtonClick: () -> Unit,
    //onCategoryClick: (MovieCategoryLabelViewState) -> Unit,
    onNavigateToMovieDetails: (HomeMovieViewState) -> Unit
) {
    var popularMovies by remember { mutableStateOf(popularCategoryViewState) }
    var nowMovies by remember { mutableStateOf(nowPlayingCategoryViewState) }
    var upcomingMovies by remember { mutableStateOf(upcomingCategoryViewState) }

    HomeScreen(
        modifier = modifier,
        popularMovies = popularMovies,
        nowMovies = nowMovies,
        upcomingMovies = upcomingMovies,
        onMovieClick = onMovieClick,
        onLikeButtonClick = onLikeButtonClick,
        onCategoryClick = {
            when (it.itemId) {
                0, 1, 2, 3 -> popularMovies =
                    homeScreenMapper.toHomeMovieCategoryViewState(
                        listOf(
                            MovieCategory.POPULAR_STREAMING,
                            MovieCategory.POPULAR_ON_TV,
                            MovieCategory.POPULAR_FOR_RENT,
                            MovieCategory.POPULAR_IN_THEATRES
                        ),
                        MovieCategory.values()[it.itemId],
                        getMoviesList()
                    )
                4, 5 -> nowMovies =
                    homeScreenMapper.toHomeMovieCategoryViewState(
                        listOf(
                            MovieCategory.NOW_PLAYING_TV,
                            MovieCategory.NOW_PLAYING_MOVIES
                        ),
                        MovieCategory.values()[it.itemId],
                        getMoviesList()
                    )
                else -> upcomingMovies =
                    homeScreenMapper.toHomeMovieCategoryViewState(
                        listOf(
                            MovieCategory.UPCOMING_TODAY,
                            MovieCategory.UPCOMING_THIS_WEEK
                        ),
                        MovieCategory.values()[it.itemId],
                        getMoviesList()
                    )
            }

        },
        onNavigateToMovieDetails = onNavigateToMovieDetails

    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    popularMovies: HomeMovieCategoryViewState,
    nowMovies: HomeMovieCategoryViewState,
    upcomingMovies: HomeMovieCategoryViewState,
    onMovieClick: () -> Unit,
    onLikeButtonClick: () -> Unit,
    onCategoryClick: (MovieCategoryLabelViewState) -> Unit,
    onNavigateToMovieDetails: (HomeMovieViewState) -> Unit,
) {
    Column() {
        HomeMovieCategory(
            onCategoryClick = onCategoryClick,
            movieCategoryType = popularMovies,
            categoryTitleMain = "What's popular",
            onNavigateToMovieDetails = onNavigateToMovieDetails
        )

        HomeMovieCategory(
            onCategoryClick = onCategoryClick,
            movieCategoryType = nowMovies,
            categoryTitleMain = "Now playing",
            onNavigateToMovieDetails = onNavigateToMovieDetails
        )

        HomeMovieCategory(
            onCategoryClick = onCategoryClick,
            movieCategoryType = upcomingMovies,
            categoryTitleMain = "Upcoming",
            onNavigateToMovieDetails = onNavigateToMovieDetails
        )

    }

}

@Composable
fun HomeMovieCategory(
    onCategoryClick: (MovieCategoryLabelViewState) -> Unit,
    movieCategoryType: HomeMovieCategoryViewState,
    categoryTitleMain: String,
    onNavigateToMovieDetails: (HomeMovieViewState) -> Unit,
) {
    HomeCategoryMainTitle(
        categoryTitleMain = categoryTitleMain
    )

    HomeChoseCategory(
        movieCategoryType = movieCategoryType,
        onCategoryClick = onCategoryClick
    )

    HomeMoviesInCategory(
        moviesInCategory = movieCategoryType,
        onNavigateToMovieDetails = onNavigateToMovieDetails
    )

}

@Composable
fun HomeMoviesInCategory(
    moviesInCategory: HomeMovieCategoryViewState,
    onNavigateToMovieDetails: (HomeMovieViewState) -> Unit
) {
    LazyRow(

    ) {
        items(
            items = moviesInCategory.movies,
            itemContent = { movieInCategory ->
                MovieCard(
                    movieCardViewState = MovieCardViewState(
                        imageUrl = movieInCategory.imageUrl.toString(),
                        favouriteState = movieInCategory.isFavorite,
                    ),
                    modifier = Modifier
                        .height(200.dp)
                        .width(130.dp),
                    onCardClick = { onNavigateToMovieDetails(movieInCategory) },
                    onLikeButtonClick = {}
                )
            },
        )
    }
}

@Composable
fun HomeChoseCategory(
    movieCategoryType: HomeMovieCategoryViewState,
    onCategoryClick: (MovieCategoryLabelViewState) -> Unit
) {
    LazyRow(
    ) {
        items(
            items = movieCategoryType.movieCategories,
            itemContent = { category ->
                MovieCategoryLabel(
                    movieCategoryLabelUiState = category,
                    onItemClick = onCategoryClick,
                )
            }
        )
    }
}

@Composable
fun HomeCategoryMainTitle(
    categoryTitleMain: String
) {
    Text(
        text = categoryTitleMain,
        modifier = Modifier
            .padding(5.dp),
        fontSize = 30.sp,
        color = Color.Black,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Bold
    )

}

@Preview
@Composable
fun HomeScreenPreview() {
    MovieAppTheme {
        HomeScreen(
            modifier = Modifier,
            popularMovies = popularCategoryViewState,
            nowMovies = nowPlayingCategoryViewState,
            upcomingMovies = upcomingCategoryViewState,
            onMovieClick = {},
            onLikeButtonClick = {},
            onCategoryClick = {},
            onNavigateToMovieDetails = {}
        )
    }
}

