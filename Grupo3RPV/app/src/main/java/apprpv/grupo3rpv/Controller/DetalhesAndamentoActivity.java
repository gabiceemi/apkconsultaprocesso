package apprpv.grupo3rpv.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import apprpv.grupo3rpv.R;

/**
 * Classe responsável pela tela de detalhes dos andamentos
 */
public class DetalhesAndamentoActivity extends AppCompatActivity {

    private String data;
    private String departamento;
    private String ocorrencia;
    private String despacho;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhes_andamento);
        Bundle bundle;
        bundle = getIntent().getExtras();

        data = bundle.getString("data");
        departamento = bundle.getString("departamento");
        ocorrencia  = bundle.getString("ocorrencia");
        despacho = bundle.getString("despacho");

        visualizarDetalhesAndamento();

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
      }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    /**
     * Método responsável por apresentar os detalhes dos andamentos nos respectivos textViews
     */
    private void visualizarDetalhesAndamento(){
          TextView textViewData = (TextView) findViewById(R.id.textViewData);
          textViewData.setText(data);
          TextView textViewDepto = (TextView) findViewById(R.id.textViewDepartamento);
          textViewDepto.setText(departamento);
          TextView textViewOcorrencia = (TextView) findViewById(R.id.textViewOcorrencia);
          textViewOcorrencia.setText(ocorrencia);
          TextView textViewDespacho = (TextView) findViewById(R.id.tvDespacho);
          textViewDespacho.setText(despacho);
      }

}
