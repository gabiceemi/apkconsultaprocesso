package apprpv.grupo3rpv.Controller;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

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
 *
 * Created by Dienefer Fialho
 *
 * Classe que testa a visualização dos detalhes dos processos
 *
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class DetalhesProcessoTest {

    @Rule
    public ActivityTestRule<ConsultaActivity> mActivityTestRule = new ActivityTestRule<>(ConsultaActivity.class);
    @Test
    public void detalhesProcesso1Test() {
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
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ViewInteraction textView = onView(
                allOf(withId(R.id.TextViewRequerente)));
        textView.check(matches(withText("Jonnathan Riquelmo")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.TextViewNome)));

        textView2.check(matches(withText("Dee Fialho")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.TextViewObservacao)));
        textView3.check(matches(withText("obsobsobs")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.TextViewInstituicao)));
        textView4.check(matches(withText("Prefeitura Municipal do Alegrete")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.TextViewDepartamento)));
        textView5.check(matches(withText("Secretaria de Administração")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.TextViewAtendente)));
        textView7.check(matches(withText("Letícia Ana Lima")));


    }
    @Test
    public void detalhesProcesso2Test() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editText), isDisplayed()));
        appCompatEditText.perform(replaceText("2"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editCPFouCNPJ), isDisplayed()));
        appCompatEditText2.perform(replaceText("02834768005"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(withText("Consultar"));
        appCompatButton.perform(scrollTo(), click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ViewInteraction textView = onView(
                allOf(withId(R.id.TextViewRequerente)));
        textView.check(matches(withText("Dee Fialho")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.TextViewNome)));

        textView2.check(matches(withText("Gabi Ceemi")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.TextViewObservacao)));
        textView3.check(matches(withText("obsobsobs obsobsobs")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.TextViewInstituicao)));
        textView4.check(matches(withText("Prefeitura Municipal do Alegrete")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.TextViewDepartamento)));
        textView5.check(matches(withText("Secretaria de Fazenda")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.TextViewAtendente)));
        textView6.check(matches(withText("Nathan Miguel Pereira")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.TextViewAtendente)));
        textView7.check(matches(withText("Nathan Miguel Pereira")));

    }
    @Test
    public void detalhesProcesso3Test() {
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
        ViewInteraction textView = onView(
                allOf(withId(R.id.TextViewRequerente)));
        textView.check(matches(withText("Gabi Ceemi")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.TextViewNome)));

        textView2.check(matches(withText("Jonnathan Riquelmo")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.TextViewObservacao)));
        textView3.check(matches(withText("obsobs")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.TextViewInstituicao)));
        textView4.check(matches(withText("Prefeitura Municipal do Alegrete")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.TextViewDepartamento)));
        textView5.check(matches(withText("Secretaria de Obras e Serviços Urbanos")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.TextViewAtendente)));
        textView7.check(matches(withText("Kevin Iago dos Santos")));

    }


}
