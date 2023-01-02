package com.example.e_commerce;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterRecord1 extends RecyclerView.Adapter<AdapterRecord1.HolderRecord> {
    private Context context;
    private ArrayList<Modelrecord> recordslist ;
    DBhelper1 DB1;

    public AdapterRecord1(Context context, ArrayList<Modelrecord> recordslist) {
        this.context = context;
        this.recordslist = recordslist;
        DB1= new DBhelper1(context);
    }



    @NonNull
    @Override
    public AdapterRecord1.HolderRecord onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_add2, parent ,false);
        return new AdapterRecord1.HolderRecord(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecord1.HolderRecord holder, int position) {

      Modelrecord model = recordslist.get(position);
    String id =model.getId();
    String title1 =model.getTitle();
    String category1 =model.getCategory();
    String descreption1 =model.getDescreption();
    String img1 =model.getImage();
    String tel1 =model.getTel();
    String prix1 =model.getPrix();


      holder.titletv.setText(title1);
      holder.categorytv.setText(category1);
       holder.prixtv.setText(prix1);
        if (img1.equals("null"))
    {
        holder.imageview.setImageResource(R.drawable.ic_baseline_add_a_photo_24);

    }else{
        holder.imageview.setImageURI(Uri.parse(img1));
    }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context,RecorddetailsActivity.class);
                intent.putExtra("RECORD_ID",id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recordslist.size();
    }
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Modelrecord> getRecordslist() {
        return recordslist;
    }

    public void setRecordslist(ArrayList<Modelrecord> recordslist) {
        this.recordslist = recordslist;
    }

    public class HolderRecord extends RecyclerView.ViewHolder {

        ImageView imageview;
        TextView titletv,categorytv,prixtv;
        public HolderRecord(@NonNull View itemView) {
            super(itemView);
            imageview=itemView.findViewById(R.id.imageView);
            titletv=itemView.findViewById(R.id.titletv);
            categorytv=itemView.findViewById(R.id.categtv);
            prixtv=itemView.findViewById(R.id.prixtv);

        }
    }
}
