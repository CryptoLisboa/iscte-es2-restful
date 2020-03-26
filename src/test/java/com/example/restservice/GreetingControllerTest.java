package com.example.restservice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GreetingControllerTest {

	@Test
	public void test() {
		GreetingController gc = new GreetingController ();
		assertEquals (gc.greeting("World").getContent(), "Hello, World!");
	}

}

