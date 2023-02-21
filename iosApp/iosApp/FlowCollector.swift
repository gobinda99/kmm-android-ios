//
//  FlowCollector.swift
//  iosApp
//
//  Created by Gobinda Deb on 21/02/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

private class FlowCollector<T>: Kotlinx_coroutines_coreFlowCollector {
    
    let fun : (T?) -> Void
    
    init(fun: @escaping (_ value: T?)-> Void) {
        self.fun = fun
    }
    
    func emit(value: Any?, completionHandler: @escaping (Error?) -> Void) {
        fun(value as? T)
    }
    
}



extension Kotlinx_coroutines_coreFlow{
    
    func collect<T>(fun: @escaping (_ value: T?)-> Void) -> Void{
        
        collect(collector: FlowCollector<T>(fun: fun)) { (error) in
            
        }
    }
    
    func collect<T>(result: @escaping (_ value: T?)-> Void, completionHandler: @escaping (Error?) -> Void) -> Void{
        
        collect(collector: FlowCollector<T>(fun: result),completionHandler: completionHandler )
    }
}

