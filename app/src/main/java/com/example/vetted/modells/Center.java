package com.example.vetted.modells;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Center{
  @SerializedName("latitude")
  @Expose
  private Integer latitude;
  @SerializedName("longitude")
  @Expose
  private Integer longitude;
  public Center(){
  }
  public Center(Integer latitude,Integer longitude){
   this.latitude=latitude;
   this.longitude=longitude;
  }
  public void setLatitude(Integer latitude){
   this.latitude=latitude;
  }
  public Integer getLatitude(){
   return latitude;
  }
  public void setLongitude(Integer longitude){
   this.longitude=longitude;
  }
  public Integer getLongitude(){
   return longitude;
  }
}