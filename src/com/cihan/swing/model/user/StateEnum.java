package com.cihan.swing.model.user;

public enum StateEnum {
	NULL,
	NORMAL,
	SILINMIS;
	
	 public String toString(){
        switch(this) {
            case NORMAL:
                return "NORMAL";
            case SILINMIS:
                return "SİLİNMİŞ";
            default: return " ";
        }
	 }

}
