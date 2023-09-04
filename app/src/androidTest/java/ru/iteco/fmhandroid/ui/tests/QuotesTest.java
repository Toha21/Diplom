package ru.iteco.fmhandroid.ui.tests;


import static androidx.test.espresso.action.ViewActions.click;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.page.QuotesPage;

@RunWith(AllureAndroidJUnit4.class)
public class QuotesTest {
    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

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
    String description = "\"Ну, идеальное устройство мира в моих глазах. Где никто не оценивает, никто не осудит, где говоришь, и тебя слышат, где, если страшно, тебя обнимут и возьмут за руку, а если холодно тебя согреют.\" Юля Капис, волонтер";
    @Test
    @DisplayName("Открытие цитаты в развернутом виде")
    public void openingQuoteTest() {
        MainPage.quoteBlock.perform(click());
        QuotesPage.openOrCloseFirstQuote();
        QuotesPage.checkThatFirstQuoteContentIsFull(description);
    }


}
