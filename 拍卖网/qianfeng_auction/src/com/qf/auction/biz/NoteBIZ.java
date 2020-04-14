package com.qf.auction.biz;

import com.qf.auction.entity.Note;

public interface NoteBIZ {
    boolean addNote(Note note);
    
    int findValidateCodeByPhoneNumber(String phonenumber);
    
}
