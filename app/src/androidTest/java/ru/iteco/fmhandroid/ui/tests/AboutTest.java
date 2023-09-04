package ru.iteco.fmhandroid.ui.tests;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.intent.rule.IntentsTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.page.AboutPage;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.page.Browser;

@RunWith(AllureAndroidJUnit4.class)
public class AboutTest {
    @Rule
    public IntentsTestRule intentsTestRule =
            new IntentsTestRule(ru.iteco.fmhandroid.ui.AppActivity.class);

    @Before
    public void logIn() throws InterruptedException {
        Thread.sleep(7000);
        try {
            AuthorizationPage.isAuthorizationScreen();
        } catch (NoMatchingViewException e) {
            return;
        }
        AuthorizationPage.logIn("login2", "password2");
    }

    @Test
    @DisplayName("Полнота информации раздела \"О приложении\"")
    public void shouldBeFullContentInAboutBlock() {
        AboutPage.goToAboutBlock();
        AboutPage.checkThatAboutBlockContentIsFull();
    }

    @Test
    @DisplayName("Переход к политике конфиденциальности по ссылке")
    public void shouldGoToPrivacyPolicy() {
        AboutPage.goToAboutBlock();
        AboutPage.goToPrivacyPolicy();
        Browser.checkTheSuccessfulTransitionToPrivacyPolicy();
    }

    @Test
    @DisplayName("Переход к пользовательскому соглашению по ссылке")
    public void shouldGoToUserAgreement() {
        AboutPage.goToAboutBlock();
        AboutPage.goToTermsOfUse();
        Browser.checkTheSuccessfulTransitionToTermsOfUse();
    }
}
