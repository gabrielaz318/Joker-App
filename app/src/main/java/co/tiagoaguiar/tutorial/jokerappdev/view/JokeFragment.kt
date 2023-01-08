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
import com.google.android.material.floatingactionbutton.FloatingActionButton

class JokeFragment : Fragment() {

    companion object {
        const val CATEGORY_KEY = "category"
    }

    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView
    private lateinit var imageView: ImageView

    private lateinit var presenter: JokePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = JokePresenter(this)
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

        // Quando coloca duas !! no final voc~e informa que a informação está vindo com certeza
        val categoryName = arguments?.getString(CATEGORY_KEY)!!

        activity?.findViewById<Toolbar>(R.id.toolbar)?.title = categoryName
        progressBar = view.findViewById(R.id.progress_bar)
        textView = view.findViewById(R.id.txt_joke)

        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            presenter.findBy(categoryName.lowercase())
        }

        presenter.findBy(categoryName.lowercase())

    }

    fun showJoke(joke: Joke) {
        textView.text = joke.text
        // TODO: adicionar imagem
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