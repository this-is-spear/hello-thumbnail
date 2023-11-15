package tis.thumbnail;

import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ThumbnailController(
    private val thumbnailApplicationService: ThumbnailApplicationService
) {
    @GetMapping(
        value = ["/thumnails"],
        produces = [MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE]
    )
    suspend fun getImage(@RequestParam filename: String): ResponseEntity<ByteArray> {
        return thumbnailApplicationService.getImage(filename).let {
            ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment")
                .header("filename", it.filename)
                .body(it.file)
        }
    }

    @GetMapping(
        value = ["/thumnails/resize"],
        produces = [MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE]
    )
    suspend fun getResizedImage(
        @RequestParam filename: String,
        @RequestParam height: Int,
        @RequestParam width: Int
    ): ResponseEntity<ByteArray> {
        return thumbnailApplicationService.getImage(filename, height, width).let {
            ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment")
                .header("filename", it.filename)
                .body(it.file)
        }
    }

    @GetMapping("/thumnails/all")
    suspend fun getImages(): ThumbnailResponses {
        return thumbnailApplicationService.getImages()
    }
}
