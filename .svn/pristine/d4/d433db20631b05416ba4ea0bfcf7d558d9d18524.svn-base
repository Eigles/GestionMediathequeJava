/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package calogiciel;


import Modele.Adherent;
import Modele.DAO.AdherentDAO;
import Modele.DAO.PersonneDAO;
import Modele.Personne;
import Modele.Ville;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author imie
 */
public class CALogiciel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        try {
            // TODO code application logic here
            
            Class.forName("com.mysql.jdbc.Driver");
                       
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CALogiciel.class.getName()).log(Level.SEVERE, null, ex);
        
       }
       /* AdherentDAO adao = new AdherentDAO();
        PersonneDAO pdao = new PersonneDAO();
        Personne pers;
        int j=41;
        for(int i = 0; i<30 ; i++){
            pers=new Personne(j,"ducon","Patrick","1985-01-01","@",new Ville(2690321,"",""), false,"06", "adresse");
            pdao.create(pers);
            adao.create(new Adherent(1,"2014-10-10", Boolean.TRUE, pers,"2015-08-08"));
            j++;
        }
              
       
    }*/
     new FenetrePrincipale();
    }
}
