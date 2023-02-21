import SwiftUI
import shared

struct ContentView: View {
	let greet = Greeting().greet()
    
    init(){
        startKoin()
    }
    
    @State private var showSignUp = false
    

	var body: some View {
        
        if showSignUp {
                    SignupView(showSignUp: $showSignUp)
                } else {
                    SignInView(showSignUp: $showSignUp)
                }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
