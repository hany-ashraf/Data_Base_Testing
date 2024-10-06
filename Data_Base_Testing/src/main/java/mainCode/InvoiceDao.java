package mainCode;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDao {

    private final Connection connection;

    //Constructor that receive a connection string on db
    public InvoiceDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * A method that get the data in colums in customer and value !
     * @return all data in table
     */
     public List<Invoice> all(){
        try{
            PreparedStatement ps = connection.prepareStatement(
                    "select * from invoice");
            ResultSet rs = ps.executeQuery();

            List<Invoice> allInvoices = new ArrayList<>();
            //looping on each colum in row !
            while (rs.next()){
                allInvoices.add(new Invoice(rs.getString("customer"), rs.getDouble("value")));
            }
            return allInvoices;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param value
     * @return
     */
    public List<Invoice> allWithAtLeast(int value){
        try{
            PreparedStatement ps = connection.prepareStatement(
                    "select * from invoice where value >= ?");

            //1: The first parameter index (meaning this will replace the first ? placeholder in the SQL query).
            //This is the actual integer **value** that will replace the first ? in the SQL query.
            ps.setInt(1, value);
            ResultSet rs = ps.executeQuery();

            List<Invoice> allInvoices = new ArrayList<>();
            while(rs.next()){
                //Structure of objects of type of Invoice !
                allInvoices.add(
                        new Invoice(rs.getString("name"), rs.getInt("value"))
                );
            }
            return allInvoices;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * That function to insert data in DB
     * @param inv structure of two members
     */
    public void save(Invoice inv){
        try{
            PreparedStatement ps = connection.prepareStatement(
                    "insert into invoice (name, value) values (?,?)"
            );

            ps.setString(1, inv.customer);
            ps.setDouble(2, inv.value);
            ps.execute();

            connection.commit();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
