package com.example.floating_action_button_sample.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.floating_action_button_sample.R

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun ThumbUpSpeedDialFloatingActionButton(
    state: SpeedDialState,
    onStateChange: (SpeedDialState) -> Unit,
    onSubFab1Click: () -> Unit,
    onSubFab2Click: () -> Unit,
) {
    Box(contentAlignment = Alignment.Center) {
        FloatingActionButton(
            onClick = {
                val newState =
                    if (state == SpeedDialState.Active) SpeedDialState.Idle else SpeedDialState.Active
                onStateChange(newState)
            },
        ) {
            val mainFabRes = if (state == SpeedDialState.Active) {
                R.drawable.ic_baseline_close_24
            } else {
                R.drawable.ic_baseline_thumb_up_off_alt_24
            }
            Icon(
                painter = painterResource(id = mainFabRes),
                contentDescription = null
            )
        }
        if (state == SpeedDialState.Active) {
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

internal enum class SpeedDialState {
    Active, Idle
}

// ref: https://material.io/components/buttons-floating-action-button#specs
val REGULAR_FAB_SIZE = 56.dp
val MIN_FAB_SIZE = 40.dp

val FIRST_SUB_FAB_OFFSET_Y = REGULAR_FAB_SIZE + 8.dp
val SECOND_SUB_FAB_OFFSET_Y = FIRST_SUB_FAB_OFFSET_Y + MIN_FAB_SIZE + 16.dp
