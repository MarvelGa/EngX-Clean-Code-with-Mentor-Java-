package com.epam.engx.cleancode.naming.task2;

import java.util.Arrays;

public class User {

	protected boolean isAdmin = false;

	private String birthDay;

	private String name;

	private User[] subordinates;

	private int rating;

	public User(String name, String birthDay, User[] subordinateArray) {
		this.birthDay = birthDay;
		this.name = name;
		this.subordinates = subordinateArray;
	}

	@Override
	public String toString() {
		return "User [dateOfBirth=" + birthDay + ", name=" + name + ", isAdmin=" + isAdmin + ", subordinates="
				+ Arrays.toString(subordinates) + ", rating=" + rating + "]";
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
}
