package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.utils.MainHelper.withIndex;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class QuotesPage {
    public static ViewInteraction openOrCloseFirstQuoteButton = onView(withIndex(withId(R.id.our_mission_item_open_card_image_button), 0));

    public static void openOrCloseFirstQuote() {
        Allure.step("Раскрыть/свернуть первую цитату");
        openOrCloseFirstQuoteButton.perform(click());a
    }

    public static void checkThatFirstQuoteContentIsFull(String description) {
        Allure.step("Проверить содержимое первой цитаты");
        onView(allOf(withId(R.id.our_mission_item_description_text_view), withText(description), isDisplayed()));
    }

}
