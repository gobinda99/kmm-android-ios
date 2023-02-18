//
//  SignIn.swift
//  iosApp
//
//  Created by Gobinda Deb on 16/02/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct SignIn: View {
    @State var name : String = ""
    
    @State var error : Bool = false
    
    var body: some View {
        
        VStack{
            Text("SignIn")
                .font(.custom("AmericanTypewriter", size: 40).italic())
            
            Spacer().frame(height: 30)
            
            TextField("Name", text: $name)
                .textFieldStyle(RoundedBorderTextFieldStyle())
                .foregroundColor(.black)
                .padding(.horizontal, 20.0)
            
            if error { Text("Name cannot be empty")
                    .frame(maxWidth:.infinity,alignment: .topLeading)
                    .font(.caption)
                    .foregroundColor(.red)
                    .padding(.horizontal, 22.0)
            }
            
            Spacer().frame(height: 20)
            
            SecureField("Password", text: $name)
                .textFieldStyle(RoundedBorderTextFieldStyle())
                .foregroundColor(.black)
                .padding(.horizontal, 20.0)
            
            if(error){ Text("Name cannot be empty")
                    .frame(maxWidth:.infinity,alignment: .topLeading)
                    .font(.caption)
                    .foregroundColor(.red)
                    .padding(.horizontal, 22.0)
            }
            
            Spacer().frame(height: 10)
            
            Text("Forget password?")
                .frame(maxWidth:.infinity,
                       alignment: .topTrailing)
                .font(.caption2)
                .foregroundColor(.black)
                .padding(.horizontal, 22.0)
            
            
            Button("Submit") {
                
            }

        }
    }
}

struct SignIn_Previews: PreviewProvider {
    static var previews: some View {
        SignIn()
    }
}


