package com.example.sharedelementtransition.xml

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.sharedelementtransition.ListItem
import com.example.sharedelementtransition.databinding.ActivitiyDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivitiyDetailBinding

    private val data: ListItem? by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getParcelableExtra(
                "data", ListItem::class.java
            )
        } else {
            intent?.getParcelableExtra(
                "data"
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitiyDetailBinding.inflate(layoutInflater)
        ViewCompat.setOnApplyWindowInsetsListener(binding.detailLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setContentView(binding.root)

        initView()
    }

    private fun initView() = with(binding) {
        data?.let { data ->
            Glide.with(this@DetailActivity)
                .load(data.image)
                .into(ivDetailImg)
            tvDetailTitle.text = data.name
            tvDetailDescription.text = data.description
        }
    }

}