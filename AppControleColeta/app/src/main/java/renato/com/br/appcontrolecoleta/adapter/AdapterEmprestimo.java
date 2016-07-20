package renato.com.br.appcontrolecoleta.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

        private View viewCor = null;
        private TextView textNome = null;
        private TextView textProduto = null;
        private TextView textDataDevolucao = null;


        public ViewHolder(View view) {

            Calendar c = Calendar.getInstance();

            viewCor = view.findViewById(R.id.adp_emprestimo_cor);
            textNome = (TextView) view.findViewById(R.id.adp_emprestimo_nome);
            textProduto = (TextView) view.findViewById(R.id.adp_emprestimo_produto);
            textDataDevolucao = (TextView) view.findViewById(R.id.adp_emprestimo_devolucao);
        }

        public void popular(QuantidadeControlada quantidadeControlada) {

            int corDevolucao = quantidadeControlada.isDevolucaoHoje()? Color.YELLOW:Color.GREEN;
            corDevolucao = quantidadeControlada.isDevolucaoPrazoOK()? corDevolucao:Color.RED;

            viewCor.setBackgroundColor(corDevolucao);

            textNome.setText(quantidadeControlada.getPessoa().getNome());
            textProduto.setText(quantidadeControlada.getProduto().getNome());
            textDataDevolucao.setText(Conversao.formatarDataDDMMAAAA(quantidadeControlada.getDateDevolucao()));
        }
    }
}
