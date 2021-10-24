package com.example.rewaa;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Drawer_Adapter extends RecyclerView.Adapter<Drawer_Adapter.ViewHolder> {
    ArrayList<String> menu_title_array;
    ArrayList<Integer> menu_title_icon;
    Context context;
    selectdraweritem selectdraweritem;


    public Drawer_Adapter(ArrayList<String> menu_title_array, ArrayList<Integer> menu_title_icon, Home context) {
        this.menu_title_array = menu_title_array;
        this.menu_title_icon = menu_title_icon;
        this.context = context;

        try {
            this.selectdraweritem = ((selectdraweritem) context);
        } catch (ClassCastException e) {
            throw new ClassCastException("Error");
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.layout_drawer, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(menu_title_array.get(position));
        holder.menu_icon.setImageResource(menu_title_icon.get(position));
    }

    @Override
    public int getItemCount() {
        return menu_title_array.size();
    }

    public interface selectdraweritem {

        public void openselecteddraweritem(int pos);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView menu_icon;
        LinearLayout menu_item;
        TextView name;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            menu_item = itemView.findViewById(R.id.drawer_item_view);
            menu_icon = itemView.findViewById(R.id.drawer_image);
            name = itemView.findViewById(R.id.name);
            menu_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectdraweritem.openselecteddraweritem(getAdapterPosition());

                }
            });

        }
    }
}

