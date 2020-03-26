package com.example.restservice;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotesController {
	private DynamicDataStore dds = DynamicDataStore.getInstance();
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/notes/add")
	public Note note(@RequestParam(value = "note", defaultValue = "Lorem Ipson") String text) {
		Note note = dds.addNote(dds.getNextId(), text);
		return note;
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/notes/delete")
	public Note noteDelete(@RequestParam(value = "id", required=true) long id) {
		Note note = dds.deleteNote(id);
		return note;
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/notes")
	public List<Note> notes() {
		List<Note> notes = dds.getNotes(); 
		return notes;
	}
}
