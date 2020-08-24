package com.pucosauniverse.pshare.ui.receive;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReceiveViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ReceiveViewModel() {
        mText = new MutableLiveData<> ();
        mText.setValue ("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}