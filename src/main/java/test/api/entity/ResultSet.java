package test.api.entity;

import java.util.List;

import test.api.entity.ResultSet.GeoData.BoundData.LatLng;

public class ResultSet {
	List<AddressComponent> address_components;
	String formatted_address;
	GeoData geometry;
	String place_id;
	List<String> types;

	public List<AddressComponent> getAddress_components() {
		return address_components;
	}

	public void setAddress_components(List<AddressComponent> address_components) {
		this.address_components = address_components;
	}

	public String getFormatted_address() {
		return formatted_address;
	}

	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}

	public GeoData getGeometry() {
		return geometry;
	}

	public void setGeometry(GeoData geometry) {
		this.geometry = geometry;
	}

	public String getPlace_id() {
		return place_id;
	}

	public void setPlace_id(String place_id) {
		this.place_id = place_id;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

	public class AddressComponent{
		String long_name;
		String short_name;
		List<String> premise;

		public String getLong_name() {
			return long_name;
		}

		public void setLong_name(String long_name) {
			this.long_name = long_name;
		}

		public String getShort_name() {
			return short_name;
		}

		public void setShort_name(String short_name) {
			this.short_name = short_name;
		}

		public List<String> getPremise() {
			return premise;
		}

		public void setPremise(List<String> premise) {
			this.premise = premise;
		}
	}

	public class GeoData{
		BoundData bounds;
		LatLng location;
		String location_type;
		
		
		public LatLng getLocation() {
			return location;
		}

		public void setLocation(LatLng location) {
			this.location = location;
		}

		public String getLocation_type() {
			return location_type;
		}

		public void setLocation_type(String location_type) {
			this.location_type = location_type;
		}

		public BoundData getBounds() {
			return bounds;
		}

		public void setBounds(BoundData bounds) {
			this.bounds = bounds;
		}

		public class BoundData{
			LatLng northeast;
			LatLng southwest;

			public LatLng getNortheast() {
				return northeast;
			}

			public void setNortheast(LatLng northeast) {
				this.northeast = northeast;
			}

			public LatLng getSouthwest() {
				return southwest;
			}

			public void setSouthwest(LatLng southwest) {
				this.southwest = southwest;
			}

			public class LatLng{
				double lat;
				double lng;

				public double getLat() {
					return lat;
				}

				public void setLat(double lat) {
					this.lat = lat;
				}

				public double getLng() {
					return lng;
				}

				public void setLng(double lng) {
					this.lng = lng;
				}
			}
		} 
	}

}
