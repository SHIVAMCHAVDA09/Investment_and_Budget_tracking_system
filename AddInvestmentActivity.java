package com.example.mcminiprojectjava;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mcminiprojectjava.database.DatabaseHelper;

public class AddInvestmentActivity extends AppCompatActivity {

    private EditText nameEditText, amountEditText, dateEditText;
    private Spinner typeSpinner;
    private Button saveButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_investment);

        nameEditText = findViewById(R.id.nameEditText);
        amountEditText = findViewById(R.id.amountEditText);
        dateEditText = findViewById(R.id.dateEditText);
        typeSpinner = findViewById(R.id.typeSpinner);
        saveButton = findViewById(R.id.saveButton);

        dbHelper = new DatabaseHelper(this);

        // Set up the spinner for investment types
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.investment_types,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);

        saveButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString();
            double amount = Double.parseDouble(amountEditText.getText().toString());
            String date = dateEditText.getText().toString();
            String type = typeSpinner.getSelectedItem().toString(); // Get selected investment type

            // Add the investment to the database
            dbHelper.addInvestment(name, amount, date, type);
            Toast.makeText(AddInvestmentActivity.this, "Investment added", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
