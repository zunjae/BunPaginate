# BunPaginate

* Not a library, copy and paste in your projects folder

1) create a new file, call it BunPaginate.kt
2) include the following

```
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
```

3) Create an instance of `BunPaginate` like this

```
paginator = object : BunPaginate(recyclerViewShows) {
  override fun isLastPage(): Boolean {
    return a variable which determines whether you're on the last page
  }

  override fun isLoading(): Boolean {
    return a variable which determines whether data is being loaded
  }

  override fun loadMore(pageNumber: Int) {
    // load more data
  }
}
```

4) Attach the Paginator to the RecyclerView

```
recyclerView.addOnScrollListener(paginator)
```

It's highly recommended to use ViewModels. Please check https://developer.android.com/topic/libraries/architecture/viewmodel

# DISCLAIMER:
untested

## License

There's no license associated with this project. You may copy the code and modify it to your heart's content. You may not send me questions regarding this project. You're on your own.
