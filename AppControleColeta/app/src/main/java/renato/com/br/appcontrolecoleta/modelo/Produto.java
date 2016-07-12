package renato.com.br.appcontrolecoleta.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.space.api.spa.annotations.SpaceColumn;
import br.com.space.api.spa.annotations.SpaceId;
import br.com.space.api.spa.annotations.SpaceTable;
import br.com.space.api.spa.model.dao.db.Table;
import br.com.space.api.spa.model.domain.IPersistent;

/**
 * Created by Renato on 13/06/2016.
 */
@SpaceTable(name = "produto")
public class Produto implements Serializable , IPersistent {


    @SpaceId
    @SpaceColumn(name = "codigo")
    private int codigo = 0;

    @SpaceColumn(name = "nome")
    private String nome;

    public Produto(int codigo, String nome) {
        this.codigo = codigo;
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

    public static List<Produto> recuperarTodos() {

        List<Produto> produtos = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            produtos.add(new Produto(i, "Produto " + i));
        }

        return produtos;
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
}
