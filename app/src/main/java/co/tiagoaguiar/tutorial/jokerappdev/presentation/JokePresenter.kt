package co.tiagoaguiar.tutorial.jokerappdev.presentation

import android.util.Log
import co.tiagoaguiar.tutorial.jokerappdev.data.JokeCallback
import co.tiagoaguiar.tutorial.jokerappdev.data.JokeRemoteDataSource
import co.tiagoaguiar.tutorial.jokerappdev.model.Joke
import co.tiagoaguiar.tutorial.jokerappdev.view.JokeFragment
import com.squareup.picasso.Picasso

class JokePresenter(
    private val view: JokeFragment,
    private val categoryName: String,
    private val dataSource: JokeRemoteDataSource = JokeRemoteDataSource()
): JokeCallback {

    fun getJoke() {
        view.showProgress()

        dataSource.getJoke(this, categoryName.lowercase())
    }

    override fun onSuccess(response: Joke) {
        val img = Picasso.get().load(response.icon_url)
        view.showJoke(response, img)
    }

    override fun onError(response: String) {
        view.showFailure(response)
    }

    override fun onComplete() {
        view.hideProgress()
    }
}