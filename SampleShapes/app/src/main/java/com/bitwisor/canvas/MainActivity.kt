package com.bitwisor.canvas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import  androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCanvas()
        }
    }
}
@Preview
@Composable
fun MyCanvas() {
    Canvas(
        modifier = Modifier
            .padding(20.dp)
            .size(300.dp)
    ){
        drawRect(
            color = Color.Black,
            size = size
        )
        drawRect(
            color = Color.Red,
            topLeft = Offset(100f,100f),
            size = Size(100f,100f),
            style = Stroke(
                width = 3.dp.toPx()
            )
        )
    drawCircle(
        brush = Brush.radialGradient(
            colors = listOf(Color.Red,Color.Yellow),
            center = center,
            radius = 100f
        ),
        radius = 100f,
    )
        drawCircle(
            color = Color.LightGray,
            radius = 100f,
            center = Offset(200f,200f),
            style = Stroke(
                width = 5.dp.toPx()
            )
        )
        drawArc(
            color = Color.Green,
            startAngle = 0f,
            sweepAngle = 270f,
            useCenter = true,
            topLeft = Offset(100f,500f),
            size = Size(200f,200f),
            style = Stroke(
                    width = 3.dp.toPx()
            )
        )
        drawOval(
            color = Color.Magenta,
            topLeft = Offset(500f,100f),
            size = Size(200f,300f)
        )
        drawLine(
            color = Color.Cyan,
            start = Offset(100f, 700f),
            end = Offset(700f, 700f),
            strokeWidth = 5.dp.toPx()
        )
    }
}
