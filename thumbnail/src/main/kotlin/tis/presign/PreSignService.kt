package tis.presign

import aws.sdk.kotlin.services.s3.model.PutObjectRequest
import aws.sdk.kotlin.services.s3.presigners.presignPutObject
import org.springframework.stereotype.Service
import tis.S3Config
import tis.s3Client
import kotlin.time.Duration.Companion.hours

@Service
class PreSignService(
    private val s3Config: S3Config
) {
    suspend fun getPreSigned(filename: String): String {
        val unsignedRequest = PutObjectRequest {
            bucket = s3Config.bucket
            key = filename
        }

        val preSignedRequest = s3Client(s3Config)
            .presignPutObject(unsignedRequest, 1.hours)

        return preSignedRequest.url.toString()
    }
}

