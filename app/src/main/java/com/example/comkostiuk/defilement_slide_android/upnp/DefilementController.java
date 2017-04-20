package com.example.comkostiuk.defilement_slide_android.upnp;

import org.fourthline.cling.binding.annotations.UpnpAction;
import org.fourthline.cling.binding.annotations.UpnpInputArgument;
import org.fourthline.cling.binding.annotations.UpnpOutputArgument;
import org.fourthline.cling.binding.annotations.UpnpService;
import org.fourthline.cling.binding.annotations.UpnpServiceId;
import org.fourthline.cling.binding.annotations.UpnpServiceType;
import org.fourthline.cling.binding.annotations.UpnpStateVariable;

import java.beans.PropertyChangeSupport;

/**
 * Created by comkostiuk on 19/04/2017.
 */

@UpnpService(
        serviceType = @UpnpServiceType(value = "DefilementController", version = 1),
        serviceId = @UpnpServiceId("DefilementController")
)
public class DefilementController {

    private final PropertyChangeSupport propertyChangeSupport;

    public DefilementController() { propertyChangeSupport = new PropertyChangeSupport(this);}

    public PropertyChangeSupport getPropertyChangeSupport() {return propertyChangeSupport;}

    @UpnpStateVariable(defaultValue = "AUCUN", sendEvents = false)
    private String direction = State.AUCUN.toString();

    @UpnpStateVariable(defaultValue = "AUCUN")
    private String status = State.AUCUN.toString();

    @UpnpAction
    public void setDirection(@UpnpInputArgument(name = "NewDirectionValue") String newDirectionValue) {
        String oldValue = direction;

        switch(newDirectionValue){
            case "GAUCHE":
                direction = "Slide précédente";
                break;
            case "DROITE":
                direction = "Slide suivante";
                break;
            case "CENTRE":
                direction = State.CENTRE.toString();
                break;
            default:
                direction = State.AUCUN.toString();
        }
        getPropertyChangeSupport().firePropertyChange("status", oldValue, direction);
    }

    @UpnpAction(out = @UpnpOutputArgument(name = "DirectionValue"))
    public String getDirection() {
        return direction;
    }
}
