package com.altrdb.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.UUID;

/*
 * Author: Daniel Nelson
 * Description:
 */
public class App
{

	//Globals==============================================================
	public static String url = "jdbc:mysql://localhost:3306/altr_prod_db";

	public static String user = "root";

	public static String password = "HELLOWORLD";

	public static String tableName = CreateUUIDString(5);

	public static String creationQuery = "CREATE TABLE  " + tableName +
            "(id INTEGER not NULL, " +
            " first VARCHAR(255), " +
            " last VARCHAR(255), " +
            " age INTEGER, " +
            " email VARCHAR(255), " +
            " favoriteComic VARCHAR(255), " +
            " INDEX name (last, first), " +
            " PRIMARY KEY ( id ))";

	public static String deletionQuery = String.format("DROP TABLE %s;", tableName);

	public static boolean statementBool = false;
	//=====================================================================

	//Main Driver==========================================================

    public static void main( String[] args )
    {
    	FullDataBaseRun();
    }
	//=====================================================================

	//Methods==============================================================

    /*
     * This method initializes the connection based on the global url, user and password strings declared in the beginning
     * of the App.java file.
     */
	public static boolean FullDataBaseRun()
	{
		try
    	{

    		boolean dropTable = true;

    		Statement stmt = InitializeConnection();

    		if(stmt == null)
    		{
    			throw new Exception("Error on inializing the DriverManager/Statement");
    		}

    		Write("Table " + tableName + " Being Created...");
    		ExecuteQuery(stmt, creationQuery);

    		Write("Table Insertion Being Performed...");
    		for(int i = 1; i <11; i++)
    		{
    			String insertionQuery = String.format(
    					"INSERT INTO %s VALUES (%s,'%s','%s',29,'danielnelsondev@gmail.com','Batman');",
    					tableName,
    					i,
    					CreateUUIDString(5),
    					CreateUUIDString(5),
    					CreateUUIDString(5)
    			);

    			Write(insertionQuery);

    			ExecuteQuery(stmt, insertionQuery);
    		}

    		if(dropTable)
    		{
    			Write("Table Being Dropped...");
        		ExecuteQuery(stmt, deletionQuery);
    		}
    		else
    			Write("Preserving table in MYSQL");

    		CloseConnection();
    		return true;
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    		Write(ex.getMessage());
    		return false;
    	}
	}

    public static Statement InitializeConnection()
    {
    	try
    	{
    		Connection con = DriverManager.getConnection(url, user, password);

    		Class.forName("com.mysql.cj.jdbc.Driver");

    		Statement stmt = con.createStatement();
    		statementBool = true;
    		return stmt;
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    		Write(ex.getMessage());
    		statementBool = false;
    		return null;
    	}
    }

    /*
     * This method is used to assist in created randomized values for the insertions of the MYSQL table, and accepts
     * one parameter: an integer to determine the character length.
     */
    public static String CreateUUIDString(int size)
    {
		return  UUID.randomUUID().toString().substring(0, size);
    }

    /*
     * This method takes in a Statement object and a String (expected to be written in MYSQL query syntax),
     * to be executed on an open connection to the MYSQL database.
     */
    public static boolean ExecuteQuery(Statement stmt, String query)
    {
    	try
    	{
    		stmt.executeUpdate(query);
    		return true;
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    		Write(ex.getMessage());
    		return false;
    	}

    }

    /*
     * This method takes in a Connection object and closes it.
     */
    public static boolean CloseConnection()
    {
    	try
    	{
    		Connection con = DriverManager.getConnection(url, user, password);
    		con.close();
    		return true;
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    		Write(ex.getMessage());
    		return false;
    	}

    }

    /*
     * This method is to simplify the call for writing (with a new line) to the System's output.
     */
    public static boolean Write(String words)
    {
    	try
    	{
    		System.out.println(words);
    		return true;
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    		Write(ex.getMessage());
    		return false;
    	}

    }

}
