package example.helloworld.web;

import example.helloworld.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {

    private Greeting greeting;

    @Autowired
    public HelloRestController(Greeting greeting){this.greeting = greeting;}

    @RequestMapping(value="/greeting", method = RequestMethod.GET)
    public Greeting greet() {
        return greeting;
    }

    @RequestMapping(value="/hi", method = RequestMethod.GET)
    public String hi() {
        return "hi";
    }
}
