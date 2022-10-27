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
import coil.compose.AsyncImage

data class ActorCardViewState(
    val imageUrl: String,
    val name: String,
    val character: String,
)
@Composable
fun ActorCard(
    actorCardViewState: ActorCardViewState,
    modifier: Modifier = Modifier,
) {
    Card(

    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .wrapContentSize(),
        ){
            AsyncImage(
                model = actorCardViewState.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
                    .clip(RoundedCornerShape(6.dp))
            )
            Text(text = actorCardViewState.name,
                modifier = Modifier.width(100.dp)
                    .padding(start = 5.dp, end = 5.dp, top = 5.dp)
                    .clip(RoundedCornerShape(6.dp)),
                color = Color.DarkGray,
                fontSize = 10.sp
            )
            Text(text = actorCardViewState.character,
                modifier = Modifier.width(100.dp)
                    .padding(start = 5.dp, bottom = 5.dp, end = 5.dp, top = 5.dp)
                    .clip(RoundedCornerShape(6.dp)),
                color = Color.LightGray,
                fontSize = 10.sp
            )

        }
    }

}

@Preview
@Composable
private fun ActorCardPreview() {
    val actor = MoviesMock.getActor();
    val actorCard = ActorCardViewState(
        imageUrl = actor.imageUrl.toString(),
        name = actor.name,
        character = actor.character
    )
    ActorCard(actorCardViewState=actorCard)

}