package br.univali.contatmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import br.univali.contatmanager.R;
import br.univali.contatmanager.database.DatabaseHelper;

public class ContactActivity extends AppCompatActivity {

    private List<PhoneNumberView> phoneNumberViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        // Initialize the list to store phone number views
        phoneNumberViews = new ArrayList<>();

        // Get references to the UI elements
        final EditText nameEditText = findViewById(R.id.nameEditText);
        final LinearLayout phoneNumberLayout = findViewById(R.id.phoneNumberLayout);
        Button addPhoneNumberButton = findViewById(R.id.addPhoneNumberButton);
        Button saveContactButton = findViewById(R.id.saveContactButton);

        // Set click listener for the "Add Phone Number" button
        addPhoneNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPhoneNumberView(phoneNumberLayout);
            }
        });

        // Set click listener for the "Save Contact" button
        saveContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveContact(nameEditText.getText().toString());
            }
        });
    }

    private void addPhoneNumberView(final LinearLayout phoneNumberLayout) {
        // Inflate the phone number layout and add it to the parent layout
        View phoneNumberView = getLayoutInflater().inflate(R.layout.phone_item, phoneNumberLayout, false);
        phoneNumberLayout.addView(phoneNumberView);

        // Create a PhoneNumberView object and add it to the list
        PhoneNumberView newPhoneNumberView = new PhoneNumberView(phoneNumberView);
        phoneNumberViews.add(newPhoneNumberView);

        // Set click listener for the remove button of the phone number view
        newPhoneNumberView.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removePhoneNumberView(newPhoneNumberView, phoneNumberLayout);
            }
        });
    }

    private void removePhoneNumberView(PhoneNumberView phoneNumberView, LinearLayout phoneNumberLayout) {
        // Remove the phone number view from the parent layout
        phoneNumberLayout.removeView(phoneNumberView.itemView);

        // Remove the PhoneNumberView object from the list
        phoneNumberViews.remove(phoneNumberView);
    }

    private void saveContact(String name) {
        // Create a new Contact object with the provided name
        Contato newContact = new Contato();
        Pessoa p = new Pessoa(name);

        DatabaseHelper db = new DatabaseHelper(this);
        long id = db.createPessoa(p);

        // Iterate over the phone number views and add the phone numbers to the contact
        for (PhoneNumberView phoneNumberView : phoneNumberViews) {
            String phoneNumber = phoneNumberView.phoneNumberEditText.getText().toString();
            String phoneNumberType = phoneNumberView.phoneNumberTypeEditText.getSelectedItem().toString();
            newContact.setNumber(phoneNumber);
            newContact.setType(phoneNumberType);
            p.addContato(newContact);
            db.createContato(id, newContact);
        }

        clearUI();
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    private void clearUI() {
        // Clear the name field
        EditText nameEditText = findViewById(R.id.nameEditText);
        nameEditText.setText("");

        // Clear the phone number fields and remove the phone number views
        LinearLayout phoneNumberLayout = findViewById(R.id.phoneNumberLayout);
        phoneNumberLayout.removeAllViews();
        phoneNumberViews.clear();
    }


    // Inner class to represent a phone number view
    private class PhoneNumberView {
        View itemView;
        EditText phoneNumberEditText;
        Spinner phoneNumberTypeEditText;
        Button removeButton;

        PhoneNumberView(View itemView) {
            this.itemView = itemView;
            phoneNumberEditText = itemView.findViewById(R.id.phoneNumberEditText);
            phoneNumberTypeEditText = itemView.findViewById(R.id.phoneNumberTypeSpinner);
            removeButton = itemView.findViewById(R.id.removeButton);
        }
    }
}

