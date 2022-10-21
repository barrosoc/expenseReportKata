package org.example;

import org.example.ExpenseReport.Expense;

import java.util.List;

public class Expenses {

    private final List<Expense> expenses;

    public Expenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public int calculateMealExpenses() {
        return expenses.stream()
                .filter(e -> e.type == ExpenseReport.ExpenseType.DINNER || e.type == ExpenseReport.ExpenseType.BREAKFAST)
                .mapToInt(e -> e.amount)
                .sum();
    }
}
