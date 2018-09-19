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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coffeeshop.model.Users;
import com.coffeeshop.mybatis.mapper.UsersMapper;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private static Logger logger = LogManager.getLogger(GreetingController.class);
    @Autowired
    private UsersMapper usersMapper;
    
//    public GreetingController(UsersMapper usersMapper) {
//        this.usersMapper = usersMapper;
//    }

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        logger.info("[INFO] Request /greeting");
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @GetMapping("/all")
    public List<Users> getAll() {
        logger.info("[INFO] Request /all");
        return usersMapper.findAll();
    }
    
    @GetMapping("/addresses")
    public Users getAddress() {
        logger.info("[INFO] Request /addresses");
        return usersMapper.getUserById(new Integer(2));
    }
    
    @GetMapping("/withroles")
    public Users getRoles() {
    	logger.info("[INFO] Request /withroles");
        return usersMapper.getUserByIdWithRole(new Integer(1));
    }
}
