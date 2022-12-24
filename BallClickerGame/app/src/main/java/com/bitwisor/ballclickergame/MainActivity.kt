package com.bitwisor.ballclickergame

import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bitwisor.ballclickergame.ui.theme.BallClickerGameTheme
import kotlinx.coroutines.delay
import java.lang.Math.sqrt
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen(){
    var points by remember{
        mutableStateOf(0)
    }
    var isTimerRunning by remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier.fillMaxSize()){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(text = "Points: $points",
            fontSize = 20.sp,
            fontWeight =  FontWeight.Bold
            )

        Button(onClick = {
            isTimerRunning = !isTimerRunning
            points = 0
        }){
            Text(text = if(isTimerRunning) "Reset" else "Start")
        }
        CountDownTimer(
            isTimerRunning = isTimerRunning
        ){
            isTimerRunning = false
        }
        }
        Ballclicker(
            enabled = isTimerRunning
        ){
            points++
        }
    }


}

@Composable
fun CountDownTimer(
    time:Int = 10000,
    isTimerRunning: Boolean = false,
    onTimerEnd: () -> Unit = {}
){
    var curTime by remember {
        mutableStateOf(time)
    }

    LaunchedEffect(key1 = curTime, key2 = isTimerRunning){
        if(!isTimerRunning){
            curTime = time
            return@LaunchedEffect
        }
        if(curTime>0){
            delay(1000L)
            curTime -= 1000
        }else{
            onTimerEnd()
        }
    }
    Text(text = "CountDown: "+(curTime / 1000).toString(), fontSize = 20.sp, fontWeight = FontWeight.Bold)
}

@Composable
fun Ballclicker(
    radius:Float = 100f,
    enabled:Boolean = false,
    ballColor:Color = Color.Red,
    onBallClick:() -> Unit = { }
) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        var ballPosition by remember {
            mutableStateOf(randomOffset(radius, width = constraints.maxWidth, height = constraints.maxHeight))
        }
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(enabled) {
                    if (!enabled) {
                        return@pointerInput
                    }
                    detectTapGestures {
                        val distance = sqrt(
                            (it.x - ballPosition.x).pow(2) +
                                    (it.y - ballPosition.y).pow(2)
                        )
                        if (distance <= radius) {
                            ballPosition = randomOffset(
                                radius = radius,
                                width = constraints.maxWidth,
                                height = constraints.maxHeight
                            )
                        }
                        onBallClick()
                    }
                }
        ){
            drawCircle(
                color = Color.Red,
                radius = radius,
                center = ballPosition
            )
        }

    }


}
private fun randomOffset(radius: Float, width:Int, height: Int):Offset{
    return Offset(
        x= Random.nextInt(radius.roundToInt(),width-radius.roundToInt()).toFloat(),
        y = Random.nextInt(radius.roundToInt(), height-radius.roundToInt()).toFloat())

}