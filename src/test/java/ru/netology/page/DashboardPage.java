package ru.netology.page;


import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;


@Data


public class DashboardPage {
    private SelenideElement heading = $(withText("Личный кабинет"));

}
