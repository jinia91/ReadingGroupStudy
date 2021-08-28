package service;

import dao.MemoryResultStore;
import dao.ResultStore;
import model.CalDto;

public class FirstService implements CalService {

	@Override
	public CalDto calculate(CalDto calDto) {
	
		ResultStore store = MemoryResultStore.getResultStore();
		
		calDto.setResult(calDto.getNumber()+"");
		
		store.dtoSave(calDto);
		
		calDto.setResult("");
		
		return calDto;
	}

}
