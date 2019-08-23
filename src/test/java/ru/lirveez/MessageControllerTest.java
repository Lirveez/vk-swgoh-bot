package ru.lirveez;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MessageControllerTest {

    @Autowired
    private MockMvc mvc;

    @Value("${bot.confirmationCode}")
    private String confirmationCode;
    @Value("${bot.statusOk}")
    private String responseOk;

    @Test
    @SneakyThrows
    public void shouldReturnCommandListForHelp() {
        mvc.perform(MockMvcRequestBuilders.post("/bot/dnm").contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"type\": \"confirmation\",\n" +
                        "  \"group_id\": 152240979\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(confirmationCode));
    }

    @Test
    @SneakyThrows
    public void shouldReplyOnNewMessageType() {
        mvc.perform(MockMvcRequestBuilders.post("/bot/dnm").contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"type\": \"message_new\",\n" +
                        "  \"group_id\": 152240979\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(responseOk));
    }

    @Test
    @SneakyThrows
    public void shouldReturnConfirmationCodeForRequest() {
        mvc.perform(MockMvcRequestBuilders.post("/bot/dnm").contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"type\": \"confirmation\",\n" +
                        "  \"group_id\": 152240979\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(confirmationCode));
    }
    @Test
    @SneakyThrows
    public void shouldDeserializeCorrectlyWithoutFailures() {
        mvc.perform(MockMvcRequestBuilders.post("/bot/dnm").contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"type\": \"message_new\",\n" +
                        "  \"object\": {\n" +
                        "    \"text\": \"123\",\n" +
                        "    \"notNeededProperty\": \"blabla\"\n" +
                        "  },\n" +
                        "  \"group_id\": 1\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(responseOk));
    }

}
