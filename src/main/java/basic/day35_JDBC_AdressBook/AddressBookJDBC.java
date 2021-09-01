package basic.day35_JDBC_AdressBook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddressBookJDBC {

	public static void main(String[] args) {
		connectionEshtablished();
		readAdressbook();
		updateContactNameOfAdressbook();
		showContactsBetweenGivenDates();
	}

	private static Connection getSqlConnection() {
		Connection conn = null;
		String dbHostUrl = "jdbc:mysql://localhost:3306/adress_book_service";
		String userName = "root";
		String password = "Md78678678.";
		try {
			conn = DriverManager.getConnection(dbHostUrl, userName, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;

	}

	private static void connectionEshtablished() {
		Connection conn = getSqlConnection();
		if (conn != null) {
			System.out.println("Connection is eshtablished");
		}
	}

	private static void readAdressbook() {
		System.out.println("Displaying all data of adressBook table");
		Connection conn = getSqlConnection();

		try {
			if (conn != null) {
				String readEmpPayroll = "SELECT * FROM adressbook";

				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(readEmpPayroll);
				while (resultSet.next()) {
					String firstName = resultSet.getString(1);
					String lastName = resultSet.getString(2);
					String adress = resultSet.getString(3);
					String city = resultSet.getString(4);
					String state = resultSet.getString(5);
					Integer zip = resultSet.getInt(6);
					String phoneNumber = resultSet.getString(7);
					String email = resultSet.getString(8);
					String family = resultSet.getString(9);
					String friends = resultSet.getString(10);
					String professions = resultSet.getString(11);
					String brothers = resultSet.getString(12);
					String row = String.format(
							"User record:\n FirstName: %s,\n LastName: %s, \n Address: %s,  \n City: %s,\n State: %s , \n Zip: %d, \n PhoneNumber: %s, \n email:%s,\n Family:%s,\n Friends:%s,\n Professions:%s,\n Brothers:%s,",
							firstName, lastName, adress, city, state, zip, phoneNumber, email, family, friends,
							professions, brothers);
					System.out.println(row);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException sqlException) {
					System.out.println(sqlException.getMessage());

				}
			}
		}

	}

	private static void updateContactNameOfAdressbook() {
		System.out.println("Updating address of Sumer ");
		Connection conn = getSqlConnection();
		if (conn != null) {
			String updateEmpPayroll = "UPDATE adressbook SET address = ? WHERE lastName ='Sumer'";
			try {
				PreparedStatement preparedStatement = conn.prepareStatement(updateEmpPayroll);
				preparedStatement.setString(1, "Redhills");
				int rowUpdated = preparedStatement.executeUpdate();
				if (rowUpdated > 0) {
					System.out.println("Data is Updated");
				}
			} catch (SQLException e) {

				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException sqlException) {
						System.out.println(sqlException.getMessage());

					}
				}
			}
		}

	}

	private static void showContactsBetweenGivenDates() {
		System.out.println("Displaying contacts added between given dates");
		Connection conn = getSqlConnection();

		try {
			if (conn != null) {
				String readEmpPayroll = "SELECT lastName FROM adressbook WHERE joinedDate between '2021-08-01' and '2021-09-08'";

				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(readEmpPayroll);
				while (resultSet.next()) {

					String name = resultSet.getString(1);

					String row = String.format("User record: \n Name: %s", name);
					System.out.println(row);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException sqlException) {
					System.out.println(sqlException.getMessage());

				}
			}
		}

	}

}
