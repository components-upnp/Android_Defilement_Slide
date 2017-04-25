package com.example.comkostiuk.defilement_slide_android;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.comkostiuk.defilement_slide_android.upnp.ServiceUpnp;


import org.fourthline.cling.android.AndroidUpnpServiceImpl;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Timer;
import java.util.TimerTask;

import xdroid.toaster.Toaster;

/**
 * Created by comkostiuk on 19/04/2017.
 */

public class Defilement extends Service {


    private ServiceUpnp serviceUpnp;
    private ServiceConnection serviceConnection;
    private Context context;
    private Intent name;
    Intent i;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread().start();

        Toast.makeText(this,"Début du cours!!!",Toast.LENGTH_LONG).show();


        context = this;
        serviceUpnp = new ServiceUpnp();
        serviceConnection = serviceUpnp.getService();


        name =  new Intent(context, AndroidUpnpServiceImpl.class);
        getApplicationContext().bindService(name,
                serviceConnection,
                Context.BIND_AUTO_CREATE);


        Timer t = new Timer();

        t.schedule(new TimerTask() {
            @Override
            public void run() {
                serviceUpnp.getDeviceDefilementLocalService().getManager()
                        .getImplementation()
                        .getPropertyChangeSupport()
                        .addPropertyChangeListener(
                                new PropertyChangeListener() {
                                    @Override
                                    public void propertyChange(PropertyChangeEvent evt) {
                                        if (evt.getPropertyName().equals("status")) {
                                            String res = evt.getNewValue().toString();
                                            System.err.println("EVENEMENT!!!!");
                                            if (res == "CENTRE") {
                                                Toaster.toast("Fin du cours!!!");
                                                onDestroy();
                                            }
                                            else if (res != "AUCUN") {
                                                Toaster.toast(evt.getNewValue().toString());
                                            }
                                        }

                                    }
                                }
                        );
            }
        }, 5000);


        //On met en place un timer pour arreter le service au bout de 2 heures
        Timer end = new Timer();

        end.schedule(new TimerTask() {
            @Override
            public void run() {
                Toaster.toast("Fin du cours!!!");
                onDestroy();
            }
        },2 * 60 * 60 * 1000);

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        i = intent;
        return null;
    }

    @Override
    public void onDestroy() {
        try {
            serviceUpnp.stop();
            stopService(name);
            stopSelf();
            super.onDestroy();
            finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }


}
