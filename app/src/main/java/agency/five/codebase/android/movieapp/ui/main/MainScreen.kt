package agency.five.codebase.android.movieapp.ui.main

import agency.five.codebase.android.movieapp.navigation.MOVIE_ID_KEY
import agency.five.codebase.android.movieapp.navigation.MovieDetailsDestination
import agency.five.codebase.android.movieapp.navigation.NavigationItem
import agency.five.codebase.android.movieapp.ui.favorites.FavoritesRoute
import agency.five.codebase.android.movieapp.ui.home.HomeRoute
import agency.five.codebase.android.movieapp.ui.moviedetails.MovieRoute
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import agency.five.codebase.android.movieapp.R
import androidx.compose.foundation.layout.Box
import androidx.compose.material.*

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var showBottomBar by remember {
        mutableStateOf(true)
    }
    val showBackIcon = !showBottomBar
    Scaffold(
        topBar = {
            TopBar(
                navigationIcon = {
                    if (showBackIcon) BackIcon(onBackClick = navController::popBackStack)
                }
            )
        },
        bottomBar = {
            if (showBottomBar)
                BottomNavigationBar(
                    destinations = listOf(
                        NavigationItem.HomeDestination,
                        NavigationItem.FavoritesDestination,
                    ),
                    onNavigateToDestination = { navigationItem ->
                        navController.popBackStack(
                            NavigationItem.HomeDestination.route,
                            inclusive = false
                        )
                        navController.navigate(navigationItem.route) {
                            launchSingleTop = true
                        }
                    },
                    currentDestination = navBackStackEntry?.destination
                )
        }
    ) { padding ->
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxSize()
        ) {
            NavHost(
                navController = navController,
                startDestination = NavigationItem.HomeDestination.route,
                modifier = Modifier.padding(padding)
            ) {
                composable(NavigationItem.HomeDestination.route) {
                    showBottomBar = true
                    HomeRoute(
                        onNavigateToMovieDetails = { navigationItem ->
                            navController.navigate(
                                MovieDetailsDestination.createNavigationRoute(navigationItem.id)
                            )
                        },
                        onMovieClick = {},
                        onLikeButtonClick = {}
                    )
                }
                composable(NavigationItem.FavoritesDestination.route) {
                    showBottomBar = true
                    FavoritesRoute(
                        onNavigateToMovieDetails = { navigationItem ->
                            navController.navigate(
                                MovieDetailsDestination.createNavigationRoute(navigationItem.id)
                            )
                        },
                        onLikeButtonClick = {},
                        onMovieClick = {}
                    )
                }
                composable(
                    route = MovieDetailsDestination.route,
                    arguments = listOf(navArgument(MOVIE_ID_KEY) { type = NavType.IntType }),
                ) {
                    showBottomBar = true
                    MovieRoute()
                }
            }
        }
    }
}


@Composable
private fun TopBar(
    navigationIcon: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Box() {
        if (navigationIcon != null) {
            navigationIcon.invoke()
        }
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_movie_24),
            contentDescription = null,
            modifier = modifier
                .size(15.dp)
                .padding(end = 5.dp),
            alignment = Alignment.Center,
        )
    }
}

@Composable
private fun BackIcon(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
        contentDescription = null,
        modifier = modifier
            .clickable { onBackClick() }
            .size(15.dp)
            .padding(start = 5.dp),
        alignment = Alignment.CenterStart,
    )
}

@Composable
private fun BottomNavigationBar(
    destinations: List<NavigationItem>,
    onNavigateToDestination: (NavigationItem) -> Unit,
    currentDestination: NavDestination?,
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background
    ) {
        destinations.forEach { destination ->
            BottomNavigationItem(
                selected = currentDestination?.route == destination.route,
                icon = {
                    painterResource(
                        id = if (currentDestination?.route == destination.route) {
                            destination.selectedIconId
                        } else {
                            destination.unselectedIconId
                        }
                    )
                },
                onClick = {
                    onNavigateToDestination(destination)
                })


        }
    }
}
