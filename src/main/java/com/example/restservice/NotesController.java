package com.example.restservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotesController {
	private final AtomicLong counter = new AtomicLong(1);
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/note/add")
	public Note note(@RequestParam(value="note", defaultValue="Lorem Ipson") String note) {
		// TODO1: instead should get next id from mongo db
		long id = counter.getAndAdd(1);
		// END TODO1
		// TODO2: save newNote to mongo db before returning or on new thread
		Note newNote = new Note(id, note);
		// END TODO2
		return newNote;
	}
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/notes")
	public List<Note> notes() {
		List<Note> notes = new ArrayList<Note>();
		// TODO3: instead should get all existing notes from mongo db
		notes.add(new Note(1, "Lorem Ipson 1"));
		notes.add(new Note(2, "Lorem Ipson 2"));
		notes.add(new Note(3, "Lorem Ipson 3"));
		// END TODO3
		return notes;
	}
}
