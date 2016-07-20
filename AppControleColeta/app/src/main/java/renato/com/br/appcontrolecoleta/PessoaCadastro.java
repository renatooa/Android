package renato.com.br.appcontrolecoleta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import br.com.space.api.core.util.StringUtil;
import renato.com.br.appcontrolecoleta.modelo.Pessoa;
import renato.com.br.appcontrolecoleta.modelo.QuantidadeControlada;

public class PessoaCadastro extends AppCompatActivity {

    private EditText editNome = null;
    private EditText editLogradouro = null;
    private EditText editBairro = null;
    private EditText editCidade = null;
    private EditText editEstado = null;

    Pessoa pessoaEdicao = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa_cadastro);

        editNome = (EditText) findViewById(R.id.cad_pessoa_nome);
        editLogradouro = (EditText) findViewById(R.id.cad_pessoa_Logradouro);
        editBairro = (EditText) findViewById(R.id.cad_pessoa_bairro);
        editCidade = (EditText) findViewById(R.id.cad_pessoa_cidade);
        editEstado = (EditText) findViewById(R.id.cad_pessoa_estado);

        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(PessoaList.PARAM_PESSOA)) {
            pessoaEdicao = (Pessoa) getIntent().getExtras().getSerializable(PessoaList.PARAM_PESSOA);
            popularViewsPessoaEdicao();
        }
    }

    private void popularViewsPessoaEdicao() {
        if (pessoaEdicao != null) {
            editNome.setText(pessoaEdicao.getNome());

            editLogradouro.setText(pessoaEdicao.getLogradouro());
            editBairro.setText(pessoaEdicao.getBairro());
            editCidade.setText(pessoaEdicao.getCidade());
            editEstado.setText(pessoaEdicao.getEstado());
        }
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

            boolean edicao = pessoaEdicao != null;

            Pessoa pessoa = edicao ? pessoaEdicao : new Pessoa();

            pessoa.set(editNome.getText().toString(), editLogradouro.getText().toString(), editBairro.getText().toString(), editCidade.getText().toString(), editEstado.getText().toString());

            boolean persistido = edicao ? pessoa.atualizar() : pessoa.salvar();

            if (persistido) {
                Toast.makeText(this, getString(R.string.mensagem_gravado_sucesso), Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(this, getString(R.string.alerta_falha_gravacao), Toast.LENGTH_LONG).show();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
