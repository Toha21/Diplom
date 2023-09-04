package ru.iteco.fmhandroid.ui.tests;

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
import ru.iteco.fmhandroid.ui.page.ClaimCreationAndEditingPage;
import ru.iteco.fmhandroid.ui.page.ClaimsPage;
import ru.iteco.fmhandroid.ui.utils.MainHelper;

@RunWith(AllureAndroidJUnit4.class)
public class ClaimsTest {
    String errorMessage = "Fill empty fields";

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
    @Test
    @DisplayName("Наличие притензии в блоке \"Притензии\"")
    public void shouldBeThreeClaimsInClaimsBlock() throws InterruptedException {
        ClaimsPage.goToClaimsBlock();
        Thread.sleep(3000);
        ClaimsPage.checkThatThereAreThreeClaimsItemsInTheClaimsBlock();
    }
    @Test
    @DisplayName("Откыть притензии (вся иформация видна)")
    public void shouldBeFullContentOfExpandedClaimInClaimsBlock() throws InterruptedException {
        ClaimsPage.goToClaimsBlock();
        ClaimsPage.goToFirstClaimFromClaimsBlock();
        Thread.sleep(3000);
        ClaimsPage.checkFullContentOfExpandedClaim();
    }

    @Test
    @DisplayName("Добавление новой притензии с корректно заполненными полями")
    public void AddingNewClaimWithCorrectlyFilledFields() throws InterruptedException {

        String description = MainHelper.getRandomNewsDescription();//описание
        String title = MainHelper.getRandomClaimTitle();//название
        String testName = title;
        ClaimsPage.initiateTheCreationOfClaim();
        ClaimCreationAndEditingPage.addClaimTitle(title);
        ClaimCreationAndEditingPage.chooseExecutor();
        ClaimCreationAndEditingPage.addClaimCurrentDate();
        ClaimCreationAndEditingPage.addClaimCurrentTime();
        ClaimCreationAndEditingPage.addClaimDescription(description);
        ClaimCreationAndEditingPage.saveNewClaim();
        ClaimsPage.goToClaimsBlock();
        Thread.sleep(10000);
        ClaimCreationAndEditingPage.findAddedClaim(testName);
        Thread.sleep(5000);
    }
    @Test
    @DisplayName("Новая притензия с незаполнеными полями")
    public void addNewClaimWithУEmptyFields() throws InterruptedException {

        ClaimsPage.initiateTheCreationOfClaim();
        ClaimCreationAndEditingPage.saveNewClaim();
        Thread.sleep(5000);
        ClaimCreationAndEditingPage.checkToastErrorMessage(errorMessage);
    }

    @Test
    @DisplayName("Новая притензия с незаполненой дата")
    public void addNewClaimWithBlankDate() throws InterruptedException {
        String description = MainHelper.getRandomNewsDescription();
        String title = MainHelper.getRandomClaimTitle();

        ClaimsPage.initiateTheCreationOfClaim();
        ClaimCreationAndEditingPage.addClaimTitle(title);
        ClaimCreationAndEditingPage.chooseExecutor();
        ClaimCreationAndEditingPage.addClaimCurrentTime();
        ClaimCreationAndEditingPage.addClaimDescription(description);
        ClaimCreationAndEditingPage.saveNewClaim();
        Thread.sleep(5000);
        ClaimCreationAndEditingPage.checkToastErrorMessage(errorMessage);
    }
    @Test //не стабилен (падает при запуске все тестов)
    @DisplayName("Выбран статус \"Открыта\" при фильтрации притензии")
    public void openStatusIsChosenDuringClaimsFiltering() throws InterruptedException {
        ClaimsPage.goToClaimsBlock();
        ClaimsPage.clickButtonFilter();
        ClaimsPage.clickRemoveCheckBoxOpen();
        ClaimsPage.clickButtonOk();
        Thread.sleep(6000);
        ClaimsPage.goToFirstClaimFromClaimsBlock();
        Thread.sleep(5000);
        ClaimsPage.statusCheck();
    }




}
