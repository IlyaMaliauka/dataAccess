package integration;


import com.epam.training.config.SpringConfig;
import com.epam.training.config.SpringMvcConfig;
import com.epam.training.model.User;
import com.epam.training.model.impl.UserImpl;
import com.epam.training.service.EventService;
import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class, SpringMvcConfig.class})
@WebAppConfiguration
public class UserControllerIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private EventService eventServiceMock;

    //Add WebApplicationContext field here

    //The setUp() method is omitted.

    @Test
    public void findAll_ShouldAddTodoEntriesToModelAndRenderTodoListView() throws Exception {
        User first = new UserImpl("john doe", "sample@mail.com");

        User second = new UserImpl("bar", "bar@mail.com");

       // when(eventServiceMock.findAll()).thenReturn(Arrays.asList(first, second));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("todo/list"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/todo/list.jsp"))
                .andExpect(model().attribute("todos", hasSize(2)))
                .andExpect(model().attribute("todos", hasItem(
                        allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("description", is("Lorem ipsum")),
                                hasProperty("title", is("Foo"))
                        )
                )))
                .andExpect(model().attribute("todos", hasItem(
                        allOf(
                                hasProperty("id", is(2L)),
                                hasProperty("description", is("Lorem ipsum")),
                                hasProperty("title", is("Bar"))
                        )
                )));

        //verify(eventServiceMock, times(1)).findAll();
    }
}
