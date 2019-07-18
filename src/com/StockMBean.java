package com;

import com.sun.faces.context.SessionMap;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Hamza Nawaz on 6/16/2019.
 */
@SessionScoped
@ManagedBean(name = "stockm")
public class StockMBean {
    Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

    Connection con=null;
    public StockMBean()
    {}

    public StockBBean getStockb() {
        return stockb;
    }

    public void setStockb(StockBBean stockb) {
        this.stockb = stockb;
    }

    StockBBean stockb;
    @PostConstruct
    public void init()
    {
        stockb=new StockBBean();
    }

    public void connection()
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

    public void saveToDb() throws SQLException
    {
        connection();
        PreparedStatement preparedStatement=con.prepareStatement("insert into stock (nameofproduct, quantity, purchaseprice, dateofpurchase) values (?,?,?,?);");
        preparedStatement.setString(1,this.stockb.nameofproduct);
        preparedStatement.setDouble(2, this.stockb.quantity);
        preparedStatement.setDouble(3,stockb.purchaseprice);
        preparedStatement.setString(4, this.stockb.dateofpurchase);

        preparedStatement.executeUpdate();

        this.stockb.setNameofproduct("");
        this.stockb.setQuantity(0);
        this.stockb.setPurchaseprice(0);
        this.stockb.setDateofpurchase("00/00/0000");



    }

    public List<StockBBean> getStockList() throws SQLException
    {
        List<StockBBean> list=new ArrayList<StockBBean>();
        connection();
        PreparedStatement pst=con.prepareStatement("SELECT * FROM stock");
        ResultSet res=pst.executeQuery();
        while(res.next())
        {
            StockBBean sb=new StockBBean();
            sb.nameofproduct=res.getString("nameofproduct");
            sb.quantity=res.getDouble("quantity");
            sb.purchaseprice=res.getDouble("purchaseprice");
            sb.dateofpurchase=res.getString("dateofpurchase");

            list.add(sb);

        }
        return list;
    }


    public String DeleteStock(StockBBean sb) throws SQLException
    {
        String nameofproduct=sb.nameofproduct;
        double quantity=sb.quantity;
        double purchaseprice=sb.purchaseprice;
        String dateofpurchase=sb.dateofpurchase;

        connection();

        String sql="delete from stock WHERE nameofproduct='"+nameofproduct+"'";
        Statement stmt=con.createStatement();
        stmt.executeUpdate(sql);
        System.out.println("Success");
        return null;

    }



    public String editStock() throws SQLException {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        String nameofproduct = params.get("action");
        System.out.println("Edit Name = " + nameofproduct.toString());
        try {
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/buildings";
            String user = "root";
            String pass = "";
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pass);
            PreparedStatement pstmt4 = con.prepareStatement("SELECT  * FROM stock WHERE nameofproduct='" + nameofproduct + "'");
            ResultSet res = pstmt4.executeQuery();
            StockBBean sb = new StockBBean();
            while (res.next()) {
                sb.setNameofproduct(res.getString("nameofproduct"));
                sb.setQuantity(res.getDouble("quantity"));
                sb.setPurchaseprice(res.getDouble("purchaseprice"));
                sb.setDateofpurchase(res.getString("dateofpurchase"));
                sessionMap.put("editStock", sb);
                return "/edit.xhtml?faces-redirect=true";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/edit.xhtml?faces-redirect=true";
    }
    public String updateStock() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        String nameofproduct = params.get("update_action");
        System.out.print("UpdateStock : "+nameofproduct);
        try {
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/buildings";
            String user = "root";
            String pass = "";
            Class.forName(driver);
           Connection con = DriverManager.getConnection(url, user, pass);
            StockBBean sb =(StockBBean)sessionMap.get("editStock");
            PreparedStatement pstmt = con.prepareStatement("UPDATE stock SET nameofproduct=?,quantity=?,purchaseprice=?,dateofpurchase=? WHERE nameofproduct ='"+nameofproduct.toString()+"'");
            pstmt.setString(1,sb.getNameofproduct());
            pstmt.setDouble(2,sb.getQuantity());
            pstmt.setDouble(3,sb.getPurchaseprice());
            pstmt.setString(4,sb.getDateofpurchase());
            int res = pstmt.executeUpdate();

            if(res >0)
            {
                return "/StockDisplay.xhtml?faces-redirect=true";
            }
            else
            {
                return "/Error.xhtml?faces-redirect=true";
            }
        }catch (Exception e){e.printStackTrace();}
        return "/edit.xhtml?faces-redirect=true";
    }

}
