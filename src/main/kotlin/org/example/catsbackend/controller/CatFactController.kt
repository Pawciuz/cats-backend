package org.example.catsbackend.controller

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import org.example.catsbackend.services.CatFactWithUser
import org.example.catsbackend.services.CatFactsAndUsersService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController


@RestController
class CatFactController(
    private val catFactsAndUsersService: CatFactsAndUsersService
) {
        @RequestMapping(value = ["/cat-facts"], method = [RequestMethod.GET], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    suspend fun getCatFactsWithUsers(): Flow<CatFactWithUser> {
        return catFactsAndUsersService.fetchCatFactsAndUsers().flowOn(Dispatchers.IO)
    }
}


