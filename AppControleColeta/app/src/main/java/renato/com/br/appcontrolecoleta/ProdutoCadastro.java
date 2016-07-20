package renato.com.br.appcontrolecoleta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import renato.com.br.appcontrolecoleta.modelo.Pessoa;
import renato.com.br.appcontrolecoleta.modelo.Produto;

public class ProdutoCadastro extends AppCompatActivity {

    private EditText editTextNome;
    private Produto produtoEdicao = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_cadastro);

        editTextNome = (EditText) findViewById(R.id.cad_produto_nome);

        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(ProdutoList.PARAM_PRODUTO)) {
            produtoEdicao = (Produto) getIntent().getExtras().getSerializable(ProdutoList.PARAM_PRODUTO);
            editTextNome.setText(produtoEdicao.getNome());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.produto_cadastro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_salvar_produto) {

            boolean isEdicao = produtoEdicao != null;

            Produto produto = isEdicao ? produtoEdicao : new Produto();

            produto.setNome(editTextNome.getText().toString());

            boolean persistido = isEdicao ? produto.atualizar() : produto.salvar();

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
