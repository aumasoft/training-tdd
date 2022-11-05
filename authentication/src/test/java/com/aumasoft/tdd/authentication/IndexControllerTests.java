package com.aumasoft.tdd.authentication;

import java.io.File;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.VncRecordingContainer.VncRecordingFormat;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Testcontainers
class IndexControllerTests {

    @Container
    private final BrowserWebDriverContainer<?> chrome = new BrowserWebDriverContainer<>()
            .withCapabilities(new ChromeOptions())
            .withRecordingMode(VncRecordingMode.RECORD_ALL, new File("./target"), VncRecordingFormat.MP4)
            .withNetwork(Network.newNetwork())
            .withAccessToHost(true);

    @LocalServerPort
    private int          port;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Test
    void test() {
        org.testcontainers.Testcontainers.exposeHostPorts(port);
        final RemoteWebDriver driver = chrome.getWebDriver();
        driver.get("http://host.testcontainers.internal:" + port);

        final boolean expectedTextFound = driver.findElements(By.cssSelector("p"))
                                                .stream()
                                                .anyMatch(element -> element.getText().contains("Spring"));

        assertTrue(expectedTextFound, "The word 'Spring' is found on page");

        final var list = jdbcTemplate.queryForList("SELECT * FROM test");
        MatcherAssert.assertThat(list, Matchers.is(Matchers.empty()));
    }

}
