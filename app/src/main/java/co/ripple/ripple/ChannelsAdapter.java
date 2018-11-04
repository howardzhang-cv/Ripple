package co.ripple.ripple;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew Kim on 11/3/18.
 */
public class ChannelsAdapter extends RecyclerView.Adapter {

    private ArrayList<String> channels;

    public ChannelsAdapter(List<String> channels){
        this.channels = (ArrayList) channels;
    }

    public static class ChannelsViewHolder extends RecyclerView.ViewHolder{

        private TextView channelName;

        public ChannelsViewHolder(@NonNull View itemView) {
            super(itemView);
            channelName = itemView.findViewById(R.id.channel_name);
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v =  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.snippet_channel_entry, viewGroup, false);
        ChannelsViewHolder vh = new ChannelsViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ChannelsViewHolder) holder).channelName.setText(channels.get(position));
    }

    @Override
    public int getItemCount() {
        return channels.size();
    }
}
