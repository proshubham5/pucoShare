package com.pucosauniverse.pshare.ui.send;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.pucosauniverse.pshare.R;

import static android.app.Activity.RESULT_OK;

public class SendFragment extends Fragment {

    private static int RESULT_LOAD_IMAGE = 1;

    private SendViewModel sendViewModel;
    private Button sendBtn;
    private Button selectBtn;
    private ImageView selectedImage;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                new ViewModelProvider (this).get (SendViewModel.class);
        View root = inflater.inflate (R.layout.fragment_send, container, false);

        initializeWidgets (root);
        setOnClickListeners ();

        sendViewModel.getText ().observe (getViewLifecycleOwner (), new Observer<String> () {
            @Override
            public void onChanged(@Nullable String s) {
                //
            }
        });

        return root;
    }

    private void initializeWidgets(View root) {
        sendBtn = root.findViewById (R.id.send_btn);
        selectBtn = root.findViewById (R.id.select_file_btn);
        selectedImage = root.findViewById (R.id.imgView);
    }

    private void setOnClickListeners() {
        sendBtn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                openGallery ();
            }
        });

        selectBtn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void openGallery() {
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult (requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selected = data.getData();
            selectedImage.setImageURI (selected);
        }
    }
}