package com.example.githubuser.ui.main

import android.telecom.Call
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.api.RetrofitClient
import com.example.githubuser.data.model.User
import com.example.githubuser.data.model.UserResponse
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel( ){
    val listUsers = MutableLiveData<ArrayList<User>>()

    fun setSearchUsers (query: String){
        RetrofitClient.apiInstance
            .getSearchUsers(query)
            .enqueue(object : Callback<UserResponse>{
                override fun onResponse(
                    call: retrofit2.Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful){
                        listUsers.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: retrofit2.Call<UserResponse>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })
    }

    fun getSearchUsers(): LiveData<ArrayList<User>> {
        return listUsers
    }
}