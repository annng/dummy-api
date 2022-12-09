package technicaltest.com.app.data.local.sample

import technicaltest.com.app.data.local.entity.PostEntity
import technicaltest.com.app.data.model.Location
import technicaltest.com.app.data.model.User

object SamplePost {
    val location = Location(
        "Jl Sisingamangaraja IX",
        "Yogyakarta",
        "DI Yogyakarta",
        "Indonesia",
        "GMT+7"
    )

    val person = User(
        "Nayanika",
        "1",
        "Betelgeuse",
        "http://img.com/picture-profile.png",
        "Mr",
        "Male",
        "Nayanika@email.com",
        "1990-12-31",
        "2022-12-05",
        "+62874943323",
        location
    )
    val post = PostEntity(
        "1",
        "Labrador",
        "http://sampleicon.com/labrador.png",
        2,
        arrayListOf("Dog", "Labrador", "Brown"),
        "http://website.labrador.com",
        "2022-12-24",
        person,
    )
}