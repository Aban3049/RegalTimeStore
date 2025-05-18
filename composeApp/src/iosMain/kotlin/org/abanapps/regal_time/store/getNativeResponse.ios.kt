package org.abanapps.regal_time.store

actual fun getNativeResponse(input: Int): Int {
    return nativeResponseProvider.getNativeResponse(input)
}

interface NativeResponseProvider {
    fun getNativeResponse(input: Int): Int
}

lateinit var nativeResponseProvider: NativeResponseProvider