/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package calogiciel;

import Modele.DAO.PersonneDAO;
import Modele.Personne;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author zemouri.cdi01
 */
public class TableModelSympathisant extends AbstractTableModel{
    private List<Personne> donnees;
    private String[] entetes;
    public TableModelSympathisant(int numPage, String recherche)
    {
        this.donnees = new PersonneDAO().findAllSympathisant(numPage,recherche);
        this.entetes = new String[]{"Nom", "Mail" , "Téléphone", "Adresse", "Ville", "Code postal","Adhérer","Modifier","Supprimer"};
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
   
      public Object getObject(int row){
       
       return this.donnees.get(row);
   }
      
   @Override
   public boolean isCellEditable(int row, int col){
        return col==6||col==7||col==8;      
   }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: return donnees.get(rowIndex).getNom_Personne()+" "+donnees.get(rowIndex).getPrenom_Personne();
            case 1: return donnees.get(rowIndex).getMail_Personne();
            case 2: return donnees.get(rowIndex).getTelephone();
            case 3: return donnees.get(rowIndex).getAdresse();
            case 4: if(donnees.get(rowIndex).getVille() != null)
                return donnees.get(rowIndex).getVille().getNomVille();
                return null;
            case 5: if(donnees.get(rowIndex).getVille() != null)
            return donnees.get(rowIndex).getVille().getCp();
                return null;
            default: 
                return null;
            }
    }
    
}
