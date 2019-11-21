package apprpv.grupo3rpv.Controller;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.itextpdf.text.DocumentException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import apprpv.grupo3rpv.Model.Domain.Andamento;
import apprpv.grupo3rpv.R;

/**
 * Classe responsável pela tela que possui as tabs de processo e andamentos
 */

@SuppressWarnings("JavaDoc")
public class TabsActivity extends AppCompatActivity {

    public ArrayList<Andamento> andamentos;
    private File pdfFile;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 111;
    private Integer numCP;
    private String numProc;
    private String data;
    private String tipo;
    private String departamento;
    private String instituicao;
    private String requerente;
    private String observacao;
    private String nome;
    private String hora;
    private String atendente;
    public Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        bundle = getIntent().getExtras();

        pegarDados();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        ViewPager mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_abrir) {
            try {
                verificaPermissaoPdf();
            } catch (FileNotFoundException | DocumentException e) {
                e.printStackTrace();
            }
            visualizarPdf();
        }
        if(item.getItemId() == R.id.action_compartilhar) {
            try {
                verificaPermissaoPdf();
            } catch (FileNotFoundException | DocumentException e) {
                e.printStackTrace();
            }
            compartilharPDF();
        }
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    ProcessoFragment processoFragment = new ProcessoFragment();
                    return processoFragment;
                case 1:
                    AndamentosFragment andamentosFragment = new AndamentosFragment();
                    return andamentosFragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Processo";
                case 1:
                    return "Andamentos";
            }
            return null;
        }
    }

    public void pegarDados(){
        numCP = bundle.getInt("numCP");
        numProc = bundle.getString("numProc");
        data = bundle.getString("data");
        tipo = bundle.getString("tipo");
        departamento = bundle.getString("departamento");
        instituicao = bundle.getString("instituicao");
        requerente = bundle.getString("requerente");
        observacao = bundle.getString("observacao");
        nome = bundle.getString("nome");
        hora = bundle.getString("hora");
        atendente = bundle.getString("atendente");
        andamentos = (ArrayList<Andamento>) bundle.getSerializable("lista");
    }

    /**
     * Método responsável pela permissão de salvar um arquivo no dispositivo
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    try {
                        verificaPermissaoPdf();
                    } catch (FileNotFoundException | DocumentException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(this, "Permissão de escrita negada (WRITE_EXTERNAL)", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    /**
     * Método responsável por verificar a permissão de salvar o PDF no dispositivo
     * @throws FileNotFoundException
     * @throws DocumentException
     */
    protected void verificaPermissaoPdf() throws FileNotFoundException,DocumentException{
        int hasWriteStoragePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CONTACTS)) {
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_CODE_ASK_PERMISSIONS);
                }
            }
        } else {
            criarArquivoPdf();
        }
    }

    /**
     * Método responsável por criar o arquivo PDF e enviar os dados do Processo para o GeradorPDF
     * @throws FileNotFoundException
     * @throws DocumentException
     */
    private void criarArquivoPdf() throws FileNotFoundException, DocumentException {
        try {
            GeradorPDF pdf = new GeradorPDF(this.andamentos);
            this.pdfFile = pdf.montaPDF(this.numCP, this.numProc, this.data, this.tipo, this.departamento, this.instituicao, this.requerente, this.observacao, this.nome, this.hora, this.atendente, this);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Método responsável por compartilhar para as redes sociais que aceitam PDF
     */
    protected void compartilharPDF(){
        Uri arquivoPDF = Uri.fromFile(pdfFile);
        Intent _intent = new Intent();
        _intent.setAction(Intent.ACTION_SEND);
        _intent.putExtra(Intent.EXTRA_STREAM,  arquivoPDF);
        _intent.putExtra(Intent.EXTRA_TEXT,  "EXTRATO DE ANDAMENTOS");
        _intent.putExtra(Intent.EXTRA_TITLE,   "Processos da Prefeitura");
        _intent.setType("application/pdf");
        startActivity(Intent.createChooser(_intent, "COMPARTILHAR ANDAMENTO"));
    }

    /**
     * Método responsável em usar um leitor de PDF do dispositivo para visualizar o extrato de andamentos
     */
    protected void visualizarPdf() {
        PackageManager packageManager = getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType("application/pdf");
        List list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
        if (list.size() > 0) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(pdfFile);
            intent.setDataAndType(uri, "application/pdf");

            startActivity(intent);
        }else{
            Toast.makeText(this,"Você precisa baixar um leitor de PDF para poder visualizar o arquivo!",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Método responsável por saltar a tela de visualizar ou compartilhar Andamentos
     * @param view
     */
    public void visualizarOuCompartilharPDF(View view) {
        try {
            verificaPermissaoPdf();
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            visualizarPdf();
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            compartilharPDF();
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Foi gerado um extrato dos andamentos na pasta Extrato de Andamentos do seu celular.\n\n O que deseja fazer?\n\n").setPositiveButton("Visualizar", dialogClickListener)
                    .setNegativeButton("Compartilhar", dialogClickListener).show();
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }

    }

}