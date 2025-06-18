import SwiftUI

@main
struct iOSApp: App {

    init() {
        AppMultiplatform.shared.initialize()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}