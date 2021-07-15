
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 import javax.faces.bean.ManagedBean;
 import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

@ManagedBean( name="customersBean" )
@SessionScoped
public class CustomersBean {
    private String firstname;
    private String lastname; 
    private String mail;
    private String password;
    private String tcNo;
    private String id;
    private String count;
 private String count2;

    public String getCount2() {
        return count2;
    }

    public void setCount2(String count2) {
        this.count2 = count2;
    }
    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
    private String controlMail;
    private String controlPassword;

    CachedRowSet rowSet=null;
    DataSource dataSource;
  

    public CustomersBean() {
        try {
	Context ctx = new InitialContext();
	dataSource = (DataSource)ctx.lookup("jdbc/addressbook");
	} catch (NamingException e) {
        e.printStackTrace();
        }
    }
    
    public String getFirstname() {return firstname;}
    public void setFirstname(String firstname) {this.firstname = firstname;}
    public String getLastname() {return lastname;}
    public void setLastname(String lastname) { this.lastname = lastname; }
    public String getMail() { return mail;}
    public void setMail(String mail) {this.mail = mail;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public String getControlMail() { return controlMail;}
    public void setControlMail(String controlMail) {this.controlMail = controlMail;}
    public String getControlPassword() {return controlPassword; }
    public void setControlPassword(String controlPassword) {this.controlPassword = controlPassword;}
    public String getTcNo() {return tcNo;}
    public void setTcNo(String tcNo) {this.tcNo = tcNo;}
    


 public  String logIn() throws SQLException{
  if ( dataSource == null )
 throw new SQLException( "Unable to obtain DataSource" );
 Connection connection = dataSource.getConnection();
 if ( connection == null )
 throw new SQLException( "Unable to connect to DataSource" );
 try {
  PreparedStatement ps =
 connection.prepareStatement( "select customers3.ID, customers3.firstname, customers3.lastname\n" +
" from customers3 where customers3.mail=? and customers3.PASSWORD=?");
 ps.setString(1, getMail() ); 
 ps.setString(2, getPassword() );  
 


 
rowSet = new com.sun.rowset.CachedRowSetImpl();
 rowSet.populate( ps.executeQuery() );
   

    return "index2";
   }
   finally { connection.close();} 
   
  }  
 public ResultSet logIn2() throws SQLException{
    if ( dataSource == null )
 throw new SQLException( "Unable to obtain DataSource" );
 Connection connection = dataSource.getConnection();
 if ( connection == null )
 throw new SQLException( "Unable to connect to DataSource" );
 try {
  PreparedStatement ps =
 connection.prepareStatement( "select customers3.firstname, customers3.lastname\n" +
" from customers3 where customers3.mail=? and customers3.PASSWORD=?");
 ps.setString(1, getMail() ); 
 ps.setString(2, getPassword() );  
 


 
rowSet = new com.sun.rowset.CachedRowSetImpl();
 rowSet.populate( ps.executeQuery() );
   

    return rowSet;
   }
   finally { connection.close();} 
   
  }  
 public String ekle() throws SQLException{
   if ( dataSource == null ) throw new SQLException( "Unable to obtain DataSource" );
   Connection connection = dataSource.getConnection();
   if ( connection == null ) throw new SQLException( "Unable to connect to DataSource" );
   try {
  PreparedStatement addEntry =
 connection.prepareStatement( "INSERT INTO CUSTOMERS3" +
 "(tcNo,firstname,lastname,mail,password)" +
 "VALUES ( ?, ?, ?, ?,? )" ); 
 addEntry.setInt( 1, Integer.parseInt(getTcNo()) );
 addEntry.setString( 2, getFirstname() );
 addEntry.setString( 3, getLastname()  );
 addEntry.setString( 4, getMail() );
 addEntry.setString( 5, getPassword() );
 addEntry.executeUpdate();  
 
   return "index";
   
   }
   finally { connection.close();} 
   
   }
 public ResultSet oldorder() throws SQLException {
  if ( dataSource == null )
 throw new SQLException( "Unable to obtain DataSource" );
 Connection connection = dataSource.getConnection();
 if ( connection == null )
 throw new SQLException( "Unable to connect to DataSource" );
 try {
  PreparedStatement ps =
 connection.prepareStatement( "select ordertable.ORDERNUMBER, ordertable.QUANTITYORDER, ordertable.TOTAL \n" +
"from ordertable, customers3 where customers3.MAIL=? and customers3.password =? and \n" +
" customers3.ID=ordertable.CUSTOMERID");
 //ps.setInt(1, Integer.parseInt(getId()));
 ps.setString(1, getMail());
  ps.setString(2, getPassword());

 //id=String.valueOf(ps);
 rowSet = new com.sun.rowset.CachedRowSetImpl();
 rowSet.populate( ps.executeQuery() );
return rowSet;
 }
    finally
 {connection.close(); } 
 }
 public ResultSet ordernumber() throws SQLException {
  if ( dataSource == null )
 throw new SQLException( "Unable to obtain DataSource" );
 Connection connection = dataSource.getConnection();
 if ( connection == null )
 throw new SQLException( "Unable to connect to DataSource" );
 try {
  PreparedStatement ps =
 connection.prepareStatement( "SELECT  CUSTOMERs3.firstname, customers3.LASTNAME,totalorder FROM \n" +
"CUSTOMERs3 INNER JOIN (select customerid, COUNT(ORDERnumber) as \n" +
"Totalorder from ordertable GROUP BY customerid\n" +
"HAVING COUNT(ORDERnumber)>=? ) as table2 ON table2.CUSTOMERID=CUSTOMERs3.ID" );
 ps.setInt(1, Integer.parseInt( getCount()) ); 
 
rowSet = new com.sun.rowset.CachedRowSetImpl();
 rowSet.populate( ps.executeQuery() );
return rowSet;
 }
    finally
 {connection.close(); } 
 }
 public ResultSet quantityordernumber() throws SQLException {
  if ( dataSource == null )
 throw new SQLException( "Unable to obtain DataSource" );
 Connection connection = dataSource.getConnection();
 if ( connection == null )
 throw new SQLException( "Unable to connect to DataSource" );
 try {
  PreparedStatement ps =
 connection.prepareStatement( "select customers3.FIRSTNAME,customers3.LASTNAME,aratablo.total from\n" +
"customers3 inner join (select customerid, sum(quantityorder) as total from ordertable \n" +
"group by customerid having sum(quantityorder)>=? and sum(quantityorder)<=?)\n" +
"as aratablo on customers3.id=aratablo.customerid " );
 ps.setInt(1, Integer.parseInt( getCount()) ); 
 ps.setInt(2, Integer.parseInt( getCount2()) );  
 
rowSet = new com.sun.rowset.CachedRowSetImpl();
 rowSet.populate( ps.executeQuery() );
return rowSet;
 }
    finally
 {connection.close(); } 
 }
 public ResultSet totalcost() throws SQLException {
  if ( dataSource == null )
 throw new SQLException( "Unable to obtain DataSource" );
 Connection connection = dataSource.getConnection();
 if ( connection == null )
 throw new SQLException( "Unable to connect to DataSource" );
 try {
  PreparedStatement ps =
 connection.prepareStatement( "select customers3.FIRSTNAME,customers3.LASTNAME,aratablo.totalcost, aratablo.total from\n" +
"customers3 inner join (select customerid, sum(quantityorder) as total, sum(total) as totalcost from ordertable \n" +
"group by customerid having sum(total)>=? and sum(total)<=?)\n" +
"as aratablo on customers3.id=aratablo.customerid" );
 ps.setDouble(1, Double.parseDouble(getCount()) ); 
ps.setDouble(2, Double.parseDouble(getCount2()) ); 
 
rowSet = new com.sun.rowset.CachedRowSetImpl();
 rowSet.populate( ps.executeQuery() );
return rowSet;
 }
    finally
 {connection.close(); } 
 }
 public String confirm() throws SQLException{
   if ( dataSource == null ) throw new SQLException( "Unable to obtain DataSource" );
   Connection connection = dataSource.getConnection();
   if ( connection == null ) throw new SQLException( "Unable to connect to DataSource" );
   try {
  PreparedStatement addEntry =
 connection.prepareStatement( "insert into ordertable (CUSTOMERID, QUANTITYORDER, TOTAL) values (\n" +
"(select id from customers3 where mail=? and password=?),\n" +
"(select count(price) as quantityorder from basket),\n" +
"(select sum(price) as  total from basket)\n" +
")" ); 
  

 addEntry.setString( 1, getMail() );
 addEntry.setString( 2, getPassword() );
 addEntry.executeUpdate();  
 
 PreparedStatement addEntry2 =
 connection.prepareStatement( "TRUNCATE TABLE BASKET" );      
 
addEntry2.executeUpdate(); 
 
   return "index2";
   
   }
   finally { connection.close();} 
   
   }
 
    }
  
