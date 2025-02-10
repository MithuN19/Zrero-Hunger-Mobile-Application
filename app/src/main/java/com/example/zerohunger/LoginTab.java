package com.example.zerohunger;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

public class LoginTab extends Fragment {

    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private TextView signUpText;

    // Hardcoded credentials (Replace with Firebase or database in future)
    private final String VALID_EMAIL = "fooddonate@gmail.com";
    private final String VALID_PASSWORD = "food1234";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_tab, container, false);

        // Initialize UI elements
        emailEditText = view.findViewById(R.id.login_email);
        passwordEditText = view.findViewById(R.id.login_password);
        loginButton = view.findViewById(R.id.login_button);
        signUpText = view.findViewById(R.id.signupText);

        // Get reference to ViewPager2 from the parent activity
        ViewPager2 viewPager = getActivity().findViewById(R.id.view_pager);
        TabLayout tabLayout = getActivity().findViewById(R.id.tab_layout);

        // Handle Login Button Click
        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (validateInputs(email, password)) {
                if (email.equals(VALID_EMAIL) && password.equals(VALID_PASSWORD)) {
                    Toast.makeText(getActivity(), "Login Successful!", Toast.LENGTH_SHORT).show();
                    navigateToDashboardActivity();
                } else {
                    Toast.makeText(getActivity(), "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity(), "Please enter a valid email and password", Toast.LENGTH_SHORT).show();
            }
        });

        // Handle Sign-Up Text Click (Navigate to SignUpTab)
        signUpText.setOnClickListener(v -> {
            if (viewPager != null && tabLayout != null) {
                viewPager.setCurrentItem(1); // 1 is the position of SignUpTab
            }
        });

        return view;
    }

    // Validate email and password input fields
    private boolean validateInputs(String email, String password) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                && !TextUtils.isEmpty(password) && password.length() >= 6;
    }

    // Navigate to Dashboard Activity
    private void navigateToDashboardActivity() {
        Intent intent = new Intent(getActivity(), dashboard.class);
        startActivity(intent);
        getActivity().finish(); // Finish the LoginTab Activity to prevent returning to it
    }
}
