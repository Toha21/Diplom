package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;

public class AboutPage {
    public static ViewInteraction versionValue = onView(withText("1.0.0"));
    public static ViewInteraction privacyPolicyValue = onView(withText("https://vhospice.org/#/privacy-policy/"));
    public static ViewInteraction termsOfUseValue = onView(withText("https://vhospice.org/#/terms-of-use"));
    public static ViewInteraction infoLabel = onView(withText("© I-Teco, 2022"));


    public static void goToAboutBlock() {
        Allure.step("Переход в блок \"О приложении\"");
        MainPage.menu.perform(click());
        MainPage.about.check(matches(isDisplayed()));
        MainPage.about.perform(click());
        versionValue.check(matches(isDisplayed()));
    }

    public static void checkThatAboutBlockContentIsFull() {
        Allure.step("Проверка, что в блоке О Хосписе полный контент");
        versionValue.check(matches(isDisplayed()));
        privacyPolicyValue.check(matches(isDisplayed()));
        termsOfUseValue.check(matches(isDisplayed()));
        infoLabel.check(matches(isDisplayed()));
    }

    public static void goToPrivacyPolicy() {
        Allure.step("Переход к политике конфиденциальности");
        privacyPolicyValue.perform(click());
    }

    public static void goToTermsOfUse() {
        Allure.step("Переход к пользовательскому соглашению");
        termsOfUseValue.perform(click());
    }



}
