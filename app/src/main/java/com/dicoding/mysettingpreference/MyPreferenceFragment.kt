package com.dicoding.mysettingpreference

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.CheckBoxPreference
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat

class MyPreferenceFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {
    private lateinit var NAME: String
    private lateinit var EMAIL: String
    private lateinit var AGE: String
    private lateinit var PHONE: String
    private lateinit var LOVE: String

    private var namePreference: EditTextPreference? = null
    private var emailPreference: EditTextPreference? = null
    private var agePreference: EditTextPreference? = null
    private var phonePreference: EditTextPreference? = null
    private var isLoveMuPreference: CheckBoxPreference? = null

    companion object {
        private const val DEFAULT_VALUE = "Tidak Ada"
    }

    override fun onCreatePreferences(bundle: Bundle?, s: String?) {
        addPreferencesFromResource(R.xml.preferences)
        init()
        setSummaries()
    }

    private fun init() {
        NAME = resources.getString(R.string.key_name)
        EMAIL = resources.getString(R.string.key_email)
        AGE = resources.getString(R.string.key_age)
        PHONE = resources.getString(R.string.key_phone)
        LOVE = resources.getString(R.string.key_love)

        namePreference = findPreference(NAME)
        emailPreference = findPreference(EMAIL)
        agePreference = findPreference(AGE)
        phonePreference = findPreference(PHONE)
        isLoveMuPreference = findPreference(LOVE)
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences?.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences?.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String?) {
        when (key) {
            NAME -> namePreference?.summary = sharedPreferences.getString(NAME, DEFAULT_VALUE)
            EMAIL -> emailPreference?.summary = sharedPreferences.getString(EMAIL, DEFAULT_VALUE)
            AGE -> agePreference?.summary = sharedPreferences.getString(AGE, DEFAULT_VALUE)
            PHONE -> phonePreference?.summary = sharedPreferences.getString(PHONE, DEFAULT_VALUE)
            LOVE -> isLoveMuPreference?.isChecked = sharedPreferences.getBoolean(LOVE, false)
        }
    }

    private fun setSummaries() {
        val sh = preferenceManager.sharedPreferences
        namePreference?.summary = sh?.getString(NAME, DEFAULT_VALUE)
        emailPreference?.summary = sh?.getString(EMAIL, DEFAULT_VALUE)
        agePreference?.summary = sh?.getString(AGE, DEFAULT_VALUE)
        phonePreference?.summary = sh?.getString(PHONE, DEFAULT_VALUE)
        isLoveMuPreference?.isChecked = sh?.getBoolean(LOVE, false) ?: false
    }
}
