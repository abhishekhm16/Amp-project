package com.example.logo;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class parameters extends ArrayAdapter<String> {
    private String[] paraNames;
    private String[] fullNames;
    private Integer[] imageid;
    private Activity context;

    public parameters(Activity context, String[] paraNames, String[] fullNames, Integer[] imageid) {
        super(context, R.layout.row_item, paraNames);
        this.context = context;
        this.paraNames = paraNames;
        this.fullNames = fullNames;
        this.imageid = imageid;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView==null)
            row = inflater.inflate(R.layout.row_item, null, true);
        TextView textViewPara = (TextView) row.findViewById(R.id.textViewParaNames);
        TextView textViewfull = (TextView) row.findViewById(R.id.textViewfullNames);
        ImageView imagelogo = (ImageView) row.findViewById(R.id.imageViewlogo);

        textViewPara.setText(paraNames[position]);
        textViewfull.setText(fullNames[position]);
        imagelogo.setImageResource(imageid[position]);
        return  row;
    }
}
