package agency.five.codebase.android.movieapp.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserScoreProgressBarCard(
    modifier: Modifier = Modifier,
    colorProgress: Color = Color.Green,
    movieProgress: Float
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.size(60.dp)

    ){
        Box(
            modifier = modifier.size(50.dp)
                .padding(5.dp)

        ) {
            Canvas(modifier = modifier.fillMaxSize()) {
                drawArc(
                    color = colorProgress,
                    startAngle = -90f,
                    sweepAngle = 360 * (0.1f * movieProgress),
                    useCenter = false,
                    style = Stroke(width = 10f, cap = StrokeCap.Round)
                )
            }
        }
        Text(text = (movieProgress * 10).toString(),
            modifier = modifier.align(Alignment.Center),
            color = Color.Black,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 15.sp
        )
    }
}

@Preview
@Composable
private fun UserScoreProgressBarPreview() {
    UserScoreProgressBarCard(movieProgress=6.9f)
}