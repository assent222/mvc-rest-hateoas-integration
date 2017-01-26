package pkk.interview.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by root on 26.01.2017.
 */
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:/spring/*-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class HelloControllerTest {
    @Autowired
    private WebApplicationContext ctx;

    private static final String BASE_URI = "http://localhost:8080";

    @Test
    public void testHelloController() throws Exception {
        System.out.println(">> testHelloController");

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
        String response = mockMvc.perform(get(BASE_URI + "/rest/hello").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();

        Map map = mapper.readValue(response, Map.class);

        assertTrue(map.containsKey("text"));
        assertEquals("hello",map.get("text"));

        System.out.println("<< testHelloController");
    }
}
