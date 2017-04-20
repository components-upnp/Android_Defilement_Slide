package com.example.comkostiuk.defilement_slide_android;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.comkostiuk.defilement_slide_android.upnp.DefilementController;
import com.example.comkostiuk.defilement_slide_android.upnp.ServiceUpnp;

import org.fourthline.cling.android.AndroidUpnpService;
import org.fourthline.cling.android.AndroidUpnpServiceImpl;
import org.fourthline.cling.model.meta.LocalService;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Timer;
import java.util.TimerTask;

import xdroid.toaster.Toaster;

public class App extends AppCompatActivity {

    private ServiceUpnp serviceUpnp;
    private ServiceConnection serviceConnection;
    private Context context;
    private LocalService<DefilementController> def;

    private AndroidUpnpService upnpService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        /*context = this;
        serviceUpnp = new ServiceUpnp();
        serviceConnection = serviceUpnp.getService();


        getApplicationContext().bindService(new Intent(context, AndroidUpnpServiceImpl.class),
                serviceConnection,
                Context.BIND_AUTO_CREATE);*/



        startService(new Intent(this, Defilement.class));

        /*Timer t = new Timer();

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
                                        if (evt.getPropertyName().equals("status"))
                                            System.err.println("EVENEMENT!!!!");
                                        Toaster.toast("EVENEMENT!!!");
                                    }
                                }
                        );
            }
        }, 5000);*/

        finish();


    }

    public void showPopup() {
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Toaster.toast("EVE");
            }
        }, 500);
    }
}
