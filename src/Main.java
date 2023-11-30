import java.sql.*;

/**
 *  Demonstration, JDBC, mysql-connector-j mot databas
 */
public class Main {
    public static void main(String[] args){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/starwars", "bill","clinton");
            statement = connection.createStatement();

            boolean hasResultSet = statement.execute("SELECT chp.character_name, chp.planet_id, p.planet_id, p.planet_name "+
                    "FROM character_home_planet chp "+
                    "LEFT OUTER JOIN planets p ON chp.planet_id = p.planet_id");
            if(hasResultSet){
                resultSet = statement.getResultSet();
                while (resultSet.next()){
                    System.out.println(resultSet.getString(1));
                }
            } else {
                System.out.println("Det var inte ett resultset");
            }

/*            System.out.print("Ange elev att skicka till valfri planet: ");
            String human = new Scanner(System.in).nextLine();
            System.out.print("Ange id för planet i fråga: ");
            int planetId = new Scanner(System.in).nextInt();
            int rowsAffected = statement.executeUpdate(
                "INSERT INTO character_home_planet(character_name, planet_id) "+
                    "VALUES ('" + human + "'," + planetId + ")");
            System.out.println(rowsAffected + " rader uppdaterade");*/


            // executeQuery, execute, updateQuery.
/*            resultSet = statement.executeQuery(
                    "SELECT chp.character_name, chp.planet_id, p.planet_id, p.planet_name "+
                        "FROM character_home_planet chp "+
                        "LEFT OUTER JOIN planets p ON chp.planet_id = p.planet_id");*/
/*            while (resultSet.next()){
                System.out.println(resultSet.getString(1));
            }*/
        } catch (SQLException err){
            System.out.println("Ett fel uppstod:\n" + err.getMessage());
        }
        finally {
            try {
                if(connection != null)
                    connection.close();
                if(statement != null)
                    statement.close();
                if(resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
