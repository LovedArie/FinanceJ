package ca.etsmtl.log240.financej;
import org.uispec4j.*;
import org.uispec4j.interception.*;
import java.util.Random;
import org.junit.Test;
public class AccountTest extends FinancejAbstractTest {
    private Table accountsTable;
    private Trigger ret;
    protected Button accountsButton;


    protected void setUp() throws Exception {
        super.setUp();

        accountsButton = getMainWindow().getButton("Comptes");
        accountsButton.click();
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
            final String name = getRandomName();

            WindowInterceptor.init(accountsButton.triggerClick()).process(new WindowHandler() {

                @Override
                public Trigger process(Window window) throws Exception {

                    accountsTable = window.getTable();
                    int initialRowCount = accountsTable.getRowCount();

                    // delete all rows with the specified name if they exist
                    if (initialRowCount > 0) {
                        for (int i = 0; i < initialRowCount; i++) {
                            // select the first row with the specified name and delete it
                            accountsTable.selectRow(i);
                            window.getButton("Delete Account").click();
                        }

                    }

                    if (accountsTable.getRowCount() == 0) {
                        System.out.println("Account1---->"+accountsTable);
                        // ajouter un compte
                        window.getTextBox("NAME_TEXT_FIELD").setText(name);
                        window.getTextBox("DESCRIPTION_TEXT_FIELD").setText("Savings");
                        window.getButton("Add Account").click();
                        System.out.println("Account1---->"+accountsTable);
                        assertEquals(accountsTable.getRowCount(), initialRowCount+1);
                        // supprimer le compte cree precedemment en cherchant le nom
                        accountsTable.selectRowsWithText(0, name);
                        window.getButton("Delete Account").click();

                        assertEquals(accountsTable.getRowCount(), initialRowCount);

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



    protected void tearDown() throws Exception {
        super.tearDown();
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