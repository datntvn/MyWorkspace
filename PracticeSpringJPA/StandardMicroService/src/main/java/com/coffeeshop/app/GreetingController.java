/**
 *
 * <p>Filename: GreetingController.java
 * <p>Created At: Aug 19th 2018
 *
 * @author DatNT
 */
package com.coffeeshop.app;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.coffeeshop.app.data.EmployeeRepository;
import com.coffeeshop.app.model.Employee;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private static Logger logger = LogManager.getLogger(GreetingController.class);
    
    @Autowired
	private EmployeeRepository employeeData;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        logger.info("[INFO] Request /greeting");
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

    @RequestMapping(value = "/listEmployees", method = RequestMethod.GET)
	public Employee employees() {
        System.out.println("---BEGIN");
        List<Employee> allEmployees = employeeData.findAll();
        
        System.out.println("size of emp == "+allEmployees.size());
        System.out.println("---END");
        Employee oneEmployee = allEmployees.get(0);
		return oneEmployee;
	}
    
}
