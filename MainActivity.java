package com.example.mcminiprojectjava;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mcminiprojectjava.database.DatabaseHelper;
import com.example.mcminiprojectjava.model.Investment;
import com.example.mcminiprojectjava.OnInvestmentDeletedListener;


import java.util.List;

public class MainActivity extends AppCompatActivity implements OnInvestmentDeletedListener {

    private TextView totalInvestments;
    private Button addInvestmentBtn, viewDetailsBtn; // Added viewDetailsBtn
    private RecyclerView investmentsRecyclerView;
    private DatabaseHelper dbHelper;
    private List<Investment> investmentsList;
    private InvestmentsAdapter investmentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalInvestments = findViewById(R.id.totalInvestments);
        addInvestmentBtn = findViewById(R.id.addInvestmentBtn);
        viewDetailsBtn = findViewById(R.id.viewDetailsBtn); // Initialize viewDetailsBtn
        investmentsRecyclerView = findViewById(R.id.investmentsRecyclerView);

        dbHelper = new DatabaseHelper(this);
        investmentsList = dbHelper.getAllInvestments();

        investmentsAdapter = new InvestmentsAdapter(investmentsList, dbHelper, this); // Pass this as listener
        investmentsRecyclerView.setAdapter(investmentsAdapter);
        investmentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        updateTotalInvestment();

        addInvestmentBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddInvestmentActivity.class);
            startActivity(intent);
        });

        // Set up the click listener for the View Details button
        viewDetailsBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, InvestmentDetailsActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onInvestmentDeleted() {
        updateTotalInvestment(); // Update total investment when an item is deleted
    }

    private void updateTotalInvestment() {
        double total = 0.0;
        for (Investment investment : investmentsList) {
            total += investment.getAmount();
        }
        totalInvestments.setText("Total Investments: $" + total);
    }
}
