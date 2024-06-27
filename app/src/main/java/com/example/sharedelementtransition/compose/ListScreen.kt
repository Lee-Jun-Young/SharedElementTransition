@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.example.sharedelementtransition.compose

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.sharedelementtransition.ListItem
import com.example.sharedelementtransition.testData
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun SharedTransitionScope.ListScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val items = remember { mutableStateOf(testData) }
    val scrollableState = rememberLazyListState()

    LazyColumn(
        modifier = modifier,
        state = scrollableState
    ) {
        itemsIndexed(items.value) { index, item ->
            ListItem(
                item = item,
                onItemClicked = onItemClick,
                sharedTransitionScope = sharedTransitionScope,
                animatedContentScope = animatedContentScope
            )
        }
    }
}

@Composable
fun ListItem(
    item: ListItem,
    onItemClicked: (Int) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    with(sharedTransitionScope) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .testTag("testItem")
                .clickable {
                    onItemClicked(item.id)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            GlideImage(
                modifier = Modifier
                    .sharedElement(
                        sharedTransitionScope.rememberSharedContentState(key = "image-${item.image}"),
                        animatedVisibilityScope = animatedContentScope
                    )
                    .size(100.dp),
                imageModel = { item.image },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )
            )
            Spacer(modifier = Modifier.size(8.dp))
            Column {
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