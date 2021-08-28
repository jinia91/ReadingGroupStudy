package service;

import dao.MemoryResultStore;
import dao.ResultStore;
import model.CalDto;

public class ClearService implements CalService {

	@Override
	public CalDto calculate(CalDto calDto) {
		
		ResultStore rs = MemoryResultStore.getResultStore();

		// db�� dto �ʱ�ȭ
		calDto = new CalDto();
		
		rs.clear();
		
		return calDto;
		
	}

}
