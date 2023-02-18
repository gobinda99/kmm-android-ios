import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.logging.*

internal fun client(engine: HttpClientEngine) = HttpClient(engine) {
    expectSuccess = true

    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Napier.v { message }
            }
        }
        level = LogLevel.INFO
    }
    install(HttpTimeout) {
        val timeout = 30000L
        connectTimeoutMillis = timeout
        requestTimeoutMillis = timeout
        socketTimeoutMillis = timeout
    }
}
