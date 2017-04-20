package com.example.comkostiuk.defilement_slide_android.upnp;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

import org.fourthline.cling.android.AndroidUpnpService;
import org.fourthline.cling.model.meta.LocalDevice;
import org.fourthline.cling.model.meta.LocalService;
import org.fourthline.cling.model.types.UDAServiceType;
import org.fourthline.cling.model.types.UDN;

import java.util.UUID;

/**
 * Created by comkostiuk on 19/04/2017.
 */

public class ServiceUpnp {
    private AndroidUpnpService upnpService;
    private UDN udnDefilement;  // TODO: Not stable! Sauvegarder l'UUID dans un fichier après la première génération
    private ServiceConnection serviceConnection;

    public ServiceUpnp() {
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                upnpService = (AndroidUpnpService) service;


                LocalService<DefilementController> remoteControllerService = getDeviceDefilementLocalService();

                // Register the device when this activity binds to the service for the first time
                if (remoteControllerService == null) {
                    try {
                        System.err.println("CREATION DEVICE!!!");
                        udnDefilement = new UDN(UUID.randomUUID());
                        LocalDevice remoteDevice = DefilementDevice.createDevice(udnDefilement);

                        upnpService.getRegistry().addDevice(remoteDevice);

                    } catch (Exception ex) {
                        System.err.println("Creating Android remote controller device failed !!!");
                        return;
                    }
                }

                System.out.println("Creation device reussie...");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                upnpService = null;
            }
        };

    }

    public ServiceConnection getService() {
        return serviceConnection;
    }

    public LocalService<DefilementController> getDeviceDefilementLocalService() {
        if (upnpService == null)
            return null;

        LocalDevice remoteDevice;
        if ((remoteDevice = upnpService.getRegistry().getLocalDevice(udnDefilement, true)) == null)
            return null;

        return (LocalService<DefilementController>)
                remoteDevice.findService(new UDAServiceType("DefilementController", 1));
    }

    public void stop() {
        upnpService.get().shutdown();
    }
}
