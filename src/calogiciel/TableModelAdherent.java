/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package calogiciel;

import Modele.Adherent;
import Modele.DAO.AdherentDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author zemouri.cdi01
 */
public class TableModelAdherent extends AbstractTableModel{
    private List<Adherent> donnees;
    private String[] entetes;
    
    public TableModelAdherent(int numPage, int etat, String recherche)
    {
        this.donnees = new AdherentDAO().findAll(numPage, etat, recherche);
        this.entetes = new String[]{"Nom", "Mail", "Téléphone", "Adresse", "Ville", "Code postal", "Date début adhésion", "Date fin adhésion", "Etat", "Confirmer", "Modifier","Supprimer"};
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: return donnees.get(rowIndex).getPersonne().getNom_Personne()+" "+donnees.get(rowIndex).getPersonne().getPrenom_Personne();
            case 1: return donnees.get(rowIndex).getPersonne().getMail_Personne();
            case 2: return donnees.get(rowIndex).getPersonne().getTelephone();
            case 3: return donnees.get(rowIndex).getPersonne().getAdresse();
            case 4: if(donnees.get(rowIndex).getPersonne().getVille() != null)
                    return donnees.get(rowIndex).getPersonne().getVille().getNomVille();
                    return null;
            case 5: if(donnees.get(rowIndex).getPersonne().getVille() != null)
                    return donnees.get(rowIndex).getPersonne().getVille().getCp();
                    return null;
            
            case 6: return changeFormatDate(donnees.get(rowIndex).getDateAdhesion(),"yyyy-MM-dd","dd MMMM yyyy");
            case 7: return changeFormatDate(donnees.get(rowIndex).getFinAdhesion(),"yyyy-MM-dd","dd MMMM yyyy");
            case 8: if(donnees.get(rowIndex).isConfirme())return "Confirmé";
                    else return "En attente";
            default: 
                return null;
            }
    }
    
    @Override
     public boolean isCellEditable(int row, int col){   
       //seuls les boutons sont editables
        if (col==10||col==11)  return true;
        if ((col==9)&&(!donnees.get(row).isConfirme())) return true;
        
             return false;
        
   }
     

      
      //Changer le format de la date
     public String changeFormatDate(String date,String formatOrg,String nvFormat)
    {
            if(date==null)return null;
		SimpleDateFormat formatNouveau = new SimpleDateFormat(nvFormat, Locale.FRENCH);
		SimpleDateFormat formatOrigine = new SimpleDateFormat(formatOrg, Locale.FRENCH);
 
		String dateFormatLong="";
 
		try {
			dateFormatLong = formatNouveau.format(formatOrigine.parse(date.trim()));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
 
		return dateFormatLong;
    
}
}
