/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gfitest.facade;

import gfitest.entity.Patient;
import gfitest.enums.SexeEnum;
import gfitest.enums.SituationFamilleEnum;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author antony
 */
public class PatientFacade extends AbstractFacade<Patient> {

    @Override
    public List<Patient> findAll() {
        List<Patient> list = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(""
                    + "SELECT `num_dossier`, `nom`, `prenom`, `dateNaissance`, "
                    + "`sexe`, `num_sejour`, `secu`, `lieuDeNaissance`, `nationalite`, "
                    + "`dateDeces`, `situationFamiliale` FROM `patient`");
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                list.add(convertResultSet(resultSet));
            }
            resultSet.close();
        } catch (SQLException ex) {
            System.err.println("ERREUR à la récupération des patients");
        }
        return list;
    }

    @Override
    public Patient findById(int id) {
        Patient patient = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(""
                    + "SELECT `num_dossier`,`nom`,`prenom`,"
                    + "`dateDeNaissance`,`sexe`,`situationFamiliale`,`numSejour`,"
                    + "`numSecu`,`lieuDeNaissance`,`nationalite` "
                    + "FROM `patient` "
                    + "WHERE num_dossier = ?");
            stmt.setInt(1, id);
           
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.first()){
                patient = convertResultSet(resultSet);
            }
            resultSet.close();
        } catch (SQLException ex) {
            System.err.println("ERREUR à la récupération du patient : " + ex.getMessage());
        }
        return patient;
    }

    @Override
    protected Patient convertResultSet(ResultSet resultSet) {
        try {
            Patient patient = new Patient();
            patient.setNum_dossier(resultSet.getInt("num_dossier"));
            patient.setNom(resultSet.getString("nom"));
            patient.setPrenom(resultSet.getString("prenom"));
            patient.setDateNaissance(resultSet.getTimestamp("dateNaissance"));
            patient.setSexe(SexeEnum.values()[resultSet.getInt("sexe")]);
            patient.setSituationFamilleEnum(SituationFamilleEnum.values()[resultSet.getInt("situationFamiliale")]);
            patient.setNumSejour(resultSet.getString("num_sejour"));
            patient.setSecu(resultSet.getString("secu"));
            patient.setCommuneNaissance(resultSet.getInt("lieuDeNaissance"));
            patient.setNationalite(resultSet.getString("nationalite"));
            patient.setDateDeces(resultSet.getTimestamp("dateDeces"));
            return patient;
        } catch (SQLException ex) {
            Logger.getLogger(PatientFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
