package org.abanapps.regal_time.store
import platform.UIKit.UIViewController

interface NativeViewFactory {

    fun createButtonView(
        label: String,
        onClick: () -> Unit
    ): UIViewController

}