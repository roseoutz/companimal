package com.companimal

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CompanimalApiServerApplication

fun main(args: Array<String>) {
    runApplication<CompanimalApiServerApplication>(*args)
}
