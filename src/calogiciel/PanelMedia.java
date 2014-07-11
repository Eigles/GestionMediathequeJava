/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package calogiciel;

import Modele.Auteur;
import Modele.DAO.AuteurDAO;
import Modele.DAO.EtatDAO;
import Modele.DAO.GenreDAO;
import Modele.DAO.MediaDAO;
import Modele.DAO.TypeMediaDAO;
import Modele.Etat;
import Modele.Genre;
import Modele.Media;
import Modele.TypeMedia;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import static java.awt.Font.PLAIN;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author poisson.cdi01
 */
public class PanelMedia extends PanelStructure implements ActionListener, ItemListener {

    private JPanel panel, panelVertical;
    private FenetrePrincipale fenetre;
    int height,width, margeCote;
    private JLabel labelTitreMedia,labelEmprunteur,labelGenre,labelLocalisation,labelEtat,labelType,labelIsbn,labelPhoto,labelDescription,zoneApercu,labelResume,labelAnneeParution,labelTome,labelNbPages,labelFormat,labelMaisonEdition,labelEnter;
    private JTextField champTitreMedia,champLocalisation,champIsbn,champPhoto,champAnneeParution,champDescription,champTome,champNbPages,champFormat,champMaisonEdition;
    private JTextArea champResume;
    JScrollPane jScrollPane1;
    private JComboBox champAuteur,champGenre,champEtat,champType;
    private ImageIcon icon;
    private JButton boutonValiderAjout,boutonChoisirFichier,boutonValiderModif,boutonAjoutType,boutonAjoutGenre,boutonAjoutAuteur;
    int idModif=0;
    private JButton boutonRetour;
    protected URLConnection urlConnection;
    protected InputStream httpStream;
        
    public PanelMedia(FenetrePrincipale fenetre)
    {   
        super(fenetre, new TableModelMedia(1,"",null,2,""));
        this.fenetre = fenetre;
        labelTitre.setText("Liste de médias");
        combo1.addItem("Tous les genres");
        List<Genre> listeCombo1 = (new GenreDAO()).findAll();
        for(Genre obj : listeCombo1)
        {
            combo1.addItem(obj);
        }
        combo1.addItemListener(this);
        this.add(combo2);
        combo2.addItem("Tous les auteurs");
        List<Auteur> listeCombo2 = (new AuteurDAO()).findAll();
        for(Auteur obj : listeCombo2)
        {
            combo2.addItem(obj);
        } 
        combo2.addItemListener(this);
        this.add(combo3);
        combo3.addItem("Toutes les disponibilités");
        List<Object> listeCombo3 = findAllDispo();
        for(Object obj : listeCombo3)
        {
            combo3.addItem(obj);
        } 
        combo3.addItemListener(this);
        MAJcombo4(new MediaDAO().compterLignes(),1);
        
        barreRecherche.setText("Rechercher par titre");
        
        boutonAjout.setText("Ajouter Média"); 
        labelPage.setText("Page n° ");
        this.add(combo1);
        this.add(combo2);
        this.add(combo3);
        this.add(combo4);
        this.add(labelPage);
        this.add(pagePlus);
        this.add(pageMoins);
        this.responsivePanel();
        this.add(tableauDeroulant);
        this.setVisible(true);
    }    
        
     // formulaire d'ajout d'un media
     public void afficherFormulaire()
     {
        this.removeAll();
        JPanel panelAjout = new JPanel();
        panelAjout.setLayout(new GridLayout(15, 2));  
        labelTitre.setText("Ajouter un Média"); 
        
        // labels des infos du média
        labelTitreMedia = new JLabel("Titre du média * : ");        
        labelEmprunteur = new JLabel("Auteur * : ");        
        labelGenre = new JLabel("Genre * : ");        
        labelLocalisation = new JLabel("Localisation * : ");
        labelEtat = new JLabel("Etat * : ");
        labelType = new JLabel("Type * : ");
        labelIsbn = new JLabel("ISBN : ");
        labelEnter = new JLabel("Appuyer sur entrée pour lancer la recherche");
        labelPhoto = new JLabel("Photo : ");
        labelDescription = new JLabel("Description : ");
        labelResume = new JLabel("Resume : ");
        labelAnneeParution = new JLabel("Année de parution : ");
        labelTome = new JLabel("Tome : ");
        labelNbPages = new JLabel("Nombre de pages : ");
        labelFormat = new JLabel("Format : ");
        labelMaisonEdition = new JLabel("Maison d'édition : ");
        
        // champ à compléter par le média
        champTitreMedia = new JTextField();
        champAuteur = new JComboBox();
        champAuteur.addItem("Auteur");
        List<Auteur> listeAuteur = (new AuteurDAO()).findAll();
        for(Auteur obj : listeAuteur)
        {
            champAuteur.addItem(obj);
        }        
        champGenre = new JComboBox();  
        champGenre.addItem("Genre");
        List<Genre> listeGenre = (new GenreDAO()).findAll();
        for(Genre obj : listeGenre)
        {
            champGenre.addItem(obj);
        }       
        champLocalisation = new JTextField();
        champLocalisation.addMouseListener(this);
        champEtat = new JComboBox();
        champEtat.addItem("Etat");
        List<Etat> listeEtat = (new EtatDAO()).findAll();
        for(Etat obj : listeEtat)
        { 
            champEtat.addItem(obj);
        }        
        champType = new JComboBox();
        champType.addItem("Type");
        List<TypeMedia> listeType = (new TypeMediaDAO()).findAll();
        for(TypeMedia obj : listeType)
        {
            champType.addItem(obj);
        }
        champIsbn = new JTextField();
        champIsbn.addKeyListener(this);
                
        JPanel panelFile = new JPanel();
        panelFile.setLayout(new FlowLayout(FlowLayout.LEFT)); 
        champPhoto = new JTextField("",20);
        champPhoto.setEditable(false);
        champPhoto.setPreferredSize(new Dimension(150,25));
        panelFile.add(champPhoto); 
        boutonChoisirFichier = new JButton("Parcourir ...");
        boutonChoisirFichier.setPreferredSize(new Dimension(100,25));
        panelFile.add(boutonChoisirFichier);
        boutonChoisirFichier.addActionListener(this);
        boutonRetour= new JButton("Retour");
        champDescription = new JTextField();        
        champAnneeParution = new JTextField();
        champTome = new JTextField();
        champNbPages = new JTextField();
        champFormat = new JTextField();
        champMaisonEdition = new JTextField();
        champResume = new JTextArea(5,50);
        jScrollPane1 = new JScrollPane(champResume);
        boutonRetour.addActionListener(this); 
        boutonValiderAjout = new JButton("Valider");
        boutonValiderAjout.addActionListener(this);    
        
        // bouton d'ajout des entités auteur, genre et type       
        boutonAjoutAuteur = new JButton("Autre");
        boutonAjoutAuteur.addActionListener(this);  
        boutonAjoutGenre = new JButton("Autre");
        boutonAjoutGenre.addActionListener(this);  
        boutonAjoutType = new JButton("Autre");
        boutonAjoutType.addActionListener(this);  
        
        zoneApercu = new JLabel();
        
        champEtat.setBackground(Color.white);
        champType.setBackground(Color.white);
        champAuteur.setBackground(Color.white);
        champGenre.setBackground(Color.white);
        panelAjout.add(labelTitreMedia);
        panelAjout.add(champTitreMedia);  
        panelAjout.add(labelIsbn);        
        panelAjout.add(champIsbn);
        panelAjout.add(labelEmprunteur);
        panelAjout.add(champAuteur);
        panelAjout.add(labelGenre); 
        panelAjout.add(champGenre);
        panelAjout.add(labelLocalisation);
        panelAjout.add(champLocalisation);
        panelAjout.add(labelEtat);
        panelAjout.add(champEtat);
        panelAjout.add(labelType);
        panelAjout.add(champType);
        panelAjout.add(labelDescription);
        panelAjout.add(champDescription);
        panelAjout.add(labelResume);
        panelAjout.add(jScrollPane1);        
        panelAjout.add(labelAnneeParution);
        panelAjout.add(champAnneeParution);
        panelAjout.add(labelTome);
        panelAjout.add(champTome);
        panelAjout.add(labelNbPages);
        panelAjout.add(champNbPages);
        panelAjout.add(labelFormat);
        panelAjout.add(champFormat);
        panelAjout.add(labelMaisonEdition);       
        panelAjout.add(champMaisonEdition);
        //panelAjout.add(labelPhoto);
        //panelAjout.add(panelFile); 
        
        zoneApercu.setBounds(880,50,300,300);
        labelTitre.setBounds(50,10,300,30);
        panelAjout.setBounds(50,50,800,460);
        boutonAjoutAuteur.setBounds(880,115,100,30);
        boutonAjoutGenre.setBounds(880,145,100,30);
        boutonAjoutType.setBounds(880,235,100,30);
        boutonRetour.setBounds(600,520,100,30);
        boutonValiderAjout.setBounds(750,520,100,30);
        labelEnter.setBounds((int)(this.getWidth()*(0.55)),85,300,30);
        
        //this.add(zoneApercu);
        this.add(labelTitre);
        this.add(panelAjout);
        this.add(boutonAjoutAuteur);
        this.add(boutonAjoutGenre);
        this.add(boutonAjoutType);
        this.add(boutonValiderAjout);
        this.add(boutonRetour);
        this.add(labelEnter);
        this.repaint();
        this.revalidate();
    }

    public List<Object> findAllDispo()
    {
        List<Object> liste = new ArrayList();
        liste.add("disponible");
        liste.add("indisponible");
        return liste;
    } 
   
    public void verifierDonnees(int action) 
    {       
        champTitreMedia.setBackground(Color.white);
        champAuteur.setBackground(Color.white);
        champGenre.setBackground(Color.white);
        champLocalisation.setBackground(Color.white);
        champEtat.setBackground(Color.white);
        champType.setBackground(Color.white);
        
        // si les champs obligatoires ne sont pas remplis
        if((champTitreMedia.getText().isEmpty())||(champLocalisation.getText().isEmpty())||(champAuteur.getSelectedItem().equals("Auteur"))
        ||(champGenre.getSelectedItem().equals("Genre"))||(champEtat.getSelectedItem().equals("Etat"))||(champType.getSelectedItem().equals("Type")))
        {       
            // vérification sur chaque champ et si non-rempli : apparait rouge
            if(champTitreMedia.getText().isEmpty()) champTitreMedia.setBackground(Color.red);
            if(champAuteur.getSelectedItem().equals("Auteur")) champAuteur.setBackground(Color.red);
            if(champGenre.getSelectedItem().equals("Genre")) champGenre.setBackground(Color.red);
            if(champLocalisation.getText().isEmpty()) champLocalisation.setBackground(Color.red);
            if(champEtat.getSelectedItem().equals("Etat")) champEtat.setBackground(Color.red);
            if(champType.getSelectedItem().equals("Type")) champType.setBackground(Color.red);
            JLabel erreur = new JLabel("Tous les champs en rouge ne sont pas remplis !");
            erreur.setForeground(Color.red);
            erreur.setBounds(100, 20, 300, 30);
            this.add(erreur);
            this.repaint();
        }
        else if((!Pattern.matches("[0-9]*", champAnneeParution.getText()))||(!Pattern.matches("[0-9]*", champNbPages.getText())))
        {
            if(!Pattern.matches("[0-9]*", champAnneeParution.getText()))
            {
                champAnneeParution.setBackground(Color.blue);
            }
            if(!Pattern.matches("[0-9]*", champNbPages.getText()))
            {
                champNbPages.setBackground(Color.blue);
            }
        }            
        else
        {                   
            // ajout d'un media
            String titre = champTitreMedia.getText() ;            
            Auteur auteur = (Auteur) champAuteur.getSelectedItem();
            Genre genre = (Genre) champGenre.getSelectedItem();            
            String localisation = champLocalisation.getText();
            Etat etat = (Etat) champEtat.getSelectedItem();  
            TypeMedia typeMedia = (TypeMedia) champType.getSelectedItem();
            String isbn = champIsbn.getText();
            String lienPhoto = champPhoto.getText();
            String description = champDescription.getText();
            int anneeParution = 0;
            if(!champAnneeParution.getText().isEmpty())
                anneeParution = Integer.parseInt(champAnneeParution.getText());
            String resume = champResume.getText();
            String tome = champTome.getText();
            int pages = 0;
            if(!champNbPages.getText().isEmpty())
                pages = Integer.parseInt(champNbPages.getText());
            String format = champFormat.getText();
            String maisonEdition = champMaisonEdition.getText();
    
            MediaDAO m = new MediaDAO();
            
            if (action == 1)
            {
                m.create(new Media(0,titre,auteur,genre,localisation,etat,isbn,lienPhoto,typeMedia,description,true,anneeParution,resume,tome,pages,format,maisonEdition));

                // affichage du message de reussite                     
                JOptionPane d = new JOptionPane();
                d.showMessageDialog(fenetre, "L'ajout a été effectué.", "information", JOptionPane.INFORMATION_MESSAGE);

                actualiser("");
            }
            if (action == 2)
            {
                System.out.println("action 2");
                m.update(new Media(idModif,titre,auteur,genre,localisation,etat,isbn,lienPhoto,typeMedia,description,true,anneeParution,resume,tome,pages,format,maisonEdition));

                // affichage du message de reussite
                JOptionPane d = new JOptionPane();
                d.showMessageDialog(fenetre, "La modification a été effectuée.", "information", JOptionPane.INFORMATION_MESSAGE);
            }
            
            this.repaint();
            
            // retour sur la liste des medias
            afficherTableauPret();
       }
    }
    
    public void afficherTableauPret(){
        this.removeAll();            
        fenetre.panelMedia = new PanelMedia(fenetre);
        fenetre.add(fenetre.panelMedia);
        fenetre.repaint();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
       if(ae.getSource().equals(boutonAjout))
       {
          afficherFormulaire();
          return;
       }
       
       if(ae.getSource().equals(boutonRetour))
       {
          afficherTableauPret();
          return;
       }
       
       if(ae.getSource().equals(boutonAjoutGenre))
       {
            // formulaire d'ajout
            JTextField champCode = new JTextField(5);
            JTextField champNom = new JTextField(25);
            JPanel formulaire = new JPanel();
            formulaire.add(new JLabel("Code * : "));
            formulaire.add(champCode);
            formulaire.add(Box.createHorizontalStrut(15));
            formulaire.add(new JLabel("Libellé * : "));
            formulaire.add(champNom);
            
            // pop up d'ajout 
            int result = JOptionPane.showConfirmDialog(null, formulaire, "Compléter le formulaire", JOptionPane.OK_CANCEL_OPTION);

            // si a validé
            if (result == JOptionPane.OK_OPTION)
            {
                GenreDAO genreDao = new GenreDAO();
                Genre genre = new Genre();
                try 
                {
                    // si au moins un champ obligatoire est vide
                    if(!(champCode.getText().equals(""))||(!(champNom.getText().equals(""))))
                    {
                        JOptionPane d = new JOptionPane();
                        d.showMessageDialog(fenetre, "Vous avez oublié un champ. L'ajout est annulé.", "information", JOptionPane.WARNING_MESSAGE);
                    }   
                    // vérification type code - en entier
                    else if(!Pattern.matches("[0-9]*", champCode.getText()))
                    {
                        JOptionPane d = new JOptionPane();
                        d.showMessageDialog(fenetre, "Les types entrés ne sont pas corrects.  L'ajout est annulé. ", "information", JOptionPane.WARNING_MESSAGE);
                    }
                    // le genre ou le code existe déjà
                    else if((genreDao.existerGenreLibelle(champNom.getText()))&&(genreDao.existerGenreCode(Integer.parseInt(champCode.getText()))))
                    {
                        JOptionPane d = new JOptionPane();
                        d.showMessageDialog(fenetre, "Le genre et/ou le code existe déjà.", "information", JOptionPane.WARNING_MESSAGE);
                    }
                    // si le libelle et le code n'existe pas - ajout du genre
                    else
                    {
                        genre.setCode(champCode.getText());
                        genre.setLibelle(champNom.getText());
                        genreDao.create(genre);
                        JOptionPane d = new JOptionPane();
                        d.showMessageDialog(fenetre, "L'ajout du genre a été effectué.", "information", JOptionPane.INFORMATION_MESSAGE);
                        // sélection dans la liste déroulante du nouveau genre ajouté
                        champGenre.removeAll();
                        List<Genre> listeGenre = (new GenreDAO()).findAll();
                        for(Genre obj : listeGenre)
                        {
                            champGenre.addItem(obj);
                            if(obj.getLibelle().equals(genre.getLibelle()))
                                champGenre.setSelectedItem(obj);
                        }
                    }
                }
                catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(PanelMedia.class.getName()).log(Level.SEVERE, null, ex);
                }           
            }           
       }
       if(ae.getSource().equals(boutonAjoutType))
       {
             // formulaire d'ajout
            JTextField champNom = new JTextField(25);
            JPanel formulaire = new JPanel();
            formulaire.add(Box.createHorizontalStrut(15));
            formulaire.add(new JLabel("Libellé d'un type * : "));
            formulaire.add(champNom);
            // pop up d'ajout 
            int result = JOptionPane.showConfirmDialog(null, formulaire, "Compléter le formulaire", JOptionPane.OK_CANCEL_OPTION);

            //si aucun champ n'est vide et que l'utilisateur a validé
            if (result == JOptionPane.OK_OPTION)
            {
                TypeMediaDAO typeDao = new TypeMediaDAO();
                TypeMedia type = new TypeMedia();
                try 
                {
                    // si le champ est vide
                    if (champNom.getText().equals(""))
                    {
                        JOptionPane d = new JOptionPane();
                        d.showMessageDialog(fenetre, "Vous n'avez pas rempli le champ. L'ajout est annulé. ", "information", JOptionPane.WARNING_MESSAGE);
                    } 
                    // sinon si le type existe déjà
                    else if(typeDao.existerType(champNom.getText()))
                    {
                        JOptionPane d = new JOptionPane();
                        d.showMessageDialog(fenetre, "Ce type existe déjà.", "information", JOptionPane.WARNING_MESSAGE);
                        
                    }
                    // sinon si le libelle n'existe pas - ajout du genre
                    else 
                    {
                        type.setLibelle(champNom.getText());
                        typeDao.create(type);
                        JOptionPane d = new JOptionPane();
                        d.showMessageDialog(fenetre, "L'ajout du type a été effectué.", "information", JOptionPane.INFORMATION_MESSAGE);
                        // sélection dans la liste déroulante du nouveau type ajouté
                        champType.removeAll();
                        List<TypeMedia> listeType = (new TypeMediaDAO()).findAll();
                        for(TypeMedia obj : listeType)
                        {
                            champType.addItem(obj);
                            if(obj.getLibelle().equals(type.getLibelle()))
                            {
                                champType.setSelectedItem(obj);
                            }
                        }
                    }
                }
                catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(PanelMedia.class.getName()).log(Level.SEVERE, null, ex);
                }           
            }         
       }
       
       if(ae.getSource().equals(boutonAjoutAuteur))
       {
            // formulaire d'ajout
            JTextField champNom = new JTextField(40);
            JTextField champPrenom = new JTextField(40);
            JTextField champDescription = new JTextField();
            JComboBox jour = new JComboBox();
            jour.addItem("jour");
            for(int i=1;i<=31;i++) {jour.addItem(i);}
            JComboBox mois = new JComboBox();
            mois.addItem("mois");
            for(int i=1;i<=12;i++) {mois.addItem(i);}
            JTextField annee = new JTextField();            
            JPanel formulaire = new JPanel();
            formulaire.setLayout(new GridLayout(4, 2));  
            formulaire.add(new JLabel("Nom * : "));
            formulaire.add(champNom);
            formulaire.add(new JLabel("Prenom * : "));
            formulaire.add(champPrenom);
            formulaire.add(new JLabel("Date de naissance : "));
            JPanel panDDN = new JPanel();
            panDDN.add(jour);
            panDDN.add(mois);
            panDDN.add(new JLabel("année : "));
            panDDN.add(annee);
            annee.setPreferredSize(new Dimension(200, 30));
            formulaire.add(panDDN);
            formulaire.add(new JLabel("Description : "));
            formulaire.add(champDescription);
            
            // pop up d'ajout 
            int result = JOptionPane.showConfirmDialog(null, formulaire, "Compléter le formulaire", JOptionPane.OK_CANCEL_OPTION);

            // si l'utilisateur a validé
            if ((result == JOptionPane.OK_OPTION))
            {                
                System.out.println("1");
                AuteurDAO auteurDao = new AuteurDAO();
                Auteur auteur = new Auteur();
                try 
                {
                    Calendar c = Calendar.getInstance();
                    int anneeActu = c.get(Calendar.YEAR);
                    //si l'annee est superieur à l'année actuelle on le fait passer dans la boucle type incorrect
                    if(Pattern.matches("[0-9](1,)", annee.getText()))
                    {
                         System.out.println("2");
                        if(Integer.parseInt(annee.getText())>=anneeActu ) 
                            annee.setText("annee");
                    }
                    
                    // si au moins un champ obligatoire est vide
                    if ((champNom.getText().equals(""))||(champPrenom.getText().equals("")))
                    {             
                         System.out.println("3");
                        JOptionPane d = new JOptionPane();
                        d.showMessageDialog(fenetre, "Vous avez oublié un champ, l'ajout est annulé", "information", JOptionPane.WARNING_MESSAGE);
                    } 
                    // vérification type du champ annee
                    else if(!Pattern.matches("[0-9]*", annee.getText()))
                    {
                        JOptionPane d = new JOptionPane();
                        d.showMessageDialog(fenetre, "Les types entrés ne sont pas corrects, l'ajout est annulé", "information", JOptionPane.WARNING_MESSAGE);
                    }
                    // l'auteur existe déjà (nom+prenom)
                    else if(auteurDao.existerAuteur(champNom.getText(),champPrenom.getText()))
                    {
                        JOptionPane d = new JOptionPane();
                        d.showMessageDialog(fenetre, "L'auteur existe déjà.", "information", JOptionPane.WARNING_MESSAGE);
                       
                    }
                    // si l'auteur (nom+prenom) n'existe pas - ajout de l'auteur                    
                    else 
                    {
                        auteur.setPrenom(champPrenom.getText());
                        auteur.setNom(champNom.getText());
                        auteur.setDescription(champDescription.getText());  
                        if(!(jour.getSelectedItem().equals("jour"))&&!(mois.getSelectedItem().equals("mois")))
                        {
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
                            String date1 = annee.getText()+"-"+mois.getSelectedItem()+"-"+jour.getSelectedItem();
                            java.util.Date d1  = simpleDateFormat.parse(date1);
                            java.sql.Date date = new java.sql.Date(d1.getTime());
                            auteur.setDateNaissance(date);
                        }
                        
                        auteurDao.create(auteur);
                        JOptionPane d = new JOptionPane();
                        d.showMessageDialog(fenetre, "L'ajout de l'auteur a été effectué.", "information", JOptionPane.INFORMATION_MESSAGE);
                        // sélection dans la liste déroulante du nouvel auteur ajouté
                        champAuteur.removeAll();
                        List<Auteur> listeAuteur = (new AuteurDAO()).findAll();
                        for(Auteur obj : listeAuteur)
                        {
                            champAuteur.addItem(obj);
                            if(obj.toString().equals(auteur.toString()))
                            {
                                champAuteur.setSelectedItem(obj);
                            }
                        }                        
                    }
                }
                catch (SQLException | ClassNotFoundException | ParseException ex) {
                    Logger.getLogger(PanelMedia.class.getName()).log(Level.SEVERE, null, ex);
                }        
            }            
       }
       if(ae.getSource().equals(boutonValiderAjout))
            {
                verifierDonnees(1);
            }  
       
       if(ae.getSource().equals(boutonValiderModif))
            {
                
                verifierDonnees(2);           
            }   
       
       if (ae.getSource().equals(boutonChoisirFichier))
       {
           JFileChooser choixfichier = new JFileChooser();
           int returnVal = choixfichier.showOpenDialog(this);
           if(returnVal == JFileChooser.APPROVE_OPTION)
           {
              String nomfichier = choixfichier.getSelectedFile().getAbsolutePath();
              champPhoto.setText(nomfichier);
              zoneApercu.setText("apercu!");  
              ImageIcon icon;
               icon = new ImageIcon(nomfichier);
              zoneApercu.setIcon(icon);
           } 
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
    
    // filtre
    @Override
    public void itemStateChanged(ItemEvent e) 
    {
        if (e.getStateChange()==ItemEvent.SELECTED)
        {
            boolean comboPage=false;
            if(e.getSource().equals(combo4))
                comboPage=true;
            MAJTableau(comboPage);}
    } 
    
    @Override
    public void keyReleased(KeyEvent e) 
    {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            //si le champ isbn est validé on remplit les champs automatiquement en instanciant un media aux infos completées
            if(e.getSource().equals(champIsbn))
            {
                icon = new ImageIcon("resources/2.gif");
                labelEnter.setText("");
                labelEnter.setIcon(icon);
                labelEnter.updateUI();
                Thread load = new Thread(new Onload(this));
                load.start();
            }
            
            //Si la touche entrée est tapée on ajoute les filtres
            if(e.getSource().equals(barreRecherche))
            {
                MAJTableau(false);
            }
            
        }
    }

    public JLabel getLabelEnter() {
        return labelEnter;
    }

    public void setLabelEnter(JLabel labelEnter) {
        this.labelEnter = labelEnter;
    }     
    
    public void MAJTableau(boolean comboPage){
            String recherche="";
            Auteur auteur =null;
            String genre="";
            int dispo=2;
            int page=1 ;
            
           this.remove(tableauDeroulant);
           if (!barreRecherche.getText().equals("Rechercher par titre"))
                recherche = barreRecherche.getText();
           
           if (!combo2.getSelectedItem().equals("Tous les auteurs"))
               auteur=(Auteur)combo2.getSelectedItem();
          
            
           if (!combo1.getSelectedItem().equals("Tous les genres"))
               genre=((Genre)combo1.getSelectedItem()).getCode();
           
           if (combo3.getSelectedItem().equals("disponible"))
                 dispo=1;           
               
           if (combo3.getSelectedItem().equals("indisponible"))
                dispo=0;
                         
           if (comboPage){
               page = (int)combo4.getSelectedItem();
           }
               
              
           tableauDeroulant=initialiserTableau(new TableModelMedia(page,genre, auteur ,dispo, recherche));
           int nblignes = new MediaDAO().compterLignesRecherche(genre, auteur, dispo, recherche);
           MAJcombo4(nblignes,page);
           responsivePanel();
           this.add(tableauDeroulant);
           this.repaint();
           
           this.updateUI();
    }
    
    // confirmation et suppression d'un média
    public void supprimerMedia(int id)
    {
        // demande de confirmation
        JOptionPane d = new JOptionPane();
        int retour = d.showConfirmDialog(fenetre, "Etes vous sur de vouloir supprimer", "Suppression", JOptionPane.OK_CANCEL_OPTION); 
       
        // ok : 0 -- annuler : 2 -- close : -1
        // suppression + actualisation de la page
        if(retour==0)
        {
            MediaDAO media = new MediaDAO();
            media.delete(id);
            fenetre.panelMedia = new PanelMedia(fenetre);
            fenetre.add(fenetre.panelMedia);
            fenetre.repaint();
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent arg0) 
    {
        // si clique sur une ligne du tableau
        if(arg0.getSource().equals(tableau))
        {
            //si double-clique
            if (arg0.getClickCount() == 2)
            {
               Point p = arg0.getPoint();

               int row = tableau.rowAtPoint(p);
               int column = tableau.convertColumnIndexToModel(tableau.columnAtPoint(p));
               if (row >= 0 && column >= 0) 
               {
                   // affiche fiche média du média de la ligne sélectionnée
                   Media media = (Media)((TableModelMedia)tableau.getModel()).getObject(row);
                   afficherFiche(media);
                }   
            }
        }
        // si on clique sur le champ localisation - remplissage automatique
        if(arg0.getSource().equals(champLocalisation))
        {
            champLocalisation.setText(getLocalisation());
        }
    }
        
    public void css(JComponent component, JPanel panel, String police, int taille, Color color, int margeG, int margeH, int largeur, int hauteur){      
        
        component.setForeground(color);
        component.setFont(new Font(police,PLAIN,taille));
        component.setBounds(margeG,margeH,largeur,hauteur);
        panel.add(component);
        
    }
    public void css(JPanel component, JPanel panel, String police, int taille, Color color, int margeG, int margeH, int largeur, int hauteur){      
        
        component.setForeground(Color.BLUE);
        component.setBackground(color);
        component.setFont(new Font(police,PLAIN,taille));
        component.setBounds(margeG,margeH,largeur,hauteur);
        panel.add(component);
        
    }
    
    public void afficherFiche(Media media){
        this.removeAll();
        panelVertical = new JPanel();
        panelVertical.setBackground((fenetre.panelCourant.getBackground()).darker());
        panelVertical.setLayout(new BoxLayout(panelVertical, BoxLayout.PAGE_AXIS));
        panel = new JPanel();
        panel.setBackground((fenetre.panelCourant.getBackground()).brighter());
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        JLabel champGenre,champType,champEtat,champAcquisition,champDescription,champAuteur,champLocalisation,champTitreMedia,champIsbn,labelAuteur,labelAcquisition,champResume,champAnneeParution,champTome,champNbPages,champFormat,champMaisonEdition;
        JLabel labelTitre = new JLabel("Fiche de média"); 
        labelTitreMedia = new JLabel("Titre du média : ");        
        labelAuteur = new JLabel("Auteur : ");        
        labelGenre = new JLabel("Genre : ");      
        labelType = new JLabel("Type : ");
        labelLocalisation = new JLabel("Localisation : "); 
        labelIsbn = new JLabel("ISBN : ");        
        labelEtat = new JLabel("Etat : ");
        labelDescription = new JLabel("Description : ");     
        labelPhoto = new JLabel("Photo : "); 
        labelResume = new JLabel("Resume : ");
        labelAnneeParution = new JLabel("Année de parution : ");
        labelTome = new JLabel("Tome : ");
        labelNbPages = new JLabel("Nombre de pages : ");
        labelFormat = new JLabel("Format : ");
        labelMaisonEdition = new JLabel("Maison d'édition : ");
        
        if((media.getTitre())==null)
            champTitreMedia = new JLabel(" ");
            else
                champTitreMedia = new JLabel(media.getTitre());
        if((media.getAuteur())==null)
            champAuteur = new JLabel(" ");
            else
                champAuteur = new JLabel(media.getAuteur().getNom()+" "+media.getAuteur().getPrenom());
        if((media.getGenre())==null)
            champGenre = new JLabel(" "); 
            else
                champGenre = new JLabel(media.getGenre().getLibelle()); 
        if((media.getTypeMedia())==null)
            champType = new JLabel(" ");
            else
                champType = new JLabel(media.getTypeMedia().getLibelle());
        if((media.getLocalisation())==null)
            champLocalisation = new JLabel(" ");  
            else
                champLocalisation = new JLabel(media.getLocalisation());        
        if((media.getIsbn()).equalsIgnoreCase(""))
            champIsbn = new JLabel(" ");
            else
                champIsbn = new JLabel(""+media.getIsbn());
        if((media.getEtat())==null)
            champEtat = new JLabel(" ");
            else
                champEtat = new JLabel(media.getEtat().getLibelle());
        champAcquisition = new JLabel(" "); 
        if((media.getDescription())==null)            
            champDescription = new JLabel(" ");
            else
                champDescription = new JLabel(media.getDescription());

        champResume = new JLabel(media.getResume());
        champAnneeParution = new JLabel(""+media.getAnneeParution());
        champTome = new JLabel(media.getTome());
        champNbPages = new JLabel(""+media.getPages());
        champFormat = new JLabel(media.getFormat());
        champMaisonEdition = new JLabel(media.getMaisonEdition());        
        
        String nomfichier;
        zoneApercu = new JLabel();
        if(!(media.getLienPhoto()==null))
        {    
            nomfichier = media.getLienPhoto(); 
            ImageIcon icon = new ImageIcon(nomfichier);
            zoneApercu.setIcon(icon);            
        }
        else
        {
            zoneApercu.setText("Pas d'image pour ce média");
        }
        
        css(labelTitreMedia,panelVertical,"Times New Roman",20,Color.darkGray,0,0,0,0);
        css(new JLabel("  "),panelVertical,"Times New Roman",20,Color.darkGray,0,0,0,0);        
        css(labelAuteur,panelVertical,"Times New Roman",20,Color.darkGray,0,0,0,0);
        css(labelGenre,panelVertical,"Times New Roman",20,Color.darkGray,0,0,0,0);
        css(labelType,panelVertical,"Times New Roman",20,Color.darkGray,0,0,0,0);
        css(labelLocalisation,panelVertical,"Times New Roman",20,Color.darkGray,0,0,0,0);
        css(labelIsbn,panelVertical,"Times New Roman",20,Color.darkGray,0,0,0,0);
        css(labelEtat,panelVertical,"Times New Roman",20,Color.darkGray,0,0,0,0);
        css(labelDescription,panelVertical,"Times New Roman",20,Color.darkGray,0,0,0,0);       
        css(labelAnneeParution,panelVertical,"Times New Roman",20,Color.darkGray,0,0,0,0);
        css(labelTome,panelVertical,"Times New Roman",20,Color.darkGray,0,0,0,0);
        css(labelNbPages,panelVertical,"Times New Roman",20,Color.darkGray,0,0,0,0);
        css(labelFormat,panelVertical,"Times New Roman",20,Color.darkGray,0,0,0,0);        
        css(labelMaisonEdition,panelVertical,"Times New Roman",20,Color.darkGray,0,0,0,0);
        css(labelResume,panelVertical,"Times New Roman",20,Color.darkGray,0,0,0,0);
        
        css(champTitreMedia,panel,"Times New Roman",20,Color.darkGray,0,0,0,0);
        champTitreMedia.setFont(new Font("Times New Roman",Font.BOLD,25));
        css(new JLabel("  "),panel,"Times New Roman",20,Color.darkGray,0,0,0,0);
        css(champAuteur,panel,"Times New Roman",20,Color.darkGray,0,0,0,0);
        css(champGenre,panel,"Times New Roman",20,Color.darkGray,0,0,0,0);
        css(champType,panel,"Times New Roman",20,Color.darkGray,0,0,0,0);
        css(champLocalisation,panel,"Times New Roman",20,Color.darkGray,0,0,0,0);
        css(champIsbn,panel,"Times New Roman",20,Color.darkGray,0,0,0,0);
        css(champEtat,panel,"Times New Roman",20,Color.darkGray,0,0,0,0);
        css(champDescription,panel,"Times New Roman",20,Color.darkGray,0,0,0,0);
        css(champAnneeParution,panel,"Times New Roman",20,Color.darkGray,0,0,0,0);
        css(champTome,panel,"Times New Roman",20,Color.darkGray,0,0,0,0);
        css(champNbPages,panel,"Times New Roman",20,Color.darkGray,0,0,0,0);
        css(champFormat,panel,"Times New Roman",20,Color.darkGray,0,0,0,0);
        css(champMaisonEdition,panel,"Times New Roman",20,Color.darkGray,0,0,0,0);
        css(champResume,panel,"Times New Roman",20,Color.darkGray,0,0,0,0);
        
        
        css(labelTitre,this,"Times New Roman",30,Color.GRAY,fenetre.WidthEcran(0.35),fenetre.WidthEcran(0.03),fenetre.WidthEcran(0.45),fenetre.HeightEcran(0.05));
        css(panelVertical,this,"Times New Roman",20,Color.white,fenetre.WidthEcran(0.20),fenetre.WidthEcran(0.07),fenetre.WidthEcran(0.15),fenetre.HeightEcran(0.50));
        css(panel,this,"Times New Roman",20,Color.white,fenetre.WidthEcran(0.35),fenetre.WidthEcran(0.07),fenetre.WidthEcran(0.30),fenetre.HeightEcran(0.50));
        //css(zoneApercu,this,"Times New Roman",20,Color.BLUE,fenetre.WidthEcran(0.65),fenetre.WidthEcran(0.05),fenetre.WidthEcran(0.25),fenetre.HeightEcran(0.50));
       
        this.responsivePanel();
        this.repaint();
        this.revalidate();
    }   
    
    // panel - formulaire de modification du média choisi
    public void modifierMedia(Media media) throws MalformedURLException, IOException
    {
        idModif = media.getIdMedia();
        this.removeAll();
        JPanel panelAjout = new JPanel();
        panelAjout.setLayout(new GridLayout(15, 2));
        labelTitre.setText("Modifier un Média");  
        
        //labels du formulaire
        labelTitreMedia = new JLabel("Titre du média : ");        
        labelEmprunteur = new JLabel("Auteur : ");        
        labelGenre = new JLabel("Genre : ");        
        labelLocalisation = new JLabel("Localisation : ");
        labelEtat = new JLabel("Etat : ");
        labelType = new JLabel("Type : ");
        labelIsbn = new JLabel("ISBN : ");
        labelPhoto = new JLabel("Photo : ");
        labelDescription = new JLabel("Description : ");        
        labelResume = new JLabel("Resume : ");
        labelAnneeParution = new JLabel("Année de parution : ");
        labelTome = new JLabel("Tome : ");
        labelNbPages = new JLabel("Nombre de pages : ");
        labelFormat = new JLabel("Format : ");
        labelMaisonEdition = new JLabel("Maison d'édition : ");        
        
        if(media.getTitre()!=null)
        champTitreMedia = new JTextField(media.getTitre());
            else champTitreMedia = new JTextField();
        
        champAuteur = new JComboBox();        
        List<Auteur> listeAuteur = (new AuteurDAO()).findAll();
        for(Auteur obj : listeAuteur)
        {
            champAuteur.addItem(obj);
            if(media.getAuteur()!=null)
            {
                if(obj.getIdAuteur()==media.getAuteur().getIdAuteur())
                    champAuteur.setSelectedItem(obj);
            }
        }
        
        champGenre = new JComboBox(); 
        List<Genre> listeGenre = (new GenreDAO()).findAll();
        for(Genre obj : listeGenre)
        {
            champGenre.addItem(obj);
            if(media.getGenre()!=null)
            {
                if(obj.getIdGenre()==media.getGenre().getIdGenre())
                    champGenre.setSelectedItem(obj);
            }
        }
        
        if(media.getLocalisation()!=null)
            champLocalisation = new JTextField(media.getLocalisation());
        else champLocalisation = new JTextField();
        
        champEtat = new JComboBox();                                                                 
        List<Etat> listeEtat = (new EtatDAO()).findAll();                                          
        for(Etat obj : listeEtat)                                                                  
        {                                                                                         
            champEtat.addItem(obj); 
            if(media.getEtat()!=null)
            {
                if(obj.getIdEtat()==media.getEtat().getIdEtat())                           
                    champGenre.setSelectedItem(obj);        
            }            
        }
        
        champType = new JComboBox();
        List<TypeMedia> listeType = (new TypeMediaDAO()).findAll();
        for(TypeMedia obj : listeType)
        {
            champType.addItem(obj);
            if(media.getTypeMedia()!=null)
            {
                if(obj.getIdTypeMedia()==media.getTypeMedia().getIdTypeMedia())
                    champGenre.setSelectedItem(obj); 
            }
        }
        
        if(media.getIsbn()!=null)
            champIsbn = new JTextField(media.getIsbn());
            else champIsbn = new JTextField();
        JPanel panelFile = new JPanel();       
        if(media.getLienPhoto()!=null)
            champPhoto = new JTextField(media.getLienPhoto());
            else champPhoto = new JTextField("",20);
                champPhoto.setEditable(false);         
        champPhoto.setPreferredSize(new Dimension(250,25));
        panelFile.add(champPhoto); 
        
        boutonChoisirFichier = new JButton("Parcourir ...");        
        panelFile.add(boutonChoisirFichier);
        boutonChoisirFichier.addActionListener(this);
        boutonChoisirFichier.setPreferredSize(new Dimension(100,25));
        
        if(media.getDescription()!=null)
        champDescription = new JTextField(media.getDescription());
        else
            champDescription = new JTextField();
        if(media.getDescription()!=null)
            champAnneeParution = new JTextField(""+media.getAnneeParution());
        else champAnneeParution = new JTextField();
        if(media.getTome()!=null)
            champTome = new JTextField(media.getTome());
        else
            champTome = new JTextField();
        champNbPages = new JTextField(""+media.getPages());
        if(media.getFormat()!=null)
            champFormat = new JTextField(media.getFormat());
        else champFormat = new JTextField();
        if(media.getMaisonEdition()!=null)
        champMaisonEdition = new JTextField(media.getMaisonEdition());
        else
            champMaisonEdition = new JTextField();
        if(media.getResume()!=null)
        champResume = new JTextArea(media.getResume(),5,50);
        else
           champResume = new JTextArea(5,50);        
        jScrollPane1 = new JScrollPane(champResume);
        boutonValiderModif = new JButton("Valider");       
        boutonValiderModif.addActionListener(this);
        
        String nomfichier;
        

        if(!(media.getLienPhoto()==null))
        {    
        }
        else
        {
            zoneApercu.setText("Pas d'image pour ce média");
        }
              
        boutonRetour= new JButton("Retour");
        boutonRetour.addActionListener(this); 
        
        // bouton d'ajout des entités auteur, genre et type       
        boutonAjoutAuteur = new JButton("Autre");
        boutonAjoutAuteur.addActionListener(this);  
        boutonAjoutGenre = new JButton("Autre");
        boutonAjoutGenre.addActionListener(this);  
        boutonAjoutType = new JButton("Autre");
        boutonAjoutType.addActionListener(this); 
        
        // ajout des composants aux panels
        panelAjout.add(labelTitreMedia);
        panelAjout.add(champTitreMedia);        
        panelAjout.add(labelEmprunteur);
        panelAjout.add(champAuteur);
        panelAjout.add(labelGenre); 
        panelAjout.add(champGenre);
        panelAjout.add(labelLocalisation);
        panelAjout.add(champLocalisation);
        panelAjout.add(labelEtat);
        panelAjout.add(champEtat);
        panelAjout.add(labelType);
        panelAjout.add(champType);
        panelAjout.add(labelIsbn);        
        panelAjout.add(champIsbn);
        //panelAjout.add(labelPhoto);
       // panelAjout.add(panelFile);
        panelAjout.add(labelDescription);
        panelAjout.add(champDescription);
        panelAjout.add(labelResume);
        panelAjout.add(jScrollPane1);        
        panelAjout.add(labelAnneeParution);
        panelAjout.add(champAnneeParution);
        panelAjout.add(labelTome);
        panelAjout.add(champTome);
        panelAjout.add(labelNbPages);
        panelAjout.add(champNbPages);
        panelAjout.add(labelFormat);
        panelAjout.add(champFormat);
        panelAjout.add(labelMaisonEdition);       
        panelAjout.add(champMaisonEdition);
                
        boutonAjoutAuteur.setBounds(880,115,100,30);
        boutonAjoutGenre.setBounds(880,145,100,30);
        boutonAjoutType.setBounds(880,235,100,30);
        //this.zoneApercu.setBounds(880,50,300,300);
        this.labelTitre.setBounds(50,10,300,30);
        panelAjout.setBounds(50,50,800,460);
        boutonRetour.setBounds(600,520,100,30);
        boutonValiderModif.setBounds(750,520,100,30);
        
        //this.add(zoneApercu);
        this.add(labelTitre);
        this.add(panelAjout); 
        this.add(boutonAjoutAuteur);
        this.add(boutonAjoutGenre);
        this.add(boutonAjoutType);
        this.add(boutonRetour);
        this.add(boutonValiderModif);
        this.repaint();
        this.revalidate();
    }
    
    // retourne sur le tableau général
    public void actualiser(String action)
    {
        this.responsivePanel();
        this.updateUI();
    }
    
    private String getLocalisation()
    {
        String codeGenre,prenom,nom,localisation;
        if((champGenre.getSelectedItem() != champGenre.getItemAt(0)) && (champAuteur.getSelectedItem() != champAuteur.getItemAt(0)))
        {
        codeGenre = ((Genre)champGenre.getSelectedItem()).getCode();
        prenom = ((Auteur)champAuteur.getSelectedItem()).getPrenom().substring(0,1).toUpperCase();
        nom = ((Auteur)champAuteur.getSelectedItem()).getNom().substring(0,2).toUpperCase();
        localisation = codeGenre.concat(" ").concat(prenom).concat(nom);
        }
        else
        {
            localisation = null;
        }
        return localisation;
    }



    public FenetrePrincipale getFenetre() {
        return fenetre;
    }

    public void setFenetre(FenetrePrincipale fenetre) {
        this.fenetre = fenetre;
    }

    public JTextField getChampTitreMedia() {
        return champTitreMedia;
    }

    public void setChampTitreMedia(JTextField champTitreMedia) {
        this.champTitreMedia = champTitreMedia;
    }

    public JTextField getChampLocalisation() {
        return champLocalisation;
    }

    public void setChampLocalisation(JTextField champLocalisation) {
        this.champLocalisation = champLocalisation;
    }

    public JTextField getChampIsbn() {
        return champIsbn;
    }

    public void setChampIsbn(JTextField champIsbn) {
        this.champIsbn = champIsbn;
    }

    public JTextField getChampPhoto() {
        return champPhoto;
    }

    public void setChampPhoto(JTextField champPhoto) {
        this.champPhoto = champPhoto;
    }

    public JTextField getChampAnneeParution() {
        return champAnneeParution;
    }

    public void setChampAnneeParution(JTextField champAnneeParution) {
        this.champAnneeParution = champAnneeParution;
    }

    public JTextField getChampTome() {
        return champTome;
    }

    public void setChampTome(JTextField champTome) {
        this.champTome = champTome;
    }

    public JTextField getChampNbPages() {
        return champNbPages;
    }

    public void setChampNbPages(JTextField champNbPages) {
        this.champNbPages = champNbPages;
    }

    public JTextField getChampFormat() {
        return champFormat;
    }

    public void setChampFormat(JTextField champFormat) {
        this.champFormat = champFormat;
    }

    public JTextField getChampMaisonEdition() {
        return champMaisonEdition;
    }

    public void setChampMaisonEdition(JTextField champMaisonEdition) {
        this.champMaisonEdition = champMaisonEdition;
    }

    public JTextArea getChampResume() {
        return champResume;
    }

    public void setChampResume(JTextArea champResume) {
        this.champResume = champResume;
    }

    public JComboBox getChampAuteur() {
        return champAuteur;
    }

    public void setChampAuteur(JComboBox champAuteur) {
        this.champAuteur = champAuteur;
    }

    public JComboBox getChampGenre() {
        return champGenre;
    }

    public void setChampGenre(JComboBox champGenre) {
        this.champGenre = champGenre;
    }

    public JComboBox getChampEtat() {
        return champEtat;
    }

    public void setChampEtat(JComboBox champEtat) {
        this.champEtat = champEtat;
    }

    public JComboBox getChampType() {
        return champType;
    }

    public void setChampType(JComboBox champType) {
        this.champType = champType;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public JTextField getChampDescription() {
        return champDescription;
    }

    public void setChampDescription(JTextField champDescription) {
        this.champDescription = champDescription;
    }
}
