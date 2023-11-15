package tis.thumbnail;

import org.springframework.stereotype.Service

@Service
class ThumbnailApplicationService(
    private val thumbnailService: ThumbnailService
) {
    suspend fun getImage(filename: String): ThumbnailResponse {
        return thumbnailService.getImage(filename)
    }

    suspend fun getImage(filename: String, height: Int, weight: Int): ThumbnailResponse {
        return thumbnailService.getImage(filename).let { response ->
            response.file?.let {
                ThumbnailConcrete(response.filename, height, weight, it)
            }
        }?.let {
            ThumbnailResizer(it)
        }?.let {
            ThumbnailSampleDecorator(it)
        }?.let {
            ThumbnailResponse(it.name, it.content())
        } ?: ThumbnailResponse(filename)
    }

    suspend fun getImages(): ThumbnailResponses {
        return thumbnailService.getImages()
    }
}
