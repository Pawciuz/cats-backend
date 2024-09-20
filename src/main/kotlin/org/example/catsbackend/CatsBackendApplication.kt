package org.example.catsbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class CatsBackendApplication

fun main(args: Array<String>) {
    runApplication<CatsBackendApplication>(*args)
}
