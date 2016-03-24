package example.helloworld.model;

import org.springframework.stereotype.Component;

@Component
public class Greeting {
    private String name = "World";
    private String greeting = "Hello";

    public String getName() {
        return name;
    }

    public String getGreeting() {
        return greeting;
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.greeting, this.name);
    }
}
