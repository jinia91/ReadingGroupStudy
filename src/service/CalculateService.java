package service;

import dao.MemoryResultStore;
import dao.ResultStore;
import model.CalDto;

public class CalculateService implements CalService {

	@Override
	public CalDto calculate(CalDto calDto) {

		ResultStore rs = MemoryResultStore.getResultStore();

		// 1. DB�� ����� �ǿ����ڿ� �����ȣ ��������
		double result = rs.resultFind();
		String oper = rs.operFind();

		// 2. ��ǲ���� Dto�� �ǿ�����2 ��������
		double inNumber = calDto.getNumber();

		// 3. ��� ����
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

		
		// 4 ��� ����� dto�� ����
		calDto.setResult(result + "");
		
		// 5-1 ���ο� �����ȣ�� "="�ΰ�� 
		if (calDto.getOper().equals("=")) {
			
			rs.resultSave(result);
			rs.numberSave(inNumber);
			return calDto;
		}

		// 5-2 '=' �� �ƴѰ�� ǥ�ð� �ʱ�ȭ�� ��ȯ
		rs.dtoSave(calDto);
		
		calDto.setResult("");
		return calDto;
	}

}
