package renato.com.br.appcontrolecoleta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import renato.com.br.appcontrolecoleta.modelo.Pessoa;
import renato.com.br.appcontrolecoleta.modelo.QuantidadeControlada;

public class PessoaCadastro extends AppCompatActivity {


    private EditText editNome = null;
    private EditText editLogradouro = null;
    private EditText editBairro = null;
    private EditText editCidade = null;
    private EditText editEstado = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa_cadastro);

        editNome = (EditText) findViewById(R.id.cad_pessoa_nome);
        editLogradouro = (EditText) findViewById(R.id.cad_pessoa_Logradouro);
        editBairro = (EditText) findViewById(R.id.cad_pessoa_bairro);
        editCidade = (EditText) findViewById(R.id.cad_pessoa_cidade);
        editEstado = (EditText) findViewById(R.id.cad_pessoa_estado);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pessoa_cadastro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_salvar_pessoa) {

            Pessoa pessoa = new Pessoa(editNome.getText().toString(), getEndereco());
            boolean salvar = pessoa.salvar();

            if (salvar) {
                Toast.makeText(this, getString(R.string.mensagem_gravado_sucesso), Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(this, getString(R.string.alerta_falha_gravacao), Toast.LENGTH_LONG).show();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private String getEndereco() {
        return editLogradouro.getText().toString() + ", " + editBairro.getText().toString()
                + " - " + editCidade.getText().toString() + ", " + editEstado.getText().toString();
    }
}
