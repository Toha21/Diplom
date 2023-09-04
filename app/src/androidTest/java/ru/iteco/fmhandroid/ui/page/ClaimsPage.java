package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.utils.MainHelper;


public class ClaimsPage {
    public static ViewInteraction claimsBlockName = onView(withText("Claims"));
    public static ViewInteraction addNewClaims = onView(withId(R.id.add_new_claim_material_button));
    public static ViewInteraction firstClaimCard = onView(MainHelper.withIndex(withId(R.id.claim_list_card), 0));
    public static ViewInteraction firstClaimTopicInClaimsBlock = onView(MainHelper.withIndex(withId(R.id.description_material_text_view), 0));
    public static ViewInteraction secondClaimTopicInClaimsBlock = onView(MainHelper.withIndex(withId(R.id.description_material_text_view), 1));
    public static ViewInteraction thirdClaimTopicInClaimsBlock = onView(MainHelper.withIndex(withId(R.id.description_material_text_view), 2));

    public static ViewInteraction filtersButton = onView(withId(R.id.filters_material_button));
    public static ViewInteraction open = onView(withId(R.id.item_filter_open));
    public static ViewInteraction okButton = onView(withId(R.id.claim_list_filter_ok_material_button));
    public static ViewInteraction titleTextOfClaim = onView(withId(R.id.title_text_view));
    public static ViewInteraction executorTextOfClaim = onView(withId(R.id.executor_name_text_view));
    public static ViewInteraction planeDateOfClaim = onView(withId(R.id.plane_date_text_view));
    public static ViewInteraction planTimeOfClaim = onView(withId(R.id.plan_time_text_view));
    public static ViewInteraction statusOfClaim = onView(withId(R.id.status_label_text_view));
    public static ViewInteraction descriptionOfClaim = onView(withId(R.id.description_text_view));
    public static ViewInteraction authorOfClaim = onView(withId(R.id.author_name_text_view));
    public static ViewInteraction creationDateOfClaim = onView(withId(R.id.create_data_text_view));
    public static ViewInteraction creationTimeOfClaim = onView(withId(R.id.create_time_text_view));
    public static ViewInteraction firstCommentDescriptionOfClaim = onView(MainHelper.withIndex(withId(R.id.comment_description_text_view), 0));
    public static ViewInteraction firstCommentatorNameOfClaim = onView(MainHelper.withIndex(withId(R.id.commentator_name_text_view), 0));
    public static ViewInteraction firstCommentDateOfClaim = onView(MainHelper.withIndex(withId(R.id.comment_date_text_view), 0));
    public static ViewInteraction firstCommentTimeOfClaim = onView(MainHelper.withIndex(withId(R.id.comment_time_text_view), 0));
    public static ViewInteraction status = onView(withId(R.id.status_label_text_view));
    public static void goToClaimsBlock() throws InterruptedException {
        Allure.step("Переход в блок \"притензии\"");
        MainPage.menu.perform(click());
        MainPage.claims.check(matches(isDisplayed()));
        MainPage.claims.perform(click());
        Thread.sleep(3500);
        claimsBlockName.check(matches(isDisplayed()));
    }
    public static void checkThatThereAreThreeClaimsItemsInTheClaimsBlock() {
        Allure.step("Проверка, что в блоке притензии имеется минимум 3 притензии");
        firstClaimTopicInClaimsBlock.check(matches(isDisplayed()));
        secondClaimTopicInClaimsBlock.check(matches(isDisplayed()));
        thirdClaimTopicInClaimsBlock.check(matches(isDisplayed()));
    }
    public static void goToFirstClaimFromClaimsBlock() {
        Allure.step("Переход к первой притензии");
        firstClaimCard.perform(click());
        titleTextOfClaim.check(matches(isDisplayed()));
    }
    public static void statusCheck() {
        Allure.step("Проверка статуса");
        status.perform(click());
        titleTextOfClaim.check(matches(isDisplayed()));
    }
    public static void checkFullContentOfExpandedClaim() {
        Allure.step("Проверка, всех элементов притензии");
        titleTextOfClaim.check(matches(isDisplayed()));
        executorTextOfClaim.check(matches(isDisplayed()));
        planeDateOfClaim.check(matches(isDisplayed()));
        planTimeOfClaim.check(matches(isDisplayed()));
        statusOfClaim.check(matches(isDisplayed()));
        descriptionOfClaim.check(matches(isDisplayed()));
        authorOfClaim.check(matches(isDisplayed()));
        creationDateOfClaim.check(matches(isDisplayed()));
        creationTimeOfClaim.check(matches(isDisplayed()));
        firstCommentDescriptionOfClaim.check(matches(isDisplayed()));
        firstCommentatorNameOfClaim.check(matches(isDisplayed()));
        firstCommentDateOfClaim.check(matches(isDisplayed()));
        firstCommentTimeOfClaim.check(matches(isDisplayed()));
    }
    public static void initiateTheCreationOfClaim() {
        Allure.step("Начинаем создавать притензии (переход в раздел создания притензии)");
        addNewClaims.perform(click());
        ClaimCreationAndEditingPage.titleOfClaimsCreatingPage.check(matches(isDisplayed()));
    }


    public static void clickButtonFilter() {
        Allure.step("Нажать на кнопку Фильтрация");
        filtersButton.perform(click());
    }
    public static void clickRemoveCheckBoxOpen() {
        Allure.step("Снять флажок с чекбокса Открыта");
        open.perform(click());
    }
    public static void clickButtonOk() {
        Allure.step("Нажать кнопку ОК");
        okButton.perform(click());
    }

}

