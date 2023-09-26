package ru.altunin.bracketschecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.altunin.bracketschecker.webapp.BracketsCheckerApplication;

@SpringBootTest(classes= BracketsCheckerApplication.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public abstract class AbstractTestApi {
    @Autowired
    protected MockMvc mvc;

}
