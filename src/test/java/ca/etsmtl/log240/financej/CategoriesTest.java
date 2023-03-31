package ca.etsmtl.log240.financej;
import org.junit.*;
import java.util.List;
import java.util.Random;
import org.uispec4j.Table;
import org.uispec4j.Trigger;
import org.uispec4j.Window;
import org.uispec4j.interception.WindowHandler;
import org.uispec4j.interception.WindowInterceptor;


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
        WindowInterceptor.init(categoriesButton.triggerClick()).process(new ValidCategoriesHandler(ValidFieldsGenerator.getRandomName(), "Savings", (float) ValidFieldsGenerator.getRandomDouble())).run();
    }
    @Test
    public void testAddAndDeleteCategoriesWithValidNames() throws Exception {
        for(String category : categoriesNomsAlphaNum) {
            WindowInterceptor.init(categoriesButton.triggerClick()).process(new ValidCategoriesHandler(category, "Savings", (float) ValidFieldsGenerator.getRandomDouble())).run();
        }
    }
    @Test
    public void testAddAndDeleteCategoriesWithInvalidName( ) throws Exception {
        WindowInterceptor.init(categoriesButton.triggerClick()).process(new InvalidCategoriesHandler("", "Savings", -1000000)).run();
    }
    @Test
    public void testAddAndDeleteCategoriesWithEmptyDescription() throws Exception {
        Random random = new Random();
        WindowInterceptor.init(categoriesButton.triggerClick()).process(new ValidCategoriesHandler(categoriesNomsAlphaNum.get(random.nextInt(49)), "Savings", (float) ValidFieldsGenerator.getRandomDouble())).run();
    }
    @Test
    public void testAddAndDeleteCategoriesWithValidDescriptions() throws Exception {
        Random random = new Random();
        for(String description : descriptionsValides) {
            WindowInterceptor.init(categoriesButton.triggerClick()).process(new ValidCategoriesHandler(categoriesNomsAlphaNum.get(random.nextInt(49)), description, (float) ValidFieldsGenerator.getRandomDouble())).run();
        }
    }


    //T0-01 test pour ajouter une catégorie valide avecl e budget maximal
    @Test
    public void testAddCategoryMinimumValue(){
       float budget = (float) -80_000_000_00.00000;
        WindowInterceptor.init(categoriesButton.triggerClick()).process(new ValidCategoriesHandler("Te","$", budget)).run();
    }
    private class ValidCategoriesHandler extends WindowHandler {
        private String name;
        private String description;
        private float budget;
        public ValidCategoriesHandler(String name, String description, float budget) {
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
        private void testCategories(Window window, String name, String description, float budget) throws Exception {
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
    private class InvalidCategoriesHandler extends WindowHandler {
        private String name;
        private String description;
        private long budget;

        public InvalidCategoriesHandler(String name, String description, long budget) {
            this.name = name;
            this.description = description;
            this.budget = budget;
        }
        public Trigger process(Window window){
            testError(window, name, description,budget);
            ret = window.getButton("Close").triggerClick();
            return ret;
        }
        private void testError(Window window, String name, String description, long budget){
            categoriesTable = window.getTable();
            int rowCount = categoriesTable.getRowCount();

            // delete all rows if they exist
            if (rowCount > 0) {
                for (int i = 0; i < rowCount; i++) {
                    // select the first row with the specified name and delete it
                    categoriesTable.selectRow(i);
                    window.getButton("Delete Category").triggerClick();
                }
                rowCount = categoriesTable.getRowCount();
            }

            if (accountsTable.getRowCount() == 0) {
                System.out.println("Category table before adding the category---->" + categoriesTable.toString());
                // ajouter une catégorie
                window.getTextBox("NAME_TEXT_FIELD").setText(name);
                window.getTextBox("DESCRIPTION_TEXT_FIELD").setText(description);
                String s = String.valueOf(budget);
                window.getTextBox("BUDGET_TEXT_FIELD").setText(s);

                window.getButton("Add Category").click();
                System.out.println("Category table after adding the category---->" + categoriesTable.toString());
                //On ne devrait rien avoir dans la table
                assertEquals(0,categoriesTable.getRowCount());
            }
        }
    }


}
