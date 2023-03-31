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
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class CategoriesTest extends FinancejAbstractTest {
    private Table categoriesTable;
    private List<String> categoriesNomsAlphaNum = ValidFieldsGenerator.generateValidAccountNames();
    private List<String> descriptionsValides = ValidFieldsGenerator.generateValidDescriptions();
    private List<Double> budgetsValides = ValidFieldsGenerator.generateValidBudgets();

    private Trigger ret;

    @Before
    protected void setUp() throws Exception {
        super.setUp();
    }
    @After
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    @AfterClass
    public void tearDownClass() throws Exception {
        //closing connection to database
        exitButton.click();
    }
    @Test
    public void testAddAndDeleteCategoriesWithRandomName() throws Exception {
        WindowInterceptor.init(categoriesButton.triggerClick()).process(new ValidCategoriesHandler(ValidFieldsGenerator.getRandomName(), "Savings", ValidFieldsGenerator.getRandomDouble())).run();
    }
    @Test
    public void testAddAndDeleteCategoriesWithValidNames() throws Exception {
        for(String category : categoriesNomsAlphaNum) {
            WindowInterceptor.init(categoriesButton.triggerClick()).process(new ValidCategoriesHandler(category, "Savings", ValidFieldsGenerator.getRandomDouble())).run();
        }
    }
    @Test
    public void testAddAndDeleteCategoriesWithEmptyName() throws Exception {
        assertThrow();
    }
    @Test
    public void testAddAndDeleteCategoriesWithEmptyDescription() throws Exception {
        Random random = new Random();
        WindowInterceptor.init(categoriesButton.triggerClick()).process(new ValidCategoriesHandler(categoriesNomsAlphaNum.get(random.nextInt(49)), "Savings", ValidFieldsGenerator.getRandomDouble())).run();
    }
    @Test
    public void testAddAndDeleteCategoriesWithValidDescriptions() throws Exception {
        Random random = new Random();
        for(String description : descriptionsValides) {
            WindowInterceptor.init(categoriesButton.triggerClick()).process(new ValidCategoriesHandler(categoriesNomsAlphaNum.get(random.nextInt(49)), description, ValidFieldsGenerator.getRandomDouble())).run();
        }
    }
    @Test
    public void testAddCategory() throws Exception {
        WindowInterceptor.init(categoriesButton.triggerClick()).process(new ValidCategoriesHandler("Te","$AAAAA", -1000000000d)).run();
    }
    /*@Test
    public void testAddCategoryNameInvalid() {
        assertThrows(Throwable.class,
        ()->{
            WindowInterceptor.init(accountsButton.triggerClick()).process(new ValidAccountsHandler("","$")).run();
        });
    }*/
    private class ValidCategoriesHandler extends WindowHandler {
        private String name;
        private String description;
        private double budget;
        public ValidCategoriesHandler(String name, String description, double budget) {
            this.name = name;
            this.description = description;
            this.budget = budget;
        }

        public Trigger process(Window window) throws Exception {
            testCategories(window, name, description, budget);
            ret = window.getButton("Close").triggerClick();
            return ret;
        }

        //Vérifie si la catégorie est bien ajoutée et si elle est bien supprimée
        private void testCategories(Window window, String name, String description, double budget) throws Exception {
            categoriesNomsAlphaNum = ValidFieldsGenerator.generateValidAccountNames();
            descriptionsValides = ValidFieldsGenerator.generateValidDescriptions();
            budgetsValides = ValidFieldsGenerator.generateValidBudgets();

            categoriesTable = window.getTable();
            int rowCount = categoriesTable.getRowCount();

            // delete all rows if they exist
            if (rowCount > 0) {
                for (int i = 0; i < rowCount; i++) {
                    // select the first row with the specified name and delete it
                    categoriesTable.selectRow(i);
                    window.getButton("Delete Category").click();
                }
                rowCount = categoriesTable.getRowCount();
            }

            if (categoriesTable.getRowCount() == 0) {
                System.out.println("Account table before adding the account---->" + categoriesTable.toString());
                // ajouter une catégorie
                window.getTextBox("NAME_TEXT_FIELD").setText(name);
                window.getTextBox("DESCRIPTION_TEXT_FIELD").setText(description);
                window.getTextBox("BUDGET_TEXT_FIELD").setText(Double.toString(budget));
                window.getButton("Add Category").click();

                System.out.println("Category table after adding the category---->" + categoriesTable.toString());

                // Create the expectedTable array
                String[][] expectedTable = new String[][]{{name, description,Double.toString(budget)}};

                // vérifier que la catégorie a été ajoutée
                assertEquals(categoriesTable.getRowCount(), rowCount + 1);

                // vérifier que la table de données est correcte
                window.getTable().contentEquals(expectedTable);

                // supprimer la catégorie créée precedemment en cherchant le nom
                categoriesTable.selectRowsWithText(0, name);
                window.getButton("Delete Category").click();

                System.out.println("Category table after deleting the category---->" + categoriesTable.toString());

                // vérifier que la catégorie a été supprimée
                assertEquals(categoriesTable.getRowCount(), rowCount);
            }
        }
    }
}
