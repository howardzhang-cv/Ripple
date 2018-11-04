package co.ripple.ripple;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.MessagesClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by howardzhang on 11/3/18.
 */


/*
User class stores:
    username - display username
    location - description of location
    subscribed channels - all channels subscribed to
    sent requests - organized into map, key: channel id
    received requests - organized into map, key: channel id
    address and port - used for direct messaging
 */
public class User {

    private String username;
    private String location;
    private String address;
    private String port;
    private List<Message> sent;
    private List<Request> received;
    private List<Integer> channels;

    public User(Context context) {
        SharedPreferences sp = context.getSharedPreferences("ripple", Context.MODE_PRIVATE);
        username = sp.getString("username", "anonymous");
        location = sp.getString("location", "unavailable");
        String channelString = sp.getString("channel", "");
        String[] channelSplit = channelString.split(" ");
        for(String channel : channelSplit){
            channels.add(Integer.parseInt(channel));
        }
        sent = new ArrayList<Message>();
        received = new ArrayList<Request>();
    }

    //adds and removes from list of subscribed channels
    public void addChannel(int id){
        channels.add(id);
    }
    public void removeChannel(int id){
        channels.remove(Integer.valueOf(id));
    }
    //confirms whether or not request is included
    public boolean isIncluded(int channelId){
        return channels.contains(channelId);
    }

    //updates location description
    public void updateLocation(String location){
        this.location = location;
    }

    //updates username
    public void updateUsername(String username){
        this.username = username;
    }

    //adds and removes from list of sent requests
    public void addSent(Message message){
        sent.add(message);
    }
    public void removeSent(Request request){
        sent.remove(request);
    }
    //unpublishes all sent requests
    public void unpublishSent(MessagesClient client){
        for(Message m : sent){
            client.unpublish(m);
        }
    }

    public String getAddress(){
        return address;
    }
    public String getPort(){
        return port;
    }

    //adds and removes from list of received requests
    public void addReceived(Request request){
        received.add(request);
    }
    public void removeReceived(Request request){
        received.remove(request);
    }
}
