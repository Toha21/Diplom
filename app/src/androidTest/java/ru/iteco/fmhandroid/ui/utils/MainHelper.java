package ru.iteco.fmhandroid.ui.utils;

import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Random;

public class MainHelper {
    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {
            int currentIndex = 0;

            @Override
            public void describeTo(Description description) {
                description.appendText("with index: ");
                description.appendValue(index);
                matcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                return matcher.matches(view) && currentIndex++ == index;
            }
        };
    }
    public static String getRandomClaimTitle() {
        Random random = new Random();
        return "Новая претензия № " + random.nextInt(500);
    }
    public static String getRandomNewsDescription() {
        Random random = new Random();
        return "Коммент № " + random.nextInt(500) + "а";
    }


}