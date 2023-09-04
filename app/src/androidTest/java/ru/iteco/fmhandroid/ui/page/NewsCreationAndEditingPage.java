package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.rule.ActivityTestRule;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class NewsCreationAndEditingPage {
    public static ViewInteraction titleOfNewsCreatingWindow = onView(withId(R.id.custom_app_bar_sub_title_text_view));
    public static ViewInteraction categoryTextInputOfNews = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    public static ViewInteraction publicationDateTextInputOfNews = onView(withId(R.id.news_item_publish_date_text_input_edit_text));
    public static ViewInteraction okButton = onView(withText("OK"));
    public static ViewInteraction timeTextInputOfNews = onView(withId(R.id.news_item_publish_time_text_input_edit_text));
    public static ViewInteraction descriptionTextInputOfNews = onView(withId(R.id.news_item_description_text_input_edit_text));
    public static ViewInteraction saveButtonOfNews = onView(withId(R.id.save_button));
    public static ViewInteraction newsList = onView(withId(R.id.news_list_recycler_view));


    public static void chooseCategory(String title) {
        Allure.step("Выбрать категорию новости " + title);
        categoryTextInputOfNews.perform(click());
        onView(withText(title))
                .inRoot(RootMatchers.isPlatformPopup())
                .perform(click());
    }

    public static void addNewsCurrentDate() {
        Allure.step("Выбрать текущую дату новой Новости");
        publicationDateTextInputOfNews.perform(click());
        okButton.perform(click());
    }

    public static void addNewsCurrentTime() {
        Allure.step("Выбрать текущее время новой Новости");
        timeTextInputOfNews.perform(click());
        okButton.perform(click());
    }

    public static void addNewsDescription(String description) {
        Allure.step("Добавить описание новой новости " + description);
        descriptionTextInputOfNews.perform(replaceText(description), closeSoftKeyboard());
    }

    public static void saveNewNews() {
        Allure.step("Сохранение новой Новости");
        saveButtonOfNews.perform(scrollTo(), click());
    }

    public static void findAddedNews(String title) throws InterruptedException {
        Allure.step("Найти добавленную претензию по названию");
        newsList.perform(RecyclerViewActions.scrollTo(hasDescendant(withText(title)))).check(matches(isDisplayed()));
        Thread.sleep(5000);
    }

    public static void checkToastErrorMessage(ActivityTestRule<AppActivity> activityTestRule) {
        Allure.step("Появление всплывающего сообщения об ошибке");
        onView(withText("Fill empty fields"))
                    .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow()
                            .getDecorView())))).check(matches(withText("Fill empty fields")));
        }


    }




