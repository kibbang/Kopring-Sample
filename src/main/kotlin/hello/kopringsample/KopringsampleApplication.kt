package hello.kopringsample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KopringsampleApplication

fun main(args: Array<String>) {
    runApplication<KopringsampleApplication>(*args)
}
