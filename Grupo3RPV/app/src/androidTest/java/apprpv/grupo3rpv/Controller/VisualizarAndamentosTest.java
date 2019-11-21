package apprpv.grupo3rpv.Controller;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by Dienefer Fialho
 *
 * Classe que testa a visualização dos andamentos dos processos
 *
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class VisualizarAndamentosTest {

    @Rule
    public ActivityTestRule<ConsultaActivity> mActivityTestRule = new ActivityTestRule<>(ConsultaActivity.class);

    @Test
    public void visualizarAndamento1doProcesso1test() {
           ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editText), isDisplayed()));
        appCompatEditText.perform(replaceText("1"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editCPFouCNPJ), isDisplayed()));
        appCompatEditText2.perform(replaceText("01819901050"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonPesquisar), withText("Consultar")));
        appCompatButton.perform(scrollTo(), click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
          ViewInteraction relativeLayout = onView(
                allOf(childAtPosition(
                        withId(R.id.listViewF),
                        0),
                        isDisplayed()));
        relativeLayout.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.textViewData)));
        textView.check(matches(withText("30/02/2016")));



    }
    @Test
    public void visualizarAndamento2doProcesso1test() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editText), isDisplayed()));
        appCompatEditText.perform(replaceText("1"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editCPFouCNPJ), isDisplayed()));
        appCompatEditText2.perform(replaceText("01819901050"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonPesquisar), withText("Consultar")));
        appCompatButton.perform(scrollTo(), click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction relativeLayout = onView(
                allOf(childAtPosition(
                        withId(R.id.listViewF),
                        1),
                        isDisplayed()));
        relativeLayout.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.textViewData)));
        textView.check(matches(withText("05/10/2016")));

    }
    @Test
    public void visualizarAndamento3doProcesso1test() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editText), isDisplayed()));
        appCompatEditText.perform(replaceText("1"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editCPFouCNPJ), isDisplayed()));
        appCompatEditText2.perform(replaceText("01819901050"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonPesquisar), withText("Consultar")));
        appCompatButton.perform(scrollTo(), click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction relativeLayout = onView(
                allOf(childAtPosition(
                        withId(R.id.listViewF),
                        2),
                        isDisplayed()));
        relativeLayout.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.textViewData)));
        textView.check(matches(withText("12/12/2016")));

    }
    @Test
    public void visualizarAndamento1doProcesso2test() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editText), isDisplayed()));
        appCompatEditText.perform(replaceText("2"), closeSoftKeyboard());

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


        ViewInteraction relativeLayout = onView(
                allOf(childAtPosition(
                        withId(R.id.listViewF),
                        0),
                        isDisplayed()));
        relativeLayout.perform(click());


        ViewInteraction textView = onView(
                allOf(withId(R.id.textViewData)));
        textView.check(matches(withText("25/07/2015")));

    }
    @Test
    public void visualizarAndamento2doProcesso2test() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editText), isDisplayed()));
        appCompatEditText.perform(replaceText("2"), closeSoftKeyboard());

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

        ViewInteraction relativeLayout = onView(
                allOf(childAtPosition(
                        withId(R.id.listViewF),
                        1),
                        isDisplayed()));
        relativeLayout.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.textViewData)));
        textView.check(matches(withText("21/08/2015")));

    }
    @Test
    public void visualizarAndamento3doProcesso2test() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editText), isDisplayed()));
        appCompatEditText.perform(replaceText("2"), closeSoftKeyboard());

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

        ViewInteraction relativeLayout = onView(
                allOf(childAtPosition(
                        withId(R.id.listViewF),
                        2),
                        isDisplayed()));
        relativeLayout.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.textViewData)));
        textView.check(matches(withText("30/10/2015")));

    }
    @Test
    public void visualizarAndamento1doProcesso3test() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editText), isDisplayed()));
        appCompatEditText.perform(replaceText("3"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editCPFouCNPJ), isDisplayed()));
        appCompatEditText2.perform(replaceText("10706431901"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonPesquisar), withText("Consultar")));
        appCompatButton.perform(scrollTo(), click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ViewInteraction relativeLayout = onView(
                allOf(childAtPosition(
                        withId(R.id.listViewF),
                        0),
                        isDisplayed()));
        relativeLayout.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.textViewData)));
        textView.check(matches(withText("12/04/2014")));

    }
    @Test
    public void visualizarAndamento2doProcesso3test() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editText), isDisplayed()));
        appCompatEditText.perform(replaceText("3"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editCPFouCNPJ), isDisplayed()));
        appCompatEditText2.perform(replaceText("10706431901"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonPesquisar), withText("Consultar")));
        appCompatButton.perform(scrollTo(), click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        ViewInteraction relativeLayout = onView(
                allOf(childAtPosition(
                        withId(R.id.listViewF),
                        1),
                        isDisplayed()));
        relativeLayout.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.textViewData)));
        textView.check(matches(withText("25/12/2014")));

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
                        && view.equals(((ViewGroup)parent).getChildAt(position));
            }
        };
    }
}