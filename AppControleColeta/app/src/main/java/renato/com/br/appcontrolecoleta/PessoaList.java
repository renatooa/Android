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

public class PessoaList extends AppCompatActivity implements ListView.OnItemClickListener, ListView.OnItemLongClickListener {

    public final static String PARAM_PESSOA = "PESSOA";
    private ListView listView = null;

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

        listView = (ListView) findViewById(R.id.main_lista_pessoa);

        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);

        List<Pessoa> pessoas = Pessoa.recuperarTodas();

        listView.setAdapter(new AdapterPessoa(this, pessoas));
    }

    @Override
    protected void onResume() {
        super.onResume();

        listView.setAdapter(new AdapterPessoa(this, Pessoa.recuperarTodas()));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(this, ColetaActivity.class);
        intent.putExtras(getBundlParametroPessoa(position));

        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, PessoaCadastro.class);
        intent.putExtras(getBundlParametroPessoa(position));

        startActivity(intent);

        return false;
    }

    private Bundle getBundlParametroPessoa( int position) {

        Pessoa pessoa = (Pessoa) listView.getAdapter().getItem(position);

        Bundle bundle = new Bundle();

        bundle.putSerializable(PARAM_PESSOA, pessoa);
        return bundle;
    }
}
