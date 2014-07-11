/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package calogiciel;

import Modele.Abonne;
import Modele.DAO.AbonneDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;
import javax.swing.JScrollPane;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author zemouri.cdi01
 */
public class PanelAbonnes extends PanelStructure implements ActionListener, ItemListener{

    private JScrollPane tableauDeroulant;
    private TableRowSorter<TableModelAbonnes> sorter;
    private List<RowFilter<TableModelAbonnes,Object>> filters;
    private RowFilter<TableModelAbonnes, Object> rf1;
    private RowFilter<TableModelAbonnes,Object> fooBarFilter;
    
    public PanelAbonnes(FenetrePrincipale fenetre)
    {
        super(fenetre, new TableModelAbonnes());
        labelTitre.setText("Liste des abonnés");
        /*TableModelAbonnes model = new TableModelAbonnes();
        sorter = new TableRowSorter<>(model); 
        tableau.setRowSorter(sorter);
        tableauDeroulant = new JScrollPane(tableau); 
        this.add(tableauDeroulant);*/
        combo1.addItem("Trier par nom de famille");
        List<Abonne> listeCombo1 = (new AbonneDAO()).findAll();
        for(Abonne obj : listeCombo1)
        {
            combo1.addItem(obj.getPersonne().getNom_Personne());
        }
        combo1.addItemListener(this);
        this.style.getStylePanelStructure(this);
        this.setVisible(true);
        responsivePanel();
    }
    
     public void itemStateChanged(ItemEvent ie) {
        filters = new ArrayList<>(1);
        String text1;
        text1 = (String) combo1.getSelectedItem();
        try
        {
            rf1 = RowFilter.regexFilter(text1,tableau.getColumnModel().getColumnIndex("Nom"));
            filters.add(rf1);
        }
        catch (PatternSyntaxException ex)
        {
            System.out.println("erreur combo");
        }
        if(text1.equals(combo1.getItemAt(0)))
        {
             filters.remove(rf1);
        }
        fooBarFilter = RowFilter.andFilter(filters);
        sorter.setRowFilter(fooBarFilter);
    }
    
     @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource().equals(boutonAjout))
        {
            
        }
    }
}
