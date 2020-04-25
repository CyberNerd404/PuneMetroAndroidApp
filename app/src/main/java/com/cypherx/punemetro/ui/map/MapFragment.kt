package com.cypherx.punemetro.ui.map

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cypherx.punemetro.R
import com.cypherx.punemetro.ui.map.line.Line1and2Fragment
import com.cypherx.punemetro.ui.map.line.Line3Fragment
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds

class MapFragment : Fragment() {

    private val mAppUnitId: String by lazy {

        "ca-app-pub-8526043973479269~9892234790"
    }
    private val mInterstitialAdUnitId: String by lazy {

        "ca-app-pub-8526043973479269~9892234790"
    }
    private lateinit var mInterstitialAd: InterstitialAd

    private lateinit var galleryViewModel: MapViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
                ViewModelProviders.of(this).get(MapViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_map, container, false)
        val lineButton1 = root.findViewById(R.id.line1andline2Buttom) as Button
        val lineButton2 = root.findViewById(R.id.line3Butoon) as Button

        mInterstitialAd = InterstitialAd(requireContext())

        initializeInterstitialAd(mAppUnitId)

        loadInterstitialAd(mInterstitialAdUnitId)


        // ads
        if (mInterstitialAd.isLoaded) {
            mInterstitialAd.show()
        } else {
            Log.d("TAG", "The interstitial ad wasn't loaded yet.")
        }
        runAdEvents()

        // fragment line map

        lineButton1.setOnClickListener {
            val fragment = fragmentManager?.beginTransaction()
            fragment?.replace(R.id.framelayout, Line1and2Fragment())
            fragment?.commit()
        }
        lineButton2.setOnClickListener {
            val fragment = fragmentManager?.beginTransaction()
            fragment?.replace(R.id.framelayout, Line3Fragment())
            fragment?.commit()
        }



        val textView: TextView = root.findViewById(R.id.text_gallery)
        galleryViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
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
//                startActivity(Intent(this@MainActivity, DetailActivity::class.java))
////                finish()
            }
        }
    }

    private fun loadInterstitialAd(mInterstitialAdUnitId: String) {
        mInterstitialAd.adUnitId = mInterstitialAdUnitId
        mInterstitialAd.loadAd(AdRequest.Builder().build())

    }

    private fun initializeInterstitialAd(mAppUnitId: String) {
        MobileAds.initialize(requireContext(),mAppUnitId)


    }







}
