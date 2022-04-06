package com.example.floating_action_button_sample.ui


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.floating_action_button_sample.R

@Composable
fun DetailScreen(openCreate: () -> Unit) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                cutoutShape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50))
            ) {}
        },
        floatingActionButton = {
            CreateFloatingActionButton(openCreate)
        },
        floatingActionButtonPosition = FabPosition.End,
        isFloatingActionButtonDocked = true
    ) {
        Box(Modifier.fillMaxSize()) {
            Text(
                text = "Detail Screen",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun CreateFloatingActionButton(openCreate: () -> Unit) {
    val state = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }
    AnimatedVisibility(
        visibleState = state,
        enter = scaleIn(),
        exit = scaleOut()
    ) {
        FloatingActionButton(onClick = openCreate) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_create_24),
                contentDescription = null
            )
        }
    }
}
