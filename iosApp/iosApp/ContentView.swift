import UIKit
import SwiftUI
import ComposeApp
import GoogleSignIn
import Firebase
import FirebaseCore
import FirebaseAuth

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController(
            nativeViewFactory: IOSNativeViewFactory.shared
        )
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        ComposeView().onOpenURL(perform: { url in
            GIDSignIn.sharedInstance.handle(url)
        })
                /*.ignoresSafeArea(.keyboard)*/ // Compose has own keyboard handler
    }
}



