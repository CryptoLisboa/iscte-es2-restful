package com.example.restservice;

class Note {

	private final long id;
	private final String note;
	
	public Note(long id, String note) {
		this.id = id;
		this.note = note;
	}

	public long getId() {
		return id;
	}

	public String getNote() {
		return note;
	}
	
	@Override
	public String toString() {
		return "Note [id=" + id + ", note=" + note + "]";
	}
}