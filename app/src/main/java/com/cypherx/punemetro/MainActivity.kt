package com.cypherx.punemetro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.gms.ads.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var mAdView: AdView
    private val mAppUnitId: String by lazy {

        "ca-app-pub-8526043973479269/2013744770"
    }
    private val mInterstitialAdUnitId: String by lazy {

        "ca-app-pub-8526043973479269/1822173081"
    }
    private lateinit var mInterstitialAd: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        mAdView = findViewById(R.id.adView)

        initializeBannerAd(mAppUnitId)

        loadBannerAd()

        mInterstitialAd = InterstitialAd(this)

        initializeInterstitialAd(mAppUnitId)

        loadInterstitialAd(mInterstitialAdUnitId)


        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
            if (mInterstitialAd.isLoaded) {
                mInterstitialAd.show()
            } else {
                Log.d("TAG", "The interstitial ad wasn't loaded yet.")
                startActivity(Intent(this@MainActivity, OtherActivity::class.java))
                finish()
            }
        }
        runAdEvents()
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun runAdEvents() {
        mInterstitialAd.adListener = object : AdListener() {

            // If user clicks on the ad and then presses the back, s/he is directed to DetailActivity.
            override fun onAdClicked() {
                super.onAdOpened()
                mInterstitialAd.adListener.onAdClosed()
            }

            // If user closes the ad, s/he is directed to DetailActivity.
            override fun onAdClosed() {
                startActivity(Intent(this@MainActivity, OtherActivity::class.java))
                finish()
            }
        }
    }

    private fun loadInterstitialAd(mInterstitialAdUnitId: String) {
        mInterstitialAd.adUnitId = mInterstitialAdUnitId
        mInterstitialAd.loadAd(AdRequest.Builder().build())

    }

    private fun initializeInterstitialAd(mAppUnitId: String) {
        MobileAds.initialize(this,mAppUnitId)

    }

    private fun loadBannerAd() {
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }

    private fun initializeBannerAd(mAppUnitId: String) {
        MobileAds.initialize(this, mAppUnitId)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
