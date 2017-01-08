package com.danielszabo.android.capitalquiz;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;


public class ScoreBoardView extends BaseAdapter {

    private Context context;
    private List<Ranking> lstRanking;

    public ScoreBoardView(Context context, List<Ranking> lstRanking) {
        this.context = context;
        this.lstRanking = lstRanking;
    }

    @Override
    public int getCount() {
        return lstRanking.size();
    }

    @Override
    public Object getItem(int position) {
        return lstRanking.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row,null);

        //score
        TextView txtTop = (TextView)view.findViewById(R.id.txtTop);
        txtTop.setText(String.valueOf(lstRanking.get(position).getScore()));

        //name
        TextView txtName = (TextView)view.findViewById(R.id.txtName);
        txtName.setText(String.valueOf(lstRanking.get(position).getName()));

        return view;

    }
}
