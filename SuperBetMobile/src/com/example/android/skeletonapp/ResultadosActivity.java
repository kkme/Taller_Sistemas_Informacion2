package com.example.android.skeletonapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ResultadosActivity  extends ListActivity  {
	
    private static final int DIALOG_TEXT_ENTRY = 7;
    
   // private EditText monto;
    
    @SuppressWarnings("deprecation")
	@Override
    protected Dialog onCreateDialog(int id) {
    	
    //	monto = (EditText) findViewById(R.id.monto_edit);
        switch (id) {
        case DIALOG_TEXT_ENTRY:
    	
    	 LayoutInflater factory = LayoutInflater.from(this);
         final View textEntryView = factory.inflate(R.layout.alert_dialog_text_entry, null);
         return new AlertDialog.Builder(ResultadosActivity.this)
             .setIcon(R.drawable.alert_dialog_icon)
             .setTitle(R.string.alert_dialog_text_entry)
             .setView(textEntryView)
             .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                 public void onClick(DialogInterface dialog, int whichButton) {
                	 try{
                	 Double m = Double.parseDouble(((EditText) textEntryView.findViewById( R.id.monto_edit ) ).getText().toString());
                	 RestClient.apostar(m);
                	 }catch(Exception e){
                		 e.printStackTrace();
                	 }
                	 finish();
                     /* User clicked OK so do some stuff */
                 }
             })
             .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                 public void onClick(DialogInterface dialog, int whichButton) {
                	 finish();
                     /* User clicked cancel so do some stuff */
                 }
             })
             .create();
        }
		return null;
    }
	    
		@Override
		public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);

		  setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, RestClient.getResultados()));
		  
		  ListView lv = getListView();
		  lv.setTextFilterEnabled(true);

		  lv.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View view,
		        int position, long id) {
		      // When clicked, show a toast with the TextView text
		      Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
		          Toast.LENGTH_SHORT).show();
		      RestClient.setResultado(((TextView) view).getText().toString());
		      showDialog(DIALOG_TEXT_ENTRY);
		     // Intent siguiente = new Intent(ResultadosActivity.this, com.example.android.skeletonapp.AlertDialogSamples.class);
		     // ResultadosActivity.this.startActivity(siguiente);
		    }
		  });
		}
		

	}


