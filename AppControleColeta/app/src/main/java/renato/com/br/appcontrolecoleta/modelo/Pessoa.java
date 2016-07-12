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
@SpaceTable(name = "pessoa")
public class Pessoa implements Serializable, IPersistent {

    @SpaceId
    @SpaceColumn(name = "codigo")
    private int codigo = 0;
    @SpaceColumn(name = "nome", length = 256)
    private String nome;
    @SpaceColumn(name = "endereco", length = 512)
    private String endereco;

    public Pessoa() {
    }

    public Pessoa(int codigo, String nome, String endereco) {
        this.codigo = codigo;
        this.nome = nome;
        this.endereco = endereco;
    }

    public Pessoa(int codigo, String nome) {
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public static List<Pessoa> recuperarTodas() {

        List<Pessoa> pessoas = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            pessoas.add(new Pessoa(i, "Pessoa " + i, "Rua " + i));
        }

        return pessoas;
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
