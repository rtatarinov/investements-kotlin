package com.investments.httpapi.domain.category

import com.investments.httpapi.domain.adapters.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class Category(
    private var name: String,
    @Serializable(with = UUIDSerializer::class)
    private val id: UUID
) {
    fun getId(): String {
        return this.id.toString()
    }

    fun getName(): String {
        return this.name
    }
    
    fun setName(value: String) {
        this.name = value
    }
}