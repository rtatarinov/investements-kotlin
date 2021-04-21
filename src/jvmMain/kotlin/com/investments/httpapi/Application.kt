import com.investments.httpapi.config.ManualConfig
import com.investments.httpapi.routes.registerCategoriesRoutes
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.ktor.ext.Koin

fun main() {
    val envHttpExternalPort = System.getenv("HTTP_EXTERNAL_PORT")
    val httpExternalPort: Int = envHttpExternalPort?.toInt() ?: 8000

    embeddedServer(Netty, port = httpExternalPort) {
        install(ContentNegotiation) {
            json()
        }

        install(DefaultHeaders)

        install(CallLogging)

        install(Koin) {
            modules(ManualConfig().getCategoryModule())
        }

        install(CORS) {
            method(HttpMethod.Get)
            method(HttpMethod.Post)
            method(HttpMethod.Patch)
            method(HttpMethod.Put)
            method(HttpMethod.Delete)
            anyHost()
        }

        install(Compression) {
            gzip()
        }

        registerCategoriesRoutes()
    }.start(wait = true)
}