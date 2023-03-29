package ca.etsmtl.log240.financej;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ValidFieldsGenerator {
    //Create a list of accounts names between 2 and 50 characters with alphanumeric characters
    public static List<String> generateValidAccountNames() {
        List<String> names = new ArrayList<>();

        for (int length = 2; length <= 50; length++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                //Append random alphanumeric character to name of account
                if (ThreadLocalRandom.current().nextBoolean()) {
                    sb.append((char) ThreadLocalRandom.current().nextInt('a', 'z' + 1));
                } else {
                    sb.append((char) ThreadLocalRandom.current().nextInt('0', '9' + 1));
                }
            }
            names.add(sb.toString());
        }

//            System.out.println("List of valid account names---->" + names.toString());
        return names;
    }
    //Create a list of descriptions between 1 and 250 characters with any printable characters
    public static List<String> generateValidDescriptions() {
        List<String> descriptions = new ArrayList<>();

        for (int length = 1; length <= 250; length++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                //Append random printable character for description of account
                char randomChar = (char) ThreadLocalRandom.current().nextInt(32, 127); // limit to ASCII printable characters
                sb.append(randomChar);
            }
            descriptions.add(sb.toString());
        }

//            System.out.println("List of valid account descriptions---->" + descriptions.toString());
        return descriptions;
    }
    public static String getRandomName() {
        String[] firstNames = {"Emma", "Liam", "Olivia", "Noah", "Ava", "Ethan", "Sophia", "Lucas", "Mia", "Mason", "Isabella", "Logan", "Charlotte", "Elijah", "Amelia", "Caleb", "Harper", "Aiden", "Abigail", "Gabriel", "Emily", "Benjamin", "Evelyn", "Owen", "Elizabeth", "William", "Avery", "Michael", "Sofia", "Alexander", "Ella", "Daniel", "Madison", "Matthew", "Scarlett", "Jackson", "Victoria", "Sebastian", "Grace", "Henry", "Chloe", "Samuel", "Aria", "David", "Lily", "Wyatt", "Zoe"};
        String[] lastNames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia", "Martinez", "Robinson", "Clark", "Rodriguez", "Lewis", "Lee", "Walker", "Hall", "Allen", "Young", "King", "Wright", "Scott", "Green", "Baker", "Adams", "Nelson", "Carter", "Mitchell", "Perez", "Roberts", "Turner", "Phillips", "Campbell", "Parker", "Evans", "Edwards", "Collins", "Stewart", "Sanchez", "Morris", "Rogers", "Reed", "Cook", "Morgan", "Bell", "Murphy", "Bailey", "Cooper", "Richardson", "Cox", "Howard", "Ward", "Torres", "Peterson", "Gray", "Ramirez", "James", "Watson", "Brooks", "Kelly", "Sanders", "Price", "Bennett", "Wood", "Barnes", "Ross", "Henderson", "Coleman", "Jenkins", "Perry", "Powell", "Sullivan", "Russell", "Ortiz", "Jensen", "Kim"};
        Random random = new Random();
        String firstName = firstNames[random.nextInt(firstNames.length)];
        String lastName = lastNames[random.nextInt(lastNames.length)];
        return firstName + "" + lastName;
    }
}
