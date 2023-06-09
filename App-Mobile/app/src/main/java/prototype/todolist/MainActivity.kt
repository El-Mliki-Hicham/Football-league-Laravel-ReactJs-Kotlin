package prototype.todolist


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import prototype.todolist.databinding.ActivityMainBinding


/**
 * Main Activity and entry point for the app.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the navigation host fragment from this Activity
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        // Instantiate the navController using the NavHostFragment
        navController = navHostFragment.navController
        // Make sure actions in the ActionBar get propagated to the NavController
        setupActionBarWithNavController(navController)

        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#36788E")))

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {


        // Inflate the menu to add items to the action bar
        menuInflater.inflate(R.menu.layout_menu, menu)


        // Find the search menu item and set its icon
        val searchItem = menu?.findItem(R.id.action_search)
        searchItem?.setIcon(R.drawable.ic_baseline_search_24)

        //change title of menu
        supportActionBar?.setTitle("FOOTBALL LEAGUE")

        //change color and size  of title
        supportActionBar?.apply {
            title = SpannableString("FOOTBALL LEAGUE").apply {
                setSpan(
                    ForegroundColorSpan(ContextCompat.getColor(this@MainActivity, R.color.white)),
                    0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                setSpan(
                    AbsoluteSizeSpan(18, true),
                    0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }

        return true
    }
    /**
     * Enables back button support. Simply navigates one element up on the stack.
     */
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        return when (item.itemId) {
            R.id.action_search -> {
                navController.navigate(R.id.searshLeagueFragment)
                true
            }
            R.id.action_home -> {
                navController.navigate(R.id.leagueListFragment)
                true
            }
            R.id.action_favorite -> {
                println("favoriteeee")
                navController.navigate(R.id.favoriteLeagueFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}