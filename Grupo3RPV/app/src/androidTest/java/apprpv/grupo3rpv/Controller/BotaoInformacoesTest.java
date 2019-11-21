package apprpv.grupo3rpv.Controller;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import apprpv.grupo3rpv.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 *
 * Created by Dienefer Fialho
 *
 * Classe que testa o funcionamento dos botões de sobre e ajuda na tela inicial do aplicativo
 *
 */

@RunWith(AndroidJUnit4.class)
public class BotaoInformacoesTest {

    @Rule
    public ActivityTestRule<ConsultaActivity> mActivityTestRule = new ActivityTestRule<>(ConsultaActivity.class);

    @Test
    public void botaoInfoTest() {
        ViewInteraction appCompatImageButton = onView(
                withId(R.id.infoo));
        appCompatImageButton.perform(scrollTo(), click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.alertTitle)));
        textView.check(matches(withText("Sobre o aplicativo")));

        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button1), withText("Fechar")));
        appCompatButton.perform(scrollTo(), click());
    }
}
