package org.d3if3014.asesment_mobpro.network

import MessageResponse
import Zakat
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://bukti-zakat.vercel.app/"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()



private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface UserApi {
    @Multipart
    @POST("zakats/")
    suspend fun addData(
        @Part("nama") title: RequestBody,
        @Part("deskripsi") type: RequestBody,
        @Part("user_email") userEmail: RequestBody,
        @Part file: MultipartBody.Part
    ): Zakat

    @GET("zakats/")
    suspend fun getAllData(
        @Query("user_email") email: String,
    ): List<Zakat>

    @DELETE("zakats/{zakat_id}")
    suspend fun deleteData(
        @Path("zakat_id") id: Int,
        @Query("email") email: String
    ): MessageResponse
}


object Api {
    val userService: UserApi by lazy {
        retrofit.create(UserApi::class.java)
    }

    fun getImageUrl(imageId: String): String{
        return BASE_URL + "zakats/images/$imageId"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }