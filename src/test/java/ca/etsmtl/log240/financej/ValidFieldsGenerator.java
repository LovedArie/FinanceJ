package ca.etsmtl.log240.financej;

import java.util.ArrayList;
import java.util.Date;
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

    //Create a list of budget between Double.MIN_VALUE and Double.MAX_VALUE
    public static List<Double> generateValidBudgets() {
        List<Double> budgets = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            budgets.add(ThreadLocalRandom.current().nextDouble(Double.MIN_VALUE, Double.MAX_VALUE));
        }
        return budgets;
    }
    public static String getRandomName() {
        String[] firstNames = {"Emma", "Liam", "Olivia", "Noah", "Ava", "Ethan", "Sophia", "Lucas", "Mia", "Mason", "Isabella", "Logan", "Charlotte", "Elijah", "Amelia", "Caleb", "Harper", "Aiden", "Abigail", "Gabriel", "Emily", "Benjamin", "Evelyn", "Owen", "Elizabeth", "William", "Avery", "Michael", "Sofia", "Alexander", "Ella", "Daniel", "Madison", "Matthew", "Scarlett", "Jackson", "Victoria", "Sebastian", "Grace", "Henry", "Chloe", "Samuel", "Aria", "David", "Lily", "Wyatt", "Zoe"};
        String[] lastNames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia", "Martinez", "Robinson", "Clark", "Rodriguez", "Lewis", "Lee", "Walker", "Hall", "Allen", "Young", "King", "Wright", "Scott", "Green", "Baker", "Adams", "Nelson", "Carter", "Mitchell", "Perez", "Roberts", "Turner", "Phillips", "Campbell", "Parker", "Evans", "Edwards", "Collins", "Stewart", "Sanchez", "Morris", "Rogers", "Reed", "Cook", "Morgan", "Bell", "Murphy", "Bailey", "Cooper", "Richardson", "Cox", "Howard", "Ward", "Torres", "Peterson", "Gray", "Ramirez", "James", "Watson", "Brooks", "Kelly", "Sanders", "Price", "Bennett", "Wood", "Barnes", "Ross", "Henderson", "Coleman", "Jenkins", "Perry", "Powell", "Sullivan", "Russell", "Ortiz", "Jensen", "Kim"};
        Random random = new Random();
        String firstName = firstNames[random.nextInt(firstNames.length)];
        String lastName = lastNames[random.nextInt(lastNames.length)];
        return firstName + "" + lastName;
    }

    public static List<Date> getValidRandomDate() {
        // return random day beetwen 1 and 31
        // return random month beetwen 1 and 12
        // return random year beetwen -9999 and 9999
        int day;
        int month;
        int year;
        boolean bool = true;
        int count = 0;
        List<Date> dates = new ArrayList<>();
        Date date = new Date();
        while (bool) {
            day = ThreadLocalRandom.current().nextInt(1, 31 + 1);
            month = ThreadLocalRandom.current().nextInt(1, 12 + 1);
            year = ThreadLocalRandom.current().nextInt(-9999, 9999 + 1);
            date.setDate(day);date.setMonth(month);date.setYear(year);
            if(checkIfDateIsValid(date)) {
                dates.add(date);
                if(count == 100) {
                    bool = false;
                }
            }
            count++;
        }
        // print date format AAAA-MM-JJ
        //System.out.println("Date: " + year + "-" + month + "-" + day);
        return dates;
    }

    public static boolean checkIfDateIsValid(Date date) {
        //check if the Date is valid
        if (date.getYear() < -9999 || date.getYear() > 9999) {
            return false;
        }
        if (date.getMonth() < 1 || date.getMonth() > 12) {
            return false;
        }
        if (date.getDate() < 1 || date.getDate() > 31) {
            return false;
        }
        //check if the day is valid for its month (bissextil year for example)
        if (date.getMonth() == 2) {
            if (date.getDate() > 29) {
                return false;
            }
            if (date.getDate() == 29 && date.getYear() % 4 != 0) {
                return false;
            }
        }
        if (date.getMonth() == 4 || date.getMonth() == 6 || date.getMonth() == 9 || date.getMonth() == 11) {
            if (date.getDate() > 30) {
                return false;
            }
        }
        return true;
    }

    //generate random list of amount between MIN DOUBLE VALUE and MAX DOUBLE VALUE
    public static List<Double> generateValidRandomAmount() {
        List<Double> amounts = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            amounts.add(ThreadLocalRandom.current().nextDouble(Double.MIN_VALUE, Double.MAX_VALUE));
        }
        return amounts;
    }

    // generate random list of boolean
    public static List<Boolean> generateValidRandomBoolean() {
        List<Boolean> booleans = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            booleans.add(ThreadLocalRandom.current().nextBoolean());
        }
        return booleans;
    }

    // generate invalid description of account
    public static List<String> generateInvalidDescription() {
        List<String> descriptions = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            StringBuilder sb = new StringBuilder();
            int length = ThreadLocalRandom.current().nextInt(0, 300 + 1);
            for (int j = 0; j < length; j++) {
                //Append special character (!, @, #, $, %, ^, &, *, (, ), _, +, =, {, }, [, ], |, :, ;, ', ", <, >, ?, /, \)
                sb.append((char) ThreadLocalRandom.current().nextInt(33, 47 + 1));
            }
            descriptions.add(sb.toString());
        }
        return descriptions;
    }
}
