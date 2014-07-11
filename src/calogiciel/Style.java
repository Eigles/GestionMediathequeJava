/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package calogiciel;

import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author zemouri.cdi01
 * blanc:#FFFFFF
 * rouge:#C800000
 * noir:#000000
 */
public class Style {
    private FenetrePrincipale fenetrePrincipale;
    private PanelStructure panelStructure;
    private PanelAccueil panelAccueil;
    private PanelEnTete panelEnTete;
    private PanelMenu panelMenu;
    String bleu = "#D7F5FF";
    String marron = "#41240C";
    String rouge = "#A61B1A";
    String blanc = "#FFFFFF";
    public Style()
    {
    }
    public void getStylefenetrePrincipale(FenetrePrincipale fenetre)
    {
        this.fenetrePrincipale = fenetre;
        /*fenetre principale*/
        fenetrePrincipale.getContentPane().setBackground(Color.decode(rouge));
    }
    public void getStylePanelEnTete(PanelEnTete panelEnTete)
    {
        this.panelEnTete = panelEnTete;
        this.panelEnTete.setBackground(Color.decode(marron));
        this.panelEnTete.getLabelTitre().setFont(new Font("Lato",Font.ITALIC,48));
        this.panelEnTete.getLabelTitre().setForeground(Color.decode(rouge));
        this.panelEnTete.getLabelTitre().updateUI();
        this.panelEnTete.getImageAccueil().setForeground(Color.decode(blanc));
    }
    public void getStylePanelAcceuil(PanelAccueil panelAccueil)
    {
         this.panelAccueil = panelAccueil;
         /*panel acceuil*/
         this.panelAccueil.setBackground(Color.decode(rouge));
         /*panel acceuil boutton Mediathèque*/
         this.panelAccueil.getMediatheque().setBackground(Color.decode(marron));
         this.panelAccueil.getMediatheque().setFont(new Font("Lato",Font.ITALIC,30));
         this.panelAccueil.getMediatheque().setForeground(Color.decode(bleu));
         /*panel acceuil boutton Utilisateur*/
         this.panelAccueil.getUtilisateur().setBackground(Color.decode(marron));
         this.panelAccueil.getUtilisateur().setFont(new Font("Lato",Font.ITALIC,30));
         this.panelAccueil.getUtilisateur().setForeground(Color.decode(bleu));
    }
    public void getStylePanelStructure(PanelStructure structure)
    {
        this.panelStructure = structure;
        /*panel Structure*/
        this.panelStructure.setBackground(Color.decode(rouge));
    }
    public void getStylePanelMenu(PanelMenu panelMenu)
    {
        this.panelMenu = panelMenu;
        this.panelMenu.setBackground(Color.decode(rouge));
        this.panelMenu.getJpan().setBackground(Color.decode(rouge));
        this.panelMenu.getJlabtext().setBackground(Color.decode(rouge));
        this.panelMenu.getJlabtext().setFont(new Font("Lato",Font.ITALIC,30));
        this.panelMenu.getJlabtext().setForeground(Color.decode(bleu));
        /*boutton */
        this.panelMenu.bouton1.setBackground(Color.decode(rouge));
        this.panelMenu.bouton1.setForeground(Color.decode(bleu));
        this.panelMenu.bouton1.setFont(new Font("Lato",Font.ITALIC,30));
        this.panelMenu.bouton2.setBackground(Color.decode(rouge));
        this.panelMenu.bouton2.setForeground(Color.decode(bleu));
        this.panelMenu.bouton2.setFont(new Font("Lato",Font.ITALIC,30));
        this.panelMenu.bouton3.setBackground(Color.decode(rouge));
        this.panelMenu.bouton3.setFont(new Font("Lato",Font.ITALIC,30));
        this.panelMenu.bouton3.setForeground(Color.decode(bleu));
    }
    public void getStyleBouttonSelected(PanelMenu panelMenu)
    {
        this.panelMenu = panelMenu;
        if(this.panelMenu.getBouton1().isFocusOwner())
        {
            this.panelMenu.getBouton1().setBackground(Color.decode("#A60000"));
        }
        else
        {
            this.panelMenu.getBouton1().setBackground(Color.decode("#C800000"));
        }
        if(this.panelMenu.getBouton2().isFocusOwner())
        {
            this.panelMenu.getBouton2().setBackground(Color.decode("#A60000"));
        }
        else
        {
            this.panelMenu.getBouton2().setBackground(Color.decode("#C800000"));
        }
        if(this.panelMenu.getBouton3().isFocusOwner())
        {
            this.panelMenu.getBouton3().setBackground(Color.decode("#A60000"));
        }
        else
        {
            this.panelMenu.getBouton3().setBackground(Color.decode("#C800000"));
        }
    }
}
