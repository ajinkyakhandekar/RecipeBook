package com.tghc.recipebook.data.response

import com.tghc.recipebook.constant.Status
import com.tghc.recipebook.data.modelRequest.Recipe


class ItemResponse(var status: Status, var items: MutableList<Recipe>?, var error: Throwable?) {

    companion object {
        fun loading(): ItemResponse {
            return ItemResponse(Status.LOADING, null, null)
        }

        fun success(items: MutableList<Recipe>?): ItemResponse {
            return ItemResponse(Status.SUCCESS, items, null)
        }

        fun error(error: Throwable): ItemResponse {
            return ItemResponse(Status.ERROR, null, error)
        }

        fun offline():ItemResponse{
            return ItemResponse(Status.OFFLINE, null,null)
        }
    }
}