package dao;

import model.CalDto;

public interface ResultStore {
	
	public void resultSave(double result);
	public void numberSave(double number);
	public void operSave(String oper);
	public void dtoSave(CalDto calDto);
	
	public double resultFind();
	public String operFind();
	public double numberFind();

	public void clear();

}
