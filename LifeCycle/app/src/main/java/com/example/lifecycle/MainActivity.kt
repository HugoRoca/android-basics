package com.example.lifecycle

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<MaterialButton>(R.id.btnCheck).setOnClickListener {
            startActivity(Intent(this, DialogActivity::class.java))
        }

        Log.i("Life cycle", "OnCreate")
    }

    override fun onStart(){
        super.onStart()

        mediaPlayer = MediaPlayer.create(this, R.raw.ai_1)

        Log.i("---- Life cycle", "OnStart")
    }

    override fun onPause() {
        super.onPause()

        mediaPlayer?.pause()
        position = mediaPlayer!!.currentPosition

        Log.i("---- Life cycle", "OnPause")
    }

    override fun onResume() {
        super.onResume()
        mediaPlayer?.seekTo(position)
        mediaPlayer?.start()
        Log.i("---- Life cycle", "OnResume")
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        Log.i("---- Life cycle", "OnStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("---- Life cycle", "OnRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
        Log.i("---- Life cycle", "OnDestroy")
    }
}