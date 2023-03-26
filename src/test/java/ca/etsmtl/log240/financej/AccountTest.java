package ca.etsmtl.log240.financej;
import com.sun.org.apache.xpath.internal.operations.Equals;
import org.junit.After;
import org.junit.Before;
import org.uispec4j.*;
import org.uispec4j.interception.*;

import java.util.Arrays;
import java.util.Random;
import org.junit.Test;
public class AccountTest extends FinancejAbstractTest {
    private Table accountsTable;
    private Trigger ret;
    protected Button accountsButton;

    @Before
    protected void setUp() throws Exception {
        super.setUp();
    }

    @After
    protected void tearDown() throws Exception {
        super.tearDown();
    }



    @Test
    public void testAddingAndDeletingAccount() throws Exception {
        /* Voici comment traiter une fenêtre modale avec uispec4j.
         * Voir "Intercepting windows and dialogs" dans la documentation en ligne.
         */
        WindowInterceptor.init(loginButton.triggerClick()).process(new WindowHandler() {
            public Trigger process(Window window) {
                exitButton = window.getButton("Exit");
                accountsButton = window.getButton("Accounts");


                WindowInterceptor.init(accountsButton.triggerClick()).process(new WindowHandler() {

                    public Trigger process(Window window) throws Exception {

                        accountsTable = window.getTable();
                        int rowCount = accountsTable.getRowCount();

                        // delete all rows if they exist
                        if (rowCount > 0) {
                            for (int i = 0; i < rowCount; i++) {
                                // select the first row with the specified name and delete it
                                accountsTable.selectRow(i);
                                window.getButton("Delete Account").click();
                            }
                            rowCount=accountsTable.getRowCount();

                        }

                        if (accountsTable.getRowCount() == 0) {
                            System.out.println("Account table before adding the account---->"+accountsTable.toString());
                            // ajouter un compte
                            String name;
                            name = getRandomName();
                            String description= "Savings";

                            window.getTextBox("NAME_TEXT_FIELD").setText(name);
                            window.getTextBox("DESCRIPTION_TEXT_FIELD").setText(description);
                            window.getButton("Add Account").click();

                            System.out.println("Account table after adding the account---->"+accountsTable.toString());


                            // Create the expectedTable array
                            String[][] expectedTable = new String[][] { { name, description } };


                            // vérifier que le compte a été ajouté
                            assertEquals(accountsTable.getRowCount(), rowCount+1);

                            // vérifier que la table de données est correcte
                            window.getTable().contentEquals(expectedTable);

                            // supprimer le compte cree precedemment en cherchant le nom
                            accountsTable.selectRowsWithText(0, name);
                            window.getButton("Delete Account").click();

                            System.out.println("Account table after deleting the account---->"+accountsTable.toString());

                            // vérifier que le compte a été supprimé
                            assertEquals(accountsTable.getRowCount(), rowCount);

                            // retourner un "trigger" qui ferme la fenêtre modale
                            ret = window.getButton("Close").triggerClick();

                        }

                        return window.getButton("Close").triggerClick();
                    }
                }).run();
                return ret;
            }
        }).run();
    }





    public static String getRandomName() {
        String[] firstNames = {"Emma", "Liam", "Olivia", "Noah", "Ava", "Ethan", "Sophia", "Lucas", "Mia", "Mason", "Isabella", "Logan", "Charlotte", "Elijah", "Amelia", "Caleb", "Harper", "Aiden", "Abigail", "Gabriel", "Emily", "Benjamin", "Evelyn", "Owen", "Elizabeth", "William", "Avery", "Michael", "Sofia", "Alexander", "Ella", "Daniel", "Madison", "Matthew", "Scarlett", "Jackson", "Victoria", "Sebastian", "Grace", "Henry", "Chloe", "Samuel", "Aria", "David", "Lily", "Wyatt", "Zoe"};
        String[] lastNames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia", "Martinez", "Robinson", "Clark", "Rodriguez", "Lewis", "Lee", "Walker", "Hall", "Allen", "Young", "King", "Wright", "Scott", "Green", "Baker", "Adams", "Nelson", "Carter", "Mitchell", "Perez", "Roberts", "Turner", "Phillips", "Campbell", "Parker", "Evans", "Edwards", "Collins", "Stewart", "Sanchez", "Morris", "Rogers", "Reed", "Cook", "Morgan", "Bell", "Murphy", "Bailey", "Cooper", "Richardson", "Cox", "Howard", "Ward", "Torres", "Peterson", "Gray", "Ramirez", "James", "Watson", "Brooks", "Kelly", "Sanders", "Price", "Bennett", "Wood", "Barnes", "Ross", "Henderson", "Coleman", "Jenkins", "Perry", "Powell", "Sullivan", "Russell", "Ortiz", "Jensen", "Kim"};
        Random random = new Random();
        String firstName = firstNames[random.nextInt(firstNames.length)];
        String lastName = lastNames[random.nextInt(lastNames.length)];
        return firstName + " " + lastName;
    }

}