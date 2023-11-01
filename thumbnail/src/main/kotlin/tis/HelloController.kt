package tis

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import tis.common.Hello

@RestController
class HelloController {
    @GetMapping("/hello")
    fun hello(): Mono<Hello> {
        return Mono.just(Hello("hello"))
    }
}