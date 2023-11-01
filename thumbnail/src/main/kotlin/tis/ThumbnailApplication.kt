package tis

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ThumbnailApplication

fun main(args: Array<String>) {
    runApplication<ThumbnailApplication>(*args)
}
