package model;

public class CalDto {

	private double number;
	private String operator;
	private String result = "";
	

	// getter setter
	public void setNumber(double number) {

		this.number = number;

	}

	public void setOper(String operator) {

		this.operator = operator;

	}


	public void setResult(String result) {

		this.result = result;

	}

	public String getResult() {

		return this.result;

	}

	public double getNumber() {

		return this.number;

	}

	public String getOper() {

		return this.operator;

	}

}
