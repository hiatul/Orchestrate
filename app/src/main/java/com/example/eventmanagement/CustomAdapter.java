package com.example.eventmanagement;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAdapter extends BaseAdapter{   
    String [] result;
    Context context;
 int [] imageId;
      private static LayoutInflater inflater=null;
    public CustomAdapter(SocietyList societyList, String[] societyNameList) {
        // TODO Auto-generated constructor stub
        result=societyNameList;
        context=societyList;
      //  imageId=societyImages;
         inflater = ( LayoutInflater )context.
                 getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;       
             rowView = inflater.inflate(R.layout.society_list_content, null);
             holder.tv=(TextView) rowView.findViewById(R.id.textSocietyName);
        //     holder.img=(ImageView) rowView.findViewById(R.id.society_image);       
         holder.tv.setText(result[position]);
       //  holder.img.setImageResource(imageId[position]);
         final int itemPosition = position;
         rowView.setOnClickListener(new OnClickListener() {            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_LONG).show();
                switch(itemPosition){
              
            case 0 :Intent appInfo = new Intent(context, Scie.class);
            context.startActivity(appInfo);
         break;
       case 1 :Intent app = new Intent(context, Iste.class);
            context.startActivity(app);
         break;
         case 2 :Intent apps = new Intent(context, Sae.class);
            context.startActivity(apps);
         break;
                }
            }
        });   
        return rowView;
    }

}
