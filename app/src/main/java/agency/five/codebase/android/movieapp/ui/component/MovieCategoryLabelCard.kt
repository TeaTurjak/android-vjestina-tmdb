package agency.five.codebase.android.movieapp.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.padding
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



sealed class MovieCategoryLabelTextViewState

class MovieCategoryLabelTextViewStateDirect(val text: String): MovieCategoryLabelTextViewState()
class MovieCategoryLabelTextViewStateReferenced(@StringRes val textRes: Int): MovieCategoryLabelTextViewState()

data class MovieCategoryLabelViewState(
    val itemId: Int,
    val isSelected: Boolean,
    val categoryText: MovieCategoryLabelTextViewState
)
@Composable
fun MovieCategoryLabel(
    movieCategoryLabelViewState: MovieCategoryLabelViewState,
    modifier: Modifier = Modifier
) {
    var isSelectedState by remember { mutableStateOf(movieCategoryLabelViewState.isSelected) }
    Column(modifier = modifier.width(IntrinsicSize.Min))
    {
        Text(
            text = when (movieCategoryLabelViewState.categoryText)

            {
                is MovieCategoryLabelTextViewStateDirect -> movieCategoryLabelViewState.categoryText.text
                is MovieCategoryLabelTextViewStateReferenced -> stringResource(id = movieCategoryLabelViewState.categoryText.textRes)
            },
            fontWeight = if(isSelectedState) FontWeight.Bold else FontWeight.Normal,
            color = if (isSelectedState) Color.Black else Color.Gray,
            fontSize = 18.sp,
            modifier = modifier.clickable{isSelectedState = isSelectedState.not()}

        )
        if(isSelectedState){
            Divider(
                color = Color.Black,
                thickness = 5.dp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieCategoryLabelPreview(){
    MovieCategoryLabel(
        movieCategoryLabelViewState = MovieCategoryLabelViewState(
            itemId = 0,
            isSelected = false,
            categoryText = MovieCategoryLabelTextViewStateDirect(text="Movies")
        )
    )
}