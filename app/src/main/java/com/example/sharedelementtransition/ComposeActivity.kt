@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.example.sharedelementtransition

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sharedelementtransition.databinding.ActivityComposeBinding
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

class ComposeActivity : AppCompatActivity() {
    private var binding: ActivityComposeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityComposeBinding.inflate(layoutInflater)

        binding?.root?.apply {
            ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            setContentView(this)

            binding?.composeView?.apply {
                setContent {
                    Home()
                }
            }
        }
    }
}

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
            testItem(
                item = item,
                onItemClicked = onItemClick,
                sharedTransitionScope = sharedTransitionScope,
                animatedContentScope = animatedContentScope
            )
        }
    }
}

@Composable
fun testItem(
    item: TestItem,
    onItemClicked: (Int) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    with(sharedTransitionScope) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
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
            Column {
                Text(
                    text = item.name, Modifier.Companion
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

@Composable
fun DetailScreen(
    item: TestItem,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    with(sharedTransitionScope) {
        Box {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                GlideImage(
                    modifier = Modifier
                        .Companion
                        .sharedElement(
                            sharedTransitionScope.rememberSharedContentState(key = "image-${item.image}"),
                            animatedVisibilityScope = animatedContentScope
                        )
                        .fillMaxWidth()
                        .aspectRatio(16f / 9f),
                    imageModel = { item.image })
                Text(
                    text = item.name, Modifier.Companion
                        .sharedElement(
                            sharedTransitionScope.rememberSharedContentState(key = "text-${item.name}"),
                            animatedVisibilityScope = animatedContentScope
                        )
                )
                Text(
                    text = item.description, Modifier.Companion
                        .sharedElement(
                            sharedTransitionScope.rememberSharedContentState(key = "text-${item.description}"),
                            animatedVisibilityScope = animatedContentScope
                        )
                )
            }
        }
    }
}