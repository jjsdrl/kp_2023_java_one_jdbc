package com.hw.test;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Test {
    public static void main(String[] args){
        /*
        *
        * 1.加载驱动
        * 2.获取数据库连接
        * 3.创建可执行sql语句的对象
        * 4.执行sql语句并处理结果
        * 5.关闭数据库连接
        *
        * */
        Connection conn = null;
        CallableStatement pstm = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获取数据库连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kp", "root", "123456");
            //3.创建可执行sql语句的对象
            String sql = "select * from tb_user";
            pstm = conn.prepareCall(sql);
            //4.执行sql语句并处理结果
            rs = pstm.executeQuery();
            //ResultSet 结果集合 通过游标来遍历集合中的所有数据 rs.next()执行一次，游标就会向后移动一行，若游标执行的那一行有数据，返回true ，否则返回false
            while (rs.next()){
                //把游标当前指向的那条数据的每个字段的值获取到
                int id = rs.getInt("id");//int id = rs.getInt(1);
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String status = rs.getString("status");
                String gender = rs.getString("gender");
                System.out.println(id+"---"+name+"---"+age+"---"+status+"---"+gender);
            }

        }catch (Exception e ){
            e.printStackTrace();
        }finally {
            //5.关闭数据库连接
            try {
                if (rs != null){
                    rs.close();
                }
                if (pstm != null){
                    pstm.close();
                }
                if (conn != null){
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
