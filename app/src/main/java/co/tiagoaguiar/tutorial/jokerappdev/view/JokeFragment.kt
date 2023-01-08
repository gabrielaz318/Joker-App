package co.tiagoaguiar.tutorial.jokerappdev.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import co.tiagoaguiar.tutorial.jokerappdev.R
import co.tiagoaguiar.tutorial.jokerappdev.model.Joke
import co.tiagoaguiar.tutorial.jokerappdev.presentation.JokePresenter
import com.squareup.picasso.RequestCreator
import java.lang.IllegalStateException

class JokeFragment : Fragment() {

    private lateinit var presenter: JokePresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var txtJoke: TextView
    private lateinit var imgJoke: ImageView
    private var categoryName: String =  ""

    companion object {
        const val CATEGORY_KEY = "category"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = JokePresenter(this, categoryName)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_joke, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.progress_bar)
        imgJoke = view.findViewById(R.id.img_joke)
        txtJoke = view.findViewById(R.id.txt_joke)

        (view.findViewById(R.id.fab) as View).setOnClickListener {
            presenter.getJoke()
        }

        categoryName = arguments?.getString(CATEGORY_KEY) ?: throw IllegalStateException("Parâmetro não encontrado")

        activity?.findViewById<Toolbar>(R.id.toolbar)?.title = categoryName

        presenter.getJoke()
    }

    fun showJoke(joke: Joke, image: RequestCreator) {
        txtJoke.text = joke.value
        image.into(imgJoke)
    }

    fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    fun showFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

}