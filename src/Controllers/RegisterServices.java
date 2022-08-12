package Controllers;

import DataBaseConnection.DbConnection;
import Models.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegisterServices implements RegisterService {
    @Override
    public boolean saveUsers(Users users) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query="INSERT INTO Users VALUES(?,?,?,?)";

        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,users.getUserId());
        stm.setObject(2,users.getUserName());
        stm.setObject(3,users.getPassword());
        stm.setObject(4,users.getName());

        return stm.executeUpdate()>0;
    }

    @Override
    public boolean updateUsers(Users users) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Users SET username=?,password=?,name=? WHERE userId=?");
        stm.setObject(1,users.getUserName());
        stm.setObject(2,users.getPassword());
        stm.setObject(3,users.getName());
        stm.setObject(4,users.getUserId());

        return stm.executeUpdate()>0;
    }

    @Override
    public Users searchUsers(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Users WHERE userId=?");
        stm.setObject(1, id);
        ResultSet rst = stm.executeQuery();
        if (rst.next()){
            return new Users(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteUsers(String id) {
        return false;
    }

    @Override
    public ArrayList<Users> getAllUsers() {
        return null;
    }

    @Override
    public String generateUserIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT userId FROM Users ORDER BY userId DESC LIMIT 1").executeQuery();
        if (rst.next()){
            int tempId = Integer.parseInt(rst.getString(1).split("-")[1]);
            tempId = tempId+1;

            if (tempId <= 9){
                return "U00-000"+tempId;
            }else if (tempId <= 99) {
                return "U00-00" + tempId;
            }else if (tempId <= 999){
                return "U00-0"+tempId;
            }else {
                return "U00-"+tempId;
            }
        }else {
            return "U00-0001";
        }
    }

    @Override
    public Users searchByUserName(String username) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Users WHERE username=?");
        stm.setObject(1, username);
        ResultSet rst = stm.executeQuery();
        if (rst.next()){
            return new Users(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
        } else {
            return null;
        }
    }

    @Override
    public boolean ifSearch(String username) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Users WHERE username='"+username+"'");
        ResultSet rst = stm.executeQuery();
        return rst.next();
    }

    @Override
    public boolean searchForLogin(String username, String password) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Users WHERE username='"+username+"' AND password='"+password+"'");
        ResultSet rst = stm.executeQuery();
        return rst.next();
    }
}
