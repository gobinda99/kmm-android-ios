//
//  SignUpView.swift
//  iosApp
//
//  Created by Gobinda Deb on 20/02/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct SignupView: View {
    
    @Binding var showSignUp: Bool
    
    @State private var email = ""
    @State private var name = ""
    @State private var password = ""
    @State private var confirmPassword = ""
    
    @State private var emailError = "hi"
    @State private var nameError = ""
    @State private var passwordError = ""
    @State private var confirmPasswordError = ""
    
    var body: some View {
        VStack {
            
            Text("Sign Up")
                    .font(.custom("AmericanTypewriter", size: 30).italic())
                    .padding(.bottom, 30)
            
            
            Group{
                TextField("Email", text: $email, onEditingChanged: { _ in
                    emailError = ""
                })
                .padding()
                .autocapitalization(.none)
                .keyboardType(.emailAddress)
                .disableAutocorrection(true)
                .overlay(
                    RoundedRectangle(cornerRadius: 10)
                        .stroke(Color.red, lineWidth: 1)
                        .opacity(emailError.isEmpty ? 0 : 1)
                )
                
                if !emailError.isEmpty {
                    Text(emailError)
                        .font(.caption)
                        .frame(maxWidth:.infinity,
                               alignment: .topLeading)
                        .foregroundColor(.red)
                        .padding(.bottom, 5)
                        .padding(.horizontal)
                }
                
                TextField("Name", text: $name, onEditingChanged: { _ in
                    nameError = ""
                })
                .padding()
                .overlay(
                    RoundedRectangle(cornerRadius: 10)
                        .stroke(Color.red, lineWidth: 1)
                        .opacity(nameError.isEmpty ? 0 : 1)
                )
                
                if !nameError.isEmpty {
                    Text(nameError)
                        .font(.caption)
                        .frame(maxWidth:.infinity,
                               alignment: .topLeading)
                        .foregroundColor(.red)
                        .padding(.bottom, 5)
                        .padding(.horizontal)
                }
                
                SecureField("Password", text: $password)
                .padding()
                .autocapitalization(.none)
                .overlay(
                    RoundedRectangle(cornerRadius: 10)
                        .stroke(Color.red, lineWidth: 1)
                        .opacity(passwordError.isEmpty ? 0 : 1)
                )
                
                if !passwordError.isEmpty {
                    Text(passwordError)
                        .font(.caption)
                        .frame(maxWidth:.infinity,
                               alignment: .topLeading)
                        .foregroundColor(.red)
                        .padding(.bottom, 5)
                        .padding(.horizontal)
                }
                
                SecureField("Confirm Password", text: $confirmPassword)
                .padding()
                .autocapitalization(.none)
                .overlay(
                    RoundedRectangle(cornerRadius: 10)
                        .stroke(Color.red, lineWidth: 1)
                        .opacity(confirmPasswordError.isEmpty ? 0 : 1)
                )
                
                if !confirmPasswordError.isEmpty {
                    Text(confirmPasswordError)
                        .font(.caption)
                        .frame(maxWidth:.infinity,
                               alignment: .topLeading)
                        .foregroundColor(.red)
                        .padding(.bottom, 5)
                        .padding(.horizontal)
                }
            }
            
            
            
            Button(action: {
                validateFields()
            }) {
                Text("Sign Up")
                    .foregroundColor(.white)
                    .padding()
                    .frame(maxWidth: .infinity)
                    .background(Color.blue)
                    .cornerRadius(30)
            }
            .padding(.top, 30)
            
            Text("SignIn here")
                .foregroundColor(.black)
                .underline()
                .padding(.top)
                .onTapGesture {
                        showSignUp = false
                }
                
        }
        .padding(40)
    }
    
    func validateFields() {
        
    }
    
}



struct SignUpView_Previews: PreviewProvider {
    @State private var showSignUp = false
    static var previews: some View {
        SignupView(showSignUp: .constant(true))
    }
}
