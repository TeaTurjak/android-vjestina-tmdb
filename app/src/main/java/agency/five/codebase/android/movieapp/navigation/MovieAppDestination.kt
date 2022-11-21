package agency.five.codebase.android.movieapp.navigation

sealed class MovieAppDestination(route: String) {
    open val route: String = ""
}
