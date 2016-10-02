package uk.sky;

/**
 * Created by kanke on 01/10/2016.
 */
public class LogExtract {

    private String request_timestamp;
    private String country_code;
    private String response_time;

    public LogExtract(String request_timestamp, String country_code, String response_time) {
        this.request_timestamp = request_timestamp;
        this.country_code = country_code;
        this.response_time = response_time;
    }


    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getRequest_timestamp() {
        return request_timestamp;
    }

    public void setRequest_timestamp(String request_timestamp) {
        this.request_timestamp = request_timestamp;
    }

    public String getResponse_time() {
        return response_time;
    }

    public void setResponse_time(String response_time) {
        this.response_time = response_time;
    }

}
