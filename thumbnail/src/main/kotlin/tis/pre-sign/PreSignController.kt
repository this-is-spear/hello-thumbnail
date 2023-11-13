package tis.`pre-sign`

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class PreSignController(
    private val s3Api: PreSignService
) {

    @GetMapping("pre-signed")
    suspend fun getPreSignedUrl(@RequestParam filename: String): String {
        return s3Api.getPreSigned(filename)
    }
}