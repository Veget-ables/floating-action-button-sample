package com.example.floating_action_button_sample.ui.favorite

import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp


@Composable
fun FavoriteAnimationSampleScreen() {
    var favoriteState by remember { mutableStateOf(FavoriteState.Idle) }
    val transition = updateTransition(favoriteState, label = "favorite")

    Scaffold(
        floatingActionButton = {
            FavoriteFloatingActionButton(
                transition = transition,
                onClick = {
                    favoriteState = if (favoriteState == FavoriteState.Active) {
                        FavoriteState.Idle
                    } else {
                        FavoriteState.Active
                    }
                }
            )
        },
    ) {
        Box(Modifier.fillMaxSize()) {
            Text(
                text = if (favoriteState == FavoriteState.Active) "Favorite" else "...",
                modifier = Modifier.align(Alignment.Center),
                fontSize = 32.sp
            )
        }
    }
}