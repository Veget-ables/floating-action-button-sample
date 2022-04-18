package com.example.floating_action_button_sample.ui.favorite


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.floating_action_button_sample.R


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FavoriteFloatingActionButton(
    transition: Transition<FavoriteState>,
    onClick: () -> Unit
) {
    val (backgroundColor, favRes) = if (transition.targetState == FavoriteState.Active) {
        MaterialTheme.colors.secondary to R.drawable.ic_baseline_favorite_24
    } else {
        MaterialTheme.colors.surface to R.drawable.ic_baseline_favorite_border_24
    }
    FloatingActionButton(
        onClick = onClick,
        backgroundColor = backgroundColor
    ) {
        val size by transition.animateDp(
            transitionSpec = {
                when {
                    FavoriteState.Idle isTransitioningTo FavoriteState.Active -> {
                        keyframes {
                            durationMillis = 300
                            (DEFAULT_FAVORITE_SIZE + 4.dp) at 150
                        }
                    }
                    else -> {
                        spring()
                    }
                }
            },
            label = "favorite size"
        ) { state ->
            when (state) {
                FavoriteState.Idle -> DEFAULT_FAVORITE_SIZE
                FavoriteState.Active -> DEFAULT_FAVORITE_SIZE
            }
        }
        Icon(
            painter = painterResource(favRes),
            contentDescription = "",
            modifier = Modifier.size(size),
        )
    }
}

private val DEFAULT_FAVORITE_SIZE = 24.dp

enum class FavoriteState {
    Idle, Active
}
