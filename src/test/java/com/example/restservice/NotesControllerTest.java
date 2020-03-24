<<<<<<< HEAD
package com.example.restservice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NotesControllerTest {

	@Test
	public void test() {
		NotesController nc = new NotesController ();
		assertEquals (nc.note("tamos aí").getNote(), "tamos aí");
	}

}
=======
package com.example.restservice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NotesControllerTest {

	@Test
	public void test() {
		NotesController nc = new NotesController ();
		assertEquals (nc.note("tamos aí").getNote(), "tamos aí");
	}

}
>>>>>>> branch 'master' of https://github.com/bcfoo-iscteiulpt/iscte-es2-restful.git
