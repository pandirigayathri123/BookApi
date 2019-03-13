package com.example.gayathri.booksapi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    Context context;
    ArrayList<MyModel> modelArrayList;

    public MyAdapter(Context context, ArrayList<MyModel> modelArrayList)
    {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(context).inflate(R.layout.books,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
      viewHolder.btitle.setText(modelArrayList.get(i).getT());
      viewHolder.bdesc.setText(modelArrayList.get(i).getD());
      viewHolder.bauthor.setText(modelArrayList.get(i).getA());
      Picasso.with(context).load(modelArrayList.get(i).getImg()).into(viewHolder.bimage);

    }

    @Override
    public int getItemCount() {

        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView bimage;
        TextView btitle,bdesc,bauthor;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bimage=itemView.findViewById(R.id.imageofbook);
            btitle=itemView.findViewById(R.id.title);
            bdesc=itemView.findViewById(R.id.bookdes);
            bauthor =itemView.findViewById(R.id.author);

        }
    }
}
