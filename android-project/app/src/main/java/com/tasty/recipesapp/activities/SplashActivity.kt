package com.tasty.recipesapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import com.tasty.recipesapp.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    companion object {
        const val TAG = "SplashActivity"
        const val SPLASH_TIME_OUT: Long = 2000
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate method called")

        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Use a HandlerThread to create a background thread
        val handlerThread = HandlerThread("SplashHandlerThread", -10)
        handlerThread.start() // Create a Handler on the new HandlerThread
        val handler = Handler(handlerThread.looper)
        handler.postDelayed({
            // Navigate to MainActivity after the delay
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish() },
            SPLASH_TIME_OUT
        )

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart method called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume method called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause method called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop method called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy method called")
    }
}