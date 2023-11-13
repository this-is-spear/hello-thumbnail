import org.jetbrains.kotlin.ir.backend.js.compile
import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks
val jar: Jar by tasks

bootJar.enabled = true
jar.enabled = false

plugins {
    id("com.google.osdetector") version "1.7.0"
}

dependencies {
    implementation(project(":common"))
    if (osdetector.arch.equals("aarch_64")) {
        implementation("io.netty:netty-resolver-dns-native-macos:4.1.79.Final:osx-aarch_64")
    }
    /*
    aws.sdk.kotlin:s3:0.34.5는 베타 버전입니다.
    첨고 자료 : https://docs.aws.amazon.com/sdk-for-kotlin/latest/developer-guide/get-started.html
    참고 자료 : https://github.com/awsdocs/aws-doc-sdk-examples/blob/main/kotlin/services/s3/Readme.md
     */
    implementation("aws.sdk.kotlin:s3:0.34.5-beta")

    /**
     * reactor with kotlin and coroutines
     */
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")

    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.10") {
        version {
            strictly("5.0.0-alpha.10")
        }
    }

    implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-reactor-resilience4j")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("io.micrometer:micrometer-tracing-bridge-brave")
    implementation("io.zipkin.reporter2:zipkin-reporter-brave")
    // https://mvnrepository.com/artifact/net.logstash.logback/logstash-logback-encoder
    implementation("net.logstash.logback:logstash-logback-encoder:7.4")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
}