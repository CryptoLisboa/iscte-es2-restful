package com.example.restservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class DynamicDataStore {
	private static final DynamicDataStore instance = new DynamicDataStore();
	private List<Note> notes = new ArrayList<Note>();
	private final AtomicLong counter = new AtomicLong(1);
	private DynamicDataStore() {
		long id = counter.getAndAdd(1);
		Note a =new Note(id, "Lorem Ipson 1");
		notes.add(a);
	}
	public static DynamicDataStore getInstance() {
		return instance;
	}
	public List<Note> getNotes() {
		return notes;
	}
	public AtomicLong getCounter() {
		return counter;
	}
}
