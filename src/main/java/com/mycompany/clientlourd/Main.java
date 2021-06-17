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
            spacelib.creerUsager("Toto", "Toto", "toto@gmail.com", "toto");
            if(spacelib.verifUsager("toto@gmail.com", "toto")){
                System.out.println("L'utilisateur existe bien");
                
                //Recherche disponibilités
            System.out.println("Vous êtes à la station Dimidium.");
            Long idStationDepart = 1L; // A définir
            NavetteExport n = spacelib.rechercheNavetteDepart(idStationDepart, 4);
            if(n == null){
                System.out.println("Il n'y a pas de navette disponible actuellement");
            }
            else{
                System.out.println("Une navette est disponible.");
                spacelib.reservationNavette(n.getId());
                
                Long quaiDepart = n.getQuai();
                
                System.out.println("Vous avez bien réservé la navette " + n.getId() + ".");
                System.out.println("Vous souhaitez aller à la station Terre.");
                
                Long idStationArrivee = 2L; //A définir
                QuaiExport quaiArrivee = spacelib.rechercheQuaiArrivee(idStationArrivee, n.getId());
                
                if(quaiArrivee == null){
                    System.out.println("Il n'y a pas de quai disponible actuellement");
                }
                else{
                    System.out.println("Vous avez bien réservé le quai " + quaiDepart + ".");
                    spacelib.libererQuaiArrimage(quaiDepart); //On libère le quai de départ
                    
                    System.out.println(" ");
                    System.out.println(" ");
                    System.out.println("Vous êtes bien arrivé au quai " + quaiArrivee.getId() + "");
                    
                    //Arrivée du voyageur
                    
                    spacelib.updateVoyageArrive(n.getId()); //On actualise les opérations
                    spacelib.plusDeTroisVoyage(n.getId()); //On vérifie si la navette a besoin d'être révisée
                }
            }
                
            }
            
        } catch (NamingException ex) {
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Contexte introuvable");
        }
        
    }
}
