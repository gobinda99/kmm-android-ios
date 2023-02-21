//
//  StringExt.swift
//  iosApp
//
//  Created by Gobinda Deb on 20/02/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation


extension String {
    var isBlank: Bool {
        return allSatisfy({ $0.isWhitespace })
    }

    var isValidEmail: Bool {
        // Implement email validation logic
        return true
    }

    var isValidPassword: Bool {
        // Implement password validation logic
        return true
    }
}
