package com.example.eventmanagement;

public class Event {
	private int id;
	private String name;
	private String description;
	private String short_desc;
	private String date;
	private String contacts;
	private String prize;
	private String organizers;
	private String venue;
	public Event(){}
	    public Event(String name, String short_desc) {
	
	        super();
	
	        this.name = name;
	
	        this.short_desc = short_desc;
	
	    }
	
	     
	
	    public int getId() {
	
	        return id;
	
	    }
	
	    public void setId(int id) {
	
	        this.id = id;

	    }
	
	    public String getName() {
	
	        return name;
	
	    }
	
	    public void setName(String name) {
	
	        this.name = name;
	
	    }
	
	    public String getDesc() {
	
	        return description;
	
	    }
	
	    public void setDesc(String description) {
	
	      this.description = description;
	
	    }
	
	    public String getShortDesc() {
	    	
	        return short_desc;
	
	    }
	
	    public void setShortDesc(String short_desc) {
	
	        this.short_desc = short_desc;
	
	    }
	    public String getDate() {
	    	
	        return date;
	
	    }
	
	    public void setDate(String date) {
	
	        this.date = date;
	
	    }
	    public String getContacts() {
	    	
	        return contacts;
	
	    }
	
	    public void setContacts(String contacts) {
	
	        this.contacts = contacts;
	
	    }
	    public String getPrize() {
	    	
	        return prize;
	
	    }
	
	    public void setPrize(String prize) {
	
	        this.prize = prize;
	
	    }
	    public String getOrganizer() {
	    	
	        return organizers;
	
	    }
	
	    public void setOrganizer(String organizers) {
	
	        this.organizers = organizers;
	
	    }
 public String getVenue() {
	    	
	        return venue;
	
	    }
	
	    public void setVenue(String venue) {
	
	        this.venue= venue;
	
	    }
	
	
	   
	
	    @Override
	
	    public String toString() {
	
	        return "Event [id=" + id + ", name=" + name + ", date=" + date + ", description=" + description
	        		+ ", short_desc=" + short_desc + ", organizers" + organizers + ", prize=" + prize + 
	        		", venue=" + venue + ", contacts=" + contacts + "]";
	
	    }

}
