package com.alitafreshi.domain.uti

sealed class OrderType{
    object Ascending:OrderType()
    object Descending:OrderType()
}
