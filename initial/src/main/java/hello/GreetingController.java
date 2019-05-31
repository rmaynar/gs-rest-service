package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping(value="/greeting", method= RequestMethod.POST )
    public Greeting save(@RequestParam String name) {
        System.out.println("POST");
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @RequestMapping(value="/greeting", method=RequestMethod.PUT )
    public Greeting update(@RequestParam String name) {
        System.out.println("PUT");
        if("Raúl".equals(name)) {
            return new Greeting(counter.incrementAndGet(), String.format("Actualizado %s", name));
        }
        return null;
    }

    @RequestMapping(value="/greeting", method=RequestMethod.DELETE )
    public Greeting delete(@RequestParam String name) {
        System.out.println("DELETE");
        if("Raúl".equals(name)) {
            return new Greeting(counter.incrementAndGet(), String.format("Eliminado %s", name));
        }
        return null;
    }
}
