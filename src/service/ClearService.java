package service;

import dao.MemoryResultStore;
import dao.ResultStore;
import model.CalDto;

public class ClearService implements CalService {

	@Override
	public CalDto calculate(CalDto calDto) {
		
		ResultStore rs = MemoryResultStore.getResultStore();

		// db와 dto 초기화
		calDto = new CalDto();
		
		rs.clear();
		
		return calDto;
		
	}

}
