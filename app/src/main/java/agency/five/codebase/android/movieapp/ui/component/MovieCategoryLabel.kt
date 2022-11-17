package agency.five.codebase.android.movieapp.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.Text
import androidx.compose.runtime.*


sealed class MovieCategoryLabelTextViewState {
    data class Text(val text: String) : MovieCategoryLabelTextViewState()
    data class TextRes(@StringRes val textRes: Int) : MovieCategoryLabelTextViewState()
}

data class MovieCategoryLabelViewState(
    val itemId: Int,
    val isSelected: Boolean,
    val categoryText: MovieCategoryLabelTextViewState
)

@Composable
fun MovieCategoryLabel(
    movieCategoryLabelUiState: MovieCategoryLabelViewState,
    onItemClick: (MovieCategoryLabelViewState) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(IntrinsicSize.Min)
    )
    {
        Text(
            text = when (movieCategoryLabelUiState.categoryText) {
                is MovieCategoryLabelTextViewState.Text -> movieCategoryLabelUiState.categoryText.text
                is MovieCategoryLabelTextViewState.TextRes -> stringResource(id = movieCategoryLabelUiState.categoryText.textRes)
            },
            fontWeight = if (movieCategoryLabelUiState.isSelected) FontWeight.Bold else FontWeight.Normal,
            color = if (movieCategoryLabelUiState.isSelected) Color.Black else Color.Gray,
            fontSize = 18.sp,
            modifier = Modifier.clickable { onItemClick(movieCategoryLabelUiState) }

        )
        if (movieCategoryLabelUiState.isSelected) {
            Divider(
                color = Color.Black,
                thickness = 5.dp
            )
        } else {
            Divider(
                color = Color.White,
                thickness = 5.dp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieCategoryLabelPreview() {
    var isSelected by remember { mutableStateOf(false) }
    MovieCategoryLabel(
        movieCategoryLabelUiState = MovieCategoryLabelViewState(
            itemId = 0,
            isSelected = isSelected,
            categoryText = MovieCategoryLabelTextViewState.Text(text = "Movies")
        ),
        onItemClick = { if (isSelected == true) isSelected = false else isSelected = true }
    )
}
