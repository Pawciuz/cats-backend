package org.example.catsbackend.services

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.catch
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Service
class CatFactsAndUsersService(private val webClient: WebClient) {

    fun fetchCatFactsAndUsers(): Flow<CatFactWithUser> = flow {
        for(i in 1..3) {
            try {
                // Pobieramy dane o kocich faktach
                val catFactResponse = webClient.get()
                    .uri("https://cat-fact.herokuapp.com/facts/random")
                    .retrieve()
                    .awaitBody<CatFactResponse>()

                val fact = catFactResponse.text ?: catFactResponse.fact

                // Pobieramy dane o losowym użytkowniku
                val userResponse = webClient.get()
                    .uri("https://randomuser.me/api/")
                    .retrieve()
                    .awaitBody<RandomUserResponse>()

                val username = userResponse.results.firstOrNull()?.login?.username

                if (fact != null && username != null) {
                    println(CatFactWithUser(username, fact))
                    emit(CatFactWithUser(username, fact))
                }
            } catch (e: Exception) {
                println("Error fetching data: ${e.message}")
            }


            // Opóźnienie przed kolejnym zapytaniem
            delay(10000)
        }
    }
        .catch { e ->
            println("Error in CatFactsAndUsersService: ${e.message}")
        }
}

// Data class dla odpowiedzi API
data class CatFactResponse(
    val text: String? = null,
    val fact: String? = null
)

data class RandomUserResponse(val results: List<User> = emptyList())
data class User(val login: Login? = null)
data class Login(val username: String? = null)

data class CatFactWithUser(val user: String, val fact: String)
