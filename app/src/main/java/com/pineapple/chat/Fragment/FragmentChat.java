package com.pineapple.chat.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.pineapple.chat.Adapter.RecyclerMessage;
import com.pineapple.chat.DB.Model.Message;
import com.pineapple.chat.R;
import com.pineapple.chat.Service.AppService;
import com.pineapple.chat.SocketTest;

import java.util.ArrayList;
import java.util.List;

public class FragmentChat extends Fragment {

    private EditText inName, inMessage;
    private Button send;
    public RecyclerView mRecyclerView;
    public RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    SocketTest socketTest;
    List<Message> messageList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chat, null);

        findId(v);
        recyclerStart(v);

        socketTest = new SocketTest();
        send.setOnClickListener(v1 -> attemptSend(v));

        return v;
    }

    public void recyclerStart(View v) {
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView = (RecyclerView)v.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RecyclerMessage(messageList, getActivity());
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();
    }

    private void findId(View v) {
        inName = (EditText)v.findViewById(R.id.inputName);
        inMessage = (EditText)v.findViewById(R.id.inputMessage);

        send = (Button)v.findViewById(R.id.sendMessage);
    }
    private void sendMessage(String name, String message) {
        socketTest.startSockert();
        socketTest.getSocket().emit("identify", name);
        socketTest.getSocket().emit("inMessage", message);
    }

    private void attemptSend(View v) {
        messageList.add(new Message(inName.getText().toString() + " , ", " " + inMessage.getText().toString()));
        mAdapter.notifyDataSetChanged();

        String message = inMessage.getText().toString().trim();
        String name = inName.getText().toString().trim();

        inMessage.setText("");
        inName.setText("");
        sendMessage(name, message);
    }


}
