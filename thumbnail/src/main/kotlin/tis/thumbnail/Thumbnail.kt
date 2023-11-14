package tis.thumbnail

import net.coobird.thumbnailator.Thumbnails
import org.slf4j.LoggerFactory
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

private val log = LoggerFactory.getLogger("thumbnail")

/**
 * 데코레터패턴에 기능을 추하기 위해서는 인자의 content() 메서드를 활용해야 합니다.
 */
interface Thumbnail {
    val name: String
    val content: ByteArray
    val height: Int
    val width: Int
    fun content(): ByteArray
}

class ThumbnailConcrete(
    override val name: String,
    override val height: Int,
    override val width: Int,
    override val content: ByteArray,
) : Thumbnail {
    override fun content(): ByteArray {
        return content
    }
}

abstract class ThumbnailDecorator(
    private val thumbnail: Thumbnail
) : Thumbnail by thumbnail {
    override fun content(): ByteArray {
        return thumbnail.content()
    }
}

class ThumbnailResizer(
    private val thumbnail: Thumbnail
) : ThumbnailDecorator(thumbnail) {
    override fun content(): ByteArray {
        log.info("execute thumbnail resizer")
        ByteArrayOutputStream().use { outputStream ->
            ByteArrayInputStream(thumbnail.content()).use { inputStream ->
                Thumbnails.of(inputStream)
                    .width(thumbnail.width)
                    .height(thumbnail.height)
                    .toOutputStream(outputStream)
            }
            log.info("end thumbnail resizer")
            return outputStream.toByteArray()
        }
    }
}

class ThumbnailSampleDecorator(
    private val thumbnail: Thumbnail
) : ThumbnailDecorator(thumbnail) {
    override fun content(): ByteArray {
        log.info("execute thumbnail sample decorator")
        return thumbnail.content()
    }
}