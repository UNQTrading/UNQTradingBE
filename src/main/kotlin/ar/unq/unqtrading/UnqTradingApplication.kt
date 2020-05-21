package ar.unq.unqtrading

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan
@SpringBootApplication(scanBasePackages = ["ar.unq.unqtrading"])
class UnqTradingApplication

fun main(args: Array<String>) {
	runApplication<UnqTradingApplication>(*args)
}



