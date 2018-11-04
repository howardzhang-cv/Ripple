package co.ripple.ripple;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.MessageListener;
import com.google.android.gms.nearby.messages.MessagesClient;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public User user;
    public MessageListener messageListener;
    public MessagesClient client;

    public ArrayList<String> channels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChannelsFragment fragment = new ChannelsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();

        //initialize Google Nearby client
        client = Nearby.getMessagesClient(this);

        //initialize user preferences
        user = new User(this);

        //initialize listener
        messageListener = new MessageListener(){
            @Override
            public void onFound(Message message) {
                Request request = new Request(new String(message.getContent()));
                if(user.isIncluded(request.getChannelId())){
                    user.addReceived(request);
                }
            }

            @Override
            public void onLost(Message message) {
                Request request = new Request(new String(message.getContent()));
                if(user.isIncluded(request.getChannelId())){
                    user.removeReceived(request);
                }
            }
        };
    }

    //subscribes to listener
    @Override
    protected void onStart(){
        super.onStart();
        client.subscribe(messageListener);
    }

    //unsubscribes and unpublishes
    @Override
    protected void onStop(){
        super.onStop();
        client.unsubscribe(messageListener);
        user.unpublishSent(client);
        user.save();
    }

    //called when request is to be published
    public void publishRequest(Request r){
        Message message = new Message(r.serialize().getBytes());
        client.publish(message);
        user.addSent(message);
    }

}
