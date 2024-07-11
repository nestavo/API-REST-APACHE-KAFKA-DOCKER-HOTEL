package es.nestavo.api.modelo;

import java.util.Arrays;
import java.util.Date;

import java.util.Objects;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.mongodb.lang.NonNull;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.PrePersist;

@Document(collection = "hotel1")

public class Modelo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hotelId")
	private String hotelId;

	@NonNull
	private Date checkIn;

	@NonNull
	private Date checkOut;

	private int[] ages;

	@Field("searchId")
	private String searchId;

	public Modelo() {
		super();
	}

	public Modelo(String hotelId, Date checkIn, Date checkOut, int[] ages) {
		super();
		this.hotelId = hotelId;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.ages = ages;
		this.searchId = generateSearchId(checkIn, checkOut, ages);

	}

	@PrePersist
	private void initializeSearchId() {
		if (this.searchId == null) {
			this.searchId = generateSearchId(this.checkIn, this.checkOut, this.ages);

		}
	}

	public String generateSearchId(Date checkIn, Date checkOut, int[] ages) {
		int hash = Objects.hash(checkIn, checkOut, Arrays.hashCode(ages));
		return Integer.toString(hash);
	}

	// Getters y setters

	public String getSearchId() {
		return generateSearchId(this.checkIn, this.checkOut, this.ages);
	}

	public void setSearchId(String searchId) {
		this.searchId = generateSearchId(this.checkIn, this.checkOut, this.ages);
		;
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
		return ages;
	}

	public void setAges(int[] ages) {
		this.ages = ages;
	}

}
