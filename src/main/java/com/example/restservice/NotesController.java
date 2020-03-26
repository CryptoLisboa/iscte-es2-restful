package com.example.restservice;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotesController {
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/notes/add")
	public Note note(@RequestParam(value = "note", defaultValue = "Lorem Ipson") String note) {
		System.out.println("Here to process note: " + note);
		long nextId = DynamicDataStore.getInstance().getNextId();
		return DynamicDataStore.getInstance().addNote(nextId, note);
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/notes")
	public List<Note> notes() {
		return DynamicDataStore.getInstance().getNotes();
	}
}
