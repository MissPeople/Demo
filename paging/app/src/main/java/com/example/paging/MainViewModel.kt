package com.example.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.paging.room.User
import com.example.paging.room.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainViewModel(): ViewModel(){


    private lateinit var dao: UserDao
    fun provideDao(dao: UserDao){
        this.dao = dao
    }
    val allCheeses: Flow<PagingData<UserListItem>> = Pager(
        config = PagingConfig(

            pageSize = 60,

            enablePlaceholders = true,

            maxSize = 200
        )
    ) {
        dao.allUserByName()
    }.flow
        .map { pagingData ->
            pagingData
                // Map cheeses to common UI model.
                .map { user -> UserListItem.Item(user) }
                .insertSeparators { before: UserListItem?, after: UserListItem? ->
                    if (before == null && after == null) {
                        // List is empty after fully loaded; return null to skip adding separator.
                        null
                    } else if (after == null) {
                        // Footer; return null here to skip adding a footer.
                        null
                    } else if (before == null) {
                        // Header
                        UserListItem.Separator(after.name.first())
                    } else if (!before.name.first().equals(after.name.first(), ignoreCase = true)){
                        // Between two items that start with different letters.
                        UserListItem.Separator(after.name.first())
                    } else {
                        // Between two items that start with the same letter.
                        null
                    }
                }
        }
        .cachedIn(viewModelScope)

    /*fun insert(text: CharSequence) = ioThread {
        dao.insert(Cheese(id = 0, name = text.toString()))
    }
*/
    fun remove(user: User) = CoroutineScope(Dispatchers.IO).launch {
        dao.remove(user)
    }
}