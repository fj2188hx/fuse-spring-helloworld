package org.mycompany;
import javax.annotation.Resource;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.ProducerTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spring")
public class HelloRestController {

    @Resource
    private ProducerTemplate producer = null;

    @Resource
    private CamelContext context;

    @RequestMapping(method = RequestMethod.GET, value = "/hello",
                    produces = "text/plain")
    public String hello(String msg) {
        Endpoint end = context.getEndpoint("seda:hello_world");
        producer.sendBody(end, msg);
        
        return "Hello World : msg=" + msg;
    }
}