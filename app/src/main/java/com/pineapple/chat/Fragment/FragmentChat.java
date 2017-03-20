package com.pineapple.chat.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.pineapple.chat.Adapter.RecyclerMessage;
import com.pineapple.chat.R;
import com.pineapple.chat.Service.AppService;

public class FragmentChat extends Fragment {

    private EditText inName, inMessage;
    private Button send;
    private AppService appService;
    private RecyclerView recyclerView;
    private RecyclerMessage adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chat, null);

        findId(v);
        send.setOnClickListener(v1 -> attemptSend());

        return v;
    }

    private void findId(View v) {
        inName = (EditText)v.findViewById(R.id.inputName);
        inMessage = (EditText)v.findViewById(R.id.inputMessage);

        send = (Button)v.findViewById(R.id.sendMessage);
    }
    private void sendMessage(String name, String message) {
        appService.getSocket().emit("identify", name);
        appService.getSocket().emit("inMessage", message);
    }

    private void attemptSend() {
        String message = inMessage.getText().toString().trim();
        String name = inName.getText().toString().trim();

        inMessage.setText("");
        inName.setText("");
        sendMessage(name, message);
    }
}
