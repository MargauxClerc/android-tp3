package fr.clerc.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.clerc.myapplication.model.Feature;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Feature> featureList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View view, Feature feature);
    }

    public RecyclerViewAdapter(List<Feature> featureList, OnItemClickListener listener) {
        this.featureList = featureList;
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return this.featureList.size();
    }

    public void addFeatureList(List<Feature> featureList) {
        this.featureList.addAll(featureList);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_feature, parent, false);
        return new RecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Feature feature = featureList.get(position);

        holder.image.setImageResource(R.drawable.metro_logo);
        holder.featureName.setText(feature.getProperties().getName());
        holder.featureStreet.setText(feature.getProperties().getStreet());
        holder.featureAddress.setText(feature.getProperties().getPostalCode() + " - " + feature.getProperties().getCity());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, feature);
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View itemView;
        final TextView featureName;
        final TextView featureStreet;
        final TextView featureAddress;
        final ImageView image;

        ViewHolder(View view) {
            super(view);
            itemView = view;
            featureName = view.findViewById(R.id.feature_name);
            featureStreet = view.findViewById(R.id.feature_street);
            featureAddress = view.findViewById(R.id.feature_address);
            image = view.findViewById(R.id.feature_type);
        }
    }
}
