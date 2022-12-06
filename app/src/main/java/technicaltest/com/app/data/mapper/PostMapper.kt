package technicaltest.com.app.data.mapper

import technicaltest.com.app.data.local.entity.PostEntity
import technicaltest.com.app.data.model.Post

fun Post.toEntity() : PostEntity{
    return PostEntity(
        id?:"0", text, image, likes, tags, link, publishDate, owner
    )
}

fun PostEntity.toModel() : Post{
    return Post(
        id, text, image, likes, tags, link, publishDate, owner
    )
}