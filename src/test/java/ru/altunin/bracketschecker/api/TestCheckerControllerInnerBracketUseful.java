package ru.altunin.bracketschecker.api;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.altunin.bracketschecker.AbstractTestApi;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

// is-inner-brackets-useful = true  =>  {(t)} == true, ((t)(t)) == true; ((t)), {{t}} - double same brackets == false always

@TestPropertySource(properties = {"properties.is-inner-brackets-useful=true", "properties.regex=[^a-zA-Z0-9а-яА-Я]"})
public class TestCheckerControllerInnerBracketUseful extends AbstractTestApi {

    @Test
    public void testCheckSuccessfully() throws Exception {

        List<String> texts = new ArrayList<>() {
            {
                add("(t)");
                add("{(t)}");
                add("[({t})]");
                add("((t)(t))");
                add("((t)t(t))");
                add("([((t)t)t]t)");
                add("{t(t[t(t)])}");
                add("{t[t(t{t})]}");
                add("[(t{a[b]c}d)e]");
                add("{[a{b(c)d}e]f}");
                add("[({a[b(c)d]e}f)g]");
                add("[(a{b[c]d}e)f]");
                add("[(a{b[c]d(e)f}g)h]");
                add("{t[t(t{t})]} {{{[[[[(((((t)t)t)t)t)]t]t]t]t}t}t}");
                add("{t{t{t[t[t[t[(t(t(t(t(t)))))]]]]}}} {[({t}t)t]t}");
            }
        };

        for (String text : texts) {
            String requestBody = "{\"text\":\"" + text + "\"}";
            mvc.perform(MockMvcRequestBuilders
                            .post("/api/checkBrackets")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.isCorrect").value(true));
        }
    }

    @Test
    public void testCheckUnSuccessfully() throws Exception {

        List<String> texts = new ArrayList<>() {
            {
                add("( )");
                add("(( )t(t)t");
                add("{(s] )}");
                add("[s(s{()})]");
                add("((([t]))(t))");
                add("((t)((t)))");
                add("((((t)[t)t)t)");
                add("(t(t{t(t))})");
                add("{t[t[t{t})]}");
                add("[(t{{a[b]c}}d)e]");
                add("{[a({b(c)d}e]f}");
                add("[({a[[b(c)d]e}f)g]]");
                add("[((a{b[c]d}e))f]");
                add("[(a{b[c](d(e)f}g)h]");
                add("{t[t([{t})]} {{{[[[[(((((t)t)t)t)t)]t]t]t]t}t}t}");
                add("{t{{t[t[t[t[(t(t(t(t(t)))))]]]]}}} {[({t}t)t]t}");
            }
        };

        for (String text : texts) {
            String requestBody = "{\"text\":\"" + text + "\"}";
            mvc.perform(MockMvcRequestBuilders
                            .post("/api/checkBrackets")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.isCorrect").value(false));
        }
    }

    @Test
    public void testCheckNullText() throws Exception {
        String requestBody = "{\"text\": null}";
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/checkBrackets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.message").value("The text is null!"));
    }

    @Test
    public void testCheckEmptyText() throws Exception {
        String requestBody = "{\"text\": \"\"}";
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/checkBrackets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.message").value("The text is empty!"));
    }


    @Test // regex = "[^a-zA-Z0-9а-яА-Я]"
    public void testCheckTextWithoutUsefulCharacters() throws Exception {
        String requestBody = "{\"text\": \"%^*@$#!?><|\\/.,+-_=:; \"}";
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/checkBrackets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.message").value("There are no useful characters in the text!"));
    }

}
