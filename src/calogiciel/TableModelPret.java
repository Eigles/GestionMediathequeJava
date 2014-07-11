package calogiciel;


import Modele.Emprunt;
import Modele.DAO.EmpruntDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.table.AbstractTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author poisson.cdi01
 */
public class TableModelPret extends AbstractTableModel{
 
   private List<Emprunt> donnees;
   private String[] entetes;
    
   //Constructeur
   public TableModelPret(String recherche, String etat){
      this.donnees = (new EmpruntDAO()).findAll(1,recherche, etat);
      this.entetes = new String[]{"Titre du média", "Nom de l'emprunteur","Date d'emprunt", "Date de retour", "Etat", "Action", "Supprimer"};
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
   public void addRow(List<Emprunt> donnees){
      int indice = 0, nbRow = this.getRowCount(), nbCol = this.getColumnCount();
       
      List<Emprunt> temp = this.donnees;
       
      for(Emprunt value : temp)
         this.donnees.add(value);       
          
      temp = null;
      //Cette méthode permet d'avertir le tableau que les données
      //ont été modifiées, ce qui permet une mise à jour complète du tableau
      this.fireTableDataChanged();
   }    
    
   @Override
   public boolean isCellEditable(int row, int col){
      if (col==6&&(donnees.get(row)).getEtat().equals("Terminé"))return true;
      return (col==5)&&(!(donnees.get(row)).getEtat().equals("Terminé"));      
   }
   
    public Object getObject(int row)
    {
        return this.donnees.get(row);
    }
   
   @Override
    public Object getValueAt(int rowIndex, int columnIndex) 
    {		
        switch (columnIndex) {

            case 0:return donnees.get(rowIndex).getMedia().getTitre();
            case 1:return donnees.get(rowIndex).getPersonne().getNom_Personne()+" "+donnees.get(rowIndex).getPersonne().getPrenom_Personne();
            case 2:return changeFormatDate(donnees.get(rowIndex).getDateEmprunt(),"yyyy-MM-dd","dd MMMM yyyy");
            case 3:return changeFormatDate(donnees.get(rowIndex).getDateRetour(),"yyyy-MM-dd","dd MMMM yyyy") ;
            case 4:return donnees.get(rowIndex).getEtat();  
            default:return null;
            }     
    }
    
    //Changer le format de la date
     public String changeFormatDate(String date,String formatOrg,String nvFormat)
    {
                if(date==null) return null;
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

        
