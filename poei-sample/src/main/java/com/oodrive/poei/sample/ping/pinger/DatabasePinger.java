package com.oodrive.poei.sample.ping.pinger;

import org.springframework.transaction.annotation.Transactional;

import com.oodrive.poei.sample.ping.dao.PingDAO;


public class DatabasePinger implements Pinger {

	private final PingDAO pingDAO;

	public DatabasePinger(PingDAO pingDAO) {this.pingDAO = pingDAO;}

	@Transactional
	@Override
	public int ping() {
		pingDAO.insert();
		return pingDAO.count();
	}
}
