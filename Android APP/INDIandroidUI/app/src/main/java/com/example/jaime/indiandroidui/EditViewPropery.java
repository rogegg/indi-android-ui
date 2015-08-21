package com.example.jaime.indiandroidui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import java.util.Iterator;
import java.util.TreeSet;

import laazotea.indi.client.INDIProperty;

/**
 * Created by Jaime on 18/8/15.
 */
public class EditViewPropery extends DialogFragment implements View.OnClickListener{

    private INDIProperty p;
    UIPropertyManager ui;
    private View v;
    private TreeSet<UIPropertyManager> uiProperties;

    static EditViewPropery newInstance(){
        EditViewPropery fragment = new EditViewPropery();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return createDialog();
    }

    /**
     * Crea un diálogo de alerta sencillo
     * @return Nuevo diálogo
     */
    public AlertDialog createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        ui=null;
        setUiProperties();

        boolean end = false;
        for( Iterator it = uiProperties.iterator(); it.hasNext() && !end;){
            UIPropertyManager aux=(UIPropertyManager)it.next();
            if(aux.handlesProperty(p)){
                end=true;
                ui=aux;
            }
        }

        v = ui.getUpdateView(p,inflater,this);

        Button button=(Button)v.findViewById(R.id.update_button);

        button.setOnClickListener(this);

        builder.setView(v);


        return builder.create();
    }

    private void setUiProperties() {
        //Create order set
        uiProperties = new TreeSet<>(new UIPropertyManagerOrder());

        //add UI object
        uiProperties.add(new UIBlobPropertyManager());
        uiProperties.add(new UITextPropertyManager());
        uiProperties.add(new UISwitchPropertyManager());
        uiProperties.add(new UINumberPropertyManager());
        uiProperties.add(new UILightPropertyManager());
        uiProperties.add(new UIConnecPropertyManager());

    }

    public void setProperty(INDIProperty p){
        this.p=p;
    }

    @Override
    public void onClick(View v) {
        ui.updateProperty(p,this.v);
        dismiss();
    }
}