package co.tiagoaguiar.tutorial.jokerappdev.presentation

import android.graphics.Color
import co.tiagoaguiar.tutorial.jokerappdev.data.CategoryRemoteDataSource
import co.tiagoaguiar.tutorial.jokerappdev.data.ListCategoryCallback
import co.tiagoaguiar.tutorial.jokerappdev.model.Category
import co.tiagoaguiar.tutorial.jokerappdev.view.HomeFragment

class HomePresenter(
    private val view: HomeFragment,
    private val dataSource: CategoryRemoteDataSource = CategoryRemoteDataSource()
): ListCategoryCallback {

    fun findAllCategories() {
        view.showProgress()
        dataSource.findAllCategories(this)
    }

    override fun onComplete() {
        view.hideProgress()
    }

    override fun onError(message: String) {
        view.showFailure(message)
    }

    override fun onSuccess(response: List<String>) {
        val start = 25
        val end = 65
        val diff = (end - start) / response.size


        val categories = response.mapIndexed { index, category ->
            val hsv = floatArrayOf(
                start + (diff * index).toFloat(),
                100.0f,
                100.0f
            )
            Category(
                category.replaceFirstChar { it.uppercase() },
                Color.HSVToColor(hsv).toLong()
            )
        }

        view.showCategories(categories)
    }

    private fun fakeRequest() {

    }
}