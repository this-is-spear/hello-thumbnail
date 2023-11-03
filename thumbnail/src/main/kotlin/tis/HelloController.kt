package tis

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import tis.common.HelloDto

@RestController
class HelloController (private val helloService: HelloService) {
    @GetMapping("/hello")
    fun hello(): Mono<HelloDto> {
        return helloService.hello()
    }

    @GetMapping("/slow-hello")
    fun slowHello(): Mono<HelloDto> {
        println("slow")
        return helloService.slowlyHello()
    }
}