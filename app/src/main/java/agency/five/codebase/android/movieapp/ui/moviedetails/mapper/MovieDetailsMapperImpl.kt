package agency.five.codebase.android.movieapp.ui.moviedetails.mapper

import agency.five.codebase.android.movieapp.model.MovieDetails
import agency.five.codebase.android.movieapp.ui.moviedetails.ActorViewState
import agency.five.codebase.android.movieapp.ui.moviedetails.CrewmanViewState
import agency.five.codebase.android.movieapp.ui.moviedetails.MovieDetailsViewState

class MovieDetailsMapperImpl : MovieDetailsMapper {

    override fun toMovieDetailsViewState(movieDetails: MovieDetails): MovieDetailsViewState {
        return MovieDetailsViewState(
            movieDetails.movie.id,
            movieDetails.movie.imageUrl.toString(),
            movieDetails.voteAverage,
            movieDetails.movie.title,
            movieDetails.movie.overview,
            movieDetails.movie.isFavorite,
            movieDetails.crew.map { crewman ->
                CrewmanViewState(
                    id = crewman.id,
                    name = crewman.name,
                    job = crewman.job
                )
            },
            movieDetails.cast.map { actor ->
                ActorViewState(
                    id = actor.id,
                    name = actor.name,
                    character = actor.character,
                    imageUrl = actor.imageUrl
                )
            }
        )
    }

}
