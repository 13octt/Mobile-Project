package com.example.sampleproject;

import com.example.sampleproject.Model.Asset;
import com.example.sampleproject.Model.AssetDescriptor;
import com.example.sampleproject.Model.AssetUserCurrent;
import com.example.sampleproject.Model.Flow;
import com.example.sampleproject.Model.Info;
import com.example.sampleproject.Model.Map;
import com.example.sampleproject.Model.MetaItemDescriptor;
import com.example.sampleproject.Model.RealmAccessible;
import com.example.sampleproject.Model.User;
import com.example.sampleproject.Model.UserRoles;
import com.example.sampleproject.Model.ValueDescriptor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterface {

    @GET("api/master/map")
    Call<Map> getMap();

    @GET("api/master/realm/accessible")
    Call<List<RealmAccessible>> getRealmAccessible();

    @GET("api/master/user/userRoles")
    Call<List<UserRoles>> getUserRoles();

    @GET("api/master/info")
    Call<Info> getInfo();

    @GET ("api/master/model/assetDescriptors")
    Call<List<AssetDescriptor>> getAssetDescriptor();

    @GET("api/master/asset/user/current")
    Call<List<AssetUserCurrent>> getAssetUserCurrent();

    @GET("api/master/flow")
    Call<List<Flow>> getFlow();

    @GET("api/master/user/user")
    Call<User> getUser();


    @GET("api/master/asset/{assetID}")
    Call<Asset> getAsset(@Path("assetID") String assetID);//, @Header("Authorization") String auth);

    @GET("api/master/model/valueDescriptors")
    Call<List<ValueDescriptor>> getValueDescriptor();
    
    @GET("api/master/model/metaItemDescriptors")
    Call<List<MetaItemDescriptor>> getMetaItemDescriptor();

}
