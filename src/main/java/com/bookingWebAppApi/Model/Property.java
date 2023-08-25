package com.bookingWebAppApi.Model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Property extends Auditable <String>  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;
    private String name;
    private String propertyType;
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private double propertyPrice;
    private String propertyCountry;
    private String propertyCity;
    private String propertyState;
    
    private String propertyZipCode;
    
    private List<String> propertyImageUrl;

    @Column(columnDefinition = "text")
    private String theSpace_noOfAccommodation;
    
    private String theSpace_noOfBathrooms;
    
    @Column(columnDefinition = "text")
    private String theSpace_noOfBedrooms;
    
    @Column(columnDefinition = "text")
    private String beds_noOfKing;
    
    @Column(columnDefinition = "text")
    private String beds_noOfQueen;
    
    @Column(columnDefinition = "text")
    private String beds_noOfSingle;
    
    @Column(columnDefinition = "text")
    private String bathrooms_noOfMasterBathroom;
    
    @Column(columnDefinition = "text")
    private String bathrooms_noOfPrivateBathroom;
    
    @Column(columnDefinition = "text")
    private String bathrooms_noOfHalfBath;
    
    @Column(columnDefinition = "text")
    private String sharedSpaces_kitchen;
    
    @Column(columnDefinition = "text")
    private String sharedSpaces_laudryRoom;
    
    @Column(columnDefinition = "text")
    private String sharedSpaces_outDoorParking;
    
    @Column(columnDefinition = "text")
    private String sharedSpaces_garage;
    
    @Column(columnDefinition = "text")
    private String sharedSpaces_balcony;
    
    @Column(columnDefinition = "text")
    private String sharedSpaces_backyard;
    
    @Column(columnDefinition = "text")
    private String propertyAddress1;
    
    @Column(columnDefinition = "text")
    private String propertyAddress2;
    
    @Column(columnDefinition = "text")
    private String amenities_wifi;
    
    @Column(columnDefinition = "text")
    private String amenities_towelsBedsheetsSoapAndToiletpaper;
    
    @Column(columnDefinition = "text")
    private String amenities_shampoo;
    
    @Column(columnDefinition = "text")
    private String amenities_closetDrawers;
    
    @Column(columnDefinition = "text")
    private String amenities_hairDryer;
    
    @Column(columnDefinition = "text")
    private String amenities_LEDTV;
    
    @Column(columnDefinition = "text")
    private String amenities_grill;
    
    @Column(columnDefinition = "text")
    private String amenities_parking;
    
    @Column(columnDefinition = "text")
    private String amenities_outdoorSwimmingPool;
    
    @Column(columnDefinition = "text")
    private String amenities_ironBoard;
    
    @Column(columnDefinition = "text")
    private String amenities_satelliteOrCable;
    
    @Column(columnDefinition = "text")
    private String amenities_microwave;
    
    @Column(columnDefinition = "text")
    private String amenities_boardGames;
    
    @Column(columnDefinition = "text")
    private String amenities_toaster;
    
    @Column(columnDefinition = "text")
    private String amenities_coffeeMaker;
    
    @Column(columnDefinition = "text")
    private String amenities_stove;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Userr propertyOwner;

    private double propertyCleaningFee;
    
    private String[] reviews;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public double getPropertyPrice() {
		return propertyPrice;
	}

	public void setPropertyPrice(double propertyPrice) {
		this.propertyPrice = propertyPrice;
	}

	public String getPropertyCountry() {
		return propertyCountry;
	}

	public void setPropertyCountry(String propertyCountry) {
		this.propertyCountry = propertyCountry;
	}

	public String getPropertyState() {
		return propertyState;
	}

	public void setPropertyState(String propertyState) {
		this.propertyState = propertyState;
	}

	public String getPropertyCity() {
		return propertyCity;
	}

	public void setPropertyCity(String propertyCity) {
		this.propertyCity = propertyCity;
	}

	public String getPropertyZipCode() {
		return propertyZipCode;
	}

	public void setPropertyZipCode(String propertyZipCode) {
		this.propertyZipCode = propertyZipCode;
	}

	public List<String> getPropertyImageUrl() {
		return propertyImageUrl;
	}

	public void setPropertyImageUrl(List<String> propertyImageUrl) {
		this.propertyImageUrl = propertyImageUrl;
	}

	public String getTheSpace_noOfAccommodation() {
		return theSpace_noOfAccommodation;
	}

	public void setTheSpace_noOfAccommodation(String theSpace_noOfAccommodation) {
		this.theSpace_noOfAccommodation = theSpace_noOfAccommodation;
	}

	public String getTheSpace_noOfBathrooms() {
		return theSpace_noOfBathrooms;
	}

	public void setTheSpace_noOfBathrooms(String theSpace_noOfBathrooms) {
		this.theSpace_noOfBathrooms = theSpace_noOfBathrooms;
	}

	public String getTheSpace_noOfBedrooms() {
		return theSpace_noOfBedrooms;
	}

	public void setTheSpace_noOfBedrooms(String theSpace_noOfBedrooms) {
		this.theSpace_noOfBedrooms = theSpace_noOfBedrooms;
	}

	public String getBeds_noOfKing() {
		return beds_noOfKing;
	}

	public void setBeds_noOfKing(String beds_noOfKing) {
		this.beds_noOfKing = beds_noOfKing;
	}

	public String getBeds_noOfQueen() {
		return beds_noOfQueen;
	}

	public void setBeds_noOfQueen(String beds_noOfQueen) {
		this.beds_noOfQueen = beds_noOfQueen;
	}

	public String getBeds_noOfSingle() {
		return beds_noOfSingle;
	}

	public void setBeds_noOfSingle(String beds_noOfSingle) {
		this.beds_noOfSingle = beds_noOfSingle;
	}

	public String getBathrooms_noOfMasterBathroom() {
		return bathrooms_noOfMasterBathroom;
	}

	public void setBathrooms_noOfMasterBathroom(String bathrooms_noOfMasterBathroom) {
		this.bathrooms_noOfMasterBathroom = bathrooms_noOfMasterBathroom;
	}

	public String getBathrooms_noOfPrivateBathroom() {
		return bathrooms_noOfPrivateBathroom;
	}

	public void setBathrooms_noOfPrivateBathroom(String bathrooms_noOfPrivateBathroom) {
		this.bathrooms_noOfPrivateBathroom = bathrooms_noOfPrivateBathroom;
	}

	public String getBathrooms_noOfHalfBath() {
		return bathrooms_noOfHalfBath;
	}

	public void setBathrooms_noOfHalfBath(String bathrooms_noOfHalfBath) {
		this.bathrooms_noOfHalfBath = bathrooms_noOfHalfBath;
	}

	public String getSharedSpaces_kitchen() {
		return sharedSpaces_kitchen;
	}

	public void setSharedSpaces_kitchen(String sharedSpaces_kitchen) {
		this.sharedSpaces_kitchen = sharedSpaces_kitchen;
	}

	public String getSharedSpaces_laudryRoom() {
		return sharedSpaces_laudryRoom;
	}

	public void setSharedSpaces_laudryRoom(String sharedSpaces_laudryRoom) {
		this.sharedSpaces_laudryRoom = sharedSpaces_laudryRoom;
	}

	public String getSharedSpaces_outDoorParking() {
		return sharedSpaces_outDoorParking;
	}

	public void setSharedSpaces_outDoorParking(String sharedSpaces_outDoorParking) {
		this.sharedSpaces_outDoorParking = sharedSpaces_outDoorParking;
	}

	public String getSharedSpaces_garage() {
		return sharedSpaces_garage;
	}

	public void setSharedSpaces_garage(String sharedSpaces_garage) {
		this.sharedSpaces_garage = sharedSpaces_garage;
	}

	public String getSharedSpaces_balcony() {
		return sharedSpaces_balcony;
	}

	public void setSharedSpaces_balcony(String sharedSpaces_balcony) {
		this.sharedSpaces_balcony = sharedSpaces_balcony;
	}

	public String getSharedSpaces_backyard() {
		return sharedSpaces_backyard;
	}

	public void setSharedSpaces_backyard(String sharedSpaces_backyard) {
		this.sharedSpaces_backyard = sharedSpaces_backyard;
	}

		
	public String getPropertyAddress1() {
		return propertyAddress1;
	}

	public void setPropertyAddress1(String propertyAddress1) {
		this.propertyAddress1 = propertyAddress1;
	}

	public String getPropertyAddress2() {
		return propertyAddress2;
	}

	public void setPropertyAddress2(String propertyAddress2) {
		this.propertyAddress2 = propertyAddress2;
	}

	public String getAmenities_wifi() {
		return amenities_wifi;
	}

	public void setAmenities_wifi(String amenities_wifi) {
		this.amenities_wifi = amenities_wifi;
	}

	public String getAmenities_towelsBedsheetsSoapAndToiletpaper() {
		return amenities_towelsBedsheetsSoapAndToiletpaper;
	}

	public void setAmenities_towelsBedsheetsSoapAndToiletpaper(String amenities_towelsBedsheetsSoapAndToiletpaper) {
		this.amenities_towelsBedsheetsSoapAndToiletpaper = amenities_towelsBedsheetsSoapAndToiletpaper;
	}

	public String getAmenities_shampoo() {
		return amenities_shampoo;
	}

	public void setAmenities_shampoo(String amenities_shampoo) {
		this.amenities_shampoo = amenities_shampoo;
	}

	public String getAmenities_closetDrawers() {
		return amenities_closetDrawers;
	}

	public void setAmenities_closetDrawers(String amenities_closetDrawers) {
		this.amenities_closetDrawers = amenities_closetDrawers;
	}

	public String getAmenities_hairDryer() {
		return amenities_hairDryer;
	}

	public void setAmenities_hairDryer(String amenities_hairDryer) {
		this.amenities_hairDryer = amenities_hairDryer;
	}

	public String getAmenities_LEDTV() {
		return amenities_LEDTV;
	}

	public void setAmenities_LEDTV(String amenities_LEDTV) {
		this.amenities_LEDTV = amenities_LEDTV;
	}

	public String getAmenities_grill() {
		return amenities_grill;
	}

	public void setAmenities_grill(String amenities_grill) {
		this.amenities_grill = amenities_grill;
	}

	public String getAmenities_parking() {
		return amenities_parking;
	}

	public void setAmenities_parking(String amenities_parking) {
		this.amenities_parking = amenities_parking;
	}

	public String getAmenities_outdoorSwimmingPool() {
		return amenities_outdoorSwimmingPool;
	}

	public void setAmenities_outdoorSwimmingPool(String amenities_outdoorSwimmingPool) {
		this.amenities_outdoorSwimmingPool = amenities_outdoorSwimmingPool;
	}

	public String getAmenities_ironBoard() {
		return amenities_ironBoard;
	}

	public void setAmenities_ironBoard(String amenities_ironBoard) {
		this.amenities_ironBoard = amenities_ironBoard;
	}

	public String getAmenities_satelliteOrCable() {
		return amenities_satelliteOrCable;
	}

	public void setAmenities_satelliteOrCable(String amenities_satelliteOrCable) {
		this.amenities_satelliteOrCable = amenities_satelliteOrCable;
	}

	public String getAmenities_microwave() {
		return amenities_microwave;
	}

	public void setAmenities_microwave(String amenities_microwave) {
		this.amenities_microwave = amenities_microwave;
	}

	public String getAmenities_boardGames() {
		return amenities_boardGames;
	}

	public void setAmenities_boardGames(String amenities_boardGames) {
		this.amenities_boardGames = amenities_boardGames;
	}

	public String getAmenities_toaster() {
		return amenities_toaster;
	}

	public void setAmenities_toaster(String amenities_toaster) {
		this.amenities_toaster = amenities_toaster;
	}

	public String getAmenities_coffeeMaker() {
		return amenities_coffeeMaker;
	}

	public void setAmenities_coffeeMaker(String amenities_coffeeMaker) {
		this.amenities_coffeeMaker = amenities_coffeeMaker;
	}

	public String getAmenities_stove() {
		return amenities_stove;
	}

	public void setAmenities_stove(String amenities_stove) {
		this.amenities_stove = amenities_stove;
	}

	public Userr getPropertyOwner() {
		return propertyOwner;
	}

	public void setPropertyOwner(Userr propertyOwner) {
		this.propertyOwner = propertyOwner;
	}

	
	public double getPropertyCleaningFee() {
		return propertyCleaningFee;
	}

	public void setPropertyCleaningFee(double propertyCleaningFee) {
		this.propertyCleaningFee = propertyCleaningFee;
	}
	
	public String[] getReviews() {
		return reviews;
	}



	public void setReviews(String[] reviews) {
		this.reviews = reviews;
	}




}
