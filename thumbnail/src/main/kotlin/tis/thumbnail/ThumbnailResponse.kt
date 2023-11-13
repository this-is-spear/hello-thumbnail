package tis.thumbnail

data class ThumbnailResponse(
    val filename: String,
    val file: ByteArray? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ThumbnailResponse

        return filename == other.filename
    }

    override fun hashCode(): Int {
        return filename.hashCode()
    }
}
