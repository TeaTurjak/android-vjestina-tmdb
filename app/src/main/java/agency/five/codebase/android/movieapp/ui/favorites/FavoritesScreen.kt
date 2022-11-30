package agency.five.codebase.android.movieapp.ui.favorites

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.component.MovieCard
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapper
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapperImpl
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


private val favoritesMapper: FavoritesMapper = FavoritesMapperImpl()

val favoritesViewState = favoritesMapper.toFavoritesViewState(MoviesMock.getMoviesList())

@Composable
fun FavoritesRoute(
    onNavigateToMovieDetails: (Int) -> Unit,
) {
    val favorite by remember { mutableStateOf(favoritesViewState) }
    FavoritesScreen(
        favoritesViewState = favorite,
        modifier = Modifier.fillMaxSize(),
        onMovieClick = onNavigateToMovieDetails
}

@Composable
fun FavoritesScreen(
    favoritesViewState: FavoritesViewState,
    modifier: Modifier,
    onMovieClick: (Int) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 5.dp),
    ) {
        Text(
            text = "Favorite",
            modifier = Modifier
                .padding(5.dp),
            fontSize = 30.sp,
            color = Color.Black,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            content = {
                items(
                    items = favoritesViewState.favoriteMoviesList,
                    key = { favMovie -> favMovie.id }) { card ->
                    MovieCard(
                        movieCardViewState = card.movieCardViewState,
                        modifier = Modifier
                            .width(130.dp)
                            .height(200.dp),
                        onCardClick = { onMovieClick(card.id) },
                        onLikeButtonClick = {
                            card.movieCardViewState.favouriteState.not()
                        },
                    )
                }
            }
        )
    }
}

@Preview
@Composable
fun FavoritesScreenPreview() {
    MovieAppTheme {
        FavoritesScreen(
            favoritesViewState = favoritesViewState,
            modifier = Modifier,
            onMovieClick = {},
        )
    }
}
