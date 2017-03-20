package com.pineapple.chat;

import android.content.Intent;
import android.util.Log;

import com.pineapple.chat.DB.Model.Message;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import static android.app.Service.START_STICKY_COMPATIBILITY;

public class SocketTest {

    private static final String TAG = "Soc";
    private Socket socket;
    List<Message> messageList;

    public SocketTest() {

    }


    public void startSockert() {
        try {
            socket = IO.socket("https://test-socket-dashika1992.c9users.io/");
            socket.on(Socket.EVENT_CONNECT, onConnect)
                    .on("message", onNewMessage)
                    .on(Socket.EVENT_DISCONNECT, onDisconnect)
                    .on(Socket.EVENT_ERROR, onDisconnect);
            socket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

        public int onStartCommand(Intent intent, int flags, int startId) {
            return START_STICKY_COMPATIBILITY;
        }

        private Emitter.Listener onNewMessage = new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                JSONObject data = (JSONObject) args[0];
                String name = "";
                String message = "";

                try {
                    name = data.getString("name");
                    message = data.getString("message");
                } catch (JSONException e) {
                    Log.e(TAG, e.getMessage());
                    return;
                }

                messageList.add(new Message(name, message));
            }
        };

        private Emitter.Listener onDisconnect = args -> {

        };

        private Emitter.Listener onConnect = args -> {

        };

        private Emitter.Listener onConnectError = new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.e(TAG, "Socket Error connecting");
            }
        };

    public Socket getSocket() {
        return socket;
    }

    public void onDestroy() {
        socket.disconnect();
        socket.off(Socket.EVENT_CONNECT, onConnect);
        socket.off(Socket.EVENT_DISCONNECT, onDisconnect);
        socket.off(Socket.EVENT_CONNECT_ERROR, onConnectError);
        socket.off(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
        socket.off("message", onNewMessage);
    }
}
