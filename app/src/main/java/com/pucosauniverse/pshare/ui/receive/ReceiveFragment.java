package com.pucosauniverse.pshare.ui.receive;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.pucosauniverse.pshare.R;

public class ReceiveFragment extends Fragment {

    private ReceiveViewModel receiveViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        receiveViewModel =
                ViewModelProviders.of (this).get (ReceiveViewModel.class);
        View root = inflater.inflate (R.layout.fragment_receive, container, false);
        final TextView textView = root.findViewById (R.id.text_dashboard);
        receiveViewModel.getText ().observe (getViewLifecycleOwner (), new Observer<String> () {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText (s);
            }
        });
        return root;
    }
}