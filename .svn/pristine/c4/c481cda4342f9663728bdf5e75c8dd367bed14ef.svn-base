/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package calogiciel;


import Modele.Auteur;
import Modele.DAO.MediaDAO;
import Modele.DAO.PersonneDAO;
import Modele.Genre;
import Modele.Personne;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author zemouri.cdi01
 */
public class PanelSympathisant extends PanelStructure implements ActionListener{
   
       
    public PanelSympathisant(FenetrePrincipale fenetre)
    {
        super(fenetre, new TableModelSympathisant(1,""));
        labelTitre.setText("Liste des sympahisants");
        labelFiltre.setText("");
        
        barreRecherche.setText("Rechercher par nom ou prénom");
        combo1.addItemListener(this);
        
         boutonAjout.setText("Ajouter sympathisant"); 
        this.setVisible(true);
        MAJcombo4(new PersonneDAO().compterLignesRecherche(""),1);
        labelPage.setText("Page n° ");
        this.add(labelPage);
        this.add(pagePlus);
        this.add(pageMoins);
        this.add(tableauDeroulant);
        this.responsivePanel();
    }
    

    
     public void MAJTableau(boolean comboPage){
            String recherche="";
            int page=1 ;
            
           this.remove(tableauDeroulant);
           if (!barreRecherche.getText().equals("Rechercher par nom ou prénom"))
                recherche = barreRecherche.getText();
                                   
           if (comboPage){
               page = (int)combo4.getSelectedItem();
           }
           
               
           tableauDeroulant=initialiserTableau(new TableModelSympathisant(page,recherche));
           int nblignes = new PersonneDAO().compterLignesRecherche(recherche);
           MAJcombo4(nblignes,page);
           responsivePanel();
           this.add(tableauDeroulant);
           this.repaint();
           
           this.updateUI();
    }
     
     
    
    public void changerPanel(String titre){
        
        fenetre.panelAjoutPersonne = new PanelAjoutPersonne(fenetre,titre);
        fenetre.add(fenetre.panelAjoutPersonne);
        fenetre.panelAjoutPersonne.updateUI();
        fenetre.repaint();
    }
    
    
    void modifierSympathisant(Personne pers) {
       
        changerPanel("Modifier un sympathisant");
        fenetre.panelAjoutPersonne.initialiserModif(pers);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource().equals(boutonAjout))
        {
            fenetre.panelAjoutPersonne = new PanelAjoutPersonne(fenetre, "Ajouter un sympathisant");
            fenetre.add(fenetre.panelAjoutPersonne);
            fenetre.repaint();
            fenetre.panelAjoutPersonne.updateUI();
        }
    
       
        if (ae.getSource().equals(pagePlus))
       {
           int pageActuelle = (int)combo4.getSelectedItem();
           if(pageActuelle<combo4.getItemCount())
           combo4.setSelectedItem(pageActuelle+1);
       }
       
       if (ae.getSource().equals(pageMoins))
       {
           int pageActuelle = (int)combo4.getSelectedItem();
           if(pageActuelle!=1)
            combo4.setSelectedItem(pageActuelle-1);
       }
    }
}
