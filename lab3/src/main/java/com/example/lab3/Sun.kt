package com.example.lab3

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat

class Sun : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sun)
    }

    private fun sun()
    {
        val sunImageView : ImageView = findViewById(R.id.sun)
        val sunRiseAnimation : Animation = AnimationUtils.loadAnimation(this, R.anim.sun_rise);
        sunImageView.startAnimation(sunRiseAnimation);
    }

    fun hourglass()
    {
        val hourglassView: ImageView = findViewById(R.id.animationView)
        val animationDrawableResId = R.drawable.hourglass
        val animationDrawable =  ResourcesCompat.getDrawable(resources, animationDrawableResId, null) as AnimationDrawable
        hourglassView.setImageDrawable(animationDrawable)
        hourglassView.setOnClickListener {
            animationDrawable.start()
        }
    }
}