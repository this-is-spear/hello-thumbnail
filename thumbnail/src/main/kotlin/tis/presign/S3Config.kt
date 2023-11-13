package tis.presign

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "s3.config")
class S3Config(
    val bucket: String,
    val region: String,
    val accessKeyId: String,
    val secretAccessKey: String,
)