package com.example.floating_action_button_sample.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.floating_action_button_sample.R

@Composable
fun SpeedDialSampleScreen() {
    var speedDialState by remember { mutableStateOf(SpeedDialState.Idle) }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                cutoutShape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50))
            ) {}
        },
        floatingActionButton = {
            ThumbUpSpeedDialFloatingActionButton(
                state = speedDialState,
                onStateChange = { state -> speedDialState = state },
                onSubFab1Click = { /* Action */ },
                onSubFab2Click = { /* Action */ }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        isFloatingActionButtonDocked = true
    ) {
        Box(Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(R.drawable.background),
                contentDescription = null,
                modifier = Modifier
                    .size(400.dp)
                    .align(Alignment.Center)
            )

            // SpeedDialState.Activeならコンテンツにぼかしを入れてFABが目立つようにする
            if (speedDialState == SpeedDialState.Active) {
                Spacer(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White.copy(alpha = 0.8f))
                        .clickable { speedDialState = SpeedDialState.Idle })
            }
        }
    }
}
