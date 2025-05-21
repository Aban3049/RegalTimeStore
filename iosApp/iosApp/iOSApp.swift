import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    
    @UIApplicationDelegateAdaptor(AppDelegate.self) var delegate
    
    init(){
        GetNativeResponse_iosKt.nativeResponseProvider = IOSNativeResponseProvider()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
