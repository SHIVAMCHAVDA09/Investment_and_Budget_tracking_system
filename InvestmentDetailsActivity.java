package com.example.mcminiprojectjava;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mcminiprojectjava.database.DatabaseHelper;
import com.example.mcminiprojectjava.model.Investment;

import java.util.List;

public class InvestmentDetailsActivity extends AppCompatActivity {

    private TableLayout investmentTable;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment_details);

        investmentTable = findViewById(R.id.investmentTable);
        dbHelper = new DatabaseHelper(this);

        // Fetch the investment data from the database
        List<Investment> investmentList = dbHelper.getAllInvestments();

        // Populate the table with investment data
        populateTable(investmentList);
    }

    private void populateTable(List<Investment> investmentList) {
        for (Investment investment : investmentList) {
            // Create a new TableRow
            TableRow tableRow = new TableRow(this);

            // Create TextViews for each column
            TextView nameTextView = new TextView(this);
            nameTextView.setText(investment.getName());
            nameTextView.setPadding(8, 8, 8, 8);

            TextView amountTextView = new TextView(this);
            amountTextView.setText(String.valueOf(investment.getAmount()));
            amountTextView.setPadding(8, 8, 8, 8);

            TextView dateTextView = new TextView(this);
            dateTextView.setText(investment.getDate());
            dateTextView.setPadding(8, 8, 8, 8);

            TextView typeTextView = new TextView(this); // New TextView for investment type
            typeTextView.setText(investment.getType()); // Get the type from the Investment object
            typeTextView.setPadding(8, 8, 8, 8);

            // Add the TextViews to the TableRow
            tableRow.addView(nameTextView);
            tableRow.addView(amountTextView);
            tableRow.addView(dateTextView);
            tableRow.addView(typeTextView); // Add the investment type TextView

            // Add the TableRow to the TableLayout
            investmentTable.addView(tableRow);
        }
    }
}
