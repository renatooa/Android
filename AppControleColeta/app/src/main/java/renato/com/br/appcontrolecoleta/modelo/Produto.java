package renato.com.br.appcontrolecoleta.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Renato on 13/06/2016.
 */
public class Produto implements Serializable {

    private int codigo = 0;
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
}
