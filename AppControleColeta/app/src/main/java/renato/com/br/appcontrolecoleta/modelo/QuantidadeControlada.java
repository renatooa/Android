package renato.com.br.appcontrolecoleta.modelo;

import java.io.Serializable;
import java.util.Date;

import br.com.space.api.spa.model.dao.db.Table;
import br.com.space.api.spa.model.domain.IPersistent;

/**
 * Created by Renato on 13/06/2016.
 */
public class QuantidadeControlada implements Serializable , IPersistent {

    private Produto produto = null;

    private Pessoa pessoa = null;

    private double quantidade = 0;

    private Date date = null;

    private Date dateDevolucao = null;

    public QuantidadeControlada(){

    }

    public QuantidadeControlada(Produto produto, Pessoa pessoa, double quantidade, Date dateDevolucao) {
        this.produto = produto;
        this.pessoa = pessoa;
        this.quantidade = quantidade;
        this.date = new Date();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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
    }

    public Date getDateDevolucao() {
        return dateDevolucao;
    }

    public void setDateDevolucao(Date dateDevolucao) {
        this.dateDevolucao = dateDevolucao;
    }

    public static boolean salvar(QuantidadeControlada quantidadeControlada){
        return true;
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
