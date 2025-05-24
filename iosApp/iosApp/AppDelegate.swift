//
//  AppDelegate.swift
//  iosApp
//
//  Created by Muhammad Aban on 21/05/2025.
//

import GoogleSignIn
import Firebase


class AppDelegate: NSObject, UIApplicationDelegate {
    
    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {
        FirebaseApp.configure()
        return true
    }
    
}

