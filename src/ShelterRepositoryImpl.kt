package ru.modelov

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import ru.modelov.DataBaseFactory.query

class ShelterRepositoryImpl : ShelterRepository {

    override suspend fun getShelters(): List<ShelterCardEntity> {
        return query {
            Shelter.selectAll().map {
                rowToShelterCard(it)
            }
        }
    }

    override suspend fun getSheltersByCity(city: String): List<ShelterCardEntity> =
        query {
            Shelter.select {
                Shelter.location.eq(city)
            }.map { rowToShelterCard(it) }
        }


    override suspend fun getShelterById(id: Long): ShelterDetailEntity =
        query {
            Shelter.select {
                Shelter.id.eq(id)
            }.map { rowToDetailShelter(it) }.single()
        }

    override suspend fun insertShelter(shelter: ShelterCardEntity) {
        query {
            Shelter.insert {
                it[title] = shelter.title
                it[location] = shelter.location
            }
        }
    }


    private fun rowToDetailShelter(row: ResultRow): ShelterDetailEntity =
        ShelterDetailEntity(
            id = row[Shelter.id],
            title = row[Shelter.title],
            location = row[Shelter.location],
            description = row[Shelter.description],
            imageUrl = row[Shelter.imageUrl],
            contacts = row[Shelter.contacts]
        )

    private fun rowToShelterCard(row: ResultRow): ShelterCardEntity =
        ShelterCardEntity(
            id = row[Shelter.id],
            title = row[Shelter.title],
            location = row[Shelter.location],
            imageUrl = row[Shelter.imageUrl]
        )
}