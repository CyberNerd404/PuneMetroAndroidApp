package com.cypherx.punemetro.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.cypherx.punemetro.R
import com.cypherx.punemetro.ui.about.detail.AboutusFragment
import com.cypherx.punemetro.ui.about.detail.FeedbackFragment
import com.cypherx.punemetro.ui.about.detail.InfoFragment

class AboutFragment : Fragment() {

    private lateinit var slideshowViewModel: AboutViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
                ViewModelProviders.of(this).get(AboutViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_about, container, false)
        val infobtn = root.findViewById(R.id.infoButton) as Button
        val feedbackbtn = root.findViewById(R.id.feedbackButton) as Button
        val aboutbtn = root.findViewById(R.id.aboutButton) as Button

//        val textView: TextView = root.findViewById(R.id.text_slideshow)
//        slideshowViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        infobtn.setOnClickListener {
            val fragment = fragmentManager?.beginTransaction()
            fragment?.replace(R.id.framelayoutAbout, InfoFragment())
            fragment?.commit()
        }

        feedbackbtn.setOnClickListener {
            val fragment = fragmentManager?.beginTransaction()
            fragment?.replace(R.id.framelayoutAbout, FeedbackFragment())
            fragment?.commit()
        }

        aboutbtn.setOnClickListener {
            val fragment = fragmentManager?.beginTransaction()
            fragment?.replace(R.id.framelayoutAbout, AboutusFragment())
            fragment?.commit()
        }


        return root
    }

}
