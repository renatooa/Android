package renato.com.br.appcontrolecoleta.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import br.com.space.api.core.util.ListUtil;
import br.com.space.api.spa.android.model.dao.GenericDAO;
import br.com.space.api.spa.model.domain.IPersistent;
import renato.com.br.appcontrolecoleta.MainActivity;
import renato.com.br.appcontrolecoleta.R;
import renato.com.br.appcontrolecoleta.dao.BD;
import renato.com.br.appcontrolecoleta.modelo.QuantidadeControlada;

public class VerificadorDevolucaoService extends Service {

    private ScheduledThreadPoolExecutor pool = null;

    private GenericDAO<IPersistent> dao = null;

    public VerificadorDevolucaoService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        pool = new ScheduledThreadPoolExecutor(1);
        dao = BD.getNewDao(this);

        long delayInicial = 1;
        long periodo = 30;
        TimeUnit unit = TimeUnit.MINUTES;

        pool.scheduleAtFixedRate(new VerificadorDevolucao(), delayInicial, periodo, unit);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (pool != null) {
            try {
                pool.shutdownNow();
            } catch (Exception e) {
                Log.e(getClass().getName(), e.getMessage(), e);
            }
        }
        super.onDestroy();
    }

    private void dispararNotificacao(List<QuantidadeControlada> quantidadeControladas) {

        Context context = getApplicationContext();
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        String aviso = getString(R.string.mensagem_notificacao_devolucao, Integer.toString(quantidadeControladas.size()));
        String titulo = getString(R.string.titulo_notificacao_devolucao);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(titulo)
                .setContentText(aviso)
                .setContentIntent(pendingIntent);

        NotificationManagerCompat nm = NotificationManagerCompat.from(context);

        nm.notify(1, builder.build());
    }

    public class VerificadorDevolucao implements Runnable {

        @Override
        public void run() {

            List<QuantidadeControlada> quantidadeControladas = QuantidadeControlada.recupearHojeNaoDevolvidas(dao);

            if (ListUtil.isValida(quantidadeControladas)) {
                dispararNotificacao(quantidadeControladas);
            }
        }
    }
}
