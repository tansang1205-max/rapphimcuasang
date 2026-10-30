package com.sang.rapcuasang.data.api

import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

data class MovieResponse(
    val status: Boolean,
    val items: List<Movie>,
    val pagination: Pagination?
)

data class MovieDetailResponse(
    val status: Boolean,
    val movie: MovieDetail,
    val episodes: List<ServerData>
)

data class Movie(
    @SerializedName("_id") val id: String,
    val name: String,
    val slug: String,
    val origin_name: String?,
    val poster_url: String,
    val thumb_url: String,
    val year: Int?
)

data class MovieDetail(
    val name: String,
    val origin_name: String,
    val content: String,
    val poster_url: String,
    val thumb_url: String,
    val year: Int,
    val time: String,
    val episode_current: String,
    val quality: String,
    val lang: String,
    val category: List<Category>,
    val country: List<Country>
)

data class Category(val id: String, val name: String, val slug: String)
data class Country(val id: String, val name: String, val slug: String)

data class ServerData(
    @SerializedName("server_name") val serverName: String,
    @SerializedName("server_data") val serverDataList: List<EpisodeItem>
)

data class EpisodeItem(
    val name: String,
    val slug: String,
    val filename: String,
    @SerializedName("link_embed") val linkEmbed: String,
    @SerializedName("link_m3u8") val linkM3u8: String
)

data class Pagination(val totalItems: Int, val totalItemsPerPage: Int, val currentPage: Int)

interface ApiService {
    @GET("danh-sach/{type}")
    suspend fun getMoviesByType(
        @Path("type") type: String,
        @Query("page") page: Int = 1
    ): MovieResponse

    @GET("phim/{slug}")
    suspend fun getMovieDetails(
        @Path("slug") slug: String
    ): MovieDetailResponse

    @GET("v1/api/tim-kiem")
    suspend fun searchMovies(
        @Query("keyword") keyword: String
    ): MovieResponse

    companion object {
        const val BASE_URL = "https://phimapi.com/"
        
        fun create(): ApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}
