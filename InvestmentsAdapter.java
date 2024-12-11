package com.example.mcminiprojectjava;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mcminiprojectjava.database.DatabaseHelper;
import com.example.mcminiprojectjava.model.Investment;

import java.util.List;

public class InvestmentsAdapter extends RecyclerView.Adapter<InvestmentsAdapter.InvestmentViewHolder> {

    private List<Investment> investmentsList;
    private DatabaseHelper dbHelper; // Database helper reference

    public InvestmentsAdapter(List<Investment> investmentsList, DatabaseHelper dbHelper, MainActivity mainActivity) {
        this.investmentsList = investmentsList;
        this.dbHelper = dbHelper; // Initialize database helper
    }

    @NonNull
    @Override
    public InvestmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_investment, parent, false);
        return new InvestmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvestmentViewHolder holder, int position) {
        Investment investment = investmentsList.get(position);
        holder.nameTextView.setText(investment.getName());
        holder.amountTextView.setText("Amount: $" + investment.getAmount());
        holder.dateTextView.setText("Date: " + investment.getDate());
        holder.typeTextView.setText("Type: " + investment.getType()); // Display investment type

        // Set up delete button click listener
        holder.deleteButton.setOnClickListener(v -> {
            dbHelper.deleteInvestment(investment.getId()); // Call to delete from database
            investmentsList.remove(position); // Remove from the list
            notifyItemRemoved(position); // Notify adapter about item removal
            notifyItemRangeChanged(position, investmentsList.size()); // Update item positions
        });
    }

    @Override
    public int getItemCount() {
        return investmentsList.size();
    }

    public static class InvestmentViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, amountTextView, dateTextView, typeTextView; // Added typeTextView
        Button deleteButton;

        public InvestmentViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            amountTextView = itemView.findViewById(R.id.amountTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            typeTextView = itemView.findViewById(R.id.typeTextView); // Added for displaying type
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
