package ru.iteco.fmhandroid.ui.tests;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.page.MainPage;

@RunWith(AllureAndroidJUnit4.class)
public class AuthorizationTest {

    @Rule
    public ActivityTestRule<ru.iteco.fmhandroid.ui.AppActivity> activityTestRule =
            new ActivityTestRule<>(ru.iteco.fmhandroid.ui.AppActivity.class);

    String validLogin = "login2";
    String validPassword = "password2";

    @Before
    public void sleep() throws InterruptedException {
        Thread.sleep(7000);
        try {
            AuthorizationPage.isAuthorizationScreen();
        } catch (NoMatchingViewException e) {
            MainPage.logOut();
        }
    }

    @Test
    @DisplayName("Вход в личный кабинет с валидными данными")
    public void shouldLogInWithValidData() throws InterruptedException {
        AuthorizationPage.logIn(validLogin, validPassword);
        MainPage.checkTradeMark();
        MainPage.logOut();

    }
    @Test
    @DisplayName("Вход в личный кабинет с пустыми полями")
    public void shouldTryLogInWithEmptyField() throws InterruptedException {
        AuthorizationPage.clickSignInButton();
        AuthorizationPage.checkMessageThatFieldShouldNotBeEmpty(activityTestRule);
    }
    @Test
    @DisplayName("Выход из личного кабинета")
    public void shouldLogOut() throws InterruptedException {
        AuthorizationPage.logIn(validLogin, validPassword);
        MainPage.logOut();
        AuthorizationPage.isAuthorizationScreen();
    }
    @Test
    @DisplayName("Поле Логин пустое, при авторизации (оторажение ошибки")
    public void loginFieldIsEmpty() throws InterruptedException {
        AuthorizationPage.logIn("",validPassword);
        AuthorizationPage.textErrorWrong();
    }




}