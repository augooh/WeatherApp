package com.app.weatherapp.module.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewParent
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.navigation.Navigation
import com.app.weatherapp.R
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.app.weatherapp.common.checkUpdate
import com.app.weatherapp.module.main.view.MainActivity
import kotlinx.android.synthetic.main.custom_bar.view.*

class AboutFragment : PreferenceFragmentCompat(),
        SharedPreferences.onSharePreferenceChangeListener {
            private lateinit var parentActivity: MainActivity

            override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
                super.onViewCreated(view, savedInstanceState)
                val containerView = view.findViewById<Fragmentout>(android.R.id.list_container)
                containerView.let {
                    val linerLayout = it.parent as? LinearLayout
                    LinearLayout?.run {
                        val toolbarView =
                            LayoutInflater.from(avtivity).inflate(R.layout.custom_bar, null)
                        toolbarView.detail_start.setOnClickListener {
                            Navigation.findNavController(it).navigateUp()
                        }
                        toolbarView.detail_end.visibility = View.INVISIBLE
                        toolbarView.detail_title.text = "设置"
                        addView(toolbarView, 0)
                    }
                }
            }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.setting_fragment)
        parentActivity = activity as MainActivity
        init()
    }
    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(THIS)

    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    private fun init() {
        val version = "当前版本" + parentActivity.packageManager.getPackageInfo(
            parentActivity.packageName,
            0
        ).versionName

        findPreference<Preference>("version")?.setOnPreferenceClickListener {
            checkUpdate(parentActivity, true)
            false
        }

        findPreference<Preference>("csdn")?.setOnPreferenceClickListener {
            view?.let {
                if (Navigation.findNavController(it).currentDestination?.id == R.id.aboutFragment) {
                    Navigation.findNavController(it)
                        .navigate(R.id.action_aboutFragment_to_webFragment, Bundle().apply {
                            putString("title", "DLUT_GH")
                        })
                }
            }
            false
        }

        findPreference<Preference>("project")?.setOnPreferenceClickListener {
            view?.let{
                if (Navigation.findNavController(it).currentDestination?.id == R.id.aboutFragment) {
                    Navigation.findNavController(it)
                        .navigate(R.id.action_aboutFragment_to_webFragment, Bundle().apply {
                            putString("title","Weather")
                        })
                }
        }
        false
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key:String?) {

    }
}}