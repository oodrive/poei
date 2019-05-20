package com.oodrive.poei.sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.oodrive.poei.sample.person.greeter.Greeter;
import com.oodrive.poei.sample.person.greeter.HelloGreeter;
import com.oodrive.poei.sample.ping.dao.PingDAO;
import com.oodrive.poei.sample.ping.pinger.DatabasePinger;
import com.oodrive.poei.sample.ping.pinger.Pinger;


@Configuration
public class SampleConfig {
	@Bean
	PingDAO pingDAO(JdbcTemplate jdbcTemplate) {
		return new PingDAO(jdbcTemplate);
	}

	@Bean
	Pinger pinger(PingDAO pingDAO) {
		return new DatabasePinger(pingDAO);
	}

	@Bean
	Greeter greeter() {
		return new HelloGreeter();
	}
}
