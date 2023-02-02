package technicaltest.com.app.data.local

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import android.util.Log
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

class SharedPreference(context: Context) {
    private val PREF_NAME = "sample_preference"
    private val ENCRYPTION_KEY = "key"

    private val preferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor = preferences.edit()

    fun saveKey(key: SecretKey) {
        if (getKey() == null) {
            val keyBytes = key.encoded
            val keyString = Base64.encodeToString(keyBytes, Base64.DEFAULT)
            editor.putString(ENCRYPTION_KEY, keyString)
            editor.apply()
        }
    }

    fun getKey(): SecretKey? {
        val keyString = preferences.getString(ENCRYPTION_KEY, "")
        return if (!keyString.isNullOrEmpty()) {
            val keyBytes = Base64.decode(keyString, Base64.DEFAULT)
            SecretKeySpec(keyBytes, "AES")
        } else {
            null
        }
    }
}