package com.example.sharedelementtransition.xml

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sharedelementtransition.ListItem
import com.example.sharedelementtransition.R
import com.example.sharedelementtransition.databinding.ActivityXmlBinding
import com.example.sharedelementtransition.testData

class XmlActivity : AppCompatActivity() {

    private lateinit var adapter: ItemAdapter
    private lateinit var binding: ActivityXmlBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityXmlBinding.inflate(layoutInflater)
        ViewCompat.setOnApplyWindowInsetsListener(binding.xmlLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setContentView(binding.root)

        initView()
    }

    private fun initView() = with(binding) {
        val items = testData
        adapter = ItemAdapter { view, data -> onItemClick(view, data) }
        adapter.submitList(items)

        rvList.layoutManager = LinearLayoutManager(this@XmlActivity)
        rvList.adapter = adapter
    }

    private fun onItemClick(view: View, data: ListItem) {
        val ivImage = view.findViewById<View>(R.id.iv_list_img)
        val tvTitle = view.findViewById<View>(R.id.tv_list_title)
        val tvDescription = view.findViewById<View>(R.id.tv_list_description)

        val intent = Intent(this, DetailActivity::class.java)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            androidx.core.util.Pair(ivImage, "shared_img"),
            androidx.core.util.Pair(tvTitle, "shared_title"),
            androidx.core.util.Pair(tvDescription, "shared_description")
        )

        intent.putExtra("data", data)
        startActivity(intent, options.toBundle())
    }
}