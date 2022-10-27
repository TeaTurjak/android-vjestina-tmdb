package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class CrewItemCardViewState(
    val name: String,
    val job: String,
)
@Composable
fun CrewItemCard(
    crewItemCardViewState: CrewItemCardViewState,
    modifier: Modifier = Modifier,
) {
    Card(

    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .wrapContentSize(),
        ){
            Text(text = crewItemCardViewState.name,
                modifier = Modifier.padding(start = 5.dp, end = 5.dp, top = 5.dp)
                    .clip(RoundedCornerShape(6.dp)),
                color = Color.DarkGray,
                fontSize = 10.sp
            )
            Text(text = crewItemCardViewState.job,
                modifier = Modifier.padding(start = 5.dp, bottom = 5.dp, end = 5.dp, top = 5.dp)
                    .clip(RoundedCornerShape(6.dp)),
                color = Color.LightGray,
                fontSize = 10.sp
            )
        }
    }
}

@Preview
@Composable
private fun CrewItemCardPreview() {
    val crewMan = MoviesMock.getCrewman();
    val crewCard = CrewItemCardViewState(
        name = crewMan.name,
        job = crewMan.job
    )
    CrewItemCard(crewItemCardViewState=crewCard)
}