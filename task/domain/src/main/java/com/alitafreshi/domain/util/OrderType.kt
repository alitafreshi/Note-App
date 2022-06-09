package com.alitafreshi.domain.util

sealed class OrderType{
    object Ascending:OrderType()
    object Descending:OrderType()
}
