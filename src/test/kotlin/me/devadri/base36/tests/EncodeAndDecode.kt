package me.devadri.base36.tests

import me.devadri.base36.Base36
import kotlin.test.Test
import kotlin.test.assertEquals

class EncodeAndDecode {

    private val ogText = "Hey! This is a test"
    private val encryptedText = "16T8FTP50GBACXHZ40C65R81LAUELW"

    @Test
    fun encode() {
        assertEquals(encryptedText, Base36.encode(ogText))
    }

    @Test
    fun decode() {
        assertEquals(ogText, Base36.decode(encryptedText))
    }
}