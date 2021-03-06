package renato.com.br.appcontrolecoleta;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import renato.com.br.appcontrolecoleta.adapter.AdapterEmprestimo;
import renato.com.br.appcontrolecoleta.adapter.AdapterPessoa;
import renato.com.br.appcontrolecoleta.dao.BD;
import renato.com.br.appcontrolecoleta.modelo.Pessoa;
import renato.com.br.appcontrolecoleta.modelo.Produto;
import renato.com.br.appcontrolecoleta.modelo.QuantidadeControlada;
import renato.com.br.appcontrolecoleta.service.VerificadorDevolucaoService;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ListView.OnItemClickListener {

    public final static String PARAM_COLETA = "PARAM_COLETA";

    public final static String PARAM_LISTA_DEVOLUCAO = "PARAM_LISTA_DEVOLUCAO";

    public static Context context = null;

    public ListView listViewEmprestimos = null;

    public ArrayList<QuantidadeControlada> devolucoes = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.titulo_emprestimos));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();

                bundle.putString(PessoaList.PARAM_SELECIONAE_PESSOA, PessoaList.PARAM_SELECIONAE_PESSOA);

                Intent intent = new Intent(MainActivity.this, PessoaList.class);

                intent.putExtras(bundle);

                devolucoes.clear();
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        listViewEmprestimos = (ListView) findViewById(R.id.list_emprestimos);
        listViewEmprestimos.setOnItemClickListener(this);

        if (savedInstanceState != null && savedInstanceState.containsKey(PARAM_LISTA_DEVOLUCAO)) {
            devolucoes = (ArrayList<QuantidadeControlada>) savedInstanceState.getSerializable(PARAM_LISTA_DEVOLUCAO);
        } else {
            devolucoes = new ArrayList<>(QuantidadeControlada.recuperarTodas());
        }
        listViewEmprestimos.setAdapter(new AdapterEmprestimo(this, devolucoes));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(PARAM_LISTA_DEVOLUCAO, devolucoes);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (devolucoes.size() == 0){
            popularListView();
        }
    }

    private void popularListView() {
        devolucoes.clear();
        devolucoes.addAll(QuantidadeControlada.recuperarTodas());
        ((AdapterEmprestimo) listViewEmprestimos.getAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_principal) {

        } else if (id == R.id.nav_cliente) {
            startActivity(new Intent(this, PessoaList.class));
        } else if (id == R.id.nav_produto) {
            startActivity(new Intent(this, ProdutoList.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

        final QuantidadeControlada quantidadeControlada = (QuantidadeControlada) listViewEmprestimos.getAdapter().getItem(position);

        if (!quantidadeControlada.isDevolvido()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.title_deseja_fazer)
                    .setItems(R.array.opcoes_emprestimo, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            switch (which) {
                                case 0: // Devolver

                                    quantidadeControlada.setDevolvido(true);
                                    boolean sucessoDevolucao = quantidadeControlada.atualizar();
                                    if (sucessoDevolucao) {
                                        Toast.makeText(MainActivity.this, getString(R.string.mensagem_devolvido_sucesso), Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(MainActivity.this, getString(R.string.alerta_falha), Toast.LENGTH_LONG).show();
                                    }

                                    break;
                                case 1: // Excluir

                                    boolean sucesso = quantidadeControlada.excluir();
                                    if (sucesso) {
                                        Toast.makeText(MainActivity.this, getString(R.string.mensagem_excluido_sucesso), Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(MainActivity.this, getString(R.string.alerta_falha), Toast.LENGTH_LONG).show();
                                    }

                                    break;
                            }
                            popularListView();
                        }
                    });
            builder.show();
        } else {
            Toast.makeText(MainActivity.this, getString(R.string.mensagem_ja_devolvido), Toast.LENGTH_LONG).show();
        }
    }
}
