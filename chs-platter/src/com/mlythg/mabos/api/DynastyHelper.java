package com.mlythg.mabos.api;

import com.mlythg.mabos.api.interfaces.DynastyAPI;
import com.plnyyanks.tba.apiv2.RetrofitConverter;

import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public final class DynastyHelper {

    public static final String API_URL = "https://fieldbookcode.com/56e1f68a5d27c403000d65c2";

    private static DynastyAPI dynAPI;
    private static OkClient okClient;
    private static RestAdapter adapter;

    public static DynastyAPI getAPI(){
        setupRestAdapter();
        if(dynAPI == null){
            dynAPI = adapter.create(DynastyAPI.class);
        }
        return dynAPI;
    }

    private static void setupOkClient(){
        if(okClient == null){
            okClient = new OkClient();
        }
    }

    private static void setupRestAdapter(){
        setupOkClient();
        if(adapter == null){
            adapter = new RestAdapter.Builder()
                    .setEndpoint(API_URL)
                    .setConverter(new RetrofitConverter())
                    .setErrorHandler(new APIErrorHandler())
                    .setClient(okClient)
//                    .setLogLevel(LogLevel.FULL)
                    .build();
        }
    }

    static class APIErrorHandler implements ErrorHandler {
        @Override
        public Throwable handleError(RetrofitError cause) {
            System.out.println(cause);
            Response response = cause.getResponse();
            if(response != null) {
                TypedByteArray data = (TypedByteArray) (response.getBody());
                byte[] bytes = data.getBytes();
                System.out.println(new String(bytes));
            }
            return cause;
        }
    }

}
