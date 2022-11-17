package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

data class MovieCardViewState(
    val imageUrl: String,
    val favouriteState: Boolean,
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieCard(
    movieCardViewState: MovieCardViewState,
    onCardClick: () -> Unit,
    onLikeButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = Modifier,
        shape = MaterialTheme.shapes.medium,
        onClick = onCardClick
    ) {
        Box(modifier = Modifier) {
            AsyncImage(
                model = movieCardViewState.imageUrl,
                contentDescription = movieCardViewState.imageUrl,
                Modifier
                    .size(130.dp, 200.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieCardPreview() {
    val movie = MoviesMock.getMoviesList()[0];
    var onCardClick = remember { mutableStateOf(true) }
    var onLikeButtonClick = remember { mutableStateOf(true) }

    MovieCard(movieCardViewState = MovieCardViewState(
        imageUrl = movie.imageUrl.toString(),
        favouriteState = onLikeButtonClick.value
    ),
        onCardClick = { onCardClick != onCardClick },
        onLikeButtonClick = { onLikeButtonClick != onLikeButtonClick }
    )
}
