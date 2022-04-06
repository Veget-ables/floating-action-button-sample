package com.example.floating_action_button_sample.ui


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
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
fun DetailScreen() {
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun ThumbUpSpeedDialFloatingActionButton(
    state: SpeedDialState,
    onStateChange: (SpeedDialState) -> Unit,
    onSubFab1Click: () -> Unit,
    onSubFab2Click: () -> Unit,
) {
    val transition = updateTransition(state, label = "speed_dial")

    Box(contentAlignment = Alignment.Center) {
        FloatingActionButton(
            onClick = {
                val newState =
                    if (state == SpeedDialState.Active) SpeedDialState.Idle else SpeedDialState.Active
                onStateChange(newState)
            },
        ) {
            val mainFabRes = if (transition.targetState == SpeedDialState.Active) {
                R.drawable.ic_baseline_close_24
            } else {
                R.drawable.ic_baseline_thumb_up_off_alt_24
            }
            Icon(
                painter = painterResource(id = mainFabRes),
                contentDescription = null
            )
        }
        if (transition.targetState == SpeedDialState.Active) {
            Plus1SubFab(onClick = onSubFab1Click)
            Plus2SubFab(onClick = onSubFab2Click)
        }
    }
}

@Composable
private fun Plus1SubFab(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        modifier = Modifier
            .size(MIN_FAB_SIZE)
            .offset(y = -FIRST_SUB_FAB_OFFSET_Y)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_exposure_plus_1_24),
            contentDescription = null
        )
    }
}

@Composable
private fun Plus2SubFab(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        modifier = Modifier
            .size(MIN_FAB_SIZE)
            .offset(y = -SECOND_SUB_FAB_OFFSET_Y)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_exposure_plus_2_24),
            contentDescription = null
        )
    }
}

enum class SpeedDialState {
    Active, Idle
}

// ref: https://material.io/components/buttons-floating-action-button#specs
val REGULAR_FAB_SIZE = 56.dp
val MIN_FAB_SIZE = 40.dp

val FIRST_SUB_FAB_OFFSET_Y = REGULAR_FAB_SIZE + 8.dp
val SECOND_SUB_FAB_OFFSET_Y = FIRST_SUB_FAB_OFFSET_Y + MIN_FAB_SIZE + 16.dp
