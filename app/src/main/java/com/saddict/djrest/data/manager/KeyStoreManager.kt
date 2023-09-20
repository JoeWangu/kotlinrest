package com.saddict.djrest.data.manager

/*import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.io.InputStream
import java.io.OutputStream
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec


class KeyStoreManager {
    private val keyStore = KeyStore.getInstance("AndroidKeystore").apply {
        load(null)
    }

    private val encryptCipher = Cipher.getInstance(TRANSFORMATION).apply {
        init(Cipher.ENCRYPT_MODE, getKey())
    }

    private fun getDecryptCipherForIv(iv: ByteArray): Cipher{
        return Cipher.getInstance(TRANSFORMATION).apply {
            init(Cipher.DECRYPT_MODE, getKey(), IvParameterSpec(iv))
        }
    }

    private fun getKey(): SecretKey{
        val existingKey = keyStore.getEntry("secret", null) as? KeyStore.SecretKeyEntry
        return existingKey?.secretKey ?: createKey()
    }

    private fun createKey(): SecretKey{
        return KeyGenerator.getInstance(ALGORITHM).apply {
            init(
                KeyGenParameterSpec.Builder(
                    "secret", KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                )
                    .setBlockModes(BLOCK_MODE)
                    .setEncryptionPaddings(PADDING)
                    .setUserAuthenticationRequired(false)
                    .setRandomizedEncryptionRequired(true)
                    .build()
            )
        }.generateKey()
    }

//    @RequiresApi(Build.VERSION_CODES.O)
//    fun encryptAndSave(bytes: ByteArray){
//        val encryptedToken = encryptCipher.doFinal(bytes)
//        val passwordProtection = KeyStore.PasswordProtection(null)
//        keyStore.setEntry(keyAlias, KeyStore.SecretKeyEntry(getKey()), passwordProtection)
//    }

//    fun encrypt(bytes: ByteArray): Pair<ByteArray, ByteArray> {
//        val encryptedBytes = encryptCipher.doFinal(bytes)
//        return Pair(encryptCipher.iv, encryptedBytes)
//    }
//
//    fun decrypt(iv: ByteArray, encryptedBytes: ByteArray): ByteArray {
//        return getDecryptCipherForIv(iv).doFinal(encryptedBytes)
//    }


    fun encrypt(
        bytes: ByteArray,
        outputStream: OutputStream
    ): ByteArray{
        val encryptedBytes = encryptCipher.doFinal(bytes)
        outputStream.use {
            it.write(encryptCipher.iv.size)
            it.write(encryptCipher.iv)
            it.write(encryptedBytes.size)
            it.write(encryptedBytes)
        }
        return encryptedBytes
    }

    fun decrypt(inputStream: InputStream): ByteArray{
        return inputStream.use {
            val ivSize = it.read()
            val iv = ByteArray(ivSize)
            it.read()

            val encryptedBytesSize = it.read()
            val encryptedBytes = ByteArray(encryptedBytesSize)
            it.read()

            getDecryptCipherForIv(iv).doFinal(encryptedBytes)
        }
    }

    companion object{
        private const val ALGORITHM = KeyProperties.KEY_ALGORITHM_AES
        private const val BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC
        private const val PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7
        private const val TRANSFORMATION = "$ALGORITHM/$BLOCK_MODE/$PADDING"
//        private const val keyAlias = "MY_AUTH_TOKEN_KEY"
    }
}*/