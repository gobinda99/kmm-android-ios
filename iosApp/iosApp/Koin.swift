//
//  Koin.swift
//  iosApp
//
//  Created by Gobinda Deb on 21/02/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

func startKoin() {
    // You could just as easily define all these dependencies in Kotlin,
    // but this helps demonstrate how you might pass platform-specific
    // dependencies in a larger scale project where declaring them in
    // Kotlin is more difficult, or where they're also used in
    // iOS-specific code.

    let userDefaults = UserDefaults(suiteName: "SAMPLE_SETTINGS")!
    let doOnStartup = { NSLog("Hello from iOS/Swift!") }

    let koinApplication = KoinIOSKt.doInitKoinIos(
        userDefaults: userDefaults,
        doOnStartup: doOnStartup
    )
    _koin = koinApplication.koin
}

private var _koin: Koin_coreKoin?
var koin: Koin_coreKoin {
    return _koin!
}





