package tis

import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
import aws.sdk.kotlin.services.s3.S3Client
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "s3.config")
class S3Config(
    val bucket: String,
    val region: String,
    val accessKeyId: String,
    val secretAccessKey: String,
)

suspend fun s3Client(
    s3Config: S3Config
) = S3Client.fromEnvironment {
    region = s3Config.region
    credentialsProvider = StaticCredentialsProvider {
        accessKeyId = s3Config.accessKeyId
        secretAccessKey = s3Config.secretAccessKey
    }
}
