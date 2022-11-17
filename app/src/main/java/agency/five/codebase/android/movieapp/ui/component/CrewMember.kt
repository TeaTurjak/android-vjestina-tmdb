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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class CrewMemberViewState(
    val name: String,
    val job: String,
)

@Composable
fun CrewMember(
    crewMemberViewState: CrewMemberViewState,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .wrapContentSize()
    ) {
        Column() {
            Text(
                text = crewMemberViewState.name,
                Modifier
                    .padding(start = 5.dp, end = 5.dp, top = 5.dp)
                    .clip(RoundedCornerShape(6.dp)),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp
            )
            Text(
                text = crewMemberViewState.job,
                Modifier
                    .padding(start = 5.dp, bottom = 5.dp, end = 5.dp, top = 5.dp)
                    .clip(RoundedCornerShape(6.dp)),
                color = Color.Black,
                fontSize = 10.sp
            )
        }
    }
}

@Preview
@Composable
private fun CrewMemberPreview() {
    val crewMemberMock = MoviesMock.getCrewman();
    val crewMember = CrewMemberViewState(
        name = crewMemberMock.name,
        job = crewMemberMock.job
    )
    CrewMember(crewMemberViewState = crewMember)
}
