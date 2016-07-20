package renato.com.br.appcontrolecoleta.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.space.api.spa.annotations.SpaceColumn;
import br.com.space.api.spa.annotations.SpaceId;
import br.com.space.api.spa.annotations.SpaceTable;
import br.com.space.api.spa.model.dao.db.Table;
import br.com.space.api.spa.model.domain.IPersistent;
import renato.com.br.appcontrolecoleta.dao.BD;
import renato.com.br.appcontrolecoleta.util.ID;

/**
 * Created by Renato on 13/06/2016.
 */
@SpaceTable(name = "produto")
public class Produto implements Serializable, IPersistent {


    @SpaceId
    @SpaceColumn(name = "codigo")
    private int codigo = 0;

    @SpaceColumn(name = "nome")
    private String nome;

    public Produto() {
        this.codigo = ID.getId();
    }

    public Produto(String nome) {
        this();
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    @Override
    public String toString() {
        return nome;
    }

    @Override
    public Table getTable() {
        return null;
    }

    @Override
    public void setTable(Table table) {
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static List<Produto> recuperarTodos() {
        return BD.getDao().list(Produto.class);
    }

    public static Produto recupear(int produtoCodigo) {
        return BD.getDao().uniqueResult(Produto.class, "codigo = ?", new String[]{Integer.toString(produtoCodigo)});
    }

    public boolean salvar() {
        return BD.getDao().insert(this) > 0;

    }

    public boolean atualizar() {
        return BD.getDao().update(this) > 0;
    }
}
