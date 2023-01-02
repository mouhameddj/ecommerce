package com.example.e_commerce;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterRecord extends RecyclerView.Adapter<AdapterRecord.HolderRecord> {
    private Context context;
    private ArrayList<Modelrecord> recordslist ;
    DBhelper1 DB1;

    public AdapterRecord(Context context, ArrayList<Modelrecord> recordslist) {
        this.context = context;
        this.recordslist = recordslist;
        DB1= new DBhelper1(context);
    }

    @NonNull
    @Override
    public HolderRecord onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_add, parent ,false);
        return new HolderRecord(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderRecord holder, int position) {
        Modelrecord model = recordslist.get(position);
        String id =model.getId();
        String title1 =model.getTitle();
        String category1 =model.getCategory();
        String descreption1 =model.getDescreption();
        String img1 =model.getImage();
        String tel1 =model.getTel();
        String prix1 =model.getPrix();
        String user1 =model.getUser();


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
      holder.morebtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              showMoreDialog(
                     ""+position,
                      ""+id,
                      ""+img1,
                      ""+title1,
                      ""+descreption1,
                      ""+category1,
                      ""+tel1,
                     ""+prix1,
                      ""+user1

              );

          }
      });



    }

 private void showMoreDialog(String position, String id, String img1,String title1, String descreption1, String category1, String tel1, String prix1,String user1) {
       String[] options = {"Edit", "DELETE"};
       AlertDialog.Builder builder = new AlertDialog.Builder(context);
builder.setItems(options, new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which==1){
            DB1.deletedata(id);
            ((HomeActivity)context).onResume();

        }
        else if (which==0){
            Intent intent = new Intent(context,add_product.class);
            intent.putExtra("ID",id);
            intent.putExtra("IMG",img1);
            intent.putExtra("TITLE",title1);
            intent.putExtra("DESCR",descreption1);
            intent.putExtra("CATEG",category1);
            intent.putExtra("TEL",tel1);
            intent.putExtra("PRIX",prix1);

            intent.putExtra("USER",user1);


            intent.putExtra("IsEditMode",true);
            context.startActivity(intent);


        }

    }
});
builder.create().show();
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


    class HolderRecord extends RecyclerView.ViewHolder{
        ImageButton morebtn;
        ImageView imageview;
        TextView titletv,categorytv,prixtv;
        public HolderRecord(@NonNull View itemView) {
            super(itemView);
            imageview=itemView.findViewById(R.id.imageView);
            titletv=itemView.findViewById(R.id.titletv);
            categorytv=itemView.findViewById(R.id.categtv);
            prixtv=itemView.findViewById(R.id.prixtv);
            morebtn=itemView.findViewById(R.id.morebtn);


        }
    }
}
