package com.alitafreshi.domain.uti

sealed class NoteOrder(open val orderType: OrderType){
    data class Date(override val orderType: OrderType ):NoteOrder(orderType)
}
