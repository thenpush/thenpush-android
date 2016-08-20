package me.thenpush.rest;

import me.thenpush.rest.models.Device;
import me.thenpush.rest.models.PushReceipt;
import me.thenpush.rest.models.ResponseMessage;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by pappacena on 12/04/16.
 */
public interface Endpoints {
    @POST("/api/v1/devices")
    Call<Device> addDevice(@Body Device device);

    @PUT("/api/v1/devices/{deviceId}")
    Call<Device> addDevice(@Path("deviceId") String deviceId, @Body Device device);

    @POST("/api/v1/receive")
    Call<PushReceipt> notifyReceipt(@Body PushReceipt pushReceipt);

    @POST("/api/v1/device/{deviceId}/flow/{flowId}/start")
    Call<ResponseMessage> startFlow(@Path("deviceId") String deviceId, @Path("flowId") String flowId);
}
