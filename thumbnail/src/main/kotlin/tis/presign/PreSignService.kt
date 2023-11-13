package tis.presign

import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
import aws.sdk.kotlin.services.s3.S3Client
import aws.sdk.kotlin.services.s3.model.PutObjectRequest
import aws.sdk.kotlin.services.s3.presigners.presignPutObject
import org.springframework.stereotype.Service
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

        val environmentCredentialsProvider = StaticCredentialsProvider {
            accessKeyId = s3Config.accessKeyId
            secretAccessKey = s3Config.secretAccessKey
        }

        val preSignedRequest = S3Client
            .fromEnvironment {
                region = s3Config.region
                credentialsProvider = environmentCredentialsProvider
            }
            .presignPutObject(unsignedRequest, 24.hours)

        return preSignedRequest.url.toString()
    }
}

