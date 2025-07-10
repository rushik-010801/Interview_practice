package com.zee.zee5app.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;
import java.util.regex.Pattern;

import javax.naming.InvalidNameException;

import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidPasswordException;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//@EqualsAndHashCode
//@NoArgsConstructor
//@AllArgsConstructor
public class Register implements Comparable<Register>
{
	
	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, id, lastName, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Register other = (Register) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(password, other.password);
	}

	public Register(String id, String firstName, String lastName, String email, String password) throws InvalidIdLengthException, InvalidNameException{
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	
	@Setter(value = AccessLevel.NONE)
	private String id;
	//Need to validate ID
	
	@Setter(value = AccessLevel.NONE)
	private String firstName;
	
	@Setter(value = AccessLevel.NONE)
	private String lastName;
	
	@Setter(value = AccessLevel.NONE)
	private String email;
	
	@Setter(value = AccessLevel.NONE)
	private String password;
	
	private BigDecimal contactNumber;
	public Register() {
		
		//System.out.println("hello from register");
	}

	public void setId(String id) throws InvalidIdLengthException{
		//throws prepares a list of exceptions throwed. 
		if(id.length() < 6) {
			//throw InvalidIdLengthException
			//this exception is throwed to the Main.java
			throw new InvalidIdLengthException("Invalid Length of ID. It should more than 6");
		}
			
		this.id = id;
	}

	public void setFirstName(String firstName) throws InvalidNameException {
		if(firstName == null || firstName == "" || firstName.length() < 2) {
			throw new InvalidNameException("Firstname is not valid");
		}
		this.firstName = firstName;
	}

	public void setLastName(String lastName) throws InvalidNameException{
		if(lastName == "" || lastName == null || lastName.length() < 2) {
			throw new InvalidNameException("Lastname is not valid");
		}
		this.lastName = lastName;
	}

	public void setEmail(String email) throws InvalidEmailException{
//		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
//                "[a-zA-Z0-9_+&*-]+)*@" +
//                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
//                "A-Z]{2,7}$";
//		Pattern pattern = Pattern.compile(emailRegex);
		if(email != null ){//&& pattern.matcher(email).matches()) {
			this.email = email;
		}
		else {
			//Throws a exception
			throw new InvalidEmailException("Invalid Email Id provided");
		}
	}

	public void setPassword(String password) throws InvalidPasswordException{
		if(id.length() < 6) {
			//throw InvalidIdLengthException
			//this exception is throwed to the Main.java
			throw new InvalidPasswordException("Invalid Password Provided");
		}
		this.password = password;
	}

	@Override
	public int compareTo(Register o) {
		// TODO Auto-generated method stub
		//for descending order
		//return o.id.compareTo(this.id);
		return this.id.compareTo(o.id);
	}
}
