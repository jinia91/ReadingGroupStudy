package service;

import dao.MemoryResultStore;
import dao.ResultStore;
import model.CalDto;

public class CalculateService implements CalService {

	@Override
	public CalDto calculate(CalDto calDto) {

		ResultStore rs = MemoryResultStore.getResultStore();

		// 1. DB에 저장된 피연산자와 연산기호 가져오기
		double result = rs.resultFind();
		String oper = rs.operFind();

		// 2. 인풋받은 Dto의 피연산자2 가져오기
		double inNumber = calDto.getNumber();

		// 3. 계산 수행
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

		
		// 4 계산 결과값 dto에 저장
		calDto.setResult(result + "");
		
		// 5-1 새로운 연산기호가 "="인경우 
		if (calDto.getOper().equals("=")) {
			
			rs.resultSave(result);
			rs.numberSave(inNumber);
			return calDto;
		}

		// 5-2 '=' 이 아닌경우 표시값 초기화후 반환
		rs.dtoSave(calDto);
		
		calDto.setResult("");
		return calDto;
	}

}
