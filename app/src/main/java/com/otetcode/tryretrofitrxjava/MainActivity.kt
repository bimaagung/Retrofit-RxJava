package com.otetcode.tryretrofitrxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.otetcode.tryretrofitrxjava.Adapter.PostAdapter
import com.otetcode.tryretrofitrxjava.Model.Post
import com.otetcode.tryretrofitrxjava.Retrofit.IMyApi
import com.otetcode.tryretrofitrxjava.Retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    internal lateinit var jsonApi:IMyApi

    //init CompositeDisposable
    internal var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //init  API
        val retrofit = RetrofitClient.instance
        jsonApi = retrofit.create(IMyApi::class.java)

        //init recycler view
        recycler_posts.setHasFixedSize(true)
        recycler_posts.layoutManager = LinearLayoutManager(this)
        fetchData()
    }

    private fun fetchData() {
        //mengambil data dari rxjava
        compositeDisposable.add(jsonApi.posts
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ posts->
                //adapter recycler mengambil data dari rxjva
                val adapter = PostAdapter(this,posts!!)
                recycler_posts.adapter = adapter
            }

        )
    }
}
