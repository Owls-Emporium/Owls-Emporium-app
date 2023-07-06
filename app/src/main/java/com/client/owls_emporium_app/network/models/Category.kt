package com.client.owls_emporium_app.network.models

class Category(
    val id: String,
    val name: String,
    val image: String
) {
    override fun toString(): String {
        return "Category(id='$id', name='$name', image='$image')"
    }
}