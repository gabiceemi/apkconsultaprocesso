package apprpv.grupo3rpv.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import apprpv.grupo3rpv.R;

/**
 * Classe responsável por apresentar o ícone na tela ao iniciar a aplicação
 */
public class SplashScreen extends AppCompatActivity implements Runnable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handler = new Handler();
        handler.postDelayed(this, 3000);
    }

    @Override
    public void run() {
        startActivity(new Intent(this, ConsultaActivity.class));
        finish();
    }
}