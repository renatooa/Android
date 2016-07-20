package renato.com.br.appcontrolecoleta;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ListIterator;

import renato.com.br.appcontrolecoleta.modelo.Pessoa;
import renato.com.br.appcontrolecoleta.modelo.Produto;

public class ProdutoList extends AppCompatActivity implements  ListView.OnItemClickListener {

    public final static String PARAM_PRODUTO = "PARAM_PRODUTO";
    private ListView listView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_list);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProdutoList.this, ProdutoCadastro.class);
                startActivity(intent);
            }
        });

        listView = (ListView) findViewById(R.id.list_produto);

        listView.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayAdapter<Produto> adp = new ArrayAdapter<Produto>(this, android.R.layout.simple_list_item_1, Produto.recuperarTodos());
        listView.setAdapter(adp);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(this, ProdutoCadastro.class);
        intent.putExtras(getBundlParametroPessoa(position));

        startActivity(intent);
    }

    private Bundle getBundlParametroPessoa( int position) {

        Produto produto = (Produto) listView.getAdapter().getItem(position);

        Bundle bundle = new Bundle();

        bundle.putSerializable(PARAM_PRODUTO, produto);
        return bundle;
    }
}
