package ru.netology.page;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;



public class DashboardPage {
    private final SelenideElement heading = $(withText("Личный кабинет"));

    public void getVisibleDashboardPage(){
       heading.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
}
