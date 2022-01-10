package com.mws.travelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_retrofit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitActivity : AppCompatActivity() {

    private val list = ArrayList<PostResponse>()
    private val listComment = ArrayList<CommentResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

//        showPosts()
//        createPost()
        showComments()
//        updatePost()
//        deletePost()
    }

    private fun deletePost() {
        RetrofitClient.instance.deletePost(1).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                tvResponseCode.text = response.code().toString()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                tvResponseCode.text = t.message
            }

        })
    }

    private fun updatePost() {
        RetrofitClient.instance.patchPost(
            5,
            4,
            5,
            null,
            "ini text yang sudah diubah"
        ).enqueue(object : Callback<PostResponse> {
            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                tvResponseCode.text = response.code().toString()

                val responseText = "Response code : ${response.code()}\n" +
                        "title: ${response.body()?.title}\n" +
                        "body: ${response.body()?.text}\n" +
                        "userId: ${response.body()?.userId}\n" +
                        "id: ${response.body()?.id}"
                tvResponseCode.text = responseText
            }

            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                tvResponseCode.text = t.message
            }

        })
    }

    private fun showComments() {
        rvPost.setHasFixedSize(true)
        rvPost.layoutManager = LinearLayoutManager(this)

//        val endpoint = 2

        RetrofitClient.instance.getComment(32).enqueue(object :
            Callback<ArrayList<CommentResponse>> {
            override fun onResponse(
                call: Call<ArrayList<CommentResponse>>,
                response: Response<ArrayList<CommentResponse>>
            ) {
                tvResponseCode.text = response.code().toString()
                response.body()?.let { listComment.addAll(it) }
                val adapter = CommentAdapter(listComment)
                rvPost.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<CommentResponse>>, t: Throwable) {
                tvResponseCode.text = t.message
            }
        })
    }

    private fun createPost() {
        RetrofitClient.instance.createPost(
            29,
            "Contoh Field",
            "Contoh teks yang akan diubah pada bagian textnya"
        ).enqueue(object : Callback<CreatePostResponse> {
            override fun onResponse(
                call: Call<CreatePostResponse>,
                response: Response<CreatePostResponse>
            ) {
                val responseText = "Response code : ${response.code()}\n" +
                        "title: ${response.body()?.title}\n" +
                        "body: ${response.body()?.text}\n" +
                        "userId: ${response.body()?.userId}" +
                        "id: ${response.body()?.id}"
                tvResponseCode.text = responseText
            }

            override fun onFailure(call: Call<CreatePostResponse>, t: Throwable) {
                tvResponseCode.text = t.message
            }
        })
    }

    private fun showPosts() {
        rvPost.setHasFixedSize(true)
        rvPost.layoutManager = LinearLayoutManager(this)

        val parameters = HashMap<String, String>()
        parameters["userId"] = "1"
        parameters["id"] = "4"

        RetrofitClient.instance.getPosts(parameters).enqueue(object:
            Callback<ArrayList<PostResponse>> {
            override fun onResponse(
                call: Call<ArrayList<PostResponse>>,
                response: Response<ArrayList<PostResponse>>
            ) {
                val responseCode = response.code().toString()
                tvResponseCode.text = responseCode
                response.body()?.let { list.addAll(it)}
                val adapter = PostAdapter(list)
                rvPost.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<PostResponse>>, t: Throwable) {

            }

        })
    }
}