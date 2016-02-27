package de.movope.cheesechess.web;

import com.gargoylesoftware.htmlunit.WebClient;
import de.movope.cheesechess.Application;
import de.movope.cheesechess.repository.ChessGameRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriverBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
public class GameViewControllerSystemTest {

    @Autowired
    private WebApplicationContext context;

    private WebDriver driver;

    @Autowired
    private ChessGameRepository chessGameRepository;

    @Before
    public void setup() {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        driver = MockMvcHtmlUnitDriverBuilder.mockMvcSetup(mockMvc).build();
        chessGameRepository.deleteAll();
    }

    @After
    public void destroy() {
        if(driver != null) {
            driver.close();
        }
    }

    @Test
    public void whenNoGamesExistTheListIsEmpty() {
        IndexPage indexPage = IndexPage.to(driver);

        assertThat(indexPage.getGameIds()).hasSize(0);
    }


    @Test
    public void youCanCreateANewGame() {
        IndexPage indexPage = IndexPage.to(driver);
        indexPage = indexPage.createMessage(IndexPage.class, "supergame");

        assertThat(indexPage.getGameIds()).hasSize(1);
        assertThat(indexPage.getGameIds().get(0).getText()).isEqualTo("supergame");
    }
}