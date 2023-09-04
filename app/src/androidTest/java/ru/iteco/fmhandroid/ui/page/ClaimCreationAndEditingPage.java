package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;

import org.hamcrest.core.IsInstanceOf;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class ClaimCreationAndEditingPage {

    public static ViewInteraction titleOfClaimsCreatingPage = onView(withText("Creating"));
    public static ViewInteraction titleTextInputOfClaim = onView(withId(R.id.title_edit_text));
    public static ViewInteraction dateField = onView((withId(R.id.date_in_plan_text_input_edit_text)));
    public static ViewInteraction executorField = onView(allOf(withId(com.google.android.material.R.id.text_input_end_icon), withContentDescription("Show dropdown menu")));
    public static ViewInteraction claimsList = onView(withId(R.id.claim_list_recycler_view));
    public static ViewInteraction timeInPlanOfClaim = onView(withId(R.id.time_in_plan_text_input_edit_text));
    public static ViewInteraction descriptionTextInputOfClaim = onView(withId(R.id.description_edit_text));
    public static ViewInteraction saveButtonOfClaim = onView(withId(R.id.save_button));
    public static ViewInteraction okButton = onView(withText("OK"));


    public static void addClaimTitle(String title) {
        Allure.step("Добавить заголовок новой Претензии");
        titleTextInputOfClaim.perform(replaceText(title), closeSoftKeyboard());
    }
    public static void addClaimDescription(String description) {
        Allure.step("Добавить описание новой Претензии");
        descriptionTextInputOfClaim.perform(replaceText(description), closeSoftKeyboard());
    }
    public static void addClaimCurrentDate() {
        Allure.step("Выбрать текущую дату новой Претензии");
        dateField.perform(click());
        okButton.perform(click());
    }
    public static void addClaimCurrentTime() {
        Allure.step("Выбрать текущее время новой Претензии");
        timeInPlanOfClaim.perform(click());
        okButton.perform(click());
    }
    public static void chooseExecutor() {
        Allure.step("Выбрать исполнителя новой Претензии");
        executorField.perform(click());
        onView(withText("Ivanov Ivan Ivanovich"))
                .inRoot(RootMatchers.isPlatformPopup())
                .perform(click());
    }
    public static void saveNewClaim() {
        Allure.step("Сохранение новой Претензии");
        saveButtonOfClaim.perform(scrollTo(), click());
    }

    public static void findAddedClaim(String title) throws InterruptedException {
        Allure.step("Найти добавленную претензию по названию");
        claimsList.perform(RecyclerViewActions.scrollTo(hasDescendant(withText(title)))).check(matches(isDisplayed()));
        Thread.sleep(5000);
    }
    public static void addClaimInvalidDate(String date) {
        Allure.step("Добавить некорректную дату новой Претензии");
        dateField.perform(longClick()).perform(replaceText(date), closeSoftKeyboard());
    }
    public static void checkToastErrorMessage(String messageError){
        Allure.step("Появление всплывающего сообщения об ошибке");
        onView(allOf(IsInstanceOf.instanceOf(android.widget.TextView.class),
                withText(messageError)))
                .check(matches(isDisplayed()));

    }

}
