package com.example.paging

import com.example.paging.room.User


sealed class UserListItem(val name: String) {
    data class Item(val user: User) : UserListItem(user.name)
    data class Separator(private val letter: Char) : UserListItem(letter.toUpperCase().toString())
}