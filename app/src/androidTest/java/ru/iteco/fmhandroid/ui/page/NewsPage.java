package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static ru.iteco.fmhandroid.ui.utils.MainHelper.withIndex;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class NewsPage {

    public static ViewInteraction openFirstNewsInNewsBlock = onView(withIndex(withId(R.id.view_news_item_image_view), 0));
    public static ViewInteraction firstCardNews = onView(withIndex(withId(R.id.news_item_material_card_view), 0));
    public static ViewInteraction firstNewsItemTitle = onView(withIndex(withId(R.id.news_item_title_text_view), 0));

    public static ViewInteraction firstNewsItemDescription = onView(withIndex(withId(R.id.news_item_description_text_view), 0));
    public static ViewInteraction firstNewsItemDate = onView(withIndex(withId(R.id.news_item_date_text_view), 0));
    public static ViewInteraction editNewsButton = onView(withId(R.id.edit_news_material_button));
    public static ViewInteraction addNewsButton = onView(withId(R.id.add_news_image_view));
    public static ViewInteraction theBeginningOfTheIntervalFilter = onView((withId(R.id.news_item_publish_date_start_text_input_edit_text)));
    public static ViewInteraction theEndOfTheIntervalFilter = onView((withId(R.id.news_item_publish_date_end_text_input_edit_text)));

    public static ViewInteraction filterButton = onView(withId( R.id.filter_button));
    public static ViewInteraction okButton = onView(withText("OK"));
    public static ViewInteraction cancelButton = onView(withId(R.id.cancel_button));
    public static ViewInteraction sortNewsButton = onView(withId(R.id.filter_news_material_button));

    public static void initiateTheCreationOfNews() {
        Allure.step("Начинаем создавать новость (переход к созданию новости)");
        MainPage.menu.perform(click());
        MainPage.news.perform(click());
        editNewsButton.perform(click());
        addNewsButton.check(matches(isDisplayed()));
        addNewsButton.perform(click());
        NewsCreationAndEditingPage.titleOfNewsCreatingWindow.check(matches(isDisplayed()));
    }
    public static void checkThatNewsDoesNotExistInNewsBlockWhenItHasNotActiveStatus(String category, String description) {
        Allure.step("Проверка того, что не активная новость не видна в блоке новостей ");
        firstCardNews.perform(click());
        firstNewsItemTitle.check(matches(not(withText(category))));
        firstNewsItemDescription.check(matches(not(withText(description))));
    }
    public static void selectTheBeginningOfTheIntervalForTheFilter() {
        Allure.step("Выбрать начало интервала для Фильтра (текущая дата)");
        theBeginningOfTheIntervalFilter.perform(click());
        okButton.perform(click());
    }
    public static void selectTheEndOfTheIntervalForTheFilter() {
        Allure.step("Выбрать конец интервала для Фильтра (текущая дата)");
        theEndOfTheIntervalFilter.perform(click());
        okButton.perform(click());
    }
    public static void checkNewsCategory(String title, int position) {
        Allure.step("Проверить категорию новости в Списке");
        ViewInteraction textView = onView(
                allOf(withIndex(withId(R.id.news_item_title_text_view), position),
                        isDisplayed()));
        textView.check(matches(withText(title)));
    }

    public static void сancelSavingNews() {
        Allure.step("Отмена сохранения новости");
        cancelButton.perform(click());
        okButton.perform(click());
        addNewsButton.check(matches(isDisplayed()));
    }

}
