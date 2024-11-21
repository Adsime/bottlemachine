package no.demo.bottlemachine

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["no.demo.bottlemachine"])
class BottlemachineApplication

fun main(args: Array<String>) {
	runApplication<BottlemachineApplication>(*args)
}
