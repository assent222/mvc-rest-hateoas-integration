package pkk.interview.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
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
public class PersonControllerTest {

    @Autowired
    private WebApplicationContext ctx;

    private static final String BASE_URI = "http://localhost:8080";

    @Test
    public void testPersonController() throws Exception {
        System.out.println(">> testPersonController");

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
        String response = mockMvc.perform(get(BASE_URI + "/rest/persons").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().getResponse().getContentAsString();

//        ObjectMapper mapper = new ObjectMapper();
//        Map map = mapper.readValue(response, Map.class);

        System.out.println("<< testPersonController");
    }
}