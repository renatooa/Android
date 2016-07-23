package renato.com.br.appcontrolecoleta.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import renato.com.br.appcontrolecoleta.service.VerificadorDevolucaoService;

public class BootCompletoReceiver extends BroadcastReceiver {

    public BootCompletoReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, VerificadorDevolucaoService.class));
    }
}
