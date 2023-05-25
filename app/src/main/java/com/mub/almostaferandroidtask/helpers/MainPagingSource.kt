package com.mub.almostaferandroidtask.helpers

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mub.almostaferandroidtask.features.home.datasource.MovieLocalSource
import com.mub.almostaferandroidtask.model.comman.Movie
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/*
* handling the loading from Room to display on Screen
*
* */
class MainPagingSource(private val sortedBy: String, val loadMore: suspend (Int) -> Unit) :
    PagingSource<Int, Movie>(), KoinComponent {
    private val dao: MovieLocalSource by inject()
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 0

        return try {
            var entities = dao.getPagedList(params.loadSize, page * params.loadSize, "%$sortedBy%")
            if (entities.isEmpty() && page >= 0) {
                loadMore.invoke(page.inc())
                entities = dao.getPagedList(params.loadSize, page * params.loadSize, "%$sortedBy%")
            }
            LoadResult.Page(
                data = entities,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (entities.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}