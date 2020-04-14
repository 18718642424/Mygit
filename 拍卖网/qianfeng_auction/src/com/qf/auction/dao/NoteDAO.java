package com.qf.auction.dao;

import com.qf.auction.entity.Note;

public interface NoteDAO {
	 int addNote(Note note);
	 
	 int findValidateCodeByPhoneNumber(String phonenumber);
}
