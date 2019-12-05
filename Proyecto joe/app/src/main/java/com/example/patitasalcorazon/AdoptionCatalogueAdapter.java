package com.example.patitasalcorazon;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patitasalcorazon.projectDatabase.Adoption;
import com.example.patitasalcorazon.projectDatabase.AdoptionViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdoptionCatalogueAdapter extends RecyclerView.Adapter
{
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = inflater.inflate(R.layout.recycler_item_view_adoption, parent, false);

        return new AdoptionCatalogueAdapter.AdoptionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        AdoptionViewHolder aHolder = (AdoptionViewHolder) holder;
        if( adoptions != null){
            Adoption a = adoptions.get(position);
            aHolder.productName.setText(a.name);
            Picasso.get()
                    .load(a.image)
                    .into(aHolder.recyclerImage);

        }else{
            aHolder.productName.setText("NONE");
        }
    }

    @Override
    public int getItemCount()
    {
        if( adoptions != null)
            return adoptions.size();
        return 0;
    }

    class AdoptionViewHolder extends RecyclerView.ViewHolder
    {
        private TextView productName;
        private ImageView recyclerImage;

        public int index;

        public AdoptionViewHolder(View itemView)
        {
            super(itemView);
            productName = itemView.findViewById(R.id.recyclerProductNameA);
            recyclerImage = itemView.findViewById(R.id.recyclerImageA);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    index = getAdapterPosition();
                    callTo.sendToNextScreen((String) productName.getText());
                }
            });
        }
    }

    private final LayoutInflater inflater;
    private List<Adoption> adoptions;

    AdoptionActivity callTo;
    public AdoptionCatalogueAdapter(AdoptionActivity context)
    {
        inflater = LayoutInflater.from(context);
        callTo = context;
    }

    void setAdoptions(List<Adoption> adoptions){
        this.adoptions = adoptions;
        notifyDataSetChanged();
    }
}
