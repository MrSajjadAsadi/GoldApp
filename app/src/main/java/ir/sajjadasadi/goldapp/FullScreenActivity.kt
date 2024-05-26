package ir.sajjadasadi.goldapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import ir.sajjadasadi.goldapp.databinding.ActivityFullScreenBinding

class FullScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFullScreenBinding
    lateinit var ImageTop: ImageView
    lateinit var textMidle: TextView
    lateinit var textBottom: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        ImageTop = binding.imageView
        textMidle = binding.textView
        textBottom = binding.textView2

        val topanimation = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        val midleanimation = AnimationUtils.loadAnimation(this, R.anim.midle_animation)
        val bootomanimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        ImageTop.startAnimation(topanimation)
        textMidle.startAnimation(midleanimation)
        textBottom.startAnimation(bootomanimation)

        val intent = Intent(this, MainActivity::class.java)


        val SplashScreenTimeOut = 4000
        Handler().postDelayed({
            startActivity(intent)
            finish()

        }, SplashScreenTimeOut.toLong())
    }


}