package renato.com.br.appcontrolecoleta.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import renato.com.br.appcontrolecoleta.PessoaCadastro;
import renato.com.br.appcontrolecoleta.PessoaList;
import renato.com.br.appcontrolecoleta.R;
import renato.com.br.appcontrolecoleta.modelo.Pessoa;
import renato.com.br.appcontrolecoleta.util.Fabrica;

import static renato.com.br.appcontrolecoleta.R.drawable.*;

/**
 * Created by Renato on 13/06/2016.
 */
public class AdapterPessoa extends ArrayAdapter<Pessoa> {

    private boolean exibeEdicao = false;

    public AdapterPessoa(Context context, List<Pessoa> pessoas, boolean exibeEdicao) {
        super(context, R.layout.adp_list_pessoa, pessoas);
        this.exibeEdicao = exibeEdicao;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder vh;

        if (view == null) {

            view = Fabrica.getInstancia().getView(getContext(), R.layout.adp_list_pessoa);

            vh = new ViewHolder(view);

            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }

        final Pessoa pessoa = getItem(position);

        vh.textNome.setText(pessoa.getNome());
        vh.textNomeLetra.setText(pessoa.getNome().substring(0, 1));
        vh.textEndereco.setText(pessoa.getEndereco());


        if (!exibeEdicao) {
            vh.imageEdit.setVisibility(View.GONE);
        }

        return view;
    }

    public class ViewHolder {
        protected TextView textNome = null;
        protected TextView textNomeLetra = null;
        protected TextView textEndereco = null;
        protected ImageView imageEdit = null;

        public ViewHolder(View view) {

            textNome = (TextView) view.findViewById(R.id.adp_pessoa_nome);
            textNomeLetra = (TextView) view.findViewById(R.id.adp_pessoa_letra);
            textEndereco = (TextView) view.findViewById(R.id.adp_pessoa_end);
            imageEdit = (ImageView) view.findViewById(R.id.adp_pessoa_img_edit);
        }
    }
}
