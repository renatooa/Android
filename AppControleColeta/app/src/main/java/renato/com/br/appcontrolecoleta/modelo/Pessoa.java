package renato.com.br.appcontrolecoleta.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.space.api.spa.annotations.SpaceColumn;
import br.com.space.api.spa.annotations.SpaceId;
import br.com.space.api.spa.annotations.SpaceTable;
import br.com.space.api.spa.annotations.SpaceTransient;
import br.com.space.api.spa.model.dao.db.Table;
import br.com.space.api.spa.model.domain.IPersistent;
import renato.com.br.appcontrolecoleta.dao.BD;
import renato.com.br.appcontrolecoleta.util.ID;

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

    @SpaceColumn(name = "logradouro", length = 512)
    private String logradouro;

    @SpaceColumn(name = "bairro", length = 512)
    private String bairro;

    @SpaceColumn(name = "cidade", length = 512)
    private String cidade;

    @SpaceColumn(name = "estado", length = 512)
    private String estado;

    public Pessoa() {
        codigo = ID.getId();
    }

    public Pessoa(String nome) {
        this();
        this.nome = nome;
    }

    public Pessoa(String nome, String logradouro, String bairro, String cidade, String estado) {
        this(nome);
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public void set(String nome, String logradouro, String bairro, String cidade, String estado) {
        this.nome = nome;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
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
        return logradouro + ", " + bairro
                + " - " + cidade + ", " + estado;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public static List<Pessoa> recuperarTodas()
    {
        return BD.getDao().list(Pessoa.class, null, null, "nome",null);
    }

    public static Pessoa recupear(int pessoaCodigo) {
        return BD.getDao().uniqueResult(Pessoa.class, "codigo = ?", new String[]{Integer.toString(pessoaCodigo)});
    }

    public boolean salvar() {
        return BD.getDao().insert(this) > 0;
    }

    public boolean atualizar() {
        return BD.getDao().update(this) > 0;
    }
}
