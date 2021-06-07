package id.fadillah.jetpacksubmission.ui.activity.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import id.fadillah.jetpacksubmission.databinding.ActivitySplashBinding
import id.fadillah.jetpacksubmission.ui.activity.home.HomeActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.splashBottomWave.apply {
            addDefaultWaves(2, 1)
            startAnimation()
        }

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, 3250L)
    }

    override fun onResume() {
        super.onResume()
        binding.splashBottomWave.resumeAnimation()
    }

    override fun onPause() {
        super.onPause()
        binding.splashBottomWave.pauseAnimation()
    }
}