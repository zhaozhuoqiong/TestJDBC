/**
 * Created by zzq on 2017/8/7.
 */
package Test;
import java.sql.*;

public class OracleConnection {
    //定义连接字符串(协议名:jdbc:oracle:thin@oracle据库服务器主机ip:数据库监听端口号:SID)
    private String url = "jdbc:oracle:thin:@10.45.66.131:1521:cc";
    private String userName = "spn";  //数据库用户名
    private String password = "smart";  //登录密码
    private Connection connector;

    private Statement statement;
    private ResultSet rs = null;

    public OracleConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");  //加载驱动
            connector = DriverManager.getConnection(url, userName, password); //建立连接
            statement = connector.createStatement();
        } catch (ClassNotFoundException ex) {
            System.out.println("Error:Can't Load the SQL Server Driver");

        } catch (SQLException ex) {
            System.out.println("Error:Can't connect to Database!");

        }

    }


    public ResultSet executeQuery(String sql) {
        rs = null;
        try {
            rs = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }


    public boolean executeUpdate(String sql) {
        try {
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public void finalize() {
        try {
            connector.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ResultSet rs = null;
        boolean flag = false;
        OracleConnection obj =  new OracleConnection();

        //执行查询
        rs = obj.executeQuery("select * from tfm_config");  //注意，不能有分号
        try {
            while (rs.next()) {  //遍历结果
                System.out.println("id:"+rs.getString(1)+" name:"+rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}

