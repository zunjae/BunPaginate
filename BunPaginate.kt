import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class BunPaginate(recyclerView: RecyclerView, val threshold: Int = 9)
  : RecyclerView.OnScrollListener() {

  var currentPage: Int = 1

  private val layoutManager: RecyclerView.LayoutManager

  init {
    recyclerView.addOnScrollListener(this)
    this.layoutManager = recyclerView.layoutManager!!
  }

  override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
    super.onScrollStateChanged(recyclerView, newState)

    if (isLastPage() || isLoading()) {
      return
    }

    val visibleItemCount = layoutManager.childCount
    val totalItemCount = layoutManager.itemCount
    val firstVisibleItemPosition = (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

    if (firstVisibleItemPosition < 0) {
      return
    }

    if (visibleItemCount + firstVisibleItemPosition + threshold >= totalItemCount) {
      currentPage += 1
      loadMore(currentPage)
    }
  }

  abstract fun loadMore(pageNumber: Int)

  abstract fun isLoading(): Boolean

  abstract fun isLastPage(): Boolean
}
