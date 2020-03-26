package com.example.restservice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DynamicDataStoreTest {

	@Test
	void testGetNextId() {
		DynamicDataStore ds = DynamicDataStore.getInstance();
		long id =ds.getCurrentId();
		long nextId =ds.getNextId();
		ds.addNote(nextId, "YA");
		assertTrue(ds.getCurrentId() == id+1);	
	}

	@Test
	void testGetNoteById() {
		DynamicDataStore ds = DynamicDataStore.getInstance();
		long id=ds.getNextId();
		String st = "YO";
		ds.addNote(id, st);
		assertEquals (ds.getNoteById(id).getNote(), st);
		assertTrue (ds.getNoteById(id).getId()==id);
	}
	
	@Test
	void testGetNoteByIdRepeated() {
		DynamicDataStore ds = DynamicDataStore.getInstance();
		long id=ds.getNextId();
		String st = "YO";
		Note nota1 = ds.addNote(id, st);
		Note nota2 = ds.addNote(id, st + " ");
		assertEquals (nota1.toString(), nota2.toString());
	}

	@Test
	void testDeleteNote() {
		DynamicDataStore ds = DynamicDataStore.getInstance();
		long id=ds.getNextId();
		String st = "Carla";
		ds.addNote(id, st);
		assertEquals (ds.deleteNote(id).getNote(), st);

	}

}
