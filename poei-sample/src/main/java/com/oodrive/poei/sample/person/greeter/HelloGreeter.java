package com.oodrive.poei.sample.person.greeter;

public class HelloGreeter implements Greeter {

	@Override
	public String greet(String name) {
		return String.format("Hello, %s", name);
	}
}
