/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clientlourd;

import com.mycompany.spacelibshared.interfremote.ExpoClientLourdRemote;
import com.mycompany.spacelibshared.utilities.NavetteExport;
import com.mycompany.spacelibshared.utilities.QuaiExport;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Jean
 */
public class Main {
    public static void main (String[] args){
        // 1 : lookup objet
        
        Context ctx;
        try {
            System.out.println(" ");
            System.out.println(" ");
            ctx = new InitialContext();
            ExpoClientLourdRemote spacelib = (ExpoClientLourdRemote) ctx.lookup("com.mycompany.spacelibshared.interfremote.ExpoClientLourdRemote");

        //2 : métier
        
            //Connexion
            boolean b = spacelib.verifUsager("toto@gmail.com", "toto");
            System.out.println(b);
            spacelib.creerUsager("Toto2", "Toto2", "toto2@gmail.com", "toto2");
            System.out.println("OK");
            

            
            Long idUsager = (long)1;
            int nbPassager = 4;
            //Recherche disponibilités
            //System.out.println("Vous êtes à la station Dimidium.");
            Long idStationDepart = (long)1;
            
            NavetteExport n = spacelib.rechercheNavetteDepart(idStationDepart, nbPassager);
            
            if(n == null){
                System.out.println("Il n'y a pas de navette disponible actuellement");
            }
            else{
                System.out.println("Une navette est disponible.");
                spacelib.reservationNavette(n.getId());
                
                Long quaiDepart = (long)1;
                
                System.out.println("Vous avez bien réservé la navette " + n.getId() + ".");
                System.out.println("Vous souhaitez aller à la station Terre.");
                
                Long idStationArrivee = (long)2;
                Long quaiArrive = (long)2;
                QuaiExport quaiArrivee = spacelib.rechercheQuaiArrivee(idStationArrivee, n.getId());
                
                if(quaiArrivee == null){
                    System.out.println("Il n'y a pas de quai disponible actuellement");
                }
                else{
                    System.out.println("Vous avez bien réservé le quai " + quaiArrivee.getId() + ".");
                    //Les opérations sont faites ici
                    spacelib.libererQuaiArrimage(quaiDepart, quaiArrivee.getId(), idUsager, nbPassager); 
                    System.out.println("Vous quittez le quai " + quaiDepart + ".");
                    
                    System.out.println(" ");
                    System.out.println(" ");
                    System.out.println("Vous êtes bien arrivé au quai " + quaiArrivee.getId() + "");
                    
                    //Arrivée du voyageur
                    
                    spacelib.updateVoyageArrive(n.getId(), idUsager, nbPassager); //On actualise les opérations
                    spacelib.plusDeTroisVoyage(n.getId()); //On vérifie si la navette a besoin d'être révisée
                    
                    /*
                    try{
                    //Retour
                    n = spacelib.rechercheNavetteDepart(idStationArrivee, nbPassager);
                    spacelib.reservationNavette(n.getId());
                    spacelib.libererQuaiArrimage(quaiArrive, quaiDepart, idUsager, nbPassager); 
                    spacelib.updateVoyageArrive(n.getId(), idUsager, nbPassager); //On actualise les opérations
                    System.out.println("Terre -> Dimidium");
                    
                    try{
                        //Aller
                    n = spacelib.rechercheNavetteDepart(idStationDepart, nbPassager);
                    spacelib.reservationNavette(n.getId());
                    spacelib.libererQuaiArrimage(quaiDepart, quaiArrive, idUsager, nbPassager); 
                    spacelib.updateVoyageArrive(n.getId(), idUsager, nbPassager); //On actualise les opérations
                    spacelib.plusDeTroisVoyage(n.getId()); //On vérifie si la navette a besoin d'être révisée
                    System.out.println("Dimidium -> DimidiumTerre");
                    
                    try{
                    //Retour
                    n = spacelib.rechercheNavetteDepart(idStationArrivee, nbPassager);
                    spacelib.reservationNavette(n.getId());
                    spacelib.libererQuaiArrimage(quaiArrive, quaiDepart, idUsager, nbPassager); 
                    spacelib.updateVoyageArrive(n.getId(), idUsager, nbPassager); //On actualise les opérations
                    System.out.println("Terre -> Dimidium");
                    }catch(Exception e){System.out.println("Erreur retour 2");}
                    
                    }catch(Exception e){System.out.println("Erreur Aller 2");}
                    
                    }catch(Exception e){System.out.println("Erreur retour");}*/
                    
                }
            }
         
        } catch (NamingException ex) {
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Contexte introuvable");
        }
        
    }
}
