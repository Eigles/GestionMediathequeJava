/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package calogiciel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author martineau.cdi01
 */
public class PanelMenu extends JPanel implements ActionListener{
    private int height,width,margeCote,heightDepart,widthjpan;
    private FenetrePrincipale fenetrePrincipale;
    private Style style;
    protected JButton bouton1,bouton2,bouton3;
    private JLabel jlabtext,jlabimage;
    private JPanel jpan;
    
    
    public PanelMenu (FenetrePrincipale fenetre,String lienIcon,String nomJlabel,String nomBoutton1,String nomBouton2){
        init(fenetre,lienIcon,nomJlabel);
        //Création des boutons
        bouton1.setText(nomBoutton1);
        bouton2.setText(nomBouton2);
        //Ajout des composants au panel
        this.add(bouton1);
        this.add(bouton2); 
        this.setVisible(true); 
    }
    public PanelMenu(FenetrePrincipale fenetre,String lienIcon,String nomJlabel,String nomBoutton1,String nomBouton2,String nomBouton3){
        init(fenetre,lienIcon,nomJlabel);
        //Création des boutons
        bouton1.setText(nomBoutton1);
        bouton2.setText(nomBouton2);
        bouton3.setText(nomBouton3);
        //Ajout des composants au panel
        this.add(bouton1);
        this.add(bouton2);
        this.add(bouton3);
        this.setVisible(true);
    }

    public void init(FenetrePrincipale fenetre,String lienIcon,String nomJlabel)
    {
        this.fenetrePrincipale = fenetre;
        style = new Style();
        this.setMinimumSize(new Dimension(1000,100));
        this.setLayout(null);
        //Création du panel contenant l'image et le label
        jpan = new JPanel();
        jpan.setLayout(null);
        ImageIcon icon = new ImageIcon(lienIcon);
        jlabimage = new JLabel(icon);
        bouton1 = new JButton();
        bouton2 = new JButton();
        bouton3 = new JButton();
        //Création du panel contenant l'image et le label
        jlabtext= new JLabel (nomJlabel);
        this.style.getStylePanelMenu(this);
        /*add*/
        jpan.add(jlabimage);
        jpan.add(jlabtext);
        this.add(jpan);
        
        /*Action Listener*/    
        bouton1.addActionListener(this);
        bouton2.addActionListener(this);
        bouton3.addActionListener(this);
        /*Positionnement responsive*/
        this.responsivePanel();
    }
    
    public JLabel getJlabtext() {
        return jlabtext;
    }

    public void setJlabtext(JLabel jlabtext) {
        this.jlabtext = jlabtext;
    }

    public JPanel getJpan() {
        return jpan;
    }

    public void setJpan(JPanel jpan) {
        this.jpan = jpan;
    }

    public JButton getBouton1() {
        return bouton1;
    }

    public void setBouton1(JButton bouton1) {
        this.bouton1 = bouton1;
    }

    public JButton getBouton2() {
        return bouton2;
    }

    public void setBouton2(JButton bouton2) {
        this.bouton2 = bouton2;
    }

    public JButton getBouton3() {
        return bouton3;
    }

    public void setBouton3(JButton bouton3) {
        this.bouton3 = bouton3;
    }
 
    public void responsivePanel()
    {
        int widthImage,widthLabelText,widthBoutton,departBoutton2,departBoutton3;
        /*Taille panel Menu*/
        height = this.fenetrePrincipale.HeightEcran(0.08);
        heightDepart = this.fenetrePrincipale.HeightEcran(0.12);
        margeCote = this.fenetrePrincipale.WidthEcran(0.00);
        width = (this.fenetrePrincipale.WidthEcran(1)-(2*margeCote));
        this.setBounds(margeCote, heightDepart ,width, height);
        /*Taille panel Bibliotheque*/
        widthjpan = (int)(this.getWidth()*0.30);
        jpan.setBounds(0,0,widthjpan,height);
        widthImage = (int)(widthjpan*0.40);
        jlabimage.setSize(widthImage, height);
        widthLabelText = (int)(widthjpan*0.60);
        jlabtext.setBounds(widthImage,0,widthLabelText,height);
        /*Taille boutton*/
        widthBoutton = (int)(jpan.getWidth()*0.45);
        bouton1.setBounds(widthjpan,0,widthBoutton,height);
        departBoutton2 = (int)(widthjpan+widthBoutton);
        bouton2.setBounds(departBoutton2,0,widthBoutton,height);
        departBoutton3 = (int)(widthjpan+(widthBoutton*2));
        bouton3.setBounds(departBoutton3,0,widthBoutton,height);
        
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
       if (ae.getSource().equals(bouton1)){
           this.style.getStyleBouttonSelected(this);
           if(jlabtext.getText().equalsIgnoreCase("Médiathèque"))
           {
                fenetrePrincipale.panelMedia = new PanelMedia(fenetrePrincipale);              
                fenetrePrincipale.add(fenetrePrincipale.panelMedia);
                fenetrePrincipale.panelMedia.updateUI();
           }
           else if(jlabtext.getText().equalsIgnoreCase("Utilisateur"))
           {
                fenetrePrincipale.panelSympathisant = new PanelSympathisant(fenetrePrincipale);             
                fenetrePrincipale.add(fenetrePrincipale.panelSympathisant);
                fenetrePrincipale.panelSympathisant.updateUI();
           }
           fenetrePrincipale.repaint();
       }
       if (ae.getSource().equals(bouton2)){     
           this.style.getStyleBouttonSelected(this);
           if(jlabtext.getText().equalsIgnoreCase("Médiathèque"))
           {
                fenetrePrincipale.panelPret = new PanelPret(fenetrePrincipale);          
                fenetrePrincipale.add(fenetrePrincipale.panelPret); 
                fenetrePrincipale.panelPret.updateUI();
           }
           else if(jlabtext.getText().equalsIgnoreCase("Utilisateur"))
           {
                fenetrePrincipale.panelAdherent = new PanelAdherent(fenetrePrincipale);
                fenetrePrincipale.add(fenetrePrincipale.panelAdherent);
                fenetrePrincipale.panelAdherent.updateUI();
           }
           fenetrePrincipale.repaint();
       }
       if (ae.getSource().equals(bouton3)){
           this.style.getStyleBouttonSelected(this);
           if(jlabtext.getText().equalsIgnoreCase("Médiathèque"))
           {
               fenetrePrincipale.remove(fenetrePrincipale.panelCourant);
           }
           else if(jlabtext.getText().equalsIgnoreCase("Utilisateur"))
           {
                   fenetrePrincipale.panelAbonnes = new PanelAbonnes(fenetrePrincipale);
                   fenetrePrincipale.add(fenetrePrincipale.panelAbonnes);
                   fenetrePrincipale.panelAbonnes.updateUI();
           }
           fenetrePrincipale.repaint();
    }
    
    }
}
