@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.example.sharedelementtransition.compose

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.sharedelementtransition.ListItem
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun SharedTransitionScope.DetailScreen(
    item: ListItem,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    with(sharedTransitionScope) {
        Box {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                GlideImage(
                    modifier = Modifier
                        .sharedElement(
                            sharedTransitionScope.rememberSharedContentState(key = "image-${item.image}"),
                            animatedVisibilityScope = animatedContentScope
                        )
                        .fillMaxWidth()
                        .aspectRatio(16f / 9f),
                    imageModel = { item.image })
                Text(
                    text = item.name, Modifier
                        .sharedElement(
                            sharedTransitionScope.rememberSharedContentState(key = "text-${item.name}"),
                            animatedVisibilityScope = animatedContentScope
                        )
                )
                Text(
                    text = item.description, Modifier
                        .sharedElement(
                            sharedTransitionScope.rememberSharedContentState(key = "text-${item.description}"),
                            animatedVisibilityScope = animatedContentScope
                        )
                )
            }
        }
    }
}