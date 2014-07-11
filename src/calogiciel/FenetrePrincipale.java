/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package calogiciel;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.bind.Marshaller.Listener;
import pkginterface.mvc.IVue;
import pkginterface.mvc.Modele;
//import pkginterface.mvc.IVue;
//import pkginterface.mvc.Modele;

/**
 *
 * @author zemouri.cdi01
 * marron:#4E3D28
 * rouge: #970000
 * blanc: #FEFEE0
 */
public class FenetrePrincipale extends JFrame implements IVue{
    protected Listener listener;
    PanelEnTete panelEnTete;
    PanelAccueil panelAccueil;
    PanelMenu panelMenu;
    PanelPret panelPret;
    PanelMedia panelMedia;
    PanelAbonnes panelAbonnes;
    PanelSympathisant panelSympathisant;
    PanelAdherent panelAdherent;
    PanelAjoutPersonne panelAjoutPersonne;
    Style style;
    JPanel panelCourant;
    
    public FenetrePrincipale()
    {
        this.setTitle("Gestion Clés d'asie");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1620,850);
        this.setMaximumSize(new Dimension(1920, 1080));
        /*Ajout des panels*/
        panelEnTete = new PanelEnTete(this); 
        panelAccueil = new PanelAccueil(this);
        /*CSS*/
        style = new Style();
        this.style.getStylefenetrePrincipale(this);
        this.add(panelEnTete);
        this.add(panelAccueil);
        this.setVisible(true);
        ComponentAdapter componentAdapter = new ComponentAdapter() { public void componentResized(ComponentEvent e) {
                // This is only called when the user releases the mouse button.
                /*Panel en tete*/
                if(panelEnTete != null)
                {
                    panelEnTete.responsivePanel();
                    panelEnTete.updateUI();
                    panelEnTete.revalidate();
                }
                if(panelAccueil != null)
                {
                    panelAccueil.responsivePanel();
                    panelAccueil.updateUI();
                    panelAccueil.revalidate();
                }
                if(panelMenu != null)
                {
                    panelMenu.responsivePanel();
                    panelMenu.updateUI();
                    panelMenu.revalidate();
                }
                if(panelMedia != null)
                {
                    panelMedia.responsivePanel();
                    panelMedia.updateUI();
                    panelMedia.revalidate();
                }
                if(panelAdherent != null)
                {
                    panelAdherent.responsivePanel();
                    panelAdherent.updateUI();
                    panelAdherent.revalidate();
                }
                if(panelSympathisant != null)
                {
                    panelSympathisant.responsivePanel();
                    panelSympathisant.updateUI();
                    panelSympathisant.revalidate();
                }
                if(panelPret != null)
                {
                    panelPret.responsivePanel();
                    panelPret.updateUI();
                    panelPret.revalidate();
                }
                if(panelAjoutPersonne != null)
                {
                    panelAjoutPersonne.responsivePanel();
                    panelAjoutPersonne.updateUI();
                    panelAjoutPersonne.revalidate();
                }
            }
        };
        this.getRootPane().addComponentListener(componentAdapter);
    }
    
   

    /*Taille de la fenetre mere*/
    public int WidthEcran(double taille)
    {
        int width = (int)(this.getWidth()*taille);
        return width; 
    }
    /*permet de determiner la hauteur voulu mettre en % exemple 0.10 pour 10%*/
    public int HeightEcran(double taille)
    {
        int height = (int)(this.getHeight()*taille);
        return height;
    }
    
    @Override
        public void setModel(Modele modele) {
        //this.modele = modele;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
        public void addListener(Listener listener) {
        this.listener = listener;
    }

    @Override
        public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
