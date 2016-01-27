/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gfitest.frame;

import gfitest.entity.Patient;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Antony
 */
public class PatientCellRendered implements ListCellRenderer<Patient>{

    @Override
    public Component getListCellRendererComponent(JList<? extends Patient> list, Patient value, int index, boolean isSelected, boolean cellHasFocus) {
        return new JLabel(value.getNom() + " " + value.getPrenom());
    }
    
    
    
}
