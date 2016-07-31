package me.thenpush.rest;

import me.thenpush.rest.models.Device;
import me.thenpush.rest.models.PushReceipt;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by pappacena on 12/04/16.
 */
public interface Endpoints {
    @POST("/api/v1/projects/devices")
    Call<Device> addDevice(@Body Device device);

    @POST("/api/v1/receive")
    Call<PushReceipt> notifyReceipt(@Body PushReceipt pushReceipt);
}
