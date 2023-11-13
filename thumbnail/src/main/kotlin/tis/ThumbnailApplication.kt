package tis

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import reactor.core.publisher.Hooks

@SpringBootApplication
@ConfigurationPropertiesScan
class ThumbnailApplication

fun main(args: Array<String>) {
    Hooks.enableAutomaticContextPropagation();
    runApplication<ThumbnailApplication>(*args)
}
