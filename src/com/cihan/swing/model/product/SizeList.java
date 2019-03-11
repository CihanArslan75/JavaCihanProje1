package com.cihan.swing.model.product;

/** @author Cihan  */
public enum SizeList {
	XXSMALL(32),
    XSMALL(34),
    SMALL(36),   
    MEDIUM(38),     
    LARGE(40),  
    XLARGE(42), 
    XXLARGE(44);
	
    private final int sizeNo;   // 32,34,36,38,40,42,44
    
    SizeList(int sizeNo) {
        this.sizeNo = sizeNo;
    }
	
    public int getSizeNo() {
	return  this.sizeNo;
    }
}