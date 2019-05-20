package com.oodrive.poei.sample.ping.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oodrive.poei.sample.ping.dto.PingDTO;
import com.oodrive.poei.sample.ping.pinger.Pinger;


@RestController
@RequestMapping(path = "/ping")
public class PingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PingController.class);

	private final Pinger pinger;

	@Autowired
	public PingController(Pinger pinger) {this.pinger = pinger;}

	@GetMapping
	public PingDTO ping() {
		int nbPings = pinger.ping();
		LOGGER.info("Pong {} times", nbPings);
		return new PingDTO(nbPings);
	}

}
