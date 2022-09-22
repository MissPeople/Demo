/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.paging

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.paging.room.User
import com.example.paging.room.UserDB

/**
 * A simple ViewHolder that can bind a Cheese or Separator item. It also accepts null items since
 * the data may not have been fetched before it is bound.
 */
class UserViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
) {
    var user: User? = null
    private val nameView = itemView.findViewById<TextView>(R.id.name)
    fun bindTo(item: UserListItem?) {

        if (item is UserListItem.Separator) {
            nameView.text =  "9527"/*"${item.name} Cheeses"*/
            nameView.setTypeface(null, Typeface.BOLD)
        } else {
            nameView.text = item?.name
            nameView.setTypeface(null, Typeface.NORMAL)
        }
        user = (item as? UserListItem.Item)?.user
        nameView.text = item?.name
    }
}

