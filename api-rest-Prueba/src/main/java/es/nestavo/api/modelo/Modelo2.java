package es.nestavo.api.modelo;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.mongodb.lang.NonNull;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Document(collection = "Searchhotel")
public class Modelo2 {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String Search;

	private String hotelId;

	@NonNull
	private Date checkIn;

	@NonNull
	private Date checkOut;

	private int[] age;

	@Id
	@Field(name = "searchId")
	private String searchId;

	private int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getSearch() {
		return Search;
	}

	public void setSearch(String search) {
		Search = search;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

	public int[] getAges() {
		return age;
	}

	public void setAges(int[] age) {
		this.age = age;
	}

	public String getSearchId() {
		return searchId;
	}

	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}

	public Modelo2() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Modelo2(String search, String hotelId, Date checkIn, Date checkOut, int[] age, String searchId, int count) {
		super();
		Search = search;
		this.hotelId = hotelId;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.age = age;
		this.searchId = searchId;
		this.count = count;
	}

	public void setMessage(String message) {
		// TODO Auto-generated method stub

	}

}
