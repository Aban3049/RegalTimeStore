//
//  IOSNativeResponseProvider.swift
//  iosApp
//
//  Created by Muhammad Aban on 16/05/2025.
//

import ComposeApp

class IOSNativeResponseProvider:NativeResponseProvider{
    
    func getNativeResponse(input: Int32) -> Int32 {
       return input + 100
    }
    
}
