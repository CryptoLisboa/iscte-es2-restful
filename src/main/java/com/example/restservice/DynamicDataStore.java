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

	public static DynamicDataStore getInstance() {
		return instance;
	}

	public List<Note> getNotes() {
		List<Note> notesAsList = new ArrayList<Note>(notes.values());
		return notesAsList;
	}

	public AtomicLong getCounter() {
		return counter;
	}
	
	public long getLastId() {
		return counter.get();
	}
	public long getNextId() {
		return counter.getAndAdd(1);
	}

	public Note addNote(long id, String note) {
		if (notes.containsKey(id)) {
			return notes.get(id);
		}
		Note n = new Note(id, note);
		notes.put(id, n);
		return n;
	}
}
