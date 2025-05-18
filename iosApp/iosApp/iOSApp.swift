import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    
    init(){
        GetNativeResponse_iosKt.nativeResponseProvider = IOSNativeResponseProvider()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
