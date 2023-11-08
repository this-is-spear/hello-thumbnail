package tis

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import tis.common.HelloDto

@Service
class HelloService(private val helloRepository: HelloRepository) {
    val log: reactor.util.Logger = reactor.util.Loggers.getLogger(this.javaClass)

    fun hello(): Mono<HelloDto> {
        return helloRepository
            .findAll()
            .reduce { t, u -> if (t.id > u.id) return@reduce t else return@reduce u }
            .log(log)
            .switchIfEmpty(helloRepository.save(Hello("hello")))
            .map { HelloDto(it.message) }
            .log(log)
    }

    fun slowlyHello(): Mono<HelloDto> {
        return helloRepository.findAll()
            .reduce { t, u -> if (t.id > u.id) return@reduce t else return@reduce u }
            .log(log)
            .switchIfEmpty(helloRepository.save(Hello("hello")))
            .publishOn(Schedulers.boundedElastic())
            .log(log)
            .doFirst { Thread.sleep(1_000) }
            .log(log)
            .map { HelloDto(it.message) }
            .log(log)
    }

    fun save(message: String): Mono<HelloDto> {
        return helloRepository
            .save(Hello(message))
            .log(log)
            .map { HelloDto(message) }
            .log(log)
    }

    fun findAll(): Flux<HelloDto> {
        return helloRepository
            .findAll()
            .log(log)
            .map { HelloDto(it.message) }
            .log(log)
    }
}