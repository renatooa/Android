package renato.com.br.appcontrolecoleta.modelo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Renato on 13/06/2016.
 */
public class QuantidadeControlada implements Serializable {

    private Produto produto = null;

    private Pessoa pessoa = null;

    private double quantidade = 0;

    private Date date = null;

    public QuantidadeControlada(Produto produto, Pessoa pessoa, double quantidade) {
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
}
