package com.acr.icecreamparlor.domain.model

data class IceCream (
    val name1: String,
    val name2: String,
    val price: String,
    val bg_color: String,
    val type: String,
    var counter: Int = 0
) {
    val completeName = "${name1} ${name2}"
}