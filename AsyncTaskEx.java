import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
                                     //input //returnType
public class getf extends AsyncTask<String,String,String> {

   private  String returnData = null;
    EditText texter;

    WeakReference<Activity> mWeakActivity;


   
     //to access activity objects this constructor is used to pass activity object from class
    public getf(Activity activity) {
        mWeakActivity = new WeakReference<Activity>(activity);
    }

    //method where the background operations is performed
    @Override
    protected String doInBackground(String... strings) {

        try  {
            URL url = new URL("http://www.android.com/");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                Reader reader = new InputStreamReader(in);
                int data = reader.read();
                while (data != -1) {
                    returnData=returnData+((char) data);
                    data = reader.read();
                }
            } finally {
                urlConnection.disconnect();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



        return returnData;
    }




    // after execution
    @Override
    protected void onPostExecute(String result) {
      
      
      
         //create activity object
        Activity activity = mWeakActivity.get();
        if (activity != null) {

            EditText edit=(EditText)activity.findViewById(R.id.putText);
            edit.setText(result);
        }



    }


   //BEfore the execution
    @Override
    protected void onPreExecute() {

    }


    @Override
    protected void onProgressUpdate(String... text) {


    }

}
