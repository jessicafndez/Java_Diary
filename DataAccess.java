package dataBaseConn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import objects.Contacts;

public class DataAccess {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private Connection conn;
	private PreparedStatement insertContacts;
	private PreparedStatement viewContactsByName;
	private PreparedStatement viewContactsBySurname;
	private PreparedStatement viewAllContacts;
	
	private static String insertCont = "INSERT INTO contacts (cName, cSurname, cTelf, cMobile, cEmail, cAddress, cCity, cCountry, cNotes) "
			+"VALUES (?,?,?,?,?,?,?,?,?)";
	private static String selectContactByName = "SELECT * FROM contacts WHERE cName LIKE ?";
	private static String selectContactBySurname = "SELECT * FROM contacts  WHERE cSurname LIKE ?";
	private static String selectAllContacts = "SELECT * FROM contacts";
	
	//
	private String typeSearch;

	
	public DataAccess() {
	        	   System.out.println("Here");
	    
					try {
						Class.forName("com.mysql.jdbc.Driver").newInstance();
						startConnection();
					} catch (InstantiationException | ClassNotFoundException | IllegalAccessException  | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		               System.out.println("Here 2");
				
	}
	
	public void startConnection() throws SQLException {
		String DB_URL = "jdbc:mysql://localhost:3306/contactsdb";
		
		String USER = "root";
        String PASS = "1234";
        
        System.out.println("-----Connecting1------");
        
        try {
			 
			 System.out.println("-----Connecting2------");
			 conn = DriverManager.getConnection(DB_URL, USER, PASS);
			 
			 insertContacts = conn.prepareStatement(insertCont,  Statement.RETURN_GENERATED_KEYS);
			 viewContactsByName = conn.prepareStatement(selectContactByName);
			 viewContactsBySurname = conn.prepareStatement(selectContactBySurname);
			 viewAllContacts = conn.prepareStatement(selectAllContacts);
			 
		 } catch (SQLException e) {
	            conn = null;
	            throw e;
	        }
	}
	
	public void insertNewContact(Contacts contact) {
		System.out.println("Inserting Contacts...");
		System.out.println("Inserting :"+contact.getName());
		try {
			insertContacts.setString(1, contact.getName());
			insertContacts.setString(2, contact.getSurname());
			insertContacts.setString(3, contact.getTel());
			insertContacts.setString(4, contact.getMobile());
			insertContacts.setString(5, contact.getEmail());
			insertContacts.setString(6, contact.getAddress());
			insertContacts.setString(7, contact.getCity());
			insertContacts.setString(8, contact.getCountry());
			insertContacts.setString(9, contact.getNotes());
			
			insertContacts.execute();
			
			//ResultSet res = insertContacts.getGeneratedKeys();
			//res.next();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Contacts getContactByName(String name) {
		System.out.println("GET CONTATC BY NAME");
		System.out.println("NAME IS: "+name);
		Contacts contact = null;
		try {
			viewContactsByName.setString(1, "%"+name+"%");
			ResultSet rs = viewContactsByName.executeQuery();
			if(rs.next()) {
				contact = new Contacts(rs.getString("cName"),rs.getString("cSurname"), rs.getString("cTelf"), rs.getString("cMobile"), rs.getString("cEmail"),
						rs.getString("cAddress"), rs.getString("cCity"), rs.getString("cCountry"), rs.getString("cNotes"));
			}
		} catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
		return contact;
	}
	
	public Contacts getContactsBySurname(String surname) {
		System.out.println("GET CONTACTS BY SURNAME");
		System.out.println("SURNAME IS: "+surname);
		Contacts contact = null;
		ResultSet rs;
		try {
			viewContactsBySurname.setString(1,  "%"+surname+"%");
			rs = viewContactsBySurname.executeQuery();
			
			while(rs.next()) {
				contact = new Contacts(rs.getString("cName"),rs.getString("cSurname"), rs.getString("cTelf"), rs.getString("cMobile"), rs.getString("cEmail"),
						rs.getString("cAddress"), rs.getString("cCity"), rs.getString("cCountry"), rs.getString("cNotes"));
				System.out.println("-->"+contact.getSurname()+": "+contact.getName());
				//return contact;
			}
		}catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}	
		System.out.println("-->"+contact.getSurname()+": "+contact.getName());
		return contact;
	}
	
	public List<Contacts> bySurname (String s) {
		Contacts contact;
		List<Contacts> listBySurname = new ArrayList<Contacts>();
		ResultSet rsContacts;
		try {
			viewContactsBySurname.setString(1,  "%"+s+"%");
			rsContacts = viewContactsBySurname.executeQuery();
			
			while(rsContacts.next()) {
				contact = new Contacts(rsContacts.getString("cName"),rsContacts.getString("cSurname"), rsContacts.getString("cTelf"), rsContacts.getString("cMobile"), rsContacts.getString("cEmail"),
						rsContacts.getString("cAddress"), rsContacts.getString("cCity"), rsContacts.getString("cCountry"), rsContacts.getString("cNotes"));
				System.out.println("-->"+contact.getSurname()+": "+contact.getName());
				listBySurname.add(contact);
			}
		}catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	
		return listBySurname;
	}
	
	public List<Contacts> byName (String s) {
		Contacts contact;
		List<Contacts> listByName = new ArrayList<Contacts>();
		ResultSet rsContacts;
		try {
			viewContactsByName.setString(1,  "%"+s+"%");
			rsContacts = viewContactsByName.executeQuery();
			
			while(rsContacts.next()) {
				contact = new Contacts(rsContacts.getString("cName"),rsContacts.getString("cSurname"), rsContacts.getString("cTelf"), rsContacts.getString("cMobile"), rsContacts.getString("cEmail"),
						rsContacts.getString("cAddress"), rsContacts.getString("cCity"), rsContacts.getString("cCountry"), rsContacts.getString("cNotes"));
				System.out.println("-->"+contact.getSurname()+": "+contact.getName());
				listByName.add(contact);
			}
		}catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	
		return listByName;
	}	
}
