package com.example.zerohunger;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

public class SignUpTab extends Fragment {

    private EditText signupEmail, signupPassword, signupConfirm;
    private Button signupButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sign_up_tab, container, false);

        signupEmail = rootView.findViewById(R.id.signup_email);
        signupPassword = rootView.findViewById(R.id.signup_password);
        signupConfirm = rootView.findViewById(R.id.signup_confirm);
        signupButton = rootView.findViewById(R.id.signup_button);

        // Get reference to ViewPager2 from the parent activity
        ViewPager2 viewPager = getActivity().findViewById(R.id.view_pager);
        TabLayout tabLayout = getActivity().findViewById(R.id.tab_layout);

        signupButton.setOnClickListener(v -> {
            String email = signupEmail.getText().toString().trim();
            String password = signupPassword.getText().toString().trim();
            String confirmPassword = signupConfirm.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(getActivity(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confirmPassword)) {
                Toast.makeText(getActivity(), "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Register Successful", Toast.LENGTH_SHORT).show();

                // Switch to the Login tab
                if (viewPager != null && tabLayout != null) {
                    viewPager.setCurrentItem(0); // 0 is the position of LoginTab
                }
            }
        });

        return rootView;
    }
}
