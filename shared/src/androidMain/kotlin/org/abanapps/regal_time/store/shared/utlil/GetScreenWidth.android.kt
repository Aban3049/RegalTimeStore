package org.abanapps.regal_time.store.shared.utlil

actual fun getScreenWidth(): Float {
    return android.content.res.Resources.getSystem().displayMetrics.widthPixels / android.content.res.Resources.getSystem().displayMetrics.density
}