//
//  SimpleIOSButton.swift
//  iosApp
//
//  Created by Muhammad Aban on 16/05/2025.
//

import SwiftUI
import ComposeApp

class IOSNativeViewFactory: NativeViewFactory{
    
    
    static var shared = IOSNativeViewFactory()
    
    func createButtonView(label: String, onClick: @escaping () -> Void) -> UIViewController {
        let view = SimpleIOSButton(labe: label, action: onClick)
        
        return UIHostingController(rootView: view)
        
    }
    
    
}

struct SimpleIOSButton: View {
    
    var labe: String
    var action: () -> Void
    
    var body: some View {
        Button(action: action){
            Text(labe)
                .font(.headline)
        }
    }
    
}

