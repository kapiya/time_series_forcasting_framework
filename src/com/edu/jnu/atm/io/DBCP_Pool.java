package com.edu.jnu.atm.io;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

public class DBCP_Pool {
    
	
	private static DataSource ds = null;
   
    static {
        try {
            InputStream in = DBCP_Pool.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
            Properties prop = new Properties();
            prop.load(in);
            ds = BasicDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }
    
    public static Connection getConnection() throws SQLException {

    	return ds.getConnection();
    	
    }
    
    public static void release (Connection conn,Statement st,ResultSet rs) {
        
    	if (rs!=null) {
            try{
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = null;
        }     
    	if (st!=null) {
            try {
                st.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }      
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        
    }
    
    
}
