package service;

import dao.MemoryResultStore;
import dao.ResultStore;
import model.CalDto;

public class EqualService implements CalService {

	@Override
	public CalDto calculate(CalDto calDto) {

		ResultStore rs = MemoryResultStore.getResultStore();

		double result = calDto.getNumber();
		double inNumber = rs.numberFind();
		String oper = rs.operFind();
		
		switch (oper) {
		case "+":
			result += inNumber;
			break;
		case "-":
			result -= inNumber;
			break;
		case "/":
			result /= inNumber;
			break;
		case "x":
			result *= inNumber;
			break;
		}
		
		rs.resultSave(result);
		calDto.setResult(result+"");
		
		return calDto;
		
	}

}
