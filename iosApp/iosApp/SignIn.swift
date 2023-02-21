//
//  SignIn.swift
//  iosApp
//
//  Created by Gobinda Deb on 16/02/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SignInView: View {
    
    @Binding var showSignUp: Bool
    
//    @State private var email = ""
//    @State private var password = ""
//
//    @State private var emailError = "hi"
//    @State private var passwordError = "hi"
    
    @StateObject var vm = SignInViewModel(repository: KotlinDependencies.shared.getUserRepo() , pref : UserDefaults(suiteName: "SAMPLE_SETTINGS")!)
    
    var body: some View {
        
        VStack{
            Text("SignIn")
                .font(.custom("AmericanTypewriter", size: 30).italic())
                .padding(.bottom, 30)
            
            Group{
                
                TextField("Email", text: $vm.uiState.email.value, onEditingChanged: { _ in
                    vm.removerEmailError()
                    
                })
                .padding()
                .autocapitalization(.none)
                .keyboardType(.emailAddress)
                .disableAutocorrection(true)
                .overlay(
                    RoundedRectangle(cornerRadius: 10)
                        .stroke(Color.red, lineWidth: 1)
                        .opacity(!vm.uiState.email.isError ? 0 : 1)
                )
                
                if vm.uiState.email.isError {
                    Text(vm.uiState.email.message)
                        .font(.caption)
                        .frame(maxWidth:.infinity,
                               alignment: .topLeading)
                        .foregroundColor(.red)
                        .padding(.bottom, 5)
                        .padding(.horizontal)
                }
                
                            
                SecureField("Password", text:$vm.uiState.pass.value )
                    .onChange(of: vm.uiState.pass.value, perform: { _ in
                        vm.removePassError()
                    })
                .padding()
                .autocapitalization(.none)
                .overlay(
                    RoundedRectangle(cornerRadius: 10)
                        .stroke(Color.red, lineWidth: 1)
                        .opacity(!vm.uiState.pass.isError ? 0 : 1)
                )
                
                if vm.uiState.pass.isError {
                    Text(vm.uiState.pass.message)
                        .font(.caption)
                        .frame(maxWidth:.infinity,
                               alignment: .topLeading)
                        .foregroundColor(.red)
                        .padding(.bottom, 5)
                        .padding(.horizontal)
                }
                
                
                Text("Forget password?")
                    .frame(maxWidth:.infinity,
                           alignment: .topTrailing)
                    .font(.caption2)
                    .foregroundColor(.black)
                
            }
            
            
            
            Button(action: {
                vm.validate()
            }) {
                Text("Submit")
                    .foregroundColor(.white)
                    .padding()
                    .frame(maxWidth: .infinity)
                    .background(Color.blue)
                    .cornerRadius(30)
            }
            .padding(.top, 30)
            
            Text("Signup here")
                .foregroundColor(.black)
                .underline()
                .padding()
                .onTapGesture {
                        showSignUp = true
                }

        }.padding(40)
    }
}

struct SignIn_Previews: PreviewProvider {
    static var previews: some View {
        SignInView(showSignUp: .constant(false))
    }
}


