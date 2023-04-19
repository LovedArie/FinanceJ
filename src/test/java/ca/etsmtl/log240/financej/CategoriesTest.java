package ca.etsmtl.log240.financej;
import org.junit.*;
import java.util.List;
import java.util.Random;
import org.uispec4j.Trigger;
import org.uispec4j.Window;
import org.uispec4j.interception.WindowHandler;
import org.uispec4j.interception.WindowInterceptor;


public class CategoriesTest extends FinancejAbstractTest {
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
        WindowInterceptor.init(categoriesButton.triggerClick()).process(new ValidCategoriesHandler(ValidFieldsGenerator.getRandomName(), "Savings", ValidFieldsGenerator.getRandomFloat(-800000000f,80000000000f))).run();
    }
    @Test
    public void testAddAndDeleteCategoriesWithValidNames() throws Exception {
        for(String category : categoriesNomsAlphaNum) {
            WindowInterceptor.init(categoriesButton.triggerClick()).process(new ValidCategoriesHandler(category, "Savings", ValidFieldsGenerator.getRandomFloat(-800000000f,80000000000f))).run();
        }
    }
    @Test
    public void testAddAndDeleteCategoriesWithRandomBudget() throws Exception {
        Random random = new Random();
        WindowInterceptor.init(categoriesButton.triggerClick()).process(new ValidCategoriesHandler(categoriesNomsAlphaNum.get(random.nextInt(49)), "Savings", ValidFieldsGenerator.getRandomFloat(-800000000f,80000000000f))).run();
    }
    @Test
    public void testAddAndDeleteCategoriesWithValidDescriptions() throws Exception {
        Random random = new Random();
        for(String description : descriptionsValides) {
            WindowInterceptor.init(categoriesButton.triggerClick()).process(new ValidCategoriesHandler(categoriesNomsAlphaNum.get(random.nextInt(49)), description, ValidFieldsGenerator.getRandomFloat(-800000000f,80000000000f))).run();
        }
    }


    //T0-01 test pour ajouter une catégorie valide avec le budget minimal
    @Test
    public void testT0_01(){;
        System.out.println("name Added: Te");
        System.out.println("Description Added: $");
        System.out.println("Budget: -80000000000000.00");


        float budget = (float) -80_000_000_000.00000;
        WindowInterceptor.init(categoriesButton.triggerClick()).process(new ValidCategoriesHandler("Te","$", -80000000000000.00f)).run();
        }


    //T1_02 test pour ajouter une catégorie avec un nom vide
    @Test
    public void testT1_02(){;
        System.out.println("name Added: ");
        System.out.println("Description Added: CE8(LB), CE10");
        System.out.println("Budget: CE13, CE16(LB)");
        WindowInterceptor.init(categoriesButton.triggerClick()).process(new InvalidCategoriesHandler("","$", 1)).run();
    }

    //T1_03 test pour ajouter une catégorie avec un nom trop long
    @Test
    public void testT1_03(){;
        System.out.println("name Added: acuteaacutiaacutumaacutusaadaadamareaaddidistiaagaa");
        System.out.println("Description Added: $");
        System.out.println("Budget: 1");
        String nameTooLong = "acuteaacutiaacutumaacutusaadaadamareaaddidistiaagaa";
        WindowInterceptor.init(categoriesButton.triggerClick()).process(new InvalidCategoriesHandler(nameTooLong,"$", 1)).run();
    }

    //T1_04 test pour ajouter une catégorie avec un nom invalide qui a un caractère spécial "$"
    @Test
    public void testT1_04(){;
        System.out.println("name Added: Te$");
        System.out.println("Description Added: Te$");
        System.out.println("Budget: 1");


        WindowInterceptor.init(categoriesButton.triggerClick()).process(new InvalidCategoriesHandler("Te$","Te$", 1)).run();
    }

    //test pour ajouter une catégorie avec une description vide
    @Test
    public void testT1_05(){;
        System.out.println("name Added: Te");
        System.out.println("Description Added: ");
        System.out.println("Budget: 1");

        WindowInterceptor.init(categoriesButton.triggerClick()).process(new InvalidCategoriesHandler("Te","", 1)).run();
    }

    @Test
    public void testT1_06(){;
        System.out.println("name Added: Te");
        System.out.println("Description Added: bonis bono bonorum bonum brevi brevis breviter brute brutus cadere caecilii caeco caelo calere campum canes captet capti captiosa careat carere careret caret caritatem carum causa causae causam causas cedentem celeritas censes censet centurionum certa");
        System.out.println("Budget: 1");

        String descTooLong = "bonis bono bonorum bonum brevi brevis breviter brute " +
                "brutus cadere caecilii caeco caelo calere campum canes captet capti " +
                "captiosa careat carere careret caret caritatem carum causa causae causam " +
                "causas cedentem celeritas censes censet centurionum certa";
        System.out.println(descTooLong);
        WindowInterceptor.init(categoriesButton.triggerClick()).process(new InvalidCategoriesHandler("Te",descTooLong, 1)).run();
    }


    @Test(expected = Error.class)
    public void testT1_07() {;
        System.out.println("name Added: Te");
        System.out.println("Description Added: $");
        System.out.println("Budget: ");

        WindowInterceptor.init(categoriesButton.triggerClick()).process(new InvalidCategoriesHandler("Te", "$", "null")).run();
    }

    //T1_08 test pour ajouter une catégorie avec un budget invalide qui est trop grand
    @Test
    public void testT1_08(){;
        System.out.println("name Added: Te");
        System.out.println("Description Added: $");
        System.out.println("Budget: 100000000000000");

        WindowInterceptor.init(categoriesButton.triggerClick()).process(new InvalidCategoriesHandler("Te","$",100000000000000f )).run();
    }

    //T1_09 test pour ajouter une catégorie avec un budget invalide qui est trop petit
    @Test
    public void testT1_09(){;
        System.out.println("name Added: Te");
        System.out.println("Description Added: $");
        System.out.println("Budget: 100000000000ab");

        WindowInterceptor.init(categoriesButton.triggerClick()).process(new InvalidCategoriesHandler("Te","$", "100000000000ab" )).run();
    }


    //T1_10 test pour ajouter une catégorie avec un budget invalide qui est trop petit
    @Test
    public void testT1_10(){;
        System.out.println("name Added: Te");
        System.out.println("Description Added: $");
        System.out.println("Budget: -1.0E14");

        WindowInterceptor.init(categoriesButton.triggerClick()).process(new InvalidCategoriesHandler("Te","$",-100000000000000f )).run();
    }

    //T0_11 Test pour ajouter une catégorie valide avec le budget maximal, nom et description longue
    @Test
    public void testT0_11(){
        System.out.println("name Added: aristotelemaristoteliarmatumarridensarsarteiuu00za");
        System.out.println("Description Added: confirmavit conflixisse conformavit congressus congue coniuncta coniunctione conquirendae conquisitis conscientia conscientiam consectetuer consectetur consecutionem consecutus consedit consentaneum consentientis consentinis consequamur consequantur%");
        System.out.println("Budget: 80,000,000,000");

        float budget = (float) 80_000_000_000.00000;
        String name ="aristotelemaristoteliarmatumarridensarsarteiuu00za";
        String desc ="confirmavit conflixisse conformavit congressus congue coniuncta coniunctione conquirendae conquisitis conscientia conscientiam consectetuer consectetur consecutionem consecutus consedit consentaneum consentientis consentinis consequamur consequantur%";
        WindowInterceptor.init(categoriesButton.triggerClick()).process(new ValidCategoriesHandler(name,desc, budget)).run();
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
            System.out.println("This is a Initial row count:"+ rowCount);
            // delete all rows if they exist
            if (rowCount > 1) {
                for (int i = 1; i < rowCount; i++) {
                    // select the first row with the specified name and delete it
                    categoriesTable.selectRow(1);
                    window.getButton("Delete Category").click();
                }
                rowCount = categoriesTable.getRowCount();
            }
            System.out.println("I am ALIVE");

            if (categoriesTable.getRowCount() == 0) {
                System.out.println("Account table before adding the account---->" + categoriesTable.toString());
                // ajouter une catégorie
                String s = String.valueOf(budget);
                window.getTextBox("NAME_TEXT_FIELD").setText(name);
                window.getTextBox("DESCRIPTION_TEXT_FIELD").setText(description);
                window.getTextBox("BUDGET_TEXT_FIELD").setText(s);

                window.getButton("Add Category").click();
                System.out.println("Category table after adding the category---->" + categoriesTable.toString());
                // Create the expectedTable array

                String[][] expectedTable = new String[][]{{name, description,s}};
                System.out.println("LUL"+rowCount);
                // vérifier que la catégorie a été ajoutée
                assertEquals(categoriesTable.getRowCount(), rowCount + 1);
                // vérifier que la table de données est correcte
                window.getTable().contentEquals(expectedTable);
                // supprimer la catégorie créée precedemment en cherchant le nom
                categoriesTable.selectRowsWithText(1, name);
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
        private float budget;
        public InvalidCategoriesHandler(String name, String description, float budget) {
            this.name = name;
            this.description = description;
            this.budget = budget;
        }

        public InvalidCategoriesHandler(String te, String $, String budget) {
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
                String s = String.valueOf(budget);
                window.getTextBox("NAME_TEXT_FIELD").setText(name);
                window.getTextBox("DESCRIPTION_TEXT_FIELD").setText(description);
                window.getTextBox("BUDGET_TEXT_FIELD").setText(s);

                window.getButton("Add Category").click();
                System.out.println("Category table after adding the category---->" + categoriesTable.toString());
                // vérifier que la table de données est vide
                assertEquals(0,categoriesTable.getRowCount());

            }
        }
    }
}
