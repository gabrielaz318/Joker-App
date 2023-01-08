package co.tiagoaguiar.tutorial.jokerappdev.data

import co.tiagoaguiar.tutorial.jokerappdev.model.Joke
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException

class JokeDayRemoteDataSource {
    fun findRandom(callback: JokeCallback) {
        HTTPClient.retrofit()
            .create(ChuckNorrisAPI::class.java)
            .findRandom()
            .enqueue(object : Callback<Joke> {
                override fun onResponse(call: Call<Joke>, response: Response<Joke>) {
                    if(response.isSuccessful) {
                        callback.onSuccess(response.body() ?: throw RuntimeException("Piada não encontrada"))
                    } else {
                        callback.onError(response.errorBody()?.string() ?: "Erro desconhecido")
                    }

                    callback.onComplete()
                }

                override fun onFailure(call: Call<Joke>, t: Throwable) {
                    callback.onError(t.message ?: "Erro interno")
                    callback.onComplete()
                }

            })
    }
}