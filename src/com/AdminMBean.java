package com;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.sql.*;

/**
 * Created by Hamza Nawaz on 6/17/2019.
 */
@ManagedBean(name = "adminm")
public class AdminMBean {
    Connection con=null;
    public AdminBBean getAdminb() {
        return adminb;
    }

    public void setAdminb(AdminBBean adminb) {
        this.adminb = adminb;
    }

    AdminBBean adminb;
    public AdminMBean()
    {}
    @PostConstruct
    public void init()
    {
        adminb=new AdminBBean();
    }

    public void connectionnn()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/buildings", "root", "");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
    }

    public void saveToDB() throws SQLException
    {
        connectionnn();
        System.out.print("Successfully open");
        PreparedStatement preparedStatement=con.prepareStatement("insert into signup (username, cnic, password) values (?,?,?);");
        preparedStatement.setString(1,this.adminb.getUsername());
        preparedStatement.setLong(2,this.adminb.getCnic());
        preparedStatement.setString(3,this.adminb.getPassword());

        preparedStatement.executeUpdate();
        this.adminb.setUsername("");
        this.adminb.setCnic(0);
        this.adminb.setPassword("");

    }



    //Login

    public String login() {

        Connection con =null;
        PreparedStatement pstmt1 = null;

        long cnic =adminb.getCnic();
        String password = adminb.getPassword();
        try {
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/buildings";
            String user = "root";
            String pass = "";
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pass);
            pstmt1 = con.prepareStatement("SELECT * FROM signup WHERE cnic'" + cnic + "' AND password='" + password.toString() + "'");
            ResultSet res1 = pstmt1.executeQuery();

            if(pstmt1 != null)
            {

                int s=0;
                while(res1.next())
                {
                    s++;
                    return "adminpannel";
                }

                if(s==0)
                {
                    return "/Error.xhtml?faces-redirect=true";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                con.close();
                pstmt1.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return "SaveData Method Called";
    }


}
