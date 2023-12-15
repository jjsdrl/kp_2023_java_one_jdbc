package com.hw.test;

import com.hw.entity.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class TestUser {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        System.out.print("请输入姓名:");
//        String name = scanner.next();
//        System.out.print("请输入年龄:");
//        int age = scanner.nextInt();
//        System.out.print("请输入status:");

//        String status = scanner.next();
//        System.out.print("请输入性别:");
//        String gender = scanner.next();
//        System.out.println(addUser(new User(name, age, status, gender)));


//        findAllUser().forEach(System.out::println);
//        System.out.print("请输入你要删除的用户的id:");
//        int id = scanner.nextInt();
//        deleteUser(id);
//        findAllUser().forEach(System.out::println);


        System.out.print("请输入你要修改的用户的id:");
        int id = scanner.nextInt();
        System.out.print("请输入修改后的数据(name,age,status,gender):");
        String newinfo = scanner.next();
        String[] split = newinfo.split(",");
        System.out.println(updateUser(new User(split[0], Integer.parseInt(split[1]), split[2], split[3]), id));

    }
    //修改用户
    public static boolean updateUser(User user,int id){
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            // 1.获取数据库连接（mysql8.0的驱动jar包可省略加载驱动步骤）
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kp", "root", "123456");
            // 2.创建可执行sql对象     ？代表占位符
            String sql = "update tb_user set name = ? , age = ? , status = ? , gender = ? where id = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,user.getName());
            pstm.setInt(2,user.getAge());
            pstm.setString(3,user.getStatus());
            pstm.setString(4,user.getGender());
            pstm.setInt(5,id);
            // 3.执行sql语句并处理结果
            int result = pstm.executeUpdate();// 返回值为数据库中多少条数据受到影响
            if (result != 0){
                flag = true;
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 4.关闭数据库连接
            try {

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
        return flag;
    }

    // 删除
    public static boolean deleteUser(int id){
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            // 1.获取数据库连接（mysql8.0的驱动jar包可省略加载驱动步骤）
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kp", "root", "123456");
            // 2.创建可执行sql对象     ？代表占位符
            String sql = "delete from tb_user where id = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1,id);
            // 3.执行sql语句并处理结果
            int result = pstm.executeUpdate();// 返回值为数据库中多少条数据受到影响
            if (result != 0){
                flag = true;
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 4.关闭数据库连接
            try {

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

        return flag;
    }

    //查询所有用户
    public static List<User> findAllUser(){
        List<User> userList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kp", "root", "123456");
            String sql = "select * from tb_user";
            pstm = conn.prepareCall(sql);
            rs = pstm.executeQuery();
            while (rs.next()){
                //把游标当前指向的那条数据的每个字段的值获取到
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
                user.setStatus(rs.getString("status"));
                user.setGender(rs.getString("gender"));
                userList.add(user);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
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
        return userList;
    }


    //添加用户方法 inster
    public static boolean addUser(User user){
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            // 1.获取数据库连接（mysql8.0的驱动jar包可省略加载驱动步骤）
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kp", "root", "123456");
            // 2.创建可执行sql对象     ？代表占位符
            String sql = "insert into tb_user values (null,?,?,?,?)";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,user.getName());
            pstm.setInt(2,user.getAge());
            pstm.setString(3,user.getStatus());
            pstm.setString(4,user.getGender());
            // 3.执行sql语句并处理结果
            int result = pstm.executeUpdate();// 返回值为数据库中多少条数据受到影响
            if (result != 0){
                flag = true;
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
        // 4.关闭数据库连接
            try {

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
        return flag;
    }
}
