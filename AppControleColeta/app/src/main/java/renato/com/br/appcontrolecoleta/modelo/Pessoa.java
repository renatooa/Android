package renato.com.br.appcontrolecoleta.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Renato on 13/06/2016.
 */
public class Pessoa implements Serializable {

    private int codigo = 0;
    private String nome;
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

    public static List<Pessoa> recuperarTodas(){

        List<Pessoa> pessoas = new ArrayList<>();

        for(int i=0; i < 100; i++){
            pessoas.add(new Pessoa(i, "Pessoa " + i,"Rua " + i));
        }

        return  pessoas;
    }

    @Override
    public String toString() {
        return nome;
    }
}
