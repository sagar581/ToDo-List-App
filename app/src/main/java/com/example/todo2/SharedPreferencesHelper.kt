package com.example.todo2
import android.content.Context

object SharedPreferencesHelper {

    private const val PREF_NAME = "user_prefs"
    var un = ""
    fun saveUserLoginStatus(context: Context, isLoggedIn: Boolean,username: String) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        un=username
        editor.putBoolean("isLoggedIn", isLoggedIn)
        editor.apply()
    }

    fun getUserLoginStatus(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }
    fun getUsername(): String{
        return un
    }
}
