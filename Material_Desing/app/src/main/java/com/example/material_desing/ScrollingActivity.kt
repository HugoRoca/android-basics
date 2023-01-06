package com.example.material_desing

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.URLUtil
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.material_desing.databinding.ActivityScrollingBinding
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.snackbar.Snackbar

class ScrollingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrollingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabButton.setOnClickListener {
            if (binding.bottomAppBar.fabAlignmentMode == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER) {
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
           } else {
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
           }
        }

        binding.bottomAppBar.setNavigationOnClickListener {
            Snackbar.make(binding.root, R.string.message_action_success, Snackbar.LENGTH_LONG)
                .setAnchorView(binding.fabButton)
                .show()
        }

        binding.contentInclude.btnSkip.setOnClickListener {
            binding.contentInclude.cvAds.visibility = View.GONE
        }

        binding.contentInclude.btnBuy.setOnClickListener {
            Snackbar.make(it, R.string.card_buying, Snackbar.LENGTH_LONG)
                .setAnchorView(binding.fabButton)
                .setAction(R.string.card_to_go) {
                    Toast.makeText(this, R.string.card_history, Toast.LENGTH_SHORT).show()
                }
                .show()
        }

        loadImage("https://imagenes.elpais.com/resizer/s6qH0KXtRg5EEj0IsgRd4sM3GIA=/1200x0/cloudfront-eu-central-1.images.arcpublishing.com/prisa/GCWTGAAASNNOJJJG2SZY2HJ2XQ.jpg")

        binding.contentInclude.cbEnablePassword.setOnClickListener {
            val enabled: Boolean = !binding.contentInclude.tilPassword.isEnabled
            binding.contentInclude.tilPassword.isEnabled = enabled
        }

        binding.contentInclude.etUrl.onFocusChangeListener = View.OnFocusChangeListener{ _, focused ->
            var errorStr: String? = null
            val url = binding.contentInclude.etUrl.text.toString()

            if (!focused) {
                if (url.isEmpty()) {
                    errorStr = getString(R.string.card_required)
                } else if (URLUtil.isValidUrl(url)) {
                    loadImage(url)
                } else {
                    errorStr = getString(R.string.card_invalid_url)
                }
            }

            binding.contentInclude.tilUrl.error = errorStr
        }

        binding.contentInclude.toggleBottom.addOnButtonCheckedListener { _, checkedId, _ ->
            binding.contentInclude.root.setBackgroundColor(
                when (checkedId) {
                    R.id.btnRed -> Color.RED
                    R.id.btnBlue -> Color.BLUE
                    else -> Color.GREEN
                }
            )
        }
    }

    private fun loadImage(url: String) {
        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(binding.contentInclude.imgCover)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}