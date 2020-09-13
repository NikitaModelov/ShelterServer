package ru.modelov

data class ShelterDetailEntity(
    val id: Long?,
    val title: String,
    val location: String,
    val description: String?,
    val imageUrl: String?,
    val contacts: String?
)