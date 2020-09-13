package ru.modelov

interface ShelterRepository {
    suspend fun getShelters(): List<ShelterCardEntity>
    suspend fun getSheltersByCity(city: String): List<ShelterCardEntity>
    suspend fun getShelterById(id: Long): ShelterDetailEntity
    suspend fun insertShelter(shelter: ShelterCardEntity)
}