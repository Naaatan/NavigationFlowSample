package nay.navigationflowsample.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import nay.navigationflowsample.R
import nay.navigationflowsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // setup Navigation
        val navView: BottomNavigationView = findViewById(R.id.main_bottom_navigation)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_fragment_container) as NavHostFragment
        val navController = navHostFragment.findNavController()

        // 表示しているFragmentであれば再生成をしない
        navView.setOnItemReselectedListener { }

        // Destination変更のコールバックを設定
        navController.addOnDestinationChangedListener { controller, next, arguments ->
            showMessageTransfer(navView.selectedItemId, next.id)
        }

        navView.setupWithNavController(navController)
    }

    private fun showMessageTransfer(previousID: Int, nextID: Int) {
        if ((previousID == nextID) && (previousID == R.id.oneFragment)) {
            // 初期画面
            Toast.makeText(this, "初期画面", Toast.LENGTH_SHORT).show()
            return
        }

        val previous = when (previousID) {
            R.id.oneFragment -> "One"
            R.id.twoFragment -> "Two"
            R.id.threeFragment -> "Three"
            else -> "Unknown"
        }

        val next = when (nextID) {
            R.id.oneFragment -> "One"
            R.id.twoFragment -> "Two"
            R.id.threeFragment -> "Three"
            else -> "Unknown"
        }

        Toast.makeText(this, "$previous -> $next", Toast.LENGTH_SHORT).show()
    }
}