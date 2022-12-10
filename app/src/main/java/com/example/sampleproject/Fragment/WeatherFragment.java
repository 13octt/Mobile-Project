package com.example.sampleproject.Fragment;

import static com.example.sampleproject.WeatherActivity.covertUnixToHour;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sampleproject.APIClient;
import com.example.sampleproject.APIInterface;
import com.example.sampleproject.Model.Asset;
import com.example.sampleproject.Model.Attributes;
import com.example.sampleproject.Model.Main;
import com.example.sampleproject.Model.Sys;
import com.example.sampleproject.Model.Value;
import com.example.sampleproject.Model.Weather;
import com.example.sampleproject.Model.WeatherData;
import com.example.sampleproject.Model.Wind;
import com.example.sampleproject.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeatherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeatherFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    APIInterface apiInterface;

    String apikey="c3ecbf09be5267cd280676a01acd3360";

    TextView txtTemp,txtWind, txtHumi, txtSunrise,txtSunset;
    public WeatherFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WeatherFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WeatherFragment newInstance(String param1, String param2) {
        WeatherFragment fragment = new WeatherFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

            txtTemp = (TextView) getView().findViewById(R.id.tvTemp);
            txtWind = (TextView) getView().findViewById(R.id.tvWind);
            txtHumi = (TextView) getView().findViewById(R.id.tvHumi);
            txtSunrise = (TextView) getView().findViewById(R.id.tvSR);
            txtSunset = (TextView) getView().findViewById(R.id.tvSS);

        }
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentManager manager = getActivity().getSupportFragmentManager();
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(R.layout.activity_weather, container, false);
        txtTemp = (TextView) view.findViewById(R.id.tvTemp);
        txtWind = (TextView) view.findViewById(R.id.tvWind);
        txtHumi = (TextView) view.findViewById(R.id.tvHumi);
        txtSunrise = (TextView) view.findViewById(R.id.tvSR);
        txtSunset = (TextView) view.findViewById(R.id.tvSS);


        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        getweather(apikey);
    }

    public void getweather(String appid){
        apiInterface = APIClient.getClient().create(APIInterface.class);


        Call<Asset> weather = apiInterface.getAsset(appid);
        weather.enqueue(new Callback<Asset>() {
            @Override
            public void onResponse(Call<Asset> call, Response<Asset> response) {
                Asset asset = response.body();
                Attributes attributes = asset.attributes;
                WeatherData weatherData = attributes.getWeatherData();
                Main main = weatherData.getValue().getMain();
                Value value = weatherData.getValue();
                Sys sys = value.getSys();
                Double temp = main.getTemp();
                Integer temperature = (int)(temp-273.15);
                Wind wind = value.getWind();
                Double winspeed = wind.getSpeed();
                Integer humidity = main.getHumidity();
                String sunrise =covertUnixToHour(sys.getSunrise()) ;
                String sunset = covertUnixToHour(sys.getSunset());


                txtTemp.setText(String.valueOf(temperature)+"°C");
                txtHumi.setText(String.valueOf(humidity)+"%");
                txtSunrise.setText(String.valueOf(sunset));
                txtSunset.setText(String.valueOf(sunrise));
                txtWind.setText(String.valueOf(winspeed)+"km/h");




            }

            @Override
            public void onFailure(Call<Asset> call, Throwable t) {
                Log.d("API CALL ASSET", t.getMessage());

            }
        });

    }
    public static String covertUnixToHour(int sunrise){
        Date date = new Date(sunrise* 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String formatted = sdf.format(date);
        return formatted;
    }
}