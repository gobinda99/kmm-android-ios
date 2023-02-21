//
//  SignInViewModel.swift
//  iosApp
//
//  Created by Gobinda Deb on 18/02/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import Combine
import shared


struct SignInState {
    var email: TripleState<String> = TripleState(value: "")
    var pass: TripleState<String> = TripleState(value: "")
    var loading: Bool = false
    var success: Bool = false
    var message: String? = nil
}


class SignInViewModel: ObservableObject {
    private let repository: UserRepository
    private let pref: UserDefaults
//    private var cancellables: Set<AnyCancellable> = []
//    var cancellables = Set<AnyCancellable>()


    @Published var uiState: SignInState = SignInState()

    init(repository: UserRepository, pref: UserDefaults) {
        self.repository = repository
        self.pref = pref
    }

    func validate() {
        var newState = uiState
        if uiState.email.value.isBlank {
            newState.email.isError = true
            newState.email.message = "Email is required"
        } else if !uiState.email.value.isValidEmail {
            newState.email.isError = true
            newState.email.message = "Email is incorrect"
        }
        if uiState.pass.value.isBlank {
            newState.pass.isError = true
            newState.pass.message = "Password is required"
        } else if !uiState.pass.value.isValidPassword {
            newState.pass.isError = true
            newState.pass.message = "Password should be at least 8 characters long"
        }

        uiState = newState

        if !newState.email.isError && !newState.pass.isError {
            newState.loading = true
            uiState = newState
        

            
            repository.getUser(email: uiState.email.value)
                .collect(result:{ (value: User?) in
                    // Handle the optional value of type Int
                    if value != nil {
                        newState.loading = false
                        newState.success = true
                        self.uiState = newState
                        self.pref.set(true, forKey: "logIn")
                    } else {
                        newState.loading = false
                        newState.success = false
                        newState.message = "No record found"
                        self.uiState = newState
                    }
                }) { (error) in
                    // Handle the error  value of type Int
                    if let error = error {
                        newState.loading = false
                        newState.message = error.localizedDescription
                        self.uiState = newState
                    }

                }
                    
                        
//                repository.getUser(uiState.email.value)
//                    .sink(
//                        receiveCompletion: { [weak self] completion in
//                            guard let self = self else { return }
//                            switch completion {
//                            case .failure(let error):
//                                newState.loading = false
//                                newState.message = error.localizedDescription
//                                self.uiState = newState
//                            case .finished:
//                                break
//                            }
//                        },
//                        receiveValue: { [weak self] user in
//                            guard let self = self else { return }
//                            if let user = user {
//                                newState.loading = false
//                                newState.success = true
//                                self.uiState = newState
//                                self.pref.set(true, forKey: "logIn")
//                            } else {
//                                newState.loading = false
//                                newState.success = false
//                                newState.message = "No record found"
//                                self.uiState = newState
//                            }
//                        }
//                    )
//                    .store(in: &cancellables)
            
           
        }
    }
    
   private func method(){

            repository.getUser(email: "aa")
            .collect{ (value: User?) in
            // Handle the optional value of type Int
            }
        
        
        repository.getUser(email: "aa")
            .collect(result:{ (value: User?) in
            // Handle the optional value of type Int
            }) { (error) in
                
                // Handle the error  value of type Int

            }
    
    }
        

    func removerEmailError() {
        uiState.email.isError = false
        uiState.message = ""
    }

    func removePassError() {
        uiState.pass.isError = false
        uiState.message = ""
    }

    func anyUserLoggedIn() -> Bool {
        return pref.bool(forKey: "logIn")

    }

    func logout() {
        pref.set(false, forKey: "logIn")
    }
}



