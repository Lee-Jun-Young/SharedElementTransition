@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.example.sharedelementtransition.compose

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sharedelementtransition.databinding.ActivityComposeBinding

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