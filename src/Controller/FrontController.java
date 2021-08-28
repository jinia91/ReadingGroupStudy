package Controller;

import model.CalDto;
import service.CalService;
import service.CalculateService;
import service.ClearService;
import service.EqualService;
import service.FirstService;

public class FrontController {

	private boolean firstFlag;
	private CalService calculateService = new CalculateService();
	private CalService clearService = new ClearService();
	private CalService firstService = new FirstService();
	private CalService equlaService = new EqualService();
	private boolean firstflag = true;

	public CalDto calControl(CalDto calDto) {

		String oper = calDto.getOper();

		if (oper.equals("C")) {
			
			calDto = clearService.calculate(calDto);
			firstflag = true;
			
		} else if (firstflag) {

			if (oper.equals("=")) {

				calDto = equlaService.calculate(calDto);

			}

			else {
				calDto = firstService.calculate(calDto);
				firstflag = false;
			}
		} else {
			calDto = calculateService.calculate(calDto);
			firstflag = true;
		}

		return calDto;
	}

}
