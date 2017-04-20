package com.example.comkostiuk.defilement_slide_android.upnp;

import org.fourthline.cling.binding.LocalServiceBindingException;
import org.fourthline.cling.binding.annotations.AnnotationLocalServiceBinder;
import org.fourthline.cling.model.DefaultServiceManager;
import org.fourthline.cling.model.ValidationException;
import org.fourthline.cling.model.meta.DeviceDetails;
import org.fourthline.cling.model.meta.DeviceIdentity;
import org.fourthline.cling.model.meta.LocalDevice;
import org.fourthline.cling.model.meta.LocalService;
import org.fourthline.cling.model.meta.ManufacturerDetails;
import org.fourthline.cling.model.meta.ModelDetails;
import org.fourthline.cling.model.types.DeviceType;
import org.fourthline.cling.model.types.UDADeviceType;
import org.fourthline.cling.model.types.UDN;

/**
 * Created by comkostiuk on 19/04/2017.
 */

public class DefilementDevice {
    static LocalDevice createDevice(UDN udn)
            throws ValidationException, LocalServiceBindingException {

        DeviceType type =
                new UDADeviceType("DefilementController", 1);

        DeviceDetails details =
                new DeviceDetails(
                        "Android Defilement Slide",
                        new ManufacturerDetails("IRIT"),
                        new ModelDetails("AndroidController", "Envoie un message slide suivante/precedente à l'étudiant", "v1")
                );

        LocalService service =
                new AnnotationLocalServiceBinder().read(DefilementController.class);

        service.setManager(
                new DefaultServiceManager<>(service, DefilementController.class)
        );

        return new LocalDevice(
                new DeviceIdentity(udn),
                type,
                details,

                service
        );
    }
}
