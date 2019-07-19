/**
 *
 * <p>Filename: DemoController.java
 * <p>Created At: July 19th 2019
 *
 * @author DatNT
 */
package com.coffeeshop.app.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.coffeeshop.app.Greeting;
import com.coffeeshop.app.data.EmployeeRepository;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.StringExpressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coffeeshop.app.model.Company;
import com.coffeeshop.app.model.Customer;
import com.coffeeshop.app.model.QCustomer;
import com.coffeeshop.app.model.QCompany;


@RestController
public class DemoController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private static Logger logger = LogManager.getLogger(DemoController.class);

    // BEGIN: for demo with QueryDSL
    // NOTE: these should be at Service layer
    @PersistenceContext
    private EntityManager em;
    // END: for demo with QueryDSL

    
    @Autowired
	private EmployeeRepository employeeData;

    @RequestMapping("/v2/demo")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        logger.info("[INFO] Request /greeting");
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

    @RequestMapping(value = "/v2/demo/subquery", method = RequestMethod.GET)
	public List<Customer> join() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QCustomer customer = QCustomer.customer;
        QCompany company = QCompany.company;
        //------------------
        JPQLQuery<Long> subQuery=JPAExpressions.select(company.id).from(company).where(company.id.eq(Long.parseLong("1")));
        List<Customer> cts = (List<Customer>) queryFactory.selectFrom(customer).where(customer.company.id.in(subQuery)).fetch();
        System.out.println("Query customer - size :: " + cts.size());
        for (Customer c : cts) {

            System.out.println("One customer name :: " + c.getName());
        }

        return cts;
    }

}
