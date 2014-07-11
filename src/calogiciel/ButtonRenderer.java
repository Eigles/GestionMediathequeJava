/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package calogiciel;

import Modele.Adherent;
import Modele.Emprunt;
import Modele.Media;
import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author poisson.cdi01
 */
public class ButtonRenderer extends JButton implements TableCellRenderer{

        
    public ButtonRenderer(){
        
    }
    
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean isFocus, int row, int col) {
        //On écrit dans le bouton ce que contient la cellule
        if(table.getColumnName(col).equals("Modifier")){
              this.setIcon(new ImageIcon("resources/modifier24.png"));
              this.setBackground(Color.white);
        }
       
        //On retourne notre bouton
        if(table.getColumnName(col).equals("Supprimer")){
             this.setIcon(new ImageIcon("resources/trash24.png"));
            
        }
        
        if(table.getColumnName(col).equals("Prêter")){
            if(((Media)(((TableModelMedia)table.getModel()).getObject(row))).isDisponibilite()){
            this.setText("Prêter");            
            }
            else this.setText("");
         }
        
        if(table.getColumnName(col).equals("Confirmer")){
            if (!((Adherent)(((TableModelAdherent)table.getModel()).getObject(row))).isConfirme()){
                this.setText("Confirmer");
            }
            else this.setText("");
            
        }
        
        if(table.getColumnName(col).equals("Adhérer")){           
                this.setText("Adhérer");           
        }
        
        if(table.getColumnName(col).equals("Action")){
            String etat= ((Emprunt)(((TableModelPret)table.getModel()).getObject(row))).getEtat();
            
            if (etat.equals("Attente de confirmation")){
                this.setText("Confirmer");
            }
             if (etat.equals("En cours")||etat.equals("En retard")){
                 this.setText("Rendre");
             }
                                
              if (etat.equals("Terminé")){
                 this.setText("");
              }
                
            
        }
        this.setBackground(Color.white);
             
        return this;
    }
}
