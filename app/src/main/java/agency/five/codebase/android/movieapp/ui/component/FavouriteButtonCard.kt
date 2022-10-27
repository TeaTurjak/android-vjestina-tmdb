package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FavouriteButtonCard(
    modifier: Modifier = Modifier,
    favouriteState: Boolean
) {
    val favoriteRemember = remember { mutableStateOf(favouriteState) }

    Image(
        painter = painterResource(id = if (favouriteState) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24),
        contentDescription = null,
        modifier = modifier.clickable {
                favoriteRemember.value = favoriteRemember.value.not()
        }
            .size(50.dp)
            .background(Color.White, CircleShape)
    )
}

@Preview
@Composable
private fun FavouriteButtonCardPreview() {
    FavouriteButtonCard(favouriteState = false)
}