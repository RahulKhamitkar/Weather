package com.rahul.todays;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.room.Room;

import com.rahul.todays.Database.AppDatabase;
import com.rahul.todays.Database.Weather;
import com.rahul.todays.Network.GetWeatherService;
import com.rahul.todays.Network.RetrofitInstance;
import com.rahul.todays.Pojo.MyPojo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView mCity;
    private TextView mDate;
    private TextView mCondition;
    private TextView mHumidity;
    private TextView mMin_temp;
    private TextView mMax_temp;
    private TextView mRainPercentage;
    private ImageView mBigImage;
    String date;

    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_main);

        mCity = findViewById(R.id.main_city);
        mDate = findViewById(R.id.date);
        mCondition = findViewById(R.id.tv_condition);
        mHumidity = findViewById(R.id.tvTemp);
        mMin_temp = findViewById(R.id.temp1);
        mMax_temp = findViewById(R.id.temp2);
        mRainPercentage = findViewById(R.id.rainPercentage);

        mBigImage = findViewById(R.id.bigImage);

        db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"production")
                .allowMainThreadQueries()
                .build();

        isNetworkAvailable();

         date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
    }

    public  void isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {

                List<Weather> weathers = db.weatherDao().getAllDetails();
                if(weathers.isEmpty()){
                    CallNet();
                }
                else {
                    update();
                }

            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {

                List<Weather> weathers = db.weatherDao().getAllDetails();

                if(weathers.isEmpty()){
                    CallNet();
                }
                else {
                    update();
                }
            }
        } else {
            // not connected to the internet
            display();
        }
    }

    private void update() {

        GetWeatherService service = RetrofitInstance.getRetrofitInstance().
                create(GetWeatherService.class);

        Call<MyPojo> call = service.getDetails("London","b6907d289e10d714a6e88b30761fae22");
        call.enqueue(new Callback<MyPojo>() {
            @Override
            public void onResponse(Call<MyPojo> call, Response<MyPojo> response) {

                AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"production")
                        .allowMainThreadQueries()
                        .build();

                Log.i("TAG","Response "+response.body().getName()+", "+
                        response.body().getClouds().getAll()+","+
                        response.body().getWeather().get(0).getMain()+", "+
                        response.body().getMain().getHumidity()+", "+
                        response.body().getMain().getTemp_min()+", "+
                        response.body().getMain().getTemp_max());

                db.weatherDao().updateWeather(new Weather(response.body().getName(),response.body().getClouds().getAll().toString(),response.body().getWeather().get(0).getMain(),response.body().getMain().getHumidity(),response.body().getMain().getTemp_min(),response.body().getMain().getTemp_max()));

                display();
            }

            @Override
            public void onFailure(Call<MyPojo> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void CallNet() {

        GetWeatherService service = RetrofitInstance.getRetrofitInstance().
                                    create(GetWeatherService.class);

        Call<MyPojo> call = service.getDetails("London","b6907d289e10d714a6e88b30761fae22");
        call.enqueue(new Callback<MyPojo>() {
            @Override
            public void onResponse(Call<MyPojo> call, Response<MyPojo> response) {

                final AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"production")
                        .allowMainThreadQueries()
                        .build();

                Log.i("TAG","Response "+response.body().getName()+", "+
                        response.body().getClouds().getAll()+","+
                        response.body().getWeather().get(0).getMain()+", "+
                        response.body().getMain().getHumidity()+", "+
                        response.body().getMain().getTemp_min()+", "+
                        response.body().getMain().getTemp_max());

                db.weatherDao().insertAll(new Weather(response.body().getName(),response.body().getClouds().getAll().toString(),response.body().getWeather().get(0).getMain(),response.body().getMain().getHumidity(),response.body().getMain().getTemp_min(),response.body().getMain().getTemp_max()));

                display();
            }

            @Override
            public void onFailure(Call<MyPojo> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void display() {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"production")
                .allowMainThreadQueries()
                .build();

        List<Weather> weathers = db.weatherDao().getAllDetails();
        Log.i("TAG","Display "+weathers);


        mCity.setText(weathers.get(0).getCityname());
        mCondition.setText(weathers.get(0).getCondition());
        mHumidity.setText(weathers.get(0).getHumidity());
        mRainPercentage.setText(weathers.get(0).getRainpercentage());
        mMax_temp.setText(weathers.get(0).getMaxtemp());
        mMin_temp.setText(weathers.get(0).getMintemp());
        mDate.setText(date);

        if(weathers.get(0).getCondition().equals("Clouds")){
            mBigImage.setImageResource(R.drawable.cloud);
        }
        else if(weathers.get(0).getCondition().equals("Drizzle")){
            mBigImage.setImageResource(R.drawable.rainy);
        }
        else {
            mBigImage.setImageResource(R.drawable.cloud_sun);

        }

    }
}
