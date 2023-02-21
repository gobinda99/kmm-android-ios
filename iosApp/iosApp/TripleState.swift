//
//  TripleState.swift
//  iosApp
//
//  Created by Gobinda Deb on 20/02/23.
//  Copyright © 2023 orgName. All rights reserved.
//


import Foundation


struct TripleState<T> {
    var value: T
    var isError: Bool = false
    var message: String = "Error"
    
}
