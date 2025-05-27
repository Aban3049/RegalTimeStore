package org.abanapps.regal_time.store.shared.utlil

import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIScreen
import platform.CoreGraphics.CGRect
import platform.CoreGraphics.CGRectGetWidth

@OptIn(ExperimentalForeignApi::class)
actual fun getScreenWidth(): Float {
    val bounds: CValue<CGRect> = UIScreen.mainScreen.bounds
    return CGRectGetWidth(bounds).toFloat()
}