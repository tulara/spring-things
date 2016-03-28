package example.springconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"example"})
public class RootApplicationContext {
    // Application services, data access objects, etc.

}
