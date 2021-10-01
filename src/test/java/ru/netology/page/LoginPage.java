package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.data.DataHelper;
import ru.netology.data.User;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class LoginPage {
      private SelenideElement loginField = $("[data-test-id=login] input");
      private SelenideElement passwordField = $("[data-test-id=password] input");
      private SelenideElement loginButton = $("[data-test-id=action-login]");
      private SelenideElement warning = $(withText("Ошибка!"));


      public VerificationPage validLogin(User user) {
            loginField.setValue(user.getLogin());
            passwordField.setValue(user.getPassword());
            loginButton.click();
            return new VerificationPage();
          }
      public void invalidLogin(User user) {
            loginField.setValue(user.getLogin());
            passwordField.setValue(user.getPassword());
            loginButton.click();
      }


}
