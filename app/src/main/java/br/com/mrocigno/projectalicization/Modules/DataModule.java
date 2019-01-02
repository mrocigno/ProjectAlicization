package br.com.mrocigno.projectalicization.Modules;

import android.app.Activity;

import java.util.concurrent.TimeUnit;

import br.com.mrocigno.projectalicization.Database.LocalData;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DataModule {
    Activity activity;

    public DataModule(Activity activity){
        this.activity = activity;
    }

    @Provides
    public LocalData getLocalData(){
        return new LocalData(activity);
    }

    @Provides
    public Retrofit getRetrofit(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder()
                .baseUrl("http://traduzame.esy.es/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    public Retrofit.Builder getRetrofitBuilder(){
        return new Retrofit.Builder()
                .baseUrl("http://traduzame.esy.es/");
    }

}
