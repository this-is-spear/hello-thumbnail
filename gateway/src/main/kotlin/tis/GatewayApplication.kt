package tis

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import reactor.core.publisher.Hooks

@SpringBootApplication
class GatewayApplication

fun main(args: Array<String>) {
    Hooks.enableAutomaticContextPropagation();
    runApplication<GatewayApplication>(*args)
}