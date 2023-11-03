package tis

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Hello(
    val message: String,
    @Id val id: ObjectId = ObjectId(),
)
