/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package calogiciel;

import Modele.Auteur;
import Modele.Media;
import Modele.DAO.MediaDAO;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author poisson.cdi01
 */
public class TableModelMedia extends AbstractTableModel{
 
   private List<Media> donnees;
   private String[] entetes;
           
   //Constructeur
   public TableModelMedia(int i, String genre, Auteur auteur, int dispo, String recherche) {
      
      this.donnees = (new MediaDAO()).findAll(i, genre, auteur, dispo, recherche);
      this.entetes = new String[]{"Titre du média", "Auteur","Genre", "Type", "Localisation", "Etat", "Disponibilité", "Prêter","Modifier", "Supprimer"};
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
   
   //Permet d'ajouter une ligne dans le tableau
   public void addRow(List<Media> donnees){
      int indice = 0, nbRow = this.getRowCount(), nbCol = this.getColumnCount();
       
      List<Media> temp = this.donnees;
       
      for(Media value : temp)
         this.donnees.add(value);       
          
      temp = null;
      //Cette méthode permet d'avertir le tableau que les données
      //ont été modifiées, ce qui permet une mise à jour complète du tableau
      this.fireTableDataChanged();
   }    
    
   @Override
   public boolean isCellEditable(int row, int col){   
       //seuls les boutons sont editables
       if ((col==7) &&(!((Media)getObject(row)).isDisponibilite())) return false;
       else
       return col >6;
        
   }
   
   public Object getObject(int row){
       
       return this.donnees.get(row);
   }
   
   @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
	
        switch (columnIndex) {
            case 0: return donnees.get(rowIndex).getTitre();
            case 1: return donnees.get(rowIndex).getAuteur().getNom()+" "+donnees.get(rowIndex).getAuteur().getPrenom();
            case 2: if(donnees.get(rowIndex).getGenre()!=null)
                return donnees.get(rowIndex).getGenre().getLibelle();
                 return null;
            case 3: if(donnees.get(rowIndex).getTypeMedia()!=null)
                return donnees.get(rowIndex).getTypeMedia().getLibelle();
                return null;
            case 4: if(donnees.get(rowIndex).getLocalisation()!=null)
                return donnees.get(rowIndex).getLocalisation();
                 return null;
            case 5: if(donnees.get(rowIndex).getEtat()!=null)
                return donnees.get(rowIndex).getEtat().getLibelle();
                 return null;
            case 6: return donnees.get(rowIndex).getDisponibilite();
            
            default: ;
            }    
        return null;
    }
}

