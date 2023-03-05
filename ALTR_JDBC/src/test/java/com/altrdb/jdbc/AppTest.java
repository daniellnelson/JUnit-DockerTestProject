package com.altrdb.jdbc;

import java.sql.Statement;

import org.junit.Test;

public class AppTest {

	@Test
	public void TestStatementCreation()
	{
		try
		{
			App.InitializeConnection();

			assert(App.statementBool);
		}
		catch(Exception Ex)
		{
			App.Write(Ex.getMessage());
		}

	}

	@Test
	public void TestCreateUUIDStringOfSize5()
	{
		try
		{
			String word = App.CreateUUIDString(5);
			Integer.parseInt(word);

		}
		catch(Exception Ex)
		{
			App.Write(Ex.getMessage());
		}
	}

	@Test
	public void TestExecuteQuery()
	{
		try
		{
			Statement stmt = App.InitializeConnection();
			assert(App.ExecuteQuery(stmt, "SHOW TABLES;"));
			}
		catch(Exception Ex)
		{
			App.Write(Ex.getMessage());
		}
	}

	@Test
	public void TestCreateNewTable()
	{
		try
		{
			Statement stmt = App.InitializeConnection();
			if(!App.statementBool)
				throw new Exception("Test failed due to the connection not initializing.");

			assert(App.ExecuteQuery(stmt, App.creationQuery));
			
		}
		catch(Exception Ex)
		{
			App.Write(Ex.getMessage());
		}
	}

	@Test
	public void TestCloseConnection()
	{
		try
		{
			App.InitializeConnection();
			if(!App.statementBool)
				throw new Exception("Test failed due to the connection not initializing.");

			assert(App.CloseConnection());
		}
		catch(Exception Ex)
		{
			App.Write(Ex.getMessage());
		}
	}

	@Test
	public void TestWrite()
	{
		try
		{
			assert(App.Write("Hello, world"));
		}
		catch(Exception Ex)
		{
			App.Write(Ex.getMessage());
		}
	}

	@Test
	public void FullDataBaseRun()
	{
		try
		{
			assert(App.FullDataBaseRun());
		}
		catch(Exception Ex)
		{
			App.Write(Ex.getMessage());
		}
	}

}
