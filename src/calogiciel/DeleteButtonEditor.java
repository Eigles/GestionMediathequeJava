/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package calogiciel;

import Modele.Adherent;
import Modele.DAO.AdherentDAO;
import Modele.DAO.EmpruntDAO;
import Modele.Emprunt;
import Modele.Media;
import Modele.Personne;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.EventObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;

/**
 *
 * @author poisson.cdi01
 */
public class DeleteButtonEditor implements TableCellEditor {
      
    protected JButton button;
    protected String text;
    private DeleteButtonListener bListener = new DeleteButtonListener();
    TableModel model;
    private FenetrePrincipale fenetre;
    
    
    public DeleteButtonEditor(TableModel model,String text, FenetrePrincipale fenetre) {
    this.model = model;
    this.text = text;
    this.button = new JButton();
    this.button.addActionListener(bListener);
    this.fenetre=fenetre;
    
    } 
    
 
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        System.out.println("ligne: "+row);
        bListener.setRow(row);
        bListener.setTable(table);
        if(table.getColumnName(column).equals("Modifier")){
            button.setIcon(new ImageIcon("resources/modifier24.png")); 
        }
        
        //On retourne notre bouton
        if(table.getColumnName(column).equals("Supprimer")){
            boolean image=false;
            if (table.getModel() instanceof TableModelPret){
                String etat=((Emprunt)(((TableModelPret)table.getModel()).getObject(row))).getEtat();
                 if(etat.equals("Terminé")){
                     
                 image=true;
                button.setIcon(new ImageIcon("resources/trash24.png"));
                 }
            }else{
            button.setIcon(new ImageIcon("resources/trash24.png"));    
            image=true;
            }
            System.out.println(image);
        }
        
        if(table.getColumnName(column).equals("Confirmer")){
            if (!((Adherent)(((TableModelAdherent)table.getModel()).getObject(row))).isConfirme()){
                button.setText("Confirmer");  
            }
        }
        
        if(table.getColumnName(column).equals("Prêter")){
             button.setText("Prêter");             
        }
        
        if(table.getColumnName(column).equals("Adhérer")){            
                button.setText("Adhérer");  
        }
        
        if(table.getColumnName(column).equals("Action")){
            String etat= ((Emprunt)(((TableModelPret)table.getModel()).getObject(row))).getEtat();
            if(etat.equals("En cours")||etat.equals("En retard"))
                button.setText("Rendre");
            if(etat.equals("Attente de confirmation"))
                button.setText("Confirmer");                        
        }
        
       button.setBackground(Color.white);
       return button;
    }
    

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public boolean isCellEditable(EventObject eo) {
        return true;
    }

    @Override
    public boolean shouldSelectCell(EventObject eo) {
        return true;
    }

    @Override
    public boolean stopCellEditing() {
        return true;
    }

    @Override
    public void cancelCellEditing() {
       
    }

    @Override
    public void addCellEditorListener(CellEditorListener cl) {
        
    }

    @Override
    public void removeCellEditorListener(CellEditorListener cl) {
        
    }



    
    class DeleteButtonListener implements ActionListener 
    {         
        private int row;
        private JTable table;
         
        public void setRow(int row){this.row = row;}
        public void setTable(JTable table){this.table = table;}         
        
        public void actionPerformed(ActionEvent event) 
        {
           if(text.equals("Modifier"))
            {
                
                JButton j =  (JButton) event.getSource(); 
                if(fenetre.panelCourant.equals(fenetre.panelMedia))
                {
                    Media media = (Media)((TableModelMedia)model).getObject(row);
                    try {
                        fenetre.panelMedia.modifierMedia(media);
                    } catch (IOException ex) {
                        Logger.getLogger(DeleteButtonEditor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if(fenetre.panelCourant.equals(fenetre.panelPret))
                {
                    Emprunt emprunt = (Emprunt)((TableModelPret)model).getObject(row);
                   /* try {
                        fenetre.panelPret.modifierEmprunt(emprunt);
                    } catch (SQLException |ClassNotFoundException ex) {
                        Logger.getLogger(DeleteButtonEditor.class.getName()).log(Level.SEVERE, null, ex);
                    }     */              
                }
                
               if(fenetre.panelCourant.equals(fenetre.panelSympathisant))
                {
                   Personne pers = (Personne)((TableModelSympathisant)model).getObject(row);
                   fenetre.panelSympathisant.modifierSympathisant(pers);
                }
               
               if(fenetre.panelCourant.equals(fenetre.panelAdherent))
                {
                   Adherent adherent = (Adherent)((TableModelAdherent)model).getObject(row);
                   fenetre.panelAdherent.modifierAdherent(adherent);
                }
              
            }
            // action du bouton supprimer sur le tableau
            else if(text.equals("Supprimer"))
            {             
                          
                // pour la page media
                if(fenetre.panelCourant.equals(fenetre.panelMedia))
                {
                    // on réccupère l'id du média à supprimer
                    int id  = ((Media)((TableModelMedia)model).getObject(row)).getIdMedia();
                    fenetre.panelMedia.supprimerMedia(id);
                }
                // pour la page pret
                else if(fenetre.panelCourant.equals(fenetre.panelPret))
                {
                    // on réccupère l'id de l'emprunt à supprimer
                    int id  = ((Emprunt)((TableModelPret)model).getObject(row)).getIdEmprunt();
                    fenetre.panelPret.supprimerEmprunt(id);
  
                }
                else if(fenetre.panelCourant.equals(fenetre.panelAdherent))
                {
                    int id;
                    id = ((Adherent)((TableModelAdherent)model).getObject(row)).getId();
                    fenetre.panelAdherent.supprimerAdherent(id);
                }
                else if(fenetre.panelCourant.equals(fenetre.panelSympathisant))
                {
                    
                    int id;
                    
                    id = ((Personne)((TableModelSympathisant)model).getObject(row)).getId_Personne();
                    fenetre.panelSympathisant.supprimerSympathisant(id);
                }
                
            }
            
           if(text.equals("Confirmer")){
                    Adherent adherent = ((Adherent)((TableModelAdherent)model).getObject(row));
                    adherent.setConfirme(Boolean.TRUE);
                    new AdherentDAO().update(adherent);
                    fenetre.panelAdherent.MAJTableau(false);
                                
               }
           
           if(text.equals("Adhérer")){
                    SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
                    Personne pers = ((Personne)((TableModelSympathisant)model).getObject(row));
                    Adherent adherent = new Adherent(0,formatDate.format(new java.util.Date()),false,pers,"");
                    new AdherentDAO().create(adherent);
                    fenetre.panelSympathisant.MAJTableau(false);
                                
               }
           
           if(text.equals("Prêter")){
                    fenetre.panelPret = new PanelPret(fenetre);
                    fenetre.add(fenetre.panelPret);
                    fenetre.panelPret.afficherFormulaire();
                    fenetre.panelPret.setMediaFormulaire(((Media)((TableModelMedia)model).getObject(row)));
                    fenetre.repaint();
                              
               }
           
            
            if(text.equals("Action")){
                
                // On récupère l'état du prêt
                String etat= ((Emprunt)(((TableModelPret)table.getModel()).getObject(row))).getEtat();
               
                //Modifier l'état du prêt
                Emprunt emprunt = ((Emprunt)((TableModelPret)model).getObject(row));
                if (etat.equals("En cours")||etat.equals("En retard"))               
                    emprunt.setEtat("Terminé");
                if (etat.equals("Attente de confirmation"))               
                    emprunt.setEtat("En cours");
                new EmpruntDAO().update(emprunt);
                
                //Mise à jour du tableau
                fenetre.panelPret.MAJTableau(false);
            
            }
        }
   }        
}
