package renato.com.br.appcontrolecoleta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.List;

import renato.com.br.appcontrolecoleta.modelo.Pessoa;
import renato.com.br.appcontrolecoleta.modelo.Produto;
import renato.com.br.appcontrolecoleta.util.Fabrica;

public class ColetaActivity extends AppCompatActivity {

    private AppCompatSpinner spinnerProduto = null;
    private EditText editQuantidade = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coleta);

        spinnerProduto = (AppCompatSpinner) findViewById(R.id.coleta_sp_produto);
        editQuantidade = (EditText) findViewById(R.id.coleta_et_quantidade);

        List<Produto> produtos = Produto.recuperarTodos();

        ArrayAdapter<Produto> arrayAdapterProduto = Fabrica.getInstancia().criarArrayAdapterCombo(this, produtos);

        spinnerProduto.setAdapter(arrayAdapterProduto);

        Pessoa pessoa = (Pessoa) getIntent().getExtras().getSerializable(MainActivity.PARAM_PESSOA);

        Toast.makeText(this, pessoa.getNome(), Toast.LENGTH_LONG).show();
    }
}
