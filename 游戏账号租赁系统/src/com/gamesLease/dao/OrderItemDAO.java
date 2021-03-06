package com.gamesLease.dao;

import com.gamesLease.bean.OrderItem;
import com.gamesLease.db.DBPool;
import com.gamesLease.db.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyx11 on 2017-5-17.
 */
public class OrderItemDAO {
    public boolean addOrderItem(OrderItem orderItem) {
        Connection conn=null;
        PreparedStatement ps=null;
        try{
            conn= DBPool.getConnection();
            String sql = "" +
                    "insert into orderitem (lease_id,uid,end,cost) values(?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, orderItem.getLeaseId());
            ps.setInt(2, orderItem.getUid());
            ps.setTimestamp(3, new Timestamp(orderItem.getEnd().getTime()));

            ps.setDouble(4,orderItem.getCost());

            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                orderItem.setId(rs.getInt(1));
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public List<OrderItem> queryOrderItemByUid(Integer uid) {
        List<OrderItem> result=null;
        Connection conn=null;
        PreparedStatement ps=null;
        try{
            conn= DBPool.getConnection();
            String sql = "select * from orderitem where uid=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, uid);

            ResultSet rs=ps.executeQuery();
            result = new ArrayList<>();
            OrderItem orderItem=null;
            while (rs.next()) {
                orderItem=new OrderItem();
                orderItem.setId(rs.getInt("id"));
                orderItem.setLeaseId(rs.getInt("lease_id"));
                orderItem.setId(rs.getInt("uid"));
                orderItem.setStart(rs.getTimestamp("start"));
                orderItem.setEnd(rs.getTimestamp("end"));
                orderItem.setCost(rs.getDouble("cost"));
                orderItem.setStatus(rs.getInt("status"));

                result.add(orderItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public List<OrderItem> queryOrderItem() {
        List<OrderItem> result=null;
        Connection conn=null;
        PreparedStatement ps=null;
        try{
            conn= DBPool.getConnection();
            String sql = "select * from orderitem ";
            ps = conn.prepareStatement(sql);

            ResultSet rs=ps.executeQuery();
            result = new ArrayList<>();
            OrderItem orderItem=null;
            while (rs.next()) {
                orderItem=new OrderItem();
                orderItem.setId(rs.getInt("id"));
                orderItem.setLeaseId(rs.getInt("lease_id"));
                orderItem.setUid(rs.getInt("uid"));
                orderItem.setStart(rs.getTimestamp("start"));
                orderItem.setEnd(rs.getTimestamp("end"));
                orderItem.setCost(rs.getDouble("cost"));
                orderItem.setStatus(rs.getInt("status"));

                result.add(orderItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public OrderItem getOrderItem(Integer id) {
        OrderItem result=null;
        Connection conn=null;
        PreparedStatement ps=null;
        try{
            conn= DBPool.getConnection();
            String sql = "" +
                    "select * from where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs=ps.executeQuery();
            if (rs.next()) {
                result=new OrderItem();
                result.setId(rs.getInt("id"));
                result.setLeaseId(rs.getInt("lease_id"));
                result.setUid(rs.getInt("uid"));
                result.setStart(rs.getTimestamp("start"));
                result.setEnd(rs.getTimestamp("end"));
                result.setCost(rs.getDouble("cost"));
                result.setStatus(rs.getInt("status"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return  result;
    }
}
