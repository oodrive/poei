package com.oodrive.poei.sample.ping.pinger;

import java.util.concurrent.atomic.AtomicInteger;


public class InMemoryPinger implements Pinger {

	private AtomicInteger nbPing = new AtomicInteger();

	@Override
	public int ping() {
		return nbPing.incrementAndGet();
	}
}
