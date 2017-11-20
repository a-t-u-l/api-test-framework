package test.api.entity;

public class SearchNumberRequestDTO {

	private String country_iso;
	private String type;
	private int pattern;
	private String region;
	private String services;
	private String lata;
	private String rate_center;
	private String limit;
	private String offset;
	
	public String getCountryIso() {
		return country_iso;
	}
	public void setCountryIso(String country_iso) {
		this.country_iso = country_iso;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPattern() {
		return pattern;
	}
	public void setPattern(int pattern) {
		this.pattern = pattern;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getLata() {
		return lata;
	}
	public void setLata(String lata) {
		this.lata = lata;
	}
	public String getRateCenter() {
		return rate_center;
	}
	public void setRateCenter(String rate_center) {
		this.rate_center = rate_center;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	public String getOffset() {
		return offset;
	}
	public void setOffset(String offset) {
		this.offset = offset;
	}
	
	

	
}
