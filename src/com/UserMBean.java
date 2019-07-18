package com;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Hamza Nawaz on 6/16/2019.
 */
@ManagedBean(name = "user")
public class UserMBean {
    Connection con=null;
    public UserBBean getUserb() {
        return userb;
    }

    public void setUserb(UserBBean userb) {
        this.userb = userb;
    }

    UserBBean userb;
    public UserMBean(){}

    @PostConstruct
    public void init()
    {
        userb=new UserBBean();
    }

    public void connection()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/buildings","root","");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
    }


    public void saveToDb() throws SQLException
    {

        connection();
        System.out.println("Open Database Successfully");
        PreparedStatement preparedStatement=con.prepareStatement("insert into user (fname, lname, cnic, total, remainingamount, address) values (?,?,?,?,?,?);");
        preparedStatement.setString(1,this.userb.getFname());
        preparedStatement.setString(2,this.userb.getLname());
        preparedStatement.setLong(3,this.userb.getCnic());
        preparedStatement.setDouble(4,this.userb.getTotalamount());
        preparedStatement.setDouble(5,this.userb.getRemainingamount());
        preparedStatement.setString(6,this.userb.getAddress());
        preparedStatement.executeUpdate();

        this.userb.setFname("");
        this.userb.setLname("");
        this.userb.setCnic(0);
        this.userb.setTotalamount(0.0);
        this.userb.setRemainingamount(0.0);
        this.userb.setAddress("");

    }
    public List<UserBBean> getPersonList() throws SQLException
    {
        List<UserBBean> list=new ArrayList<UserBBean>();
        connection();
        PreparedStatement pst=con.prepareStatement("SELECT * FROM user");
        ResultSet res=pst.executeQuery();
        while(res.next())
        {
            UserBBean userr=new UserBBean();
            userr.fname=res.getString("fname");
            userr.lname=res.getString("lname");
            userr.cnic=res.getLong("cnic");
            userr.totalamount=res.getDouble("total");
            userr.remainingamount=res.getDouble("remainingamount");
            userr.address=res.getString("address");

            list.add(userr);

        }
        return list;
    }


    public String DeleteCustomer(UserBBean u) throws SQLException
    {
        String fname=u.fname;
        String lname=u.lname;
        long cnic=u.cnic;
        double totalamount=u.totalamount;
        double remainingamount=u.remainingamount;
        String address=u.address;

        connection();

        String sql="delete from user where cnic='"+cnic+"'";
        Statement stmt=con.createStatement();
        stmt.executeUpdate(sql);
        System.out.println("Success");
        return null;

    }

  /*  public String editPerson()
    {
        FacesContext fc=FacesContext.getCurrentInstance();
        Map<String,String> params=fc.getExternalContext().getRequestParameterMap();

        String fname=params.get("action");
        System.out.println("fnames = " + fname);

        connection();

        try {
            String sql = "select * from user where fname = '" + fname + "' ";
            Statement stmt = con.createStatement();
            System.out.print(sql);
            ResultSet res = stmt.executeQuery(sql);
            UserBBean u=new UserBBean();
            while(res.next())
            {
                u.setFname(res.getString("fname"));
                u.setLname(res.getString("lname"));
                u.setCnic(res.getLong(0));
                u.setTotalamount(res.getDouble(0));
                u.setRemainingamount(res.getDouble(0));
                u.setAddress(res.getString("address"));
                session

            }

        }catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }*/

}
