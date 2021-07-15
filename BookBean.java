import java.sql.Connection;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import javax.annotation.Resource;
 import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
 import javax.sql.DataSource;
 import javax.sql.rowset.CachedRowSet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@ManagedBean ( name="bookBean" )
@SessionScoped

public class BookBean {
private String bookName;
private String isbn;
private String papers;
private String publisher;
private String bookType;
private String language;
private String price;

   

private String authorId;
private String authorName;
private String authorLastName;

private String controlBookName;
private String controlAuthorName;
private String controlAuthorLastName;
private String controlBookPub;

    
private String atleast;
private String atmost;

 DataSource dataSource;
 CachedRowSet rowSet=null;
 CachedRowSet rowSet2=null;
  CachedRowSet rowSet3=null;
  CachedRowSet rowSet4=null;
 
 
   public BookBean() {
        try {
			Context ctx = new InitialContext();
			dataSource = (DataSource)ctx.lookup("jdbc/addressbook");
		} catch (NamingException e) {
			e.printStackTrace();
		}
    }
    public String getBookName() {return bookName; }
    public void setBookName(String bookName) {this.bookName = bookName;}
    public String getIsbn() {return isbn;}
    public void setIsbn(String isbn) {this.isbn = isbn;}
    public String getPapers() {return papers;}
    public void setPapers(String papers) {this.papers = papers;}
    public String getPublisher() {return publisher;}
    public void setPublisher(String publisher) {this.publisher = publisher;}
    public String getBookType() {return bookType;}
    public void setBookType(String bookType) {this.bookType = bookType;}
    public String getLanguage() {return language;}
    public void setLanguage(String language) {this.language = language; }
    public String getAuthorId() {return authorId;}
    public void setAuthorId(String authorId) {this.authorId = authorId;}
    public String getAuthorName() { return authorName;}
    public void setAuthorName(String authorName) { this.authorName = authorName;}
    public String getAuthorLastName() {return authorLastName;}
    public void setAuthorLastName(String authorLastName) { this.authorLastName = authorLastName;}
    public String getControlBookName() {return controlBookName;}
    public void setControlBookName(String controlBookName) {this.controlBookName = controlBookName;}
    public String getControlAuthorName() {return controlAuthorName;}
    public void setControlAuthorName(String controlAuthorName) {this.controlAuthorName = controlAuthorName;}
     public String getControlAuthorLastName() {return controlAuthorLastName;}
    public void setControlAuthorLastName(String controlAuthorLastName) {this.controlAuthorLastName = controlAuthorLastName;}
    public String getControlBookPub() {return controlBookPub; }
    public void setControlBookPub(String controlBookPub) {this.controlBookPub = controlBookPub;}
    public String getAtleast() {return atleast;}
    public void setAtleast(String atleast) {this.atleast = atleast;}
    public String getAtmost() {return atmost;}
    public void setAtmost(String atmost) {this.atmost = atmost;}
     public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    
 public ResultSet bul() throws SQLException {
 if ( dataSource == null )
 throw new SQLException( "Unable to obtain DataSource" );
 Connection connection = dataSource.getConnection();
 if ( connection == null )
 throw new SQLException( "Unable to connect to DataSource" );
 try {
 PreparedStatement ps =
 connection.prepareStatement( "SELECT BOOKS.ISBN, BOOKS.BOOKNAME, AUTHORS.AUTHORNAME, AUTHORS.AUTHORLASTNAME, BOOKS.PAPERS, BOOKS.PUBLISHER,BOOKS.BOOKTYPE, BOOKS.LANGUAGE, BOOKS.PRICE FROM\n" +
"AUTHORS , BOOKS WHERE AUTHORS.AUTHORID=BOOKS.AUTHORID AND BOOKS.BOOKNAME=?" );
 ps.setString( 1, getControlBookName() ); //get bulunacak önemli
 rowSet = new com.sun.rowset.CachedRowSetImpl();
 rowSet.populate( ps.executeQuery() );
return rowSet;
 }
 finally
 {connection.close(); } 
 } 
 
 public ResultSet bulpapers() throws SQLException {
  if ( dataSource == null )
 throw new SQLException( "Unable to obtain DataSource" );
 Connection connection = dataSource.getConnection();
 if ( connection == null )
 throw new SQLException( "Unable to connect to DataSource" );
 try {
  PreparedStatement ps =
 connection.prepareStatement( "select books.ISBN, books.BOOKNAME,authors.authorname, authors.authorlastname, books.PAPERS, books.PUBLISHER, books.BOOKTYPE, books.LANGUAGE, books.PRICE\n" +
" from books,authors where books.PAPERS>=? and books.PAPERS<=? and authors.AUTHORID= books.AUTHORID" );
 ps.setInt(1, Integer.parseInt( getAtleast()) ); 
 ps.setInt(2, Integer.parseInt( getAtmost()) ); 
 

 rowSet2 = new com.sun.rowset.CachedRowSetImpl();
 rowSet2.populate( ps.executeQuery() );
return rowSet2;
 }
    finally
 {connection.close(); } 
 }
 
 public ResultSet bulprice() throws SQLException {
  if ( dataSource == null )
 throw new SQLException( "Unable to obtain DataSource" );
 Connection connection = dataSource.getConnection();
 if ( connection == null )
 throw new SQLException( "Unable to connect to DataSource" );
 try {
  PreparedStatement ps =
 connection.prepareStatement( "select books.ISBN, books.BOOKNAME,authors.authorname, authors.authorlastname, books.PAPERS, books.PUBLISHER, books.BOOKTYPE, books.LANGUAGE, books.PRICE\n" +
" from books,authors where books.PRICE>=? and books.PRICE<=? and authors.AUTHORID= books.AUTHORID" );
 ps.setDouble(1, Double.parseDouble( getAtleast()) ); 
 ps.setDouble(2, Double.parseDouble( getAtmost()) ); 
 

 rowSet2 = new com.sun.rowset.CachedRowSetImpl();
 rowSet2.populate( ps.executeQuery() );
return rowSet2;
 }
    finally
 {connection.close(); } 
 }
 
 public ResultSet bulpub() throws SQLException {
  if ( dataSource == null )
 throw new SQLException( "Unable to obtain DataSource" );
 Connection connection = dataSource.getConnection();
 if ( connection == null )
 throw new SQLException( "Unable to connect to DataSource" );
 try {
  PreparedStatement ps =
 connection.prepareStatement( "select books.ISBN, books.BOOKNAME,authors.authorname, authors.authorlastname, books.PAPERS, books.PUBLISHER, books.BOOKTYPE, books.LANGUAGE, books.PRICE\n" +
" from books,authors where books.PUBLISHER=?  and authors.AUTHORID= books.AUTHORID" );
 ps.setString(1,  getControlBookPub());  

 rowSet3 = new com.sun.rowset.CachedRowSetImpl();
 rowSet3.populate( ps.executeQuery() );
return rowSet3;
 }
    finally
 {connection.close(); } 
 }
 
 public ResultSet bulauthor() throws SQLException {
  if ( dataSource == null )
 throw new SQLException( "Unable to obtain DataSource" );
 Connection connection = dataSource.getConnection();
 if ( connection == null )
 throw new SQLException( "Unable to connect to DataSource" );
 try {
  PreparedStatement ps =
 connection.prepareStatement( "select authors.authorname, authors.authorlastname,books.ISBN, books.BOOKNAME, books.PAPERS, books.PUBLISHER, books.BOOKTYPE, books.LANGUAGE, books.PRICE\n" +
" from books,authors where authors.AUTHORNAME=? and AUTHORS.AUTHORLASTNAME=? and authors.AUTHORID= books.AUTHORID" );
 ps.setString(1,  getControlAuthorName());  
 ps.setString(2, getControlAuthorLastName());  

 rowSet4 = new com.sun.rowset.CachedRowSetImpl();
 rowSet4.populate( ps.executeQuery() );
return rowSet4;
 }
    finally
 {connection.close(); } 
 }
 
 public String  bas() throws SQLException {
  if ( dataSource == null )
 throw new SQLException( "Unable to obtain DataSource" );
 Connection connection = dataSource.getConnection();
 if ( connection == null )
 throw new SQLException( "Unable to connect to DataSource" );
 try {
    
PreparedStatement addEntry =
 connection.prepareStatement( "INSERT INTO BASKET(ISBN, BOOKNAME, PRICE)\n" +
"SELECT ISBN,BOOKNAME,PRICE FROM BOOKS WHERE BOOKS.ISBN=?" );      
 addEntry.setInt( 1, Integer.parseInt(getIsbn()) );
addEntry.executeUpdate(); 
 return "";
 }
    finally
 {connection.close(); } 
 }

 public ResultSet basket() throws SQLException {
  if ( dataSource == null )
 throw new SQLException( "Unable to obtain DataSource" );
 Connection connection = dataSource.getConnection();
 if ( connection == null )
 throw new SQLException( "Unable to connect to DataSource" );
 try {
  PreparedStatement ps =
 connection.prepareStatement( "select basket.ISBN, basket.BOOKNAME, basket.PRICE from basket");

 rowSet3 = new com.sun.rowset.CachedRowSetImpl();
 rowSet3.populate( ps.executeQuery() );
return rowSet3;
 }
    finally
 {connection.close(); } 
 }
 public ResultSet total() throws SQLException {
  if ( dataSource == null )
 throw new SQLException( "Unable to obtain DataSource" );
 Connection connection = dataSource.getConnection();
 if ( connection == null )
 throw new SQLException( "Unable to connect to DataSource" );
 try {
  PreparedStatement ps =
 connection.prepareStatement( "select sum(price) as toplam from basket");

 rowSet3 = new com.sun.rowset.CachedRowSetImpl();
 rowSet3.populate( ps.executeQuery() );
return rowSet3;
 }
    finally
 {connection.close(); } 
 }

 public ResultSet bulpub2() throws SQLException {
  if ( dataSource == null )
 throw new SQLException( "Unable to obtain DataSource" );
 Connection connection = dataSource.getConnection();
 if ( connection == null )
 throw new SQLException( "Unable to connect to DataSource" );
 try {
  PreparedStatement ps =
 connection.prepareStatement( "SELECT AUTHORS.AUTHORNAME, AUTHORS.AUTHORLASTNAME,ARATABLO.PUBLISHER FROM \n" +
"AUTHORS INNER JOIN (select authorid , publisher from books  where publisher=?)\n" +
" AS ARATABLO ON AUTHORS.AUTHORID=ARATABLO.AUTHORID" );
 ps.setString(1,  getControlBookPub());  

 rowSet3 = new com.sun.rowset.CachedRowSetImpl();
 rowSet3.populate( ps.executeQuery() );
return rowSet3;
 }
    finally
 {connection.close(); } 
 }
 
 public ResultSet avg() throws SQLException {
  if ( dataSource == null )
 throw new SQLException( "Unable to obtain DataSource" );
 Connection connection = dataSource.getConnection();
 if ( connection == null )
 throw new SQLException( "Unable to connect to DataSource" );
 try {
  PreparedStatement ps =
 connection.prepareStatement( "select authors.AUTHORNAME, authors.AUTHORLASTNAME, aratablo.papers,aratablo.bookname\n" +
"from authors INNER JOIN (select authorid,bookname, papers from books\n" +
"where papers<(select avg (papers) from books))AS ARATABLO \n" +
"ON AUTHORS.AUTHORID=ARATABLO.AUTHORID " );
  
 rowSet3 = new com.sun.rowset.CachedRowSetImpl();
 rowSet3.populate( ps.executeQuery() );
return rowSet3;
 }
    finally
 {connection.close(); } 
 }
 
 public String  remove() throws SQLException {
  if ( dataSource == null )
 throw new SQLException( "Unable to obtain DataSource" );
 Connection connection = dataSource.getConnection();
 if ( connection == null )
 throw new SQLException( "Unable to connect to DataSource" );
 try {
    
PreparedStatement addEntry =
 connection.prepareStatement( "DELETE from basket where ISBN=?" );      
 addEntry.setInt( 1, Integer.parseInt(getIsbn()) );
addEntry.executeUpdate(); 
 return "";
 }
    finally
 {connection.close(); } 
 }

public String  removebasket() throws SQLException {
  if ( dataSource == null )
 throw new SQLException( "Unable to obtain DataSource" );
 Connection connection = dataSource.getConnection();
 if ( connection == null )
 throw new SQLException( "Unable to connect to DataSource" );
 try {
    
PreparedStatement addEntry =
 connection.prepareStatement( "TRUNCATE TABLE BASKET" );      
 
addEntry.executeUpdate(); 
 return "";
 }
    finally
 {connection.close(); } 
 }

}


