package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun FavouriteButton(
    isFavourite: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = if (isFavourite) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24),
        contentDescription = null,
        modifier = modifier
            .size(50.dp)
            .background(Color.White, CircleShape)
            .clip(CircleShape)
            .clickable { onClick()}
    )
}

@Preview(showBackground = true)
@Composable
private fun FavouriteButtonPreview() {
    val isFavoriteState = remember { mutableStateOf(false) }
    FavouriteButton(
        isFavourite = isFavoriteState.value,
        onClick = { isFavoriteState.value = !isFavoriteState.value }
    )
}
