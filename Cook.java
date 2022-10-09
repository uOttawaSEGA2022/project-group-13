import java.io.*;

public class Cook{
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String accountPassword;
	private String description;
	
	public Cook(String firstName, String lastName, String emailAddress, String accountPassword, String description) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.accountPassword = accountPassword;
		this.description = description;
	}
	
	public String getFirst() {
		return firstName;
	}
	
	public String getLast() {
		return lastName;
	}
	
	public String getEmail() {
		return emailAddress;
	}
	
	public String getPassword() {
		return accountPassword;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String toString() {
		return "\nAccount Information \n************************* \nFirst name: " + firstName + "\n" + "Last name: " + lastName + "\n" + "Email: " + emailAddress + "\n" + "Password: " + accountPassword + "\n" + "Description: " + description;
	}
	
	
	
}