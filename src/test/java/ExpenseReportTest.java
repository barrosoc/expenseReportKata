import org.example.ExpenseReport;
import org.example.ExpenseReport.Expense;
import org.example.ExpenseReport.ExpenseType;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ExpenseReportTest {

    private static final int MAX_AMOUNT = 6000;
    private static final List<ExpenseType> expenseTypes = Arrays.asList(ExpenseType.BREAKFAST, ExpenseType.BREAKFAST, ExpenseType.DINNER, ExpenseType.DINNER, ExpenseType.CAR_RENTAL);
    public static final int AMOUNT_INCREMENT = 30;

    @Test
    public void goldenMaster() throws IOException {
        ByteArrayOutputStream inMemoryStream = new ByteArrayOutputStream();
        PrintStream gamesResults = new PrintStream(inMemoryStream);
        System.setOut(gamesResults);
        ExpenseReport expenseReport = new ExpenseReport(new Date(0));
        List<Expense> expenses = new ArrayList<>();
        expenseReport.printReport(expenses);
        for (ExpenseType expenseType : expenseTypes) {
            for (int amount = 0; amount < MAX_AMOUNT; amount=amount + AMOUNT_INCREMENT) {
                expenses.add(new Expense(expenseType, amount));
                expenseReport.printReport(expenses);
            }
        }
        File file = new File("./goldenMaster.txt");
        assertEquals(readStream(new ByteArrayInputStream(inMemoryStream.toByteArray())), readContent(file));
    }

    public static void main(String[] args) throws FileNotFoundException {
        PrintStream goldenMasterFile = new PrintStream(new FileOutputStream("./goldenMaster.txt"));
        System.setOut(goldenMasterFile);
        ExpenseReport expenseReport = new ExpenseReport(new Date(0));
        List<Expense> expenses = new ArrayList<>();
        expenseReport.printReport(expenses);
        for (ExpenseType expenseType : expenseTypes) {
            for (int amount = 0; amount < MAX_AMOUNT; amount=amount+ AMOUNT_INCREMENT) {
                expenses.add(new Expense(expenseType, amount));
                expenseReport.printReport(expenses);
            }
        }
    }

    private static List<String> readContent(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        return readStream(fileInputStream);
    }

    private static List<String> readStream(InputStream byteArrayInputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(byteArrayInputStream));
        List<String> result = new ArrayList<>();
        while (reader.ready()) {
            result.add(reader.readLine());
        }
        return result;
    }
}
