package com.example.patitasalcorazon;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patitasalcorazon.projectDatabase.Adoption;
import com.example.patitasalcorazon.projectDatabase.Service;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ServiceCatalogueAdapter extends RecyclerView.Adapter
{
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = inflater.inflate(R.layout.recycler_item_view_service, parent, false);

        return new ServiceCatalogueAdapter.ServiceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        ServiceCatalogueAdapter.ServiceViewHolder sHolder = (ServiceCatalogueAdapter.ServiceViewHolder) holder;
        if( services != null){
            Service s = services.get(position);
            sHolder.productName.setText(s.name);
            Picasso.get()
                    .load(s.image)
                    .into(sHolder.recyclerImage);
            sHolder.recyclerRating.setText(s.price);

        }else{
            sHolder.productName.setText("NONE");
        }
    }

    @Override
    public int getItemCount()
    {
        if( services != null)
            return services.size();
        return 0;
    }

    class ServiceViewHolder extends RecyclerView.ViewHolder
    {
        private TextView productName;
        private ImageView recyclerImage;
        private TextView recyclerRating;

        public int index;

        public ServiceViewHolder(View itemView)
        {
            super(itemView);
            productName = itemView.findViewById(R.id.recyclerProductNameS);
            recyclerImage = itemView.findViewById(R.id.recyclerImageS);
            recyclerRating = itemView.findViewById(R.id.recyclerRatingS);

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
    private List<Service> services;

    ServiceActivity callTo;
    public ServiceCatalogueAdapter(ServiceActivity context)
    {
        inflater = LayoutInflater.from(context);
        callTo = context;
    }

    void setServices(List<Service> services){
        this.services = services;
        notifyDataSetChanged();
    }
}
