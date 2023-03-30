package ca.etsmtl.log240.financej;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.uispec4j.*;
import org.uispec4j.interception.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CategoriesTest extends FinancejAbstractTest {
    private Table categoriesTable;
    private List<String> categoriesNomsAlphaNum = ValidFieldsGenerator.generateValidAccountNames();
    private List<String> descriptionsValides = ValidFieldsGenerator.generateValidDescriptions();
    private List<Double> budgetsValides = ValidFieldsGenerator.generateValidBudgets();

    private Trigger ret;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    @AfterClass
    public void tearDownClass() throws Exception {
        //closing connection to database
        exitButton.click();
    }
    private class ValidCategoriesHandler extends WindowHandler {
        private String name, description;
        private double budget;
        public ValidCategoriesHandler(String name, String description, int budget) {
            this.name = name;
            this.description = description;
            this.budget = budget;
        }

        public Trigger process(Window window) throws Exception {
            testCategories(window, name, description, budget);
            ret = window.getButton("Close").triggerClick();
            return ret;
        }

        //Vérifie si le compte est bien ajouté et s'il est bien supprimé
        private void testCategories(Window window, String name, String description, double budget) throws Exception {
            categoriesNomsAlphaNum = ValidFieldsGenerator.generateValidAccountNames();
            descriptionsValides = ValidFieldsGenerator.generateValidDescriptions();
            budgetsValides = ValidFieldsGenerator.generateValidBudgets();

            categoriesTable = window.getTable();
            int rowCount = categoriesTable.getRowCount();

            // delete all rows if they exist
            /*if (rowCount > 0) {
                for (int i = 0; i < rowCount; i++) {
                    // select the first row with the specified name and delete it
                    accountsTable.selectRow(i);
                    window.getButton("Delete Account").click();
                }
                rowCount = accountsTable.getRowCount();
            }

            if (accountsTable.getRowCount() == 0) {
                System.out.println("Account table before adding the account---->" + accountsTable.toString());
                // ajouter un compte
                window.getTextBox("NAME_TEXT_FIELD").setText(name);
                window.getTextBox("DESCRIPTION_TEXT_FIELD").setText(description);
                window.getButton("Add Account").click();

                System.out.println("Account table after adding the account---->" + accountsTable.toString());

                // Create the expectedTable array
                String[][] expectedTable = new String[][]{{name, description}};

                // vérifier que le compte a été ajouté
                assertEquals(accountsTable.getRowCount(), rowCount + 1);

                // vérifier que la table de données est correcte
                window.getTable().contentEquals(expectedTable);

                // supprimer le compte cree precedemment en cherchant le nom
                accountsTable.selectRowsWithText(0, name);
                window.getButton("Delete Account").click();

                System.out.println("Account table after deleting the account---->" + accountsTable.toString());

                // vérifier que le compte a été supprimé
                assertEquals(accountsTable.getRowCount(), rowCount);
            }*/
        }
    }
}
