package tis

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface HelloRepository : ReactiveMongoRepository<Hello, ObjectId> {
    override fun <S : Hello> save(entity: S): Mono<S>
    override fun findById(id: ObjectId): Mono<Hello>
}
