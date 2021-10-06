package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.AuthCodes;
import ru.netology.data.DataHelper;
import ru.netology.data.User;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;


public class VerificationPage {

    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verificationButton = $("[data-test-id=action-verify]");
    private SelenideElement warning = $(withText("Ошибка!"));

    public VerificationPage() {
        codeField.shouldBe(Condition.visible);
    }

    public DashboardPage validVerify(User user) {
        AuthCodes authCode = DataHelper.getAuthCode(user);
        codeField.setValue(authCode.getCode());
        verificationButton.click();
        return new DashboardPage();
    }

    public DashboardPage invalidVerify() {
        codeField.setValue(DataHelper.getInvalidAuthCode());
        verificationButton.click();
        return new DashboardPage();
    }

    public void getWarning() {
        warning.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
}
