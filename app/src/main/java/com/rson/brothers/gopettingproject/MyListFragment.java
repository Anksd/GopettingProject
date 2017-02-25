package com.rson.brothers.gopettingproject;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.rson.brothers.network.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brother on 2/25/2017.
 */
public class MyListFragment extends Fragment {

    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private RequestQueue requestQueue;
    private List<DataClass> DataList = new ArrayList<>();
    ListView DataRecyler;
    static int kartitem=0;
    TextView kart;
    private static int Global_position;
    private static  View Globalview;
    static int[] posiotioArray =new int[16];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mylistfragment,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        volleySingleton=VolleySingleton.getInstance();
        requestQueue=volleySingleton.getRequestQueue();
        DataRecyler=(ListView) getActivity().findViewById(R.id.listView2);
        kart= (TextView) getActivity().findViewById(R.id.textView);



        JsonObjectRequest request =new JsonObjectRequest(Request.Method.GET, "https://guidebook.com/service/v2/upcomingGuides/",
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //ToastDisplay.t(MainActivity.this,response.toString());
                DataList=parseJSONresponce(response);
                DataRecyler.setAdapter(new CustomAdapter(getActivity(),DataList));
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue.add(request);

        //DataRecyler= (RecyclerView) findViewById(R.id.recycler_view);
        // DataRecyler.setLayoutManager(new LinearLayoutManager(MainActivity.this));



        SetKart(0);

    }

    private  ArrayList<DataClass> parseJSONresponce(JSONObject response) {
        ArrayList<DataClass> DList = new ArrayList<>();
        if (response != null || response.length() > 0) {

            try {
                JSONArray arrayOBJ = response.getJSONArray(Keys.DATA);

                for (int i = 0; i < arrayOBJ.length(); i++) {
                    JSONObject currentData = arrayOBJ.getJSONObject(i);
                    String startdate = currentData.getString(Keys.STARTDATE);
                    String name = currentData.getString(Keys.NAME);
                    String enddate = currentData.getString(Keys.ENDDATE);
                    String ImageUrl = currentData.getString(Keys.ICON);

                    DataClass dataClass = new DataClass();
                    dataClass.setENDDATE(enddate);
                    dataClass.setICON(ImageUrl);
                    dataClass.setNAME(name);
                    dataClass.setSTARTDATE(startdate);

                    DList.add(dataClass);

                    //FinalData.append(startdate+"\n");
                }
                //ToastDisplay.t(MainActivity.this,FinalData.toString());


            } catch (JSONException e) {

            }

        }
        return DList;
    }



    public class CustomAdapter extends BaseAdapter {
        List<DataClass> result;
        Context context;
        private VolleySingleton volleySingleton;
        private ImageLoader imageLoader;

        private LayoutInflater inflater=null;

        public CustomAdapter(Context mainActivity, List<DataClass> data) {
            // TODO Auto-generated constructor stub
            result=data;
            context=mainActivity;

            volleySingleton=VolleySingleton.getInstance();
            imageLoader=volleySingleton.getImageLoader();

            inflater = ( LayoutInflater )context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return result.size();
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
            TextView tv1,tv2,tv3;
            ImageView img;
           /* Holder(View v){
                tv1=(TextView) v.findViewById(R.id.bname);
                tv2=(TextView) v.findViewById(R.id.start);
                tv3=(TextView) v.findViewById(R.id.end);
                img= (ImageView) v.findViewById(R.id.imageView);
            }*/

        }
        // Holder holder=null;
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            final Holder holder=new Holder();
            View rowView;
            //   if(rowView==null){
            inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.each_layout, null);
            //holder=new Holder(rowView);
            // rowView.setTag(holder);
       /*    }
            else {
                holder = (Holder) rowView.getTag();
            }*/

            holder.tv1=(TextView) rowView.findViewById(R.id.bname);
            holder.tv2=(TextView) rowView.findViewById(R.id.start);
            holder.tv3=(TextView) rowView.findViewById(R.id.end);
            holder.img= (ImageView) rowView.findViewById(R.id.imageView);

            // holder.tv.setTypeface(fontstyle);
            DataClass obj=new DataClass();
            obj=result.get(position);
            holder.tv1.setText(obj.getNAME());
            holder.tv2.setText(obj.getSTARTDATE());
            holder.tv3.setText(obj.getENDDATE());
            String imgUrl=obj.getICON();

            if(posiotioArray[position]==1){
                rowView.setBackgroundColor(getResources().getColor(R.color.pressed_color));
            }

            if(imgUrl!=null){
                imageLoader.get(imgUrl, new ImageLoader.ImageListener() {
                    @Override
                    public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                        holder.img.setImageBitmap(response.getBitmap());
                    }

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
            }

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub

                    Log.d("pos","value of position "+posiotioArray[position]);
                    if(posiotioArray[position]==0){
                        kartitem++;
                        posiotioArray[position]=1;
                        SetKart(kartitem);
                        Log.d("pos","value of position inside if "+posiotioArray[position]);
                        v.setBackgroundColor(getResources().getColor(R.color.pressed_color));
                        Toast.makeText(getActivity(), "This Item is add into cart", Toast.LENGTH_SHORT).show();

                    }else{
                        kartitem--;
                        posiotioArray[position]=0;
                        v.setBackgroundColor(getResources().getColor(R.color.default_color));
                        SetKart(kartitem);
                        Toast.makeText(getActivity(), "This Item is removed from cart", Toast.LENGTH_SHORT).show();
                    }

                }
            });
            return rowView;
        }

    }

    public void SetKart(int kartitem){
        kart.setText(""+kartitem);

    }
}
