package pkk.interview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 26.01.2017.
 */

@Controller
@RequestMapping(path = "/rest")
public class HelloController {

    @RequestMapping(path = "/hello")
    @ResponseBody
    public Object hello() {
        Map<String, String> map = new HashMap<>();
        map.put("text", "hello");
        return map;
    }
}
