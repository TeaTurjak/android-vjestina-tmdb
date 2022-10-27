package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage

data class MovieCardViewState(
    val imageUrl: String,
    val title: String,
    val favouriteState: Boolean,
)
@Composable
fun MovieCard(
    movieCardViewState: MovieCardViewState,
    modifier: Modifier = Modifier,
) {
    Card(

    ) {
        Box{
            AsyncImage(
                model = movieCardViewState.imageUrl,
                contentDescription = movieCardViewState.title
                )
            FavouriteButtonCard(favouriteState = false)
        }
    }
}

@Preview
@Composable
private fun MovieCardPreview() {
    val movie = MoviesMock.getMoviesList()[0];
    val movieCard = MovieCardViewState(
        imageUrl = movie.imageUrl.toString(),
        title = movie.title,
        favouriteState = movie.isFavorite
    )
    MovieCard(movieCardViewState=movieCard)

}