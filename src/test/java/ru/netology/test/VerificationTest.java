package ru.netology.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.open;

public class VerificationTest {


    @BeforeEach
    void openUrl(){
        open("http://localhost:9999");
    }

    @AfterEach
    void tearDown(){
        closeWindow();
    }

    @AfterAll
    static void cleanDb() {
        DataHelper.cleanDb();
    }

    @Test
    void shouldLoginWithVasya() {
        LoginPage loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        VerificationPage verificationPage = loginPage.validLogin(authInfo);
        verificationPage.validVerify(authInfo);
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.getHeading().shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldLoginWithPetya() {
        LoginPage loginPage = new LoginPage();
        var authInfo = DataHelper.getOtherAuthInfo();
        VerificationPage verificationPage = loginPage.validLogin(authInfo);
        verificationPage.validVerify(authInfo);
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.getHeading().shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldWarningIfInvalidPasswordAndLogin() {
        LoginPage loginPage = new LoginPage();
        var invalidAuthInfo = DataHelper.getInvalidAuthInfo();
        loginPage.invalidLogin(invalidAuthInfo);
        loginPage.getWarning().shouldBe(Condition.visible, Duration.ofSeconds(15));

    }

    @Test
    void shouldWarningIfInvalidAuthCode() {
        LoginPage loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        VerificationPage verificationPage = loginPage.validLogin(authInfo);
        verificationPage.invalidVerify();
        verificationPage.getWarning().shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
}
