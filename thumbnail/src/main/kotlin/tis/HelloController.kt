package tis

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
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
        return helloService.slowlyHello()
    }

    @GetMapping("/no")
    fun noHello(): Mono<HelloDto> {
        return Mono.just(HelloDto("no..."))
    }
    @GetMapping("/add")
    fun add(@RequestParam message: String): Mono<HelloDto> {
        return helloService.save(message)
    }

    @GetMapping("/find")
    fun find(): Flux<HelloDto> {
        return helloService.findAll()
    }
}