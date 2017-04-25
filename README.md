# Android_Defilement_Slide
Composant UPnP permettant d'avertir l'étudiant de passer à la slide suivant.

<strong> Description: </strong>

Ce composants est un service Android, une application sans interfance graphique tournant en tâche de fond. Elle permet de recevoir
des chaînes de caractères via UPnP et de les afficher à l'écran sous forme de Pop up.
Ce composant peut être utilisé lors du suivi d'un cours à distance, par exemple l'étudiant sera averti par le professeur lorsqu'il
faudra passer à la slide suivante (à coupler avec un composant permettant de recevoir des fichiers audios, pour les explications 
des slides par le professeur).

<strong>Lancement de l'application: </strong>

L'application ne peut pas communiquer via UPnP lorsque lancée dans un émulateur, elle doit être lancée sur un terminal physique et appartenir au même réseau local que les autres composants.

Il faut donc installer l'apk sur le terminal, vérifier d'avoir autorisé les sources non vérifiées.

Après démarrage de l'application, il est possible d'ajouter le composantsur wcomp en suivant la méthode décrite sur le wiki oppocampus.

Cette application ne présente pas d'interface graphique, c'est un service en arrière plan.
TODO: éteindre le service après une certaine période.

Voici un exemple d'exécution de l'application:

![alt tag](https://github.com/components-upnp/Android_Defilement_Slide/blob/master/CaptureSlideSuivante%5B1%5D.png) 

ATTENTION: 
  -redémarrer l'application fait changer l'UID du composant, il faut donc le rajouter sur wcomp à nouveau.(ce problème sera réglé utltérieurement).


<strong><u>Spécification UPnP:</u></strong>

Ce composant offre le service UPnP DefilementController, dont la description est la suivante:

  1) SetDirection(String NewDirectionValue): permet de mettre à jour la direction( des slides par exemple)
  2) GetDirection(): retourne la direction courante.
  
Ce service n'envoie aucun événement UPnP.

Voici le schéma correspondant à ce composant:

![alt tag](https://github.com/components-upnp/Android_Defilement_Slide/blob/master/Defilement.png)

<strong><u>Maintenance:</u></strong>

Le projet de l'application est projet gradle.
