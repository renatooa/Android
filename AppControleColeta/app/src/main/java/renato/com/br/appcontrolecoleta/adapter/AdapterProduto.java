package renato.com.br.appcontrolecoleta.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import renato.com.br.appcontrolecoleta.R;
import renato.com.br.appcontrolecoleta.modelo.Pessoa;
import renato.com.br.appcontrolecoleta.modelo.Produto;
import renato.com.br.appcontrolecoleta.util.Fabrica;

/**
 * Created by Renato on 13/06/2016.
 */
public class AdapterProduto extends ArrayAdapter<Produto> {

    public AdapterProduto(Context context, List<Produto> produtos) {
        super(context, R.layout.adp_list_produto, produtos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder vh;

        if (view == null) {

            view = Fabrica.getInstancia().getView(getContext(), R.layout.adp_list_produto);

            vh = new ViewHolder(view);

            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }

        final Produto produto = getItem(position);

        vh.textNome.setText(produto.getNome());
        vh.textNomeLetra.setText(produto.getNome().substring(0, 1));

        return view;
    }

    public class ViewHolder {
        protected TextView textNome = null;
        protected TextView textNomeLetra = null;

        public ViewHolder(View view) {
            textNome = (TextView) view.findViewById(R.id.adp_produto_nome);
            textNomeLetra = (TextView) view.findViewById(R.id.adp_produto_letra);
        }
    }
}
