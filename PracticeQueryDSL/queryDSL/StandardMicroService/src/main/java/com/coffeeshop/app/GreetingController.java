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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.coffeeshop.app.data.EmployeeRepository;
import com.coffeeshop.app.model.Employee;
import com.coffeeshop.app.model.QEmployee;
import com.querydsl.jpa.impl.JPAQueryFactory;

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

    // BEGIN: for demo with QueryDSL
    // NOTE: these should be at Service layer
    @PersistenceContext
    private EntityManager em;
    // END: for demo with QueryDSL

    
    @Autowired
	private EmployeeRepository employeeData;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        logger.info("[INFO] Request /greeting");
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

    // Below method does not use QueryDSL
    @RequestMapping(value = "/listEmployees", method = RequestMethod.GET)
	public Employee employees() {
        System.out.println("---BEGIN");
        List<Employee> allEmployees = employeeData.findAll();
        
        System.out.println("size of emp == "+allEmployees.size());
        System.out.println("---END");
        Employee oneEmployee = allEmployees.get(0);
		return oneEmployee;
    }
    
    @RequestMapping(value = "/oneEmployee", method = RequestMethod.GET)
	public Employee employee() {
        System.out.println("---BEGIN");
        // begin querydsl
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QEmployee qemployee = QEmployee.employee;

        System.out.println("--- query with querydsl");
        Employee c = queryFactory.selectFrom(qemployee)
            .where(qemployee.name.eq("Foo Bar"))
            .fetchOne();
        System.out.println("--- FINISH query with querydsl");
        System.out.println("v == "+ c);
        // end querydsl

        /*
 select employee0_.id as id1_0_, employee0_.dept as dept2_0_, employee0_.name as name3_0_ from employee employee0_ where employee0_.name='Foo Bar'

        */
		return c;
	}
    
}
