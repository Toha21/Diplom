package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;


public class MainPage {

    //удалить
    public static ViewInteraction tradeMark = onView(withId(R.id.trademark_image_view));
    public static ViewInteraction personalArea = onView(withId(R.id.authorization_image_button));
    public static ViewInteraction logOut = onView(withText("Log out"));


    public static ViewInteraction menu =  onView(withId(R.id.main_menu_image_button));
    public static ViewInteraction claims = onView(withText("Claims"));
    public static ViewInteraction news = onView(withText("News"));
    public static ViewInteraction about = onView(withText("About"));
    public static ViewInteraction quoteBlock = onView(withId(R.id.our_mission_image_button));

    public static void checkTradeMark() {
        Allure.step("Проверка видимости эмблемы приложения");
        tradeMark.check(matches(isDisplayed()));
    }


    private static ViewAction waitDisplayed(ViewInteraction tradeMark, int i) {
        return null;
    }

    public static void logOut() {
        Allure.step("Выход из личного кабинета");
        personalArea.perform(click());
        logOut.check(matches(isDisplayed()));
        logOut.perform(click());
    }

}


