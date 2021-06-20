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
            spacelib.creerUsager("Toto2", "Toto2", "toto2@gmail.com", "toto2");
            System.out.println("OK");
            boolean b = spacelib.verifUsager("toto@gmail.com", "toto");
            System.out.println(b);

            
           /*
            
            Long idUsager = (long)1;
            int nbPassager = 4;
            //Recherche disponibilités
            //System.out.println("Vous êtes à la station Dimidium.");
            Long idStationDepart = (long)2;
            
            NavetteExport n = spacelib.rechercheNavetteDepart(idStationDepart, nbPassager);
            
            if(n == null){
                System.out.println("Il n'y a pas de navette disponible actuellement");
            }
            else{
                System.out.println("Une navette est disponible.");
                spacelib.reservationNavette(n.getId());
                
                Long quaiDepart = n.getQuai();
                
                System.out.println("Vous avez bien réservé la navette " + n.getId() + ".");
                System.out.println("Vous souhaitez aller à la station Terre.");
                
                Long idStationArrivee = (long)1;
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
                }
            }
         
            */
        } catch (NamingException ex) {
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Contexte introuvable");
        }
        
    }
}
