package com.qf.auction.bizimpl;

import com.qf.auction.biz.NoteBIZ;
import com.qf.auction.dao.NoteDAO;
import com.qf.auction.daoimpl.NoteDAOImpl;
import com.qf.auction.entity.Note;

public class NoteBIZImpl implements NoteBIZ {

	NoteDAO noteDAO = new NoteDAOImpl();
	@Override
	public boolean addNote(Note note) {
		//实际上有利于业务拓展的
/*	int executeCount = noteDAO.addNote(note);
	if (executeCount == 0) {
		return false;
	}else {
		return true;
	}*/
	return noteDAO.addNote(note)>0;
   }

	@Override
	public int findValidateCodeByPhoneNumber(String phonenumber) {
		return noteDAO.findValidateCodeByPhoneNumber(phonenumber);
	}

}
