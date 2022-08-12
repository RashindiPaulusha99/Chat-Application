package Controllers;

import Models.Users;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RegisterService {
    boolean saveUsers(Users users) throws SQLException, ClassNotFoundException;
    boolean updateUsers(Users users) throws SQLException, ClassNotFoundException;
    Users searchUsers(String id) throws SQLException, ClassNotFoundException;
    boolean deleteUsers(String id);
    ArrayList<Users> getAllUsers();
    String generateUserIds() throws SQLException, ClassNotFoundException;
    Users searchByUserName(String username) throws SQLException, ClassNotFoundException;
}
