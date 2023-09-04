package ru.iteco.fmhandroid.ui.page;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.supportsInputMethods;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import androidx.test.espresso.ViewInteraction;
import androidx.test.rule.ActivityTestRule;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class AuthorizationPage {

    public static ViewInteraction authorization = onView(withText("Authorization"));
    public static ViewInteraction signInButton = onView(withId(R.id.enter_button));
    public static ViewInteraction inputLoginText = onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.login_text_input_layout))));
    public static ViewInteraction inputPasswordText = onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.password_text_input_layout))));


    public static void isAuthorizationScreen() {
        Allure.step("Проверка, что открыто акно авторизации");
        authorization.check(matches(isDisplayed()));
    }

    public static void logIn(String login, String password) throws InterruptedException {
        Allure.step("Авторизация (вход в личный кабинет)");
        inputLoginText.perform(replaceText(login));
        inputLoginText.check(matches(withText(login)));
        inputPasswordText.perform(replaceText(password));
        inputPasswordText.check(matches(withText(password)));
        signInButton.perform(click());
        Thread.sleep(3500);
    }

    public static void clickSignInButton() {
        Allure.step("Кликнуть на кнопку Войти");
        signInButton.perform(click());
    }

    public static void checkMessageThatFieldShouldNotBeEmpty(ActivityTestRule<AppActivity> activityTestRule) {
        onView(withText(R.string.empty_login_or_password))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow()
                        .getDecorView())))).check(matches(withText("Login and password cannot be empty")));
    }

    public static void textErrorWrong() {
        Allure.step("Отобразилаcь ошибка Wrong login or password");
        onView(allOf(withContentDescription("Wrong login or password"), isDisplayed()));
    }
}
