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
    private static final int versao = 1;
    private GenericDAO<IPersistent> dao = null;

    private BD() {
        super(MainActivity.context, Arquivo.PASTA_RAIZ + "/coletaDevolucao.db", versao, false);
        dao = new GenericDAO<IPersistent>(this);
    }

    @Override
    public void mapTables() {
        mapTable(Produto.class);
        mapTable(Pessoa.class);
       // mapTable(QuantidadeControlada.class);
    }

    public static GenericDAO<IPersistent> getDao() {
        return getInstance().dao;
    }

    public static BD getInstance() {
        if (instance == null){
            instance = new BD();
        }
        return instance;
    }
}
