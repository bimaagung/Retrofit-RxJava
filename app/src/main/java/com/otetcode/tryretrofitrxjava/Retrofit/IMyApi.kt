package com.otetcode.tryretrofitrxjava.Retrofit

import com.otetcode.tryretrofitrxjava.Model.Post
import io.reactivex.Observable
import retrofit2.http.GET

//interface untuk membuat obseravle rxjava
interface IMyApi {

    @get:GET("posts")
    val posts:Observable<List<Post>>
}