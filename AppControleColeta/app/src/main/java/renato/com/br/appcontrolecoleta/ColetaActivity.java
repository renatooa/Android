package renato.com.br.appcontrolecoleta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import renato.com.br.appcontrolecoleta.modelo.Pessoa;
import renato.com.br.appcontrolecoleta.modelo.Produto;
import renato.com.br.appcontrolecoleta.modelo.QuantidadeControlada;
import renato.com.br.appcontrolecoleta.util.Fabrica;

public class ColetaActivity extends AppCompatActivity {

    private Pessoa pessoa = null;
    private AppCompatSpinner spinnerProduto = null;
    private EditText editQuantidade = null;
    private DatePicker datePicker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coleta);

        spinnerProduto = (AppCompatSpinner) findViewById(R.id.coleta_sp_produto);
        editQuantidade = (EditText) findViewById(R.id.coleta_et_quantidade);
        datePicker = (DatePicker) findViewById(R.id.coleta_dt_devolucao);

        ArrayAdapter<Produto> arrayAdapterProduto = Fabrica.getInstancia().criarArrayAdapterCombo(this, Produto.recuperarTodos());

        spinnerProduto.setAdapter(arrayAdapterProduto);

        pessoa = (Pessoa) getIntent().getExtras().getSerializable(MainActivity.PARAM_PESSOA);

        setTitle(getString(R.string.titulo_emprestimo) + pessoa.getNome());

        Toast.makeText(this, pessoa.getNome(), Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.coleta, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_salvar) {

            QuantidadeControlada quantidadeControlada = new QuantidadeControlada(getProdutoSelecionado(), pessoa, Double.parseDouble(editQuantidade.getText().toString()), getDataDevolucao());

            if (quantidadeControlada.salvar()) {
                Toast.makeText(this, getString(R.string.mensagem_gravado_sucesso), Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(this, getString(R.string.alerta_falha_gravacao), Toast.LENGTH_LONG).show();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Produto getProdutoSelecionado() {
        return (Produto) spinnerProduto.getSelectedItem();
    }

    private Date getDataDevolucao() {

        Calendar calendar = Calendar.getInstance();
        calendar.clear();

        calendar.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
        calendar.set(Calendar.MONTH, datePicker.getMonth());
        calendar.set(Calendar.YEAR, datePicker.getYear());

        return calendar.getTime();

    }
}
