package com.pineapple.chat.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pineapple.chat.DB.Model.Message;
import com.pineapple.chat.R;

import java.util.List;

public class RecyclerMessage extends RecyclerView.Adapter<RecyclerMessage.ViewHolder>  {

    private List<Message> messageList;
    private Context context;

    public RecyclerMessage(List<Message> message, Context context) {
        messageList = message;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_message, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(messageList.get(position).getName());
        holder.textMessage.setText(messageList.get(position).getText());
    }

    @Override
    public int getItemCount() {
        if (messageList == null) {
            return 0;
        }
            return messageList.size();
    }

    //class
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textMessage, name;

        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.name);
            textMessage = (TextView) v.findViewById(R.id.textMessage);
        }
    }
}
