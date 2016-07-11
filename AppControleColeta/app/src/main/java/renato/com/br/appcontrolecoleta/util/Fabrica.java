package renato.com.br.appcontrolecoleta.util;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import renato.com.br.appcontrolecoleta.R;

/***
 * Classe Padrao Factory Classe responsavel por criar instancias de objetos de
 * acordo com as necessidades do sistema.
 * 
 * @author Desenvolvimento
 * 
 */
public class Fabrica {

	private static Fabrica instancia = null;

	private OnClickListener onClickListenerFechar = new OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			dialog.dismiss();

		}
	};

	/**
	 * Construtor padr�o.
	 */
	private Fabrica() {
	}

	/**
	 * Retorna a instancia da f�brica.
	 * 
	 * @return Inst�ncia da f�brica.
	 */
	public static Fabrica getInstancia() {
		if (instancia == null) {
			instancia = new Fabrica();
		}
		return instancia;
	}

	/**
	 * Criar um ArrayAdapter de acordo com o tipo passado para ser usado nos
	 * combosBox(Spinner).
	 * 
	 * @param context
	 *            Contexto (Activity respons�vel).
	 * @param itens
	 *            Array de itens a ser usado para gerar o ArrayAdapter.
	 * @return Inst�ncia do ArrayAdapter.
	 */
	public <T> ArrayAdapter<T> criarArrayAdapterCombo(Context context, T[] itens) {
		ArrayAdapter<T> adapter = new ArrayAdapter<T>(context,
				android.R.layout.simple_spinner_item, itens);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		return adapter;
	}

	/**
	 * Criar um ArrayAdapter de acordo com o tipo passado para ser usado nos
	 * combosBox(Spinner).
	 * 
	 * @param context
	 *            Contexto (Activity respons�vel).
	 * @param itens
	 *            Lista contendo os itens a serem usado para gerar o
	 *            ArrayAdapter.
	 * @return Inst�ncia do ArrayAdapter.
	 */
	public <T> ArrayAdapter<T> criarArrayAdapterCombo(Context context,
			List<T> itens) {

		ArrayAdapter<T> adapter = new ArrayAdapter<T>(context,
				android.R.layout.simple_spinner_item, itens);

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		return adapter;
	}

	/**
	 * Cria um ArrayAdapter<T> para ser usado em campos autoCompelte
	 * 
	 * @param context
	 *            Contexto (Activity respons�vel).
	 * @param itens
	 *            List de itens que deseja constar no auto complete
	 * @return ArrayAdapter<T> contendo o visual e a lista de auto complementos
	 *         devidamente inserida
	 */
	public <T> ArrayAdapter<T> criarArrayAdapterAutoComplete(Context context,
			List<T> itens) {
		return new ArrayAdapter<T>(context,
				android.R.layout.simple_dropdown_item_1line, itens);
	}

	/**
	 * Cria um ArrayAdapter<T> para ser usado em campos autoCompelte
	 * 
	 * @param context
	 *            context Contexto (Activity respons�vel).
	 * @param itens
	 *            itens Array de itens que deseja constar no auto complete
	 * @return ArrayAdapter<T> contendo o visual e a lista de auto complementos
	 *         devidamente inserida
	 */
	public <T> ArrayAdapter<T> criarArrayAdapterAutoComplete(Context context,
			T[] itens) {
		return new ArrayAdapter<T>(context,
				android.R.layout.simple_dropdown_item_1line, itens);
	}

	/**
	 * Cria uma tab (Aba)
	 * 
	 * @param context
	 *            context Contexto (Activity respons�vel).
	 * @param tabHost
	 *            Container para um conjunto de abas
	 * @param tagID
	 *            ID unico de presenta��o desta aba
	 * @param nomeExibicao
	 *            Nome que aparecer� para exibi��o ao usuario
	 * @param idRecursoImagem
	 *            imagem que ira compor a identifica��o da aba
	 * @param tabContentFactory
	 *            a fabrica que contem o Layout da aba
	 * @return TabSpec completa
	 */
	public TabSpec criarTab(Context context, TabHost tabHost, String tagID,
			String nomeExibicao, int idRecursoImagem,
			TabContentFactory tabContentFactory) {
		TabSpec tab = tabHost.newTabSpec(tagID);
		tab.setIndicator(nomeExibicao,
				context.getResources().getDrawable(idRecursoImagem));
		tab.setContent(tabContentFactory);
		return tab;
	}

	/**
	 * Cria um alertas com o usario exindo dois botaoes (Sim, N�o)
	 * 
	 * @param context
	 *            context context Contexto (Activity respons�vel).
	 * @param titulo
	 *            Titulo que aparecer� como titulo do dialogo
	 * @param msg
	 *            Mesagem que sera exibi para o usuario com o questinamento
	 *            inicial;
	 * @param idImagem
	 *            ID da imagem a ser utilizada para compor o Titulo
	 * @param nomeBotaoPositivo
	 *            Nome do primeiro botao (Botao positivo)
	 * @param nomeBotaoNegativo
	 *            Nome do segundo botao (Botao negativo)
	 * @param listenerPositivo
	 *            Listener que serp� executado quando o botao positivo for
	 *            precionado
	 * @param listenerNegativo
	 *            Listener que ser� executado quando o botao negativo for
	 *            precionado
	 */
	public void exibirAlertaPositivoNegativo(Context context, String titulo,
			String msg, int idImagem, String nomeBotaoPositivo,
			String nomeBotaoNegativo,
			OnClickListener listenerPositivo,
			OnClickListener listenerNegativo) {

		Builder alerta = new Builder(context);

		alerta.setIcon(idImagem);
		alerta.setTitle(titulo);
		alerta.setMessage(msg);

		alerta.setPositiveButton(nomeBotaoPositivo, listenerPositivo);
		alerta.setNegativeButton(nomeBotaoNegativo, listenerNegativo);

		alerta.show();
	}

	public void exibirAlertaSimNao(Context context, String msg,
			OnClickListener listenerSim) {

		exibirAlertaPositivoNegativo(context,
				context.getString(R.string.titulo_atencao), msg,
				android.R.drawable.ic_dialog_alert,
				context.getString(R.string.texto_sim),
				context.getString(R.string.texto_nao), listenerSim,
				onClickListenerFechar);
	}

	public void exibirAlertaSimNao(Context context, String msg,
			OnClickListener listenerSim,
			OnClickListener listenerNao) {

		exibirAlertaPositivoNegativo(context,
				context.getString(R.string.titulo_atencao), msg,
				android.R.drawable.ic_dialog_alert,
				context.getString(R.string.texto_sim),
				context.getString(R.string.texto_nao), listenerSim, listenerNao);
	}

	public void exibirAlertaEscolhaSimNao(Context context, String msg,
			OnClickListener listenerSim,
			OnClickListener listenerNao) {

		exibirAlertaPositivoNegativo(context,
				context.getString(R.string.titulo_dialogo), msg,
				android.R.drawable.ic_dialog_alert,
				context.getString(R.string.texto_sim),
				context.getString(R.string.texto_nao), listenerSim, listenerNao);
	}

	public void exibirAlertaEscolhaSimNao(Context context, String msg,
			int idImagem, OnClickListener listenerSim) {

		exibirAlertaPositivoNegativo(context,
				context.getString(R.string.titulo_dialogo), msg, idImagem,
				context.getString(R.string.texto_sim),
				context.getString(R.string.texto_nao), listenerSim,
				onClickListenerFechar);
	}

	public void exibirAlertaEscolhaSimNao(Context context, String msg,
			int idImagem, OnClickListener listenerSim,
			OnClickListener listenerNao) {

		exibirAlertaPositivoNegativo(context,
				context.getString(R.string.titulo_dialogo), msg, idImagem,
				context.getString(R.string.texto_sim),
				context.getString(R.string.texto_nao), listenerSim, listenerNao);
	}

	public void exibirAlertaEscolhaSimNao(Context context, String msg,
			OnClickListener listenerSim) {

		exibirAlertaPositivoNegativo(context,
				context.getString(R.string.titulo_dialogo), msg,
				android.R.drawable.ic_dialog_alert,
				context.getString(R.string.texto_sim),
				context.getString(R.string.texto_nao), listenerSim,
				onClickListenerFechar);
	}

	/**
	 * Cria um alertas com o usario exindo dois botaoes (Sim, N�o)
	 * 
	 * @param context
	 *            context context Contexto (Activity respons�vel).
	 * @param titulo
	 *            Titulo que aparecer� como titulo do dialogo
	 * @param msg
	 *            Mesagem que sera exibi para o usuario com o questinamento
	 *            inicial;
	 * @param idImagem
	 *            ID da imagem a ser utilizada para compor o Titulo
	 * @param nomeBotaoPositivo
	 *            Nome do primeiro botao (Botao positivo)
	 * @param nomeBotaoNegativo
	 *            Nome do segundo botao (Botao negativo)
	 * @param listenerPositivo
	 *            Listener que serp� executado quando o botao positivo for
	 *            precionado
	 * @param listenerNegativo
	 *            Listener que ser� executado quando o botao negativo for
	 *            precionado
	 */
	public void exibirAlertaPositivoNegativo(Context context, String titulo,
			String msg, int idImagem, String nomeBotaoPositivo,
			String nomeBotaoNegativo,
			OnClickListener listenerPositivo) {

		exibirAlertaPositivoNegativo(context, titulo, msg, idImagem,
				nomeBotaoPositivo, nomeBotaoNegativo, listenerPositivo,
				onClickListenerFechar);
	}

	/**
	 * 
	 * @param context
	 * @param titulo
	 * @param msg
	 * @param idImagem
	 */
	public void exibirAlerta(Context context, String titulo, String msg,
			int idImagem) {

		Builder alerta = new Builder(context);

		alerta.setIcon(idImagem);
		alerta.setTitle(titulo);
		alerta.setMessage(msg);

		alerta.setPositiveButton("Ok", new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				arg0.dismiss();
			}
		});

		alerta.show();
	}

	/**
	 * @param context
	 * @param titulo
	 * @param msg
	 * @param idImagem
	 */
	public void exibirAlerta(Context context, String titulo, String msg,
			int idImagem, OnClickListener clickOk) {

		Builder alerta = new Builder(context);

		alerta.setIcon(idImagem);
		alerta.setTitle(titulo);
		alerta.setMessage(msg);

		alerta.setPositiveButton("Ok", clickOk);

		alerta.show();
	}

	/**
	 * 
	 * @param context
	 * @param titulo
	 * @param msg
	 * @param idImagem
	 */
	public void exibirAlerta(Context context, String titulo,
			BaseAdapter adapter, OnClickListener clickListener, int idImagem) {

		Builder alerta = new Builder(context);

		alerta.setIcon(idImagem);
		alerta.setTitle(titulo);
		alerta.setAdapter(adapter, clickListener);

		alerta.setPositiveButton("Ok", new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				arg0.dismiss();
			}
		});

		alerta.show();
	}

	/**
	 * 
	 * @param context
	 * @param titulo
	 * @param msg
	 * @param idImagem
	 * @param listener
	 * @param permiteCancelar
	 */
	public void exibirAlerta(Context context, String titulo, String msg,
			int idImagem, OnClickListener listener,
			boolean permiteCancelar) {

		Builder alerta = new Builder(context);

		alerta.setIcon(idImagem);
		alerta.setTitle(titulo);
		alerta.setMessage(msg);
		alerta.setCancelable(permiteCancelar);

		alerta.setPositiveButton("Ok", listener);

		// Se nao permite cancelar, trata o evento de teclas.
		if (!permiteCancelar) {
			alerta.setOnKeyListener(new DialogInterface.OnKeyListener() {
				@Override
				public boolean onKey(DialogInterface arg0, int arg1,
						KeyEvent arg2) {
					if (arg1 == KeyEvent.KEYCODE_BACK
							|| arg1 == KeyEvent.KEYCODE_SEARCH)
						return true;
					else
						return false;
				}
			});
		}

		alerta.show();
	}
	
	

	/**
	 * 
	 * @param context
	 * @param titulo
	 * @param msg
	 * @param idImagem
	 * @param listener
	 * @param permiteCancelar
	 */
	public Builder getAlerta(Context context, String titulo, String msg,
			int idImagem, OnClickListener listener,
			boolean permiteCancelar) {

		Builder alerta = new Builder(context);

		alerta.setIcon(idImagem);
		alerta.setTitle(titulo);
		alerta.setMessage(msg);
		alerta.setCancelable(permiteCancelar);

		alerta.setPositiveButton("Ok", listener);

		// Se nao permite cancelar, trata o evento de teclas.
		if (!permiteCancelar) {
			alerta.setOnKeyListener(new DialogInterface.OnKeyListener() {
				@Override
				public boolean onKey(DialogInterface arg0, int arg1,
						KeyEvent arg2) {
					if (arg1 == KeyEvent.KEYCODE_BACK
							|| arg1 == KeyEvent.KEYCODE_SEARCH)
						return true;
					else
						return false;
				}
			});
		}
		return alerta;
	}

	/**
	 * Exibe um dialogo com op�oes para o usuario
	 * 
	 * @param context
	 *            Contexto (Activity respons�vel).
	 * @param idImagem
	 *            ID da imagem a ser utilizada para compor o Titulo
	 * @param titulo
	 *            Titulo que aparecer� como titulo do dialogo
	 * @param itens
	 *            Os itens que ira aparecer na lista
	 * @param permiteCancelar
	 *            Indica se permite cancelar a sele��o sem informar algum valor.
	 * @param listener
	 *            O ouvinte que ira tratar os eventos do dialogo
	 */
	public void exibirAlertaLista(Context context, int idImagem, String titulo,
			String[] itens, boolean permiteCancelar,
			OnClickListener listener) {

		AlertDialog alert = criarAlertaLista(context, idImagem, titulo, itens,
				permiteCancelar, listener);

		alert.show();
	}

	public AlertDialog criarAlertaLista(Context context, int idImagem,
			String titulo, String[] itens, boolean permiteCancelar,
			OnClickListener listener) {

		Builder alerta = new Builder(context);

		alerta.setTitle(titulo);
		alerta.setIcon(idImagem);

		alerta.setItems(itens, listener);
		alerta.setCancelable(permiteCancelar);

		// Se nao permite cancelar, trata o evento de teclas.
		if (!permiteCancelar) {
			alerta.setOnKeyListener(new DialogInterface.OnKeyListener() {
				@Override
				public boolean onKey(DialogInterface arg0, int arg1,
						KeyEvent arg2) {
					if (arg1 == KeyEvent.KEYCODE_BACK
							|| arg1 == KeyEvent.KEYCODE_SEARCH)
						return true;
					else
						return false;
				}

			});
		}

		return alerta.create();
	}

	/**
	 * Cria uma string representado a hora atual no formato HH:MM:SS
	 * 
	 * @return Representa��o em String da hora atual
	 */
	public String getStringHoraHHMMSS() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":"
				+ c.get(Calendar.SECOND);
	}

	public static void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			// pre-condition
			return;
		}

		int totalHeight = 0;
		int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(),
				MeasureSpec.AT_MOST);
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
		listView.requestLayout();
	}

	public int[] getRGB(String corHexadecimal) {

		if (corHexadecimal != null
				&& (corHexadecimal.trim().length() == 6 || corHexadecimal
						.trim().length() == 7)) {

			corHexadecimal = corHexadecimal.replace("#", "").trim();

			if (corHexadecimal.length() == 6) {

				int r = Integer.parseInt(corHexadecimal.substring(0, 2), 16);
				int g = Integer.parseInt(corHexadecimal.substring(2, 4), 16);
				int b = Integer.parseInt(corHexadecimal.substring(4, 6), 16);

				return new int[] { r, g, b };
			}
		}
		return null;
	}

	public int getCor(String corHexadecimal, int corPadrao) {

		int[] rgb = getRGB(corHexadecimal);

		if (rgb != null && rgb.length == 3) {
			return Color.rgb(rgb[0], rgb[1], rgb[2]);
		}

		return corPadrao;
	}

	public View getView(Context context, int layoutId) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		return inflater.inflate(layoutId, null);
	}

	public void setColorTextFilhos(LinearLayout layout, int color) {

		int countChild = layout.getChildCount();

		for (int i = 0; i < countChild; i++) {

			View child = layout.getChildAt(i);

			if (child instanceof TextView) {
				((TextView) child).setTextColor(color);
			} else if (child instanceof LinearLayout) {
				setColorTextFilhos((LinearLayout) child, color);
			}
		}
	}
}
