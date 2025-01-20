
package id.example.shashank.flixflix.Domain;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ListFilm {

    @SerializedName("data")
    @Expose
    private List<id.example.shashank.flixflix.Domain.Datum> data;
    @SerializedName("metadata")
    @Expose
    private id.example.shashank.flixflix.Domain.Metadata metadata;

    public List<id.example.shashank.flixflix.Domain.Datum> getData() {
        return data;
    }

    public void setData(List<id.example.shashank.flixflix.Domain.Datum> data) {
        this.data = data;
    }

    public id.example.shashank.flixflix.Domain.Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(id.example.shashank.flixflix.Domain.Metadata metadata) {
        this.metadata = metadata;
    }

}
