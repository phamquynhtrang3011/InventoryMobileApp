package team8.inventorymobileapp.com.inventorymobileapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.AdapterView;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.List;

import team8.inventorymobileapp.com.inventorymobileapp.Entity.Adjustment;

public class AdjustmentBySupervisor extends Activity implements AdapterView.OnItemClickListener{

    int requestCode =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjustment_by_supervisor);

      final ListView lv =  (ListView) findViewById(R.id.listview);
      lv.setOnItemClickListener(this);

      new AsyncTask<Void,Void,List<Adjustment>>()
      {
          @Override
          protected List<Adjustment> doInBackground(Void...Params) {
              return Adjustment.listOfAdjustmentBySupervisor();
          }

          @Override
          protected void onPostExecute(List<Adjustment> adjustments) {
              super.onPostExecute(adjustments);

              BaseAdapter b = new SimpleAdapter(AdjustmentBySupervisor.this, adjustments, R.layout.supervisorrow, new String[]{"AdjustmentCode", "ItemCode","Reason"},
                      new int[]{ R.id.text1, R.id.text2,R.id.text3});
              lv.setAdapter(b);
          }
      }.execute();




    }

    @Override
    public void onItemClick(AdapterView<?> av, View v, int position, long id) {
        Adjustment s = (Adjustment) av.getAdapter().getItem(position);
        Intent intent = new Intent(this, AdjustmentDetails.class);
        intent.putExtra("adjustmentCode", s.get("AdjustmentCode"));
        Toast.makeText(this,s.get("AdjustmentCode") ,Toast.LENGTH_LONG).show();
        //startActivity(intent);
        startActivityForResult(intent,requestCode);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(this.requestCode==requestCode)
        {
           if(resultCode==RESULT_OK)
           {

               final ListView lv =  (ListView) findViewById(R.id.listview);
               lv.setOnItemClickListener(this);

               new AsyncTask<Void,Void,List<Adjustment>>()
               {
                   @Override
                   protected List<Adjustment> doInBackground(Void...Params) {
                       return Adjustment.listOfAdjustmentBySupervisor();
                   }

                   @Override
                   protected void onPostExecute(List<Adjustment> adjustments) {
                       super.onPostExecute(adjustments);

                       BaseAdapter b = new SimpleAdapter(AdjustmentBySupervisor.this, adjustments, R.layout.supervisorrow, new String[]{"AdjustmentCode", "ItemCode","Reason"},
                               new int[]{ R.id.text1, R.id.text2,R.id.text3});
                       lv.setAdapter(b);
                   }
               }.execute();


           }


        }


    }
}
