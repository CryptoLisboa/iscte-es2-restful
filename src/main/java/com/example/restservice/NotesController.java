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
	public Note note(@RequestParam(value = "note", defaultValue = "Lorem Ipson") String note) {
		return dds.addNote(dds.getNextId(), note);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/notes/delete")
	public Note noteDelete(@RequestParam(value = "id", required=true) long id) {
		return dds.deleteNote(id);
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/notes")
	public List<Note> notes() {
		return dds.getNotes();
	}
}
