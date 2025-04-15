package me.devadri.base36

import java.math.BigInteger

object Base36 {

    private const val ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    private val BASE = BigInteger.valueOf(ALPHABET.length.toLong())

    @JvmStatic
    fun encode(data: String): String {
        var num = data.toByteArray(Charsets.UTF_8)
            .fold(BigInteger.ZERO) { acc, byte ->
                acc.multiply(BigInteger.valueOf(256)).add(BigInteger.valueOf(byte.toLong() and 0xFF))
            }

        if (num == BigInteger.ZERO) return ALPHABET[0].toString()

        val result = StringBuilder()
        while (num > BigInteger.ZERO) {
            val rem = num.mod(BASE).toInt()
            result.append(ALPHABET[rem])
            num = num.divide(BASE)
        }

        return result.reverse().toString()
    }

    @JvmStatic
    fun decode(encoded: String): String {
        var num = encoded.fold(BigInteger.ZERO) { acc, char ->
            val index = ALPHABET.indexOf(char.uppercaseChar())
            if (index == -1) throw IllegalArgumentException("Invalid character in base36: '$char'")
            acc.multiply(BASE).add(BigInteger.valueOf(index.toLong()))
        }

        if (num == BigInteger.ZERO) return ""

        val bytes = mutableListOf<Byte>()
        while (num > BigInteger.ZERO) {
            bytes.add(num.mod(BigInteger.valueOf(256)).toByte())
            num = num.divide(BigInteger.valueOf(256))
        }

        return bytes.reversed().toByteArray().toString(Charsets.UTF_8)
    }
}