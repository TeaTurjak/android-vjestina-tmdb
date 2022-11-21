package agency.five.codebase.android.movieapp.navigation

import agency.five.codebase.android.movieapp.R

const val HOME_ROUTE = "Home"
const val FAVORITES_ROUTE = "Favorites"

sealed class NavigationItem(
    override val route: String,
    val selectedIconId: Int,
    val unselectedIconId: Int,
    val labelId: Int,
) : MovieAppDestination(route) {
    object HomeDestination : NavigationItem(
        route = HOME_ROUTE,
        selectedIconId = R.drawable.ic_baseline_home_filled_24,
        unselectedIconId = R.drawable.ic_baseline_home_outline_24,
        labelId = R.string.home,
    )

    object FavoritesDestination : NavigationItem(
        route = FAVORITES_ROUTE,
        selectedIconId = R.drawable.ic_baseline_favorite_24,
        unselectedIconId = R.drawable.ic_baseline_favorite_border_24,
        labelId = R.string.favorites,
    )
}
