
package calogiciel;

import Modele.Adherent;
import Modele.DAO.AdherentDAO;
import Modele.DAO.EmpruntDAO;
import Modele.DAO.MediaDAO;
import Modele.Emprunt;
import Modele.Media;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author poisson.cdi01
 */

public class PanelPret extends PanelStructure implements ActionListener, KeyListener {
     
    private String[] entetes;
    protected List<Emprunt> donnees;
    private FenetrePrincipale fenetre;
    int height;
    int width, margeCote = 0;    
    JComboBox champEmprunteur;
    JTextField champMedia;
    JDateChooser champDate;    
    JLabel labelMedia,labelEmprunteur,labelDate;
    JList<Media> liste ;
    JScrollPane listeDeroulante;
    DefaultListModel<Media> listeMediaCorres;
    String mediaRecherche ;    
    boolean existThread = false;
    Media mediaPret;
    JButton boutonRetour;
        
    public PanelPret(FenetrePrincipale fenetre)
    {   
        super(fenetre, new TableModelPret("",""));
        this.fenetre = fenetre;        
        labelTitre.setText("Liste de prêts");
        combo1.addItem("Tous les états");
        List<Object> listeCombo1 =  findAllEtat();
        for(Object obj : listeCombo1)
        {
            combo1.addItem(obj);
        }   
        combo1.addItemListener(this);
        
         barreRecherche.setText("Rechercher par titre ou emprunteur");
       // tableauDeroulant = new JScrollPane(tableau);
         
        MAJcombo4(new EmpruntDAO().compterLignesRecherche("",""),1);
        
        boutonAjout.setText("Ajouter prêt"); 
        labelPage.setText("Page n° ");
        this.responsivePanel();
        this.add(combo1);
        this.add(tableauDeroulant);
        this.add(pagePlus);
        this.add(pageMoins);
        this.add(labelPage);
        this.setVisible(true);
    }
    
    public void afficherFormulaire(){
        
        this.removeAll();
        
        JPanel panelAjout = new JPanel();
        panelAjout.setLayout(new GridLayout(12, 2));
        labelTitre.setText("Ajouter un prêt");
        labelMedia = new JLabel("Titre du média : ");
        champMedia = new JTextField();
        champMedia.addKeyListener(this);
        champMedia.setEditable(true);
        
        // autocompletion sur le titre 
        mediaRecherche = new String();
        liste = new JList();
        listeMediaCorres = new DefaultListModel<>();
        liste = new JList<>(listeMediaCorres);        
        liste.addMouseListener(this);
        listeDeroulante = new JScrollPane(liste);
        listeDeroulante.setVisible(false);
        
        labelEmprunteur = new JLabel("Nom de l'emprunteur : ");
        champEmprunteur = new JComboBox();
        champEmprunteur.addItem("Choisir l'emprunteur");
        List<Adherent> listeAdherent =  (new AdherentDAO()).findAll();
        for(Adherent adh : listeAdherent)
        {
            champEmprunteur.addItem(adh);
        } 
        champEmprunteur.setEnabled(false);
        
        boutonRetour = new JButton("Retour");
        boutonRetour.addActionListener(this);
        boutonAjoutBDD = new JButton("Ajouter");
        boutonAjoutBDD.addActionListener(this);
        
        labelTitre.setBounds(10,10,200,30);
        
        panelAjout.setBounds(50,50,600,400);  
        boutonRetour.setBounds(50,470,100,40);
        boutonAjoutBDD.setBounds(180,470,100,40);     
        listeDeroulante.setBounds(50,120,600,220);       
        
        this.add(listeDeroulante);
        this.add(labelTitre);
        panelAjout.add(labelMedia);
        panelAjout.add(champMedia);
        panelAjout.add(labelEmprunteur);
        panelAjout.add(champEmprunteur);
        this.add(boutonRetour);
        this.add(boutonAjoutBDD);
        this.add(panelAjout);
        this.repaint();
        this.revalidate();
    }
    
    public void setMediaFormulaire(Media media){
        mediaPret=media;
        champEmprunteur.setEnabled(true);
        champMedia.setText(media.getTitre());
    }
   
    
    public List<Object> findAllEtat(){
        List<Object> liste = new ArrayList();
        liste.add("Attente de confirmation");
        liste.add("En cours");
        liste.add("En retard");
        liste.add("Terminé");
        return liste;
    }    
   
    @Override
    public void actionPerformed(ActionEvent ae) {
       if(ae.getSource().equals(boutonAjout))
       {
          afficherFormulaire();
       }
       if(ae.getSource().equals(boutonAjoutBDD))
       {
           try { 
               verifierDonnees(1);
           } catch (SQLException ex) {
               Logger.getLogger(PanelPret.class.getName()).log(Level.SEVERE, null, ex);
           } catch (ParseException ex) {
               Logger.getLogger(PanelPret.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
       
        if(ae.getSource().equals(boutonRetour))
       {
           afficherPanelPret();
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
    
    
    public void verifierDonnees(int action) throws SQLException, ParseException
    {   
        boolean validite = true;
        labelEmprunteur.setForeground(Color.black);
        labelMedia.setForeground(Color.black);
        Adherent adherent = new Adherent();        
        SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
        String date;
        date = format.format(new java.util.Date( ));
        if(champMedia.getText().equals("")) {labelMedia.setForeground(Color.red); validite = false;}
        if(champEmprunteur.getSelectedItem().equals(champEmprunteur.getItemAt(0))) {labelEmprunteur.setForeground(Color.red); validite = false;}
        else adherent = (Adherent) champEmprunteur.getSelectedItem();
        
        if(validite)
        {
            Emprunt emprunt = new Emprunt (0,date,adherent.getPersonne(), mediaPret, date, "confirmé");
            if(action==1)
            {
                EmpruntDAO e = new EmpruntDAO();
               /* boolean exist = e.existe(mediaPret, date);
                if (exist==false)
                {*/
                    boolean ajout = e.create(emprunt);

                    // affichage du message de réussite                     
                    JOptionPane d = new JOptionPane();
                    d.showMessageDialog(fenetre, "L'ajout a été effectué.", "information", JOptionPane.INFORMATION_MESSAGE);
               /* }
                else
                {
                    // affichage du message d'échec                     
                    JOptionPane d = new JOptionPane();
                    d.showMessageDialog(fenetre, "L'emprunt sera déjà en cours à cette période.", "information", JOptionPane.INFORMATION_MESSAGE);
                }*/
                    
                // retour sur la liste des medias
                afficherPanelPret();
            }
            else if(action==2)
            {
                
            }
        }
        else
        {
            JOptionPane d = new JOptionPane();
                d.showMessageDialog(fenetre, "Veuillez remplir les deux champs.", "information", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
  
    public void afficherPanelPret(){
        this.removeAll();            
        fenetre.panelPret = new PanelPret(fenetre);
        fenetre.add(fenetre.panelPret);
        fenetre.repaint();
    }
    
    // confirmation et suppression d'un média
    public void supprimerEmprunt(int id)
    {
        // demande de confirmation
        JOptionPane d = new JOptionPane();
        int retour = d.showConfirmDialog(fenetre, "Etes vous sur de vouloir supprimer cet emprunt?", "Suppression", JOptionPane.OK_CANCEL_OPTION); 
       
        // ok : 0 -- annuler : 2 -- close : -1
        // suppression + actualisation de la page
        if(retour==0)
        {
            
            EmpruntDAO emprunt = new EmpruntDAO();
            emprunt.delete(id);
            fenetre.panelPret = new PanelPret(fenetre);
            fenetre.add(fenetre.panelPret);
            fenetre.repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) 
    {

    }
    
    
    @Override
    public void keyPressed(KeyEvent ke) {}

    
    public class TestThread extends Thread {
        PanelPret panel;
        public TestThread(PanelPret panel){
            this.panel=panel;

        }
        
        @Override
        public void run(){
          existThread = true;
            try {
                 this.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(PanelPret.class.getName()).log(Level.SEVERE, null, ex);
            }
          existThread = false;
          panel.afficherListe();
          
        }       
      }
    
    @Override
    // autocompletion emprunteur - fct ajouter pret
    public void keyReleased(KeyEvent ke) 
    {
        if(ke.getSource().equals(champMedia)){
            TestThread thread = null ;
            if(!existThread)
            {
                thread = new TestThread(this);        
                thread.start();
            }
        }
        else super.keyReleased(ke);
    }
    
    
    public void afficherListe(){
        liste.removeAll();
        listeDeroulante.setVisible(false);
        listeMediaCorres.removeAllElements();
        List<Media> listeMedia = (new MediaDAO()).findAllAuto(champMedia.getText()); 
        for(Media m : listeMedia)
        {
            listeMediaCorres.addElement(m);
        }        
        liste.revalidate();
        
        if (listeMediaCorres.isEmpty())
            listeDeroulante.setVisible(false);
        else
        listeDeroulante.setVisible(true);              
   }

   
   
    @Override
    public void mouseClicked(MouseEvent arg0) {
       
        // selectionner un auteur dans la liste autocompletion (ajout)
        if(arg0.getSource().equals(liste))
        {
            // si double-clique
            if (arg0.getClickCount() == 2)
            {                
                // on vide la liste et on la cache
                liste.removeAll();
                listeDeroulante.setVisible(false);
                // on insere l'élément sélectionné dans le champ de titre
                champMedia.setText(liste.getSelectedValue().getTitre());
                mediaPret = liste.getSelectedValue();
                champEmprunteur.setEnabled(true);
            }
        }
    }
    
    public void MAJTableau(boolean comboPage){
            String recherche="";
            String etat="";
            int page=1 ;
            
           this.remove(tableauDeroulant);
           if (!barreRecherche.getText().equals("Rechercher par titre ou emprunteur"))
                recherche = barreRecherche.getText();
                    
            
           if (!combo1.getSelectedItem().equals("Tous les états"))
               etat=(String)combo1.getSelectedItem();
           
            if (comboPage){
               page = (int)combo4.getSelectedItem();
           }                  
             
           tableauDeroulant=initialiserTableau(new TableModelPret(recherche, etat));
           int nblignes = new EmpruntDAO().compterLignesRecherche(recherche, etat);
           MAJcombo4(nblignes,page);
           responsivePanel();
           this.add(tableauDeroulant);
           this.repaint();
           
           this.updateUI();
    }

    
    
}
