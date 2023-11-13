package tis.thumbnail;

import aws.sdk.kotlin.services.s3.model.GetObjectRequest
import aws.sdk.kotlin.services.s3.model.ListObjectsRequest
import aws.smithy.kotlin.runtime.content.toByteArray
import org.springframework.stereotype.Service
import tis.S3Config
import tis.s3Client

@Service
class ThumbnailService(
    private val s3Config: S3Config
) {
    suspend fun getImages(): ThumbnailResponses {
        return ThumbnailResponses(s3Client(s3Config).use { s3 ->
            val response = s3.listObjects(ListObjectsRequest {
                bucket = s3Config.bucket
            })
            return@use response.contents?.mapNotNull { myObject ->
                myObject.key?.let {
                    getImage(it)
                }
            } ?: emptyList()
        })
    }

    suspend fun getImage(filename: String): ThumbnailResponse {
        return s3Client(s3Config).use { s3 ->
            runCatching {
                s3.getObject(GetObjectRequest {
                    key = filename
                    bucket = s3Config.bucket
                }) { resp ->
                    return@getObject ThumbnailResponse(filename, resp.body?.toByteArray())
                }
            }.getOrDefault(ThumbnailResponse(filename))
        }
    }
}
