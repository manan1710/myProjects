package com.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CalculatorController {

	private static final String collectionName = "inputBean";

	@Autowired
	CalculatorRepository calculatorRepository;

	@Autowired
	CounterService counterService;

	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public InputBean add(InputBean bean) {

		System.out.println("num1 : " + bean.getNum1() + ", num2 : " + bean.getNum2());
		bean.setResult(bean.getNum1() + bean.getNum2());
		bean.setOperation("Addition");
		bean.setId(counterService.getNextSequence(collectionName));
		System.out.println("ID : " + bean.getId() + "\nResult : " + bean.getResult() + "\nOperation : "
				+ bean.getOperation() + "\n");

		calculatorRepository.insert(bean);

		return bean;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sub")
	public InputBean sub(InputBean bean) {

		System.out.println("num1 : " + bean.getNum1() + ", num2 : " + bean.getNum2());
		bean.setResult(bean.getNum1() - bean.getNum2());
		bean.setOperation("Subtraction");
		bean.setId(counterService.getNextSequence(collectionName));
		System.out.println("ID : " + bean.getId() + "\nResult : " + bean.getResult() + "\nOperation : "
				+ bean.getOperation() + "\n");

		calculatorRepository.insert(bean);

		return bean;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/mul")
	public InputBean mul(InputBean bean) {

		System.out.println("num1 : " + bean.getNum1() + ", num2 : " + bean.getNum2());
		bean.setResult(bean.getNum1() * bean.getNum2());
		bean.setOperation("Multiplication");
		bean.setId(counterService.getNextSequence(collectionName));
		System.out.println("ID : " + bean.getId() + "\nResult : " + bean.getResult() + "\nOperation : "
				+ bean.getOperation() + "\n");

		calculatorRepository.insert(bean);

		return bean;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/div")
	public InputBean div(InputBean bean) {

		System.out.println("num1 : " + bean.getNum1() + ", num2 : " + bean.getNum2());
		if (bean.getNum2() != 0) 
		{
			bean.setResult(bean.getNum1() / bean.getNum2());
			bean.setOperation("Division");
			bean.setId(counterService.getNextSequence(collectionName));
			System.out.println("ID : " + bean.getId() + "\nResult : " + bean.getResult() + "\nOperation : "
					+ bean.getOperation() + "\n");

			calculatorRepository.insert(bean);
		}
		else {
			System.out.println("divide by zero : exception");
		}
		return bean;
	}

	/*
	 * @RequestMapping(method = RequestMethod.POST, value = "/add/{num1}/{num2}")
	 * public String add(@RequestParam(value="num1") @PathVariable(value = "num1")
	 * Integer num1, @RequestParam(value="num2") @PathVariable(value = "num2")
	 * Integer num2) { String ans = String.format("%d + %d = %d", num1, num2,
	 * (num1+num2)); System.out.println("Answer = "+ans); return ans; }
	 */

	/*
	 * @RequestMapping(method = RequestMethod.POST, value = "/sub/{num1}/{num2}")
	 * public String sub( @RequestParam(value="num1") @PathVariable(value = "num1")
	 * Integer num1,
	 * 
	 * @RequestParam(value="num2") @PathVariable(value = "num2") Integer num2) {
	 * String ans = String.format("%d - %d = %d", num1, num2, (num1 - num2));
	 * System.out.println("Answer = " + ans); return ans; }
	 */

	/*
	 * @RequestMapping(method = RequestMethod.POST, value = "/mul/{num1}/{num2}")
	 * public String mul( @RequestParam(value="num1") @PathVariable(value = "num1")
	 * Integer num1,
	 * 
	 * @RequestParam(value="num2") @PathVariable(value = "num2") Integer num2) {
	 * String ans = String.format("%d * %d = %d", num1, num2, (num1 * num2));
	 * System.out.println("Answer = " + ans); return ans; }
	 */

	/*
	 * @RequestMapping(method = RequestMethod.POST, value = "/div/{num1}/{num2}")
	 * public String div( @RequestParam(value="num1") @PathVariable(value = "num1")
	 * Integer num1,
	 * 
	 * @RequestParam(value="num2") @PathVariable(value = "num2") Integer num2) { if
	 * (num2 != 0) { String ans = String.format("%d / %d = %d", num1, num2, (num1 /
	 * num2)); System.out.println("Answer = " + ans); return ans; } else { return
	 * ""; } }
	 */

}
