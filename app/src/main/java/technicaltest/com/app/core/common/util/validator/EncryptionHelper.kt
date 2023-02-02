package technicaltest.com.app.core.common.util.validator

import android.util.Base64
import android.util.Log
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class EncryptionHelper {
    private val keySize = 256
    private val iterationCount = 1000
    private val saltSize = 8
    private val ivSize = 16
    private var iv  : IvParameterSpec? = null

    fun generateKey(): SecretKey {
        val keyGenerator = KeyGenerator.getInstance("AES")
        keyGenerator.init(keySize)
        return keyGenerator.generateKey()
    }

    fun encryptObject(data: Any, secretKey: SecretKey): ByteArray {
        val baos = ByteArrayOutputStream()
        val oos = ObjectOutputStream(baos)
        oos.writeObject(data)
        oos.flush()
        val dataBytes = baos.toByteArray()

        val cipher = Cipher.getInstance("AES")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        return cipher.doFinal(dataBytes)
    }

    fun decryptObject(encryptedData: ByteArray, secretKey: SecretKey): Any {
        val cipher = Cipher.getInstance("AES")
        cipher.init(Cipher.DECRYPT_MODE, secretKey)
        val decryptedBytes = cipher.doFinal(encryptedData)

        val bais = ByteArrayInputStream(decryptedBytes)
        val ois = ObjectInputStream(bais)
        return ois.readObject()
    }
}
