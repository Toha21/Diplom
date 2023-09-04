package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.action.ViewActions.click;

import android.view.View;

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
import ru.iteco.fmhandroid.ui.page.NewsCreationAndEditingPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;
import ru.iteco.fmhandroid.ui.utils.MainHelper;

@RunWith(AllureAndroidJUnit4.class)
public class NewsTests {

    String title = "Объявление";
    String errorMessage = "Fill empty fields";
    public View decorView;
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

    @Test//не стабилен
    @DisplayName("Добавление новой вовости")
    public void addingNewNews() throws InterruptedException {
        String descriptionTwo = MainHelper.getRandomNewsDescription();
        String testName = descriptionTwo;

        NewsPage.initiateTheCreationOfNews();
        NewsCreationAndEditingPage.chooseCategory(title);
        NewsCreationAndEditingPage.addNewsCurrentDate();
        NewsCreationAndEditingPage.addNewsCurrentTime();
        NewsCreationAndEditingPage.addNewsDescription(descriptionTwo);
        NewsCreationAndEditingPage.saveNewNews();
        Thread.sleep(5000);
        NewsCreationAndEditingPage.findAddedNews(testName);
    }
    @Test
    @DisplayName("Новая новости с незаполненой дата")
    public void addNewNewsWithУEmptyFields() throws InterruptedException {

        NewsPage.initiateTheCreationOfNews();
        NewsCreationAndEditingPage.saveNewNews();
        NewsCreationAndEditingPage.checkToastErrorMessage(activityTestRule);
    }


    @Test
    @DisplayName("Фильтрация новостей по выбранной категории")
    public void newsFilteringByCategory() throws InterruptedException {
        MainPage.menu.perform(click()); //all news
        MainPage.news.perform(click());
        NewsPage.sortNewsButton.perform(click());
        NewsCreationAndEditingPage.chooseCategory(title);
        NewsPage.selectTheBeginningOfTheIntervalForTheFilter();
        NewsPage.selectTheEndOfTheIntervalForTheFilter();
        NewsPage.filterButton.perform(click());
        NewsPage.checkNewsCategory(title, 0);
    }
    @Test
    @DisplayName("Завершить создание новой вовости")
    public void cancelIngNewNews() throws InterruptedException {   //добавление новой новости
        String descriptionTwo = MainHelper.getRandomNewsDescription();

        NewsPage.initiateTheCreationOfNews();
        NewsCreationAndEditingPage.chooseCategory(title);
        NewsCreationAndEditingPage.addNewsCurrentDate();
        NewsCreationAndEditingPage.addNewsCurrentTime();
        NewsCreationAndEditingPage.addNewsDescription(descriptionTwo);
        NewsPage.сancelSavingNews();
    }

}
