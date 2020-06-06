package com.example.accubitsapp.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.accubitsapp.db.UserDataBase
import com.example.accubitsapp.db.model.movie.MovieResponse
import com.example.accubitsapp.db.model.movie.ResultsMovie
import com.example.accubitsapp.network.MyApi
import com.example.accubitsapp.network.SafeApiRequest
import com.example.accubitsapp.utils.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class HomeRepository(private val api: MyApi,
                     private val db: UserDataBase): SafeApiRequest() {

    private val movieResponse = MutableLiveData<MovieResponse>()

    init {
        movieResponse.observeForever {
            saveMovie(it)
        }
    }



    suspend fun getEmployee(): LiveData<List<ResultsMovie>> {

        return withContext(Dispatchers.IO) {
            fetchEmployee()
            db.saveuserdata().getMovies()
        }
    }

    private suspend fun fetchEmployee() {

            try {
                val response = apiRequest { api.getMovie() }

                movieResponse.postValue(response)
            } catch (e: Exception) {
                e.printStackTrace()
            }

    }

    private fun saveMovie(quotes: MovieResponse) {
        Coroutines.io {

            db.saveuserdata().saveAllMovies(quotes.results)

        }
    }



}