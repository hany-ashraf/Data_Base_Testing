package testCases;

import Listeners.CustomListener;
import mainCode.Invoice;
import mainCode.InvoiceDao;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.assertj.core.api.Assertions.assertThat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

// Applying the custom listener
@ExtendWith(CustomListener.class)
public class InvoiceDoaIntegrationTest {
    //Two Global private members
    private static Connection connection;
    private InvoiceDao dao;

    /**
     * That method open a new session with DB
     * @throws SQLException
     */
    @BeforeAll
    static void setUp() throws SQLException {
        // Connection details
        String url = "jdbc:sqlserver://localhost:1433;databaseName=YourDatabaseName"; // Adjust accordingly
        String username = "yourUsername"; // Replace with your SQL Server username
        String password = "yourPassword"; // Replace with your SQL Server password

        // Establishing the connection
        connection = DriverManager.getConnection(url, username, password);
        System.out.println("Connected to the database.");
    }

    @Test
    void save(){
        Invoice inv1 = new Invoice("Hany", 10);
        Invoice inv2 = new Invoice("Ashraf", 11);

        dao.save(inv1);

        List<Invoice> afterSaving = dao.all();
        assertThat(afterSaving).containsExactlyInAnyOrder(inv1);

        dao.save(inv2);

        List<Invoice> afterSavingAgain = dao.all();
        assertThat(afterSavingAgain).containsExactlyInAnyOrder(inv1, inv2);
    }

    @AfterAll
    static void tearDown() {
        // Closing the database connection
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
