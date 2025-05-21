//
//  AppDelegate.swift
//  iosApp
//
//  Created by Muhammad Aban on 21/05/2025.
//

import GoogleSignIn
import Firebase
import FirebaseCore
import FirebaseAuth

class AppDelegate: NSObject, UIApplicationDelegate {
    
    func application(
        _application:UIApplication,
        didFinishLaunchingWithOptions lauchOptions:[UIApplication.LaunchOptionsKey:Any]? = nil
    ) -> Bool {
        FirebaseApp.configure()
        return true
    }
    

    func application(
      _ app: UIApplication,
      open url: URL, options: [UIApplication.OpenURLOptionsKey : Any] = [:]
    ) -> Bool {
      var handled: Bool

      handled = GIDSignIn.sharedInstance.handle(url)
      if handled {
        return true
      }

      // Handle other custom URL types.

      // If not handled by this app, return false.
      return false
    }


}
