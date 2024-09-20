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
import java.time.LocalTime
import java.util.concurrent.Executors


@RestController
class CatFactController(
    private val catFactsAndUsersService: CatFactsAndUsersService
) {
        @RequestMapping(value = ["/cat-facts"], method = [RequestMethod.GET], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    suspend fun getCatFactsWithUsers(): Flow<CatFactWithUser> {
        return catFactsAndUsersService.fetchCatFactsAndUsers().flowOn(Dispatchers.IO)
    }
//@RequestMapping(value = ["/cat-facts"], method = [RequestMethod.GET], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
//fun streamSseMvc(): SseEmitter? {
//    val emitter = SseEmitter()
//    val sseMvcExecutor = Executors.newSingleThreadExecutor()
//    sseMvcExecutor.execute {
//        try {
//            var i = 0
//            while (true) {
//                val event = SseEmitter.event()
//                    .data("SSE MVC - " + LocalTime.now().toString())
//                    .id(i.toString())
//                    .name("sse event - mvc")
//                emitter.send(event)
//                Thread.sleep(1000)
//                i++
//            }
//        } catch (ex: Exception) {
//            emitter.completeWithError(ex)
//        }
//    }
//    return emitter
//}
}


