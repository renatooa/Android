package renato.com.br.appcontrolecoleta;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import renato.com.br.appcontrolecoleta.adapter.AdapterPessoa;
import renato.com.br.appcontrolecoleta.modelo.Pessoa;
import renato.com.br.appcontrolecoleta.modelo.Produto;

public class PessoaList extends AppCompatActivity implements ListView.OnItemClickListener {

    public final static String PARAM_PESSOA = "PESSOA";
    public final static String PARAM_SELECIONAE_PESSOA = "PARAM_SELECIONAE_PESSOA";

    private ListView listView = null;
    private boolean selecionarPessoaEmprestimo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa_list);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PessoaList.this, PessoaCadastro.class);
                startActivity(intent);
            }
        });

        selecionarPessoaEmprestimo = (getIntent().getExtras() != null && getIntent().getExtras().containsKey(PARAM_SELECIONAE_PESSOA));

        listView = (ListView) findViewById(R.id.main_lista_pessoa);
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        setTitle(selecionarPessoaEmprestimo?getString(R.string.titulo_selecione_pessoa):getString(R.string.titulo_pessoas));
        listView.setAdapter(new AdapterPessoa(this, Pessoa.recuperarTodas(), !selecionarPessoaEmprestimo));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(this, ColetaActivity.class);
        intent.putExtras(getBundlParametroPessoa(position));

        startActivity(intent);
    }

    private Bundle getBundlParametroPessoa(int position) {

        Pessoa pessoa = (Pessoa) listView.getAdapter().getItem(position);

        Bundle bundle = new Bundle();

        bundle.putSerializable(PARAM_PESSOA, pessoa);
        return bundle;
    }
}
