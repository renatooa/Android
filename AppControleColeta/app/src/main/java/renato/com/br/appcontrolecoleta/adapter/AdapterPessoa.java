package renato.com.br.appcontrolecoleta.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import renato.com.br.appcontrolecoleta.R;
import renato.com.br.appcontrolecoleta.modelo.Pessoa;
import renato.com.br.appcontrolecoleta.util.Fabrica;

/**
 * Created by Renato on 13/06/2016.
 */
public class AdapterPessoa extends ArrayAdapter<Pessoa> {

    public AdapterPessoa(Context context, List<Pessoa> pessoas) {
        super(context, R.layout.adp_list_pessoa, pessoas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder vh;

        if (view == null) {

            view = Fabrica.getInstancia().getView(getContext(),  R.layout.adp_list_pessoa);

            vh = new ViewHolder(view);

            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }

        Pessoa pessoa = getItem(position);

        vh.textNome.setText(pessoa.getNome());
        vh.textEndereco.setText(pessoa.getEndereco());

        return view;
    }

    public class  ViewHolder{
        TextView textNome = null;
        TextView textEndereco = null;

        public ViewHolder(View view) {

            textNome =(TextView) view.findViewById(R.id.adp_pessoa_nome);
            textEndereco = (TextView)view.findViewById(R.id.adp_pessoa_end);
        }
    }
}
