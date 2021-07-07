package com.keos.earnwealth.tryit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.keos.earnwealth.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private JSONArray jsonArray;

    public CustomAdapter(Context context, JSONArray jsonArray) {
        this.context = context;
        this.jsonArray = MyUtils.jsonArray;
    }

    @Override
    public int getCount() {
        return jsonArray.length();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private ArrayFilter mFilter;

    public @NonNull
    Filter getFilter() {
        if (mFilter == null) {
            mFilter = new ArrayFilter();
        }
        return mFilter;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        TextView Website = (TextView) convertView.findViewById(R.id.txtWebsite);
        TextView Url = (TextView) convertView.findViewById(R.id.txtUrl);
        TextView Id = (TextView) convertView.findViewById(R.id.txtId);

        try {
            JSONObject jsonObject = jsonArray.getJSONObject(position);

            /* ----------- Using Assets Folder this method ----------- */

           /* Website.setText(jsonObject.getString("Website"));
            Url.setText(jsonObject.getString("Url"));*/

            /* ----------- End Using Assets Folder this method ----------- */

            /* ----------- Using Internet this method ----------- */

            Id.setText(jsonObject.getString("id"));
            Website.setText(jsonObject.getString("name"));
            //Url.setText(jsonObject.getString("city"));

            JSONObject addObj = jsonObject.getJSONObject("address");
            Url.setText(addObj.getString("city"));

            /* ----------- End Using Internet this method ----------- */
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return convertView;
    }


}
