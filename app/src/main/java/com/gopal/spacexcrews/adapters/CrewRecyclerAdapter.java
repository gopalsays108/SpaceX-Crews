package com.gopal.spacexcrews.adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gopal.spacexcrews.R;
import com.gopal.spacexcrews.modal.CrewsModal;
import com.gopal.spacexcrews.viewmodal.CrewsViewModal;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CrewRecyclerAdapter extends RecyclerView.Adapter<CrewRecyclerAdapter.ViewHolder> {

    private final Context context;
    private List<CrewsModal> crewList;
    private final CrewsViewModal crewsViewModal;

    public CrewRecyclerAdapter(Context context, List<CrewsModal> crewList,
                               CrewsViewModal crewsViewModal) {
        this.context = context;
        this.crewList = crewList;
        this.crewsViewModal = crewsViewModal;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder( LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.crew_list, parent, false ) );
    }

    @Override
    public void onBindViewHolder(@NonNull CrewRecyclerAdapter.ViewHolder holder, int position) {
        holder.getName().setText( crewList.get( position ).getName() );
        holder.getActive().setText( crewList.get( position ).getStatus() );
        holder.getAgency().setText( crewList.get( position ).getAgency() );

        Glide.with( context ).load( crewList.get( position ).getImage() )
                .centerCrop()
                .into( holder.getCircleImageView() );

        holder.getDelete().setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crewsViewModal.deleteUserById( crewList.get( holder.getAdapterPosition() ).getId() );
            }
        } );

        holder.getCardView().setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlString = crewList.get( holder.getAdapterPosition() ).getWikipedia();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage("com.android.chrome");
                try {
                    context.startActivity(intent);
                } catch (ActivityNotFoundException ex) {
                    // Chrome browser presumably not installed so allow user to choose instead
                    intent.setPackage(null);
                    context.startActivity(intent);
                }
            }
        } );

    }

    public void getAllActor(List<CrewsModal> modal) {
        this.crewList = modal;
    }

    @Override
    public int getItemCount() {
        return crewList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView agency;
        private final TextView active;
        private final CircleImageView circleImageView;
        private final CardView cardView;
        private final ImageView delete;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            name = itemView.findViewById( R.id.name );
            agency = itemView.findViewById( R.id.agency );
            active = itemView.findViewById( R.id.active );
            circleImageView = itemView.findViewById( R.id.circleImageView );
            delete = itemView.findViewById( R.id.delete );
            cardView = itemView.findViewById( R.id.cardview );
        }

        public TextView getName() {
            return name;
        }

        public TextView getAgency() {
            return agency;
        }

        public TextView getActive() {
            return active;
        }

        public CircleImageView getCircleImageView() {
            return circleImageView;
        }

        public ImageView getDelete() {
            return delete;
        }
        public CardView getCardView() {
            return cardView;
        }
    }
}
