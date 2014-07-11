/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package calogiciel;

import Modele.Abonne;
import Modele.DAO.AbonneDAO;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author zemouri.cdi01
 */
public class TableModelAbonnes extends AbstractTableModel{
   private List<Abonne> donnees;
   private String[] entetes;
   
   public TableModelAbonnes()
   {
       this.donnees = new AbonneDAO().findAll();
       this.entetes = new String[]{"Nom","Mail", "Téléphone", "Adresse", "Confirmé", "Date début abonnement", "Date fin abonnement"};
   }

    //Retourne le titre de la colonne à l'indice spécifié
   @Override
   public String getColumnName(int col) {
     return this.entetes[col];
   }
 
   //Retourne le nombre de colonnes
   @Override
   public int getColumnCount() {
      return this.entetes.length;
   }
    
   //Retourne le nombre de lignes
   @Override
   public int getRowCount() {
      return this.donnees.size();
   }
   
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: return donnees.get(rowIndex).getPersonne().getNom_Personne()+" "+donnees.get(rowIndex).getPersonne().getPrenom_Personne();
            case 1:if(donnees.get(rowIndex).getPersonne() != null) 
                return donnees.get(rowIndex).getPersonne().getMail_Personne();
                return null;
            case 2: if(donnees.get(rowIndex).getPersonne() != null)
                return donnees.get(rowIndex).getPersonne().getTelephone();
                 return null;
            case 3: if(donnees.get(rowIndex).getPersonne()!=null)
                return donnees.get(rowIndex).getPersonne().getAdresse();
                return null;
            case 4: return donnees.get(rowIndex).isConfirme();
            case 5: return null;
            case 6: return donnees.get(rowIndex).getDateFinAbonnement();
            default: 
                return null;
            }
    }
    
}
