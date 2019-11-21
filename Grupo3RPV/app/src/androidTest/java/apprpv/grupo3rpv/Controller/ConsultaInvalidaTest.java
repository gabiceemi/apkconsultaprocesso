package apprpv.grupo3rpv.Controller;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import apprpv.grupo3rpv.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


@RunWith(AndroidJUnit4.class)
public class ConsultaInvalidaTest {

    @Rule
    public ActivityTestRule<ConsultaActivity> mActivityTestRule = new ActivityTestRule<>(ConsultaActivity.class);

    @Test
    public void numeroProcessoInexistenteTest() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editText), isDisplayed()));
        appCompatEditText.perform(replaceText("9"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editCPFouCNPJ), isDisplayed()));
        appCompatEditText2.perform(replaceText("02834768005"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonPesquisar), withText("Consultar")));
        appCompatButton.perform(scrollTo(), click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ViewInteraction textView = onView(
                allOf(withText("Detalhes do Processo"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                1),
                        isDisplayed()));
        textView.check(doesNotExist());

    }
    @Test
    public void numeroProcessoDeOutroCPFTest() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editText), isDisplayed()));
        appCompatEditText.perform(replaceText("1"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editCPFouCNPJ), isDisplayed()));
        appCompatEditText2.perform(replaceText("02834768005"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonPesquisar), withText("Consultar")));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction textView = onView(
                allOf(withText("Detalhes do Processo"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                1),
                        isDisplayed()));
        textView.check(doesNotExist());

    }
    @Test
    public void cpfInvalidoTest() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editText), isDisplayed()));
        appCompatEditText.perform(replaceText("2"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editCPFouCNPJ), isDisplayed()));
        appCompatEditText2.perform(replaceText("02834768000"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonPesquisar), withText("Consultar")));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction textView = onView(
                allOf(withText("Detalhes do Processo"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                1),
                        isDisplayed()));
        textView.check(doesNotExist());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
