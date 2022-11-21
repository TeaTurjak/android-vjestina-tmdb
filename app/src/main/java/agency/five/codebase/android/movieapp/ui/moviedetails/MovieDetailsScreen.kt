package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.component.*
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapper
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapperImpl
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
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
import coil.compose.AsyncImage


private val movieDetailsMapper: MovieDetailsMapper = MovieDetailsMapperImpl()

val movieDetailsViewState = movieDetailsMapper.toMovieDetailsViewState(MoviesMock.getMovieDetails())

@Composable
fun MovieRoute(
// actions
) {
    val movieDetailsViewState by remember { mutableStateOf(movieDetailsViewState) }

    MovieScreen(
        movieDetailsViewState = movieDetailsViewState
    )
}

@Composable
fun MovieScreen(
    movieDetailsViewState: MovieDetailsViewState
) {
    Column() {
        MoviePreview(movieDetailsViewState)
        OverviewOfMovie(movieDetailsViewState)
        CrewList(movieDetailsViewState)
        ActorList(movieDetailsViewState)
    }
}

@Composable
fun MoviePreview(
    movieDetailsViewState: MovieDetailsViewState
) {
    Box(
    ) {
        AsyncImage(
            model = movieDetailsViewState.imageUrl,
            contentDescription = movieDetailsViewState.title,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = "User score",
                fontSize = 5.sp,
                color = Color.White,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Light
            )
            UserScoreProgressBar(
                movieProgress = movieDetailsViewState.voteAverage
            )
            Text(
                text = movieDetailsViewState.title,
                fontSize = 15.sp,
                color = Color.White,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold
            )
            FavouriteButton(
                isFavourite = movieDetailsViewState.isFavorite,
                modifier = Modifier
                    .size(20.dp),
                onClick = { }
            )
        }
    }
}

@Composable
fun ActorList(
    movieDetailsViewState: MovieDetailsViewState
) {
    Column(
    ) {
        Text(
            text = "Top billed cast",
            modifier = Modifier
                .padding(5.dp),
            fontSize = 20.sp,
            color = Color.Black,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold
        )
        LazyHorizontalGrid(
            rows = GridCells.Fixed(1),
            content = {
                items(
                    items = movieDetailsViewState.cast,
                    key = { cast -> cast.id }
                ) { cast ->
                    ActorCard(
                        actorCardViewState = ActorCardViewState(
                            cast.name,
                            cast.character,
                            cast.imageUrl.toString()
                        )
                    )
                }
            }
        )

    }
}

@Composable
fun CrewList(
    movieDetailsViewState: MovieDetailsViewState
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        content = {
            items(
                items = movieDetailsViewState.crew,
                key = { crew -> crew.id }
            ) { crew ->
                CrewMember(
                    crewMemberViewState = CrewMemberViewState(
                        crew.name,
                        crew.job
                    )
                )
            }
        }
    )
}


@Composable
fun OverviewOfMovie(
    movieDetailsViewState: MovieDetailsViewState
) {
    Column(
    ) {
        Text(
            text = "Overview",
            modifier = Modifier
                .padding(5.dp),
            fontSize = 20.sp,
            color = Color.Black,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = movieDetailsViewState.overview,
            modifier = Modifier
                .padding(5.dp),
            fontSize = 10.sp,
            color = Color.Black,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Normal
        )
    }
}


@Preview
@Composable
fun MovieScreenPreview() {
    MovieAppTheme {
        MovieScreen(
            movieDetailsViewState = movieDetailsViewState
        )
    }
}



