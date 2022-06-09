package com.alitafreshi.domain.util

sealed class NoteOrder(open val orderType: OrderType){
    data class Date(override val orderType: OrderType ):NoteOrder(orderType)
}
