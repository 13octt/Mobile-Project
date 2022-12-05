package com.example.sampleproject.Fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sampleproject.APIClient;
import com.example.sampleproject.APIInterface;
import com.example.sampleproject.Model.Default;
import com.example.sampleproject.Model.Info;
import com.example.sampleproject.Model.Map;
import com.example.sampleproject.Model.Options;
import com.example.sampleproject.R;
import com.google.android.gms.maps.MapView;
import com.google.gson.Gson;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.Marker;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link MapFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class MapFragment extends Fragment {

    APIInterface apiInterface;
    private MapView mapView;
    private IMapController mapController;
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public MapFragment() {
        // Required empty public constructor


//        Call<Info> getInfo = apiInterface.getInfo();
//        getInfo.enqueue(new Callback<Info>() {
//            @Override
//            public void onResponse(Call<Info> call, Response<Info> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<Info> call, Throwable t) {
//
//            }
//        });


    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment MapFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static MapFragment newInstance(String param1, String param2) {
//        MapFragment fragment = new MapFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        apiInterface = APIClient.getClient().create(APIInterface.class);
//        Context ctx = getActivity().getApplicationContext();
//        org.osmdroid.config.Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
//        Call<Map> getMap = apiInterface.getMap();
//        getMap.enqueue(new Callback<Map>() {
//            @Override
//            public void onResponse(Call<Map> call, Response<Map> response) {
//                Map map = response.body();
//                Gson gson = new Gson();
//                String json = gson.toJson(map.options);
//                Context ctx = getActivity().getApplicationContext();
//                Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
//                Options options = gson.fromJson(json, Options.class);
//                json = gson.toJson(options._default);
//                Default defaultObj = gson.fromJson(json, Default.class);
//                Double zoom = defaultObj.zoom;
//                Float longitude = defaultObj.center[0];
//                Float latitude = defaultObj.center[1];
//                mapView = getView().findViewById(R.id.uitMap);
//                mapView.setTileSource(TileSourceFactory.MAPNIK);
//                mapView.setMultiTouchControls(true);
//                mapController = mapView.getController();
//                mapController.setZoom(20);
//                GeoPoint startPoint = new GeoPoint(latitude, longitude);
//                mapController.setCenter(startPoint);
//                Marker marker = new org.osmdroid.views.overlay.Marker(mapView);
//                Drawable d = ResourcesCompat.getDrawable(getResources(), R.drawable.cloud, null);
//                Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
//                Drawable dr = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, (int) (13.0f * getResources().getDisplayMetrics().density), (int) (13.0f * getResources().getDisplayMetrics().density), true));
//                marker.setIcon(dr);
//                marker.setPosition(startPoint);
//                mapView.getOverlays().add(marker);
//                marker.setTitle("Nhà xe Nhân văn :V");
//                marker.setAnchor(org.osmdroid.views.overlay.Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
//
//            }
//
//            @Override
//            public void onFailure(Call<Map> call, Throwable t) {
//
//            }
//        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.activity_map, container, false);
    }
}