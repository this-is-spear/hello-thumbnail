package tis

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import tis.common.HelloDto

@Service
class HelloService(private val helloRepository: HelloRepository) {
    fun hello(): Mono<HelloDto> {
        return helloRepository.findAll().reduce { t, u -> if (t.id!! > u.id!!) return@reduce t else return@reduce u }
            .switchIfEmpty(helloRepository.save(Hello("hello")))
            .map { HelloDto(it.message) }
    }

    fun slowlyHello(): Mono<HelloDto> {
        return helloRepository.findAll().reduce { t, u -> if (t.id!! > u.id!!) return@reduce t else return@reduce u }
            .switchIfEmpty(helloRepository.save(Hello("hello")))
            .publishOn(Schedulers.boundedElastic())
            .doFirst {Thread.sleep(1_000)}
            .map { HelloDto(it.message) }
    }
}