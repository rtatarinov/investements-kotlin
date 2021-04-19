import com.investments.httpapi.domain.routes.registerCategoriesRoutes
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.ktor.ext.Koin
import com.investments.httpapi.domain.category.*

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
            modules(categoriesModule)
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