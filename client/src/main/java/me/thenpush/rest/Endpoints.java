package me.thenpush.rest;

import me.thenpush.rest.models.Device;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by pappacena on 12/04/16.
 */
public interface Endpoints {
    @POST("/api/v1/projects/{project_id}/devices")
    Call<Device> addDevice(@Path("project_id") String projectId,
                           @Body Device device);
}
