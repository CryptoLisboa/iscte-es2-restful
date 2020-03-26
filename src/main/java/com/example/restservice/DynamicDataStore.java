package com.example.restservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class DynamicDataStore {
	private static final DynamicDataStore instance = new DynamicDataStore();
	private Map<Number, Note> notes = new HashMap<>();
	private final AtomicLong counter = new AtomicLong(1);

	private DynamicDataStore() {
		long id = counter.getAndAdd(1);
		Note a = new Note(id, "Lorem Ipson 1");
		notes.put(a.getId(), a);
	}

	static DynamicDataStore getInstance() {
		return instance;
	}

	public List<Note> getNotes() {
		List<Note> notesAsList = new ArrayList<Note>(notes.values());
		return notesAsList;
	}

	public long getNextId() {
		return counter.getAndAdd(1);
	}
	
	public long getCurrentId() {
		return counter.get();
	}
	
	public Note getNoteById(long id) {
		return notes.get(id);
	}

	public Note addNote(long id, String note) {
		if (notes.containsKey(id)) {
			return notes.get(id);
		}
		Note n = new Note(id, note);
		notes.put(id, new Note(id, note));
		return n;
	}

	public Note deleteNote(long id) {
		return notes.remove(id);
	}

}
