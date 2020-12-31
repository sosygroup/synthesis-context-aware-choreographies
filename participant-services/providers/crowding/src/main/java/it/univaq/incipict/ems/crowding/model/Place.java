package it.univaq.incipict.ems.crowding.model;

public class Place {

	private String id;

	private String areaCode;

	private Integer peopleCount;

	public Place(String id, String areaCode, Integer peopleCount) {
		this.id = id;
		this.areaCode = areaCode;
		this.peopleCount = peopleCount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Integer getPeopleCount() {
		return peopleCount;
	}

	public void setPeopleCount(Integer peopleCount) {
		this.peopleCount = peopleCount;
	}

	
}
