package themulti0.eatthatsit.ui.counter

import android.content.SharedPreferences

class CounterDatabase(private val preferences: SharedPreferences) {
    var counter: String
        get() = preferences.getString("person", "0")!!
        set(value: String) {
            preferences.edit().putString("person", value).apply()
        }
}