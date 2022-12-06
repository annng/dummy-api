package technicaltest.com.app.data.remote.domain

import retrofit2.Response
import retrofit2.http.*
import technicaltest.com.app.data.model.BaseResponseData
import technicaltest.com.app.data.model.Post
import technicaltest.com.app.data.model.User

interface PostServices {

    @GET("post")
    suspend fun getPost(
        @Query("limit") limit : Int,
        @Query("page") page : Int
    ): Response<BaseResponseData<Post>>

    @GET("user/{id}/post")
    suspend fun getUserPost(
        @Path("id") id : String,
        @Query("limit") limit : Int,
        @Query("page") page : Int
    ): Response<BaseResponseData<Post>>


    @GET("tag/{tag}/post")
    suspend fun getPostByTag(
        @Path("tag") tag : String,
        @Query("limit") limit : Int,
        @Query("page") page : Int
    ): Response<BaseResponseData<Post>>
}