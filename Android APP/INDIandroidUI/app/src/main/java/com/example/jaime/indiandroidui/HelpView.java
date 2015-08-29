package com.example.jaime.indiandroidui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Jaime on 26/8/15.
 */
public class HelpView extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.help_view, container, false);
        TextView title=(TextView) view.findViewById(R.id.title);
        TextView text=(TextView) view.findViewById(R.id.text);
        title.setText(getResources().getString(R.string.help_title));
        text.setText(Html.fromHtml(getResources().getString(R.string.help_text)));
        return view;
    }

}
