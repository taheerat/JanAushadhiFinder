package com.janaushadhi.finder.data.local.session

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(
    @ApplicationContext context: Context
) {
    private val prefs: SharedPreferences = context.getSharedPreferences("janaushadhi_prefs", Context.MODE_PRIVATE)

    fun saveUser(name: String, phone: String) {
        prefs.edit().apply {
            putString("user_name", name)
            putString("user_phone", phone)
            putBoolean("is_logged_in", true)
            apply()
        }
    }

    fun getUserName(): String = prefs.getString("user_name", "") ?: ""
    fun getUserPhone(): String = prefs.getString("user_phone", "") ?: ""
    fun isLoggedIn(): Boolean = prefs.getBoolean("is_logged_in", false)

    fun logout() {
        prefs.edit().clear().apply()
    }
}
