package renato.com.br.appcontrolecoleta.dao;

import android.content.Context;

import br.com.space.api.spa.android.model.dao.GenericDAO;
import br.com.space.api.spa.android.model.dao.db.DataBaseSQLite;
import br.com.space.api.spa.model.domain.IPersistent;
import renato.com.br.appcontrolecoleta.MainActivity;
import renato.com.br.appcontrolecoleta.modelo.Pessoa;
import renato.com.br.appcontrolecoleta.modelo.Produto;
import renato.com.br.appcontrolecoleta.modelo.QuantidadeControlada;
import renato.com.br.appcontrolecoleta.util.Arquivo;

/**
 * Created by Renato on 12/07/2016.
 */
public class BD extends DataBaseSQLite {

    private static BD instance = null;
    private static final String NOME_DB = "/emprestimo.db";
    private static final int versao = 1;
    private GenericDAO<IPersistent> dao = null;

    private BD(Context context) {
        super(context, Arquivo.getPastaRaiz().getAbsolutePath() + NOME_DB, versao, false);
        dao = new GenericDAO<IPersistent>(this);
        verificarCriarDemostracao();
    }

    public void verificarCriarDemostracao() {
        try {

            int pessoas = dao.count(Pessoa.class);

            beginTransaction();
            if (pessoas == 0) {
                dao.insert(new Pessoa("Artur Mol", "Rua Claudio Manoel, 1205", "Savassi", "BH", "MG"));
                dao.insert(new Pessoa("Renato Alves", "Rua Sl, 10", "Kennedy", "Santa Luzia", "MG"));
            }

            int produtos = dao.count(Produto.class);

            if (produtos == 0) {
                dao.insert(new Produto("Dominando o Android: Do Básico ao Avançado"));
                dao.insert(new Produto("Google Android"));
                dao.insert(new Produto("Desenvolvimento de Aplicativos Android para Leigos"));
            }
            endTransaction();
        } catch (Exception e) {
            rollBackTransaction();
        }
    }

    @Override
    public void mapTables() {
        mapTable(Produto.class);
        mapTable(Pessoa.class);
        mapTable(QuantidadeControlada.class);
    }

    public static GenericDAO<IPersistent> getDao() {
        return getInstance().dao;
    }

    public static GenericDAO<IPersistent> getNewDao(Context context) {
        return new BD(context).dao;
    }

    public static BD getInstance() {
        if (instance == null) {
            instance = new BD(MainActivity.context);
        }
        return instance;
    }
}
