# BunPaginate

* Not a library, copy and paste in your projects folder

1) Include the BunPaginate.kt file into your project
2) Create an instance of `BunPaginate` like this

```kotlin
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

3) Attach the Paginator to the RecyclerView

```
recyclerView.addOnScrollListener(paginator)
```

It's highly recommended to use ViewModels. Please check https://developer.android.com/topic/libraries/architecture/viewmodel

## License

There's no license associated with this project. You may copy the code and modify it to your heart's content. You may not send me questions regarding this project. You're on your own.
