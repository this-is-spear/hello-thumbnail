package tis

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.util.*

@Repository
interface HelloRepository : ReactiveMongoRepository<Hello, Long>{
    override fun <S : Hello> save(entity: S): Mono<S>
    override fun findById(id: Long): Mono<Hello>
}
