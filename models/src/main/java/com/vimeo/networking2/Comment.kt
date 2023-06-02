@file:JvmName("CommentUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.CommentType
import com.vimeo.networking2.enums.asEnum
import java.util.Date

/**
 * Comment data.
 *
 * @param uri The URI of this comment.
 * @param createdOn The time in ISO 8601 format when the comment was posted.
 * @param metadata Metadata for comments.
 * @param rawType The Vimeo content to which the comment relates. See [Comment.type].
 * @param resourceKey The resource key string for the comment.
 * @param text The content of the comment.
 * @param user The user who posted the comment.
 */
@JsonClass(generateAdapter = true)
data class Comment(

    @Json(name = "uri")
    override val uri: String? = null,

    @Json(name = "created_on")
    override val createdOn: Date? = null,

    @Json(name = "metadata")
    val metadata: MetadataConnections<CommentConnections>? = null,

    @Json(name = "type")
    val rawType: String? = null,

    @Json(name = "resource_key")
    override val resourceKey: String? = null,

    @Json(name = "text")
    override val text: String? = null,

    @Json(name = "user")
    override val user: User? = null

) : AbstractComment

/**
 * @see Comment.rawType
 * @see CommentType
 */
val Comment.type: CommentType
    get() = rawType.asEnum(CommentType.UNKNOWN)
