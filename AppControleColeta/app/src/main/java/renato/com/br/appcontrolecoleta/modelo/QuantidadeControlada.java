package renato.com.br.appcontrolecoleta.modelo;

import java.io.Serializable;
import java.util.Date;

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
@SpaceTable(name = "quantControl")
public class QuantidadeControlada implements Serializable, IPersistent {

    @SpaceId
    @SpaceColumn(name = "codigo")
    private int codigo = 0;

    @SpaceTransient
    private Produto produto = null;

    @SpaceTransient
    private Pessoa pessoa = null;

    @SpaceColumn(name = "produtoCodigo")
    private int produtoCodigo = 0;

    @SpaceColumn(name = "pessoaCodigo")
    private int pessoaCodigo = 0;

    @SpaceColumn(name = "quantidade")
    private double quantidade = 0;

    @SpaceTransient
    private Date date = null;

    @SpaceColumn(name = "dateMillis")
    private long dateMillis = 0;

    @SpaceTransient
    private Date dateDevolucao = null;

    @SpaceColumn(name = "dateDevolucaoMillis")
    private long dateDevolucaoMillis = 0;

    public QuantidadeControlada() {
        codigo = ID.getId();
    }

    public QuantidadeControlada(Produto produto, Pessoa pessoa, double quantidade, Date dateDevolucao) {
        this();
        setProduto(produto);
        setPessoa(pessoa);
        setDate(new Date());
        setQuantidade(quantidade);
        setDateDevolucao(dateDevolucao);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        this.produtoCodigo = produto.getCodigo();
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        this.pessoaCodigo = pessoa.getCodigo();
    }

    public int getProdutoCodigo() {
        return produtoCodigo;
    }

    public void setProdutoCodigo(int produtoCodigo) {
        this.produtoCodigo = produtoCodigo;
    }

    public int getPessoaCodigo() {
        return pessoaCodigo;
    }

    public void setPessoaCodigo(int pessoaCodigo) {
        this.pessoaCodigo = pessoaCodigo;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        this.dateMillis = date.getTime();
    }

    public long getDateMillis() {
        return dateMillis;
    }

    public void setDateMillis(long dateMillis) {
        this.dateMillis = dateMillis;
    }

    public Date getDateDevolucao() {
        return dateDevolucao;
    }

    public void setDateDevolucao(Date dateDevolucao) {
        this.dateDevolucao = dateDevolucao;
        this.dateDevolucaoMillis = dateDevolucao.getTime();
    }

    public long getDateDevolucaoMillis() {
        return dateDevolucaoMillis;
    }

    public void setDateDevolucaoMillis(long dateDevolucaoMillis) {
        this.dateDevolucaoMillis = dateDevolucaoMillis;
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

    public boolean salvar() {
        return BD.getDao().insert(this) > 0;
    }
}
