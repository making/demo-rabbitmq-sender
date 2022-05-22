package com.example.demo;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	private final AmqpTemplate amqpTemplate;

	private final AtomicInteger counter = new AtomicInteger(0);

	public HelloController(AmqpTemplate amqpTemplate) {
		this.amqpTemplate = amqpTemplate;
	}

	@PostMapping(path = "send")
	public void send(@RequestParam(defaultValue = "1") int count) {
		for (int i = 0; i < count; i++) {
			this.amqpTemplate.convertAndSend("hello", "hello", new Hello("hello" + counter.incrementAndGet()));
		}
	}
}
