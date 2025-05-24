import androidx.compose.runtime.Composition
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.window.ComposeUIViewController
import org.abanapps.regal_time.store.App
import org.abanapps.regal_time.store.NativeViewFactory
import org.abanapps.regal_time.store.di.initializeKoin
import platform.UIKit.UIViewController

val LocalNativeViewFactory = staticCompositionLocalOf<NativeViewFactory> {
error("No view Factory provided.")
}

fun MainViewController(
    nativeViewFactory: NativeViewFactory
) = ComposeUIViewController(
    configure = { initializeKoin()  }
) {

    CompositionLocalProvider(LocalNativeViewFactory provides nativeViewFactory){
        App()
    }

}