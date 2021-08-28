package dao;

import model.CalDto;

public class MemoryResultStore implements ResultStore {

	private double result;
	private String operator;
	private double number;

	// ΩÃ±€≈Ê ∆–≈œ¿∏∑Œ º≥∞Ë
	static private MemoryResultStore resultStore = new MemoryResultStore();

	private MemoryResultStore() {
	}

	public static MemoryResultStore getResultStore() {
		return resultStore;
	}
	//

	@Override
	public void resultSave(double result) {

		this.result = result;

	}

	@Override
	public void numberSave(double number) {

		this.number = number;
		
	}

	@Override
	public void operSave(String oper) {

		this.operator = oper;
		
	}

	@Override
	public double numberFind() {
		return this.number;
	}

	@Override
	public double resultFind() {
		return this.result;
	}

	@Override
	public String operFind() {
		return this.operator;
	}

	@Override
	public void clear() {

		result = 0;
		operator = "";
		number = 0;
		
	}

	@Override
	public void dtoSave(CalDto calDto) {
		
		result = Double.parseDouble(calDto.getResult());
		operator = calDto.getOper();
		number = calDto.getNumber();
		
	}

}
