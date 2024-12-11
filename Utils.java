package com.example.mcminiprojectjava.util;

import android.content.Context;
import android.widget.Toast;

public class Utils {

    // Method to show a toast message
    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    // Method to format currency value
    public static String formatCurrency(double amount) {
        return String.format("$%.2f", amount);
    }

    // Method to validate a non-empty string
    public static boolean isNotEmpty(String text) {
        return text != null && !text.trim().isEmpty();
    }
}
