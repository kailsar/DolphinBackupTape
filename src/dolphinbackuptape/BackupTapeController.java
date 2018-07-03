/*
 * TimesheetController.java
 *
 * Created on 23 August 2011, 23:14
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
/**
 *
 * @author Christopher
 */
package dolphinbackuptape;

import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

public class BackupTapeController {

    static Connection conn = null;

    /**
     * Creates a new instance of TimesheetController
     */
    public BackupTapeController() {
    }

    public void establishConnection(String username, String password) {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String url = "jdbc:jtds:sqlserver://DDL-PC001636:1433/BackupTape;instance=SQLEXPRESS;namedPipe=true";
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.err.println("Exception: ");
            System.err.println(e.getMessage());
        }
    }

    public void addTapeSet(String rig, String type, int month, int year, String location, String code) {
        try {
            String query = "INSERT INTO TapeSet (Rig, Type, Month, Year, Location, Code) "
                    + "VALUES (" + "?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, rig);
            ps.setString(2, type);
            ps.setInt(3, month);
            ps.setInt(4, year);
            ps.setString(5, location);
            ps.setString(6, code);
            ps.executeUpdate();
            ps.close();
            
        } catch (SQLException e) {
            System.err.println("Exception: ");
            System.err.println(e.getMessage());
        }
    }

    public void removeTapeSet(String tapeCode) {
        try {
            String query = "DELETE FROM TapeSet WHERE Code = "
                    + "?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, tapeCode);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.err.println("Exception: ");
            System.err.println(e.getMessage());
        }
    }
         public void changeTapeSet(String tapeCode, String location) {
        try {
            String query = "UPDATE TapeSet SET Location = '" + location + "' WHERE Code = '" + tapeCode + "'";
            System.out.println(query);
            PreparedStatement ps = conn.prepareStatement(query);
           // ps.setString(1, location);
            //ps.setString(2, tapeCode);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.err.println("Exception: ");
            System.err.println(e.getMessage());
        }
    }

    public ResultSet searchTapeSet(String rig, String type, int month, int year, String location) {
        ResultSet rs = null;
        try {
            int x = 0;
            String query = "SELECT * FROM TapeSet WHERE  ";
            if (rig != "---") {
                query = query + "Rig = '" + rig + "'";
                x = x + 1;
            }
            if (type != "---") {
                if (x > 0) {query = query + " AND ";}
                query = query + "Type = '" + type + "'";
                x = x + 1;
            }
            if (month != 0) {
                if (x > 0) {query = query + " AND ";}
                query = query + "Month = " + month;
                x = x + 1;
            }
            if (year != 0) {
                if (x > 0) {query = query + " AND ";}
                query = query + "Year = " + year;
                x = x + 1;
            }
            if (location != "---") {
                if (x > 0) {query = query + " AND ";}
                query = query + "Location = '" + location + "'";
                x = x + 1;
            }
            if (x == 0) {
                query = "SELECT * FROM TapeSet";
            }
            PreparedStatement ps = conn.prepareStatement(query);

            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.err.println("Exception: ");
            System.err.println(e.getMessage());
        }
        return rs;
    }

    public static Connection getConnection() {
        return conn;
    }
}
