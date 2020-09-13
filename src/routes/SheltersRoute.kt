@file:Suppress("IMPLICIT_CAST_TO_ANY")

package ru.modelov.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import ru.modelov.API_VERSION
import ru.modelov.ShelterCardEntity
import ru.modelov.ShelterRepository

const val SHELTERS = "$API_VERSION/shelters"
const val SHELTER_ADD = "$API_VERSION/shelter/add"

@KtorExperimentalLocationsAPI
@Location(SHELTERS)
class SheltersRoute

@KtorExperimentalLocationsAPI
@Location(SHELTER_ADD)
class ShelterAddRoute

@KtorExperimentalLocationsAPI
fun Route.shelters(repository: ShelterRepository) {
    get<SheltersRoute> {

        val parameters = call.request.queryParameters
        val shelterId = if (parameters.contains("id")) parameters["id"] else null
        val location = if (parameters.contains("city")) parameters["city"] else null

        when {
            shelterId == null && location == null -> {
                val shelters = repository.getShelters()
                call.respond(shelters)
            }

            location != null -> {
                val shelters = repository.getSheltersByCity(location)
                call.respond(shelters)
            }
            else -> {
                val shelters = repository.getShelterById(shelterId?.toLong() ?: -1)
                call.respond(shelters)
            }
        }


    }

    post<ShelterAddRoute> {
        val shelterParameters = call.receive<ShelterCardEntity>()
        println("-> $shelterParameters")
        repository.insertShelter(shelterParameters)
        call.respond(HttpStatusCode.Created)
    }
}