package tis.thumbnail;

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ThumbnailController(
    private val thumbnailService: ThumbnailService
) {
    @GetMapping("/thumnails")
    suspend fun getImage(@RequestParam(required = true) filename: String): ThumbnailResponse {
        return thumbnailService.getImage(filename)
    }

    @GetMapping("/thumnails/all")
    suspend fun getImages(): ThumbnailResponses {
        return thumbnailService.getImages()
    }
}
