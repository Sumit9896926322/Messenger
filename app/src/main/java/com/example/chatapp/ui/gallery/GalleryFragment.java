package com.example.chatapp.ui.gallery;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.chatapp.R;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private  TextView textView;
    private ImageView editStatus;
    private ImageView editPhone;
    private AlertDialog pop;
    private EditText userChange;
    private String currChange;
    private  String performAction;
    private TextView userStatus;
    private Button follow;
    private TextView userContact;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        editPhone = (ImageView) root.findViewById(R.id.editPhone);
        editStatus = (ImageView) root.findViewById(R.id.editStatus);
        follow = (Button) root.findViewById(R.id.followBtn);

        editPhone.setClickable(true);
        editStatus.setClickable(true);
        pop = new AlertDialog.Builder(getContext()).create();
        userChange = new EditText(getContext());

        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeUserValue("contact");
            }
        });
        editStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeUserValue("status");

            }
        });
        editPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeUserValue("contact");

            }
        });

        pop.setButton(DialogInterface.BUTTON_POSITIVE, performAction, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.w("Popup",performAction);
                if(currChange.equals("contact"))
                    userContact.setText(userChange.getText().toString());
                else
                    userContact.setText(userStatus.getText().toString());

            }
        });

        return root;
    }

    public  void changeUserValue(String action){
        Log.w("Click",action);

        if(action.equals("contact"))
            pop.setTitle("Edit the status");
        else
            pop.setTitle("Edit Contact Number");

        performAction = action;
        currChange = action;
        pop.setView(userChange);
        pop.show();
    }
}