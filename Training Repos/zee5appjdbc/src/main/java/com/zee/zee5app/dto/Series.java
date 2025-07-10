package com.zee.zee5app.dto;

import javax.naming.NameNotFoundException;

import com.zee.zee5app.exception.LocationNotFound;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Series implements Comparable<Series>{
	@Setter(value = AccessLevel.NONE)
	private String seriesName;
	
	private int noofepisodes;
	private String releasedate;
	//@Setter(value = AccessLevel.NONE)
	//private String trailer;
	private int length;
	private String cast;
	private String category;
	private String id;
	private int agelimit;
	private String language;
	
	public void setSeriesName(String seriesName) throws NameNotFoundException {
		if(seriesName == null || seriesName.length() == 0)
			throw new NameNotFoundException("Name not found");
		this.seriesName = seriesName;
	}
//	public void setTrailer(String trailer) throws LocationNotFound {
//		if(trailer == null || trailer.length() == 0)
//			throw new LocationNotFound("Location Not found");
//		this.trailer = trailer;
//	}
	@Override
	public int compareTo(Series o) {
		// TODO Auto-generated method stub
		return o.id.compareTo(this.id);
	}
	
	
}
