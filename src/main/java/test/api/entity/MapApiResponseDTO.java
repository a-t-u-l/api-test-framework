package test.api.entity;

import java.util.List;

public class MapApiResponseDTO {
	List<ResultSet> results;
	String status;

	public List<ResultSet> getResults() {
		return results;
	}
	public void setResults(List<ResultSet> results) {
		this.results = results;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
