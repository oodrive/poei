package com.oodrive.poei.sample.person.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oodrive.poei.sample.person.dto.GreetingDTO;
import com.oodrive.poei.sample.person.dto.PersonDTO;
import com.oodrive.poei.sample.person.greeter.Greeter;


@RestController
@RequestMapping(path = "/greeting")
public class PersonController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

	private final Greeter greeter;

	@Autowired
	public PersonController(Greeter greeter) {this.greeter = greeter;}

	@PostMapping
	public GreetingDTO greet(@RequestBody PersonDTO person) {
		LOGGER.info("Greeting {}...", person.getName());
		var message = greeter.greet(person.getName());
		return new GreetingDTO(message);
	}
}
