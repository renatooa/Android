package renato.com.br.appcontrolecoleta.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import br.com.space.api.core.sistema.Conversao;
import renato.com.br.appcontrolecoleta.R;
import renato.com.br.appcontrolecoleta.modelo.Pessoa;
import renato.com.br.appcontrolecoleta.modelo.QuantidadeControlada;
import renato.com.br.appcontrolecoleta.util.Fabrica;

/**
 * Created by Renato on 13/06/2016.
 */
public class AdapterEmprestimo extends ArrayAdapter<QuantidadeControlada> {

    public AdapterEmprestimo(Context context, List<QuantidadeControlada> quantidadeControladas) {
        super(context, R.layout.adp_list_emprestimo, quantidadeControladas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder vh;

        if (view == null) {

            view = Fabrica.getInstancia().getView(getContext(), R.layout.adp_list_emprestimo);

            vh = new ViewHolder(view);

            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }

        vh.popular(getItem(position));

        return view;
    }

    public class ViewHolder {

        private TextView diaDevolucao = null;
        private TextView textNome = null;
        private TextView textProduto = null;
        private TextView textDataDevolucao = null;
        private ImageView imageView = null;


        public ViewHolder(View view) {

            Calendar c = Calendar.getInstance();

            diaDevolucao = (TextView) view.findViewById(R.id.adp_emprestimo_dia_devolucao);
            textNome = (TextView) view.findViewById(R.id.adp_emprestimo_nome);
            textProduto = (TextView) view.findViewById(R.id.adp_emprestimo_produto);
            textDataDevolucao = (TextView) view.findViewById(R.id.adp_emprestimo_devolucao);
            imageView = (ImageView) view.findViewById(R.id.adp_emprestimo_img);
        }

        public void popular(QuantidadeControlada quantidadeControlada) {

            String dataDevolucao = Conversao.formatarDataDDMMAAAA(quantidadeControlada.getDateDevolucao());

            textNome.setText(quantidadeControlada.getPessoa().getNome());
            textProduto.setText(Conversao.formatarValor2(quantidadeControlada.getQuantidade()) + " - " + quantidadeControlada.getProduto().getNome());
            textDataDevolucao.setText(dataDevolucao);

            if (quantidadeControlada.isDevolvido()) {
                imageView.setImageResource(android.R.drawable.presence_online);
                diaDevolucao.setBackgroundResource(R.drawable.circular_textview);
                diaDevolucao.setText(getContext().getString(R.string.texto_ok));

            } else {
                int resourceDevolucao = quantidadeControlada.isDevolucaoHoje() ? R.drawable.circular_textview_orange : R.drawable.circular_textview_green;
                resourceDevolucao = quantidadeControlada.isDevolucaoPrazoOK() ? resourceDevolucao : R.drawable.circular_textview_red;

                diaDevolucao.setBackgroundResource(resourceDevolucao);
                diaDevolucao.setText(dataDevolucao.substring(0, 2));
            }
        }
    }
}
