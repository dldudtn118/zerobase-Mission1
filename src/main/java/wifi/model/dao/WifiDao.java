package wifi.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import wifi.model.vo.LocalHistory;
import wifi.model.vo.WifiInfo;

public class WifiDao {

	public int sessionIdInsert(String sessionId) {
		
		String url = "jdbc:sqlite:C:\\Users\\dldud\\OneDrive\\바탕 화면\\제로베이스 백엔드 스쿨_4기_이영수_221010\\Project1-WorkSpace\\Project1\\src\\main\\webapp\\resources\\wifiDB.db";
	 	Connection connection =null;
        PreparedStatement preparedStatement =null;		 
        int affected = 0;
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url);
            String sql = "INSERT INTO SESSION_ID(ID) VALUES(?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, sessionId);
            //preparedStatement.setString(1, sessionId);

            //4. 쿼리실행
            affected = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(preparedStatement != null && !preparedStatement.isClosed()){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if(connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return affected;
	}
	
	public int wifiInsert(ArrayList<WifiInfo> alw, String sessionId) {
		String url ="jdbc:sqlite:C:\\Users\\dldud\\OneDrive\\바탕 화면\\제로베이스 백엔드 스쿨_4기_이영수_221010\\Project1-WorkSpace\\Project1\\src\\main\\webapp\\resources\\wifiDB.db";
	 	Connection connection =null;
        PreparedStatement preparedStatement =null;		 
        int affected = 0;
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(url);
            String sql = "INSERT INTO WIFI_INFO(X_SWIFI_MGR_NO,X_SWIFI_WRDOFC,X_SWIFI_MAIN_NM,X_SWIFI_ADRES1,X_SWIFI_ADRES2,X_SWIFI_INSTL_FLOOR,"
            		+ "X_SWIFI_INSTL_TY,X_SWIFI_INSTL_MBY,X_SWIFI_SVC_SE,X_SWIFI_CMCWR,X_SWIFI_CNSTC_YEAR,X_SWIFI_INOUT_DOOR,"
            		+ "X_SWIFI_REMARS3,LNT,LAT,WORK_DTTM,ID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            
            connection.setAutoCommit(false);
            for(int i=0; i<alw.size(); i++) {
            preparedStatement.setString(1, alw.get(i).getxSwifiMgrNo());
            preparedStatement.setString(2, alw.get(i).getxSwifiWrdifc());
            preparedStatement.setString(3, alw.get(i).getxSwifiMainNm());
            preparedStatement.setString(4, alw.get(i).getxSwifiAdres1());
            preparedStatement.setString(5, alw.get(i).getxSwifiAdres2());
            preparedStatement.setString(6, alw.get(i).getxSwifiInstlFloor());
            preparedStatement.setString(7, alw.get(i).getxSwifiInstlTY());
            preparedStatement.setString(8, alw.get(i).getxSwifiInstlMBY());
            preparedStatement.setString(9, alw.get(i).getxSwifiSvcSe());
            preparedStatement.setString(10, alw.get(i).getxSwifiCMCWR());
            preparedStatement.setString(11, alw.get(i).getxSwifiCNSTCYear());
            preparedStatement.setString(12, alw.get(i).getxSwifiInOutDoor());
            preparedStatement.setString(13, alw.get(i).getxSwifiREMARS3());
            preparedStatement.setDouble(14, alw.get(i).getLnt());
            preparedStatement.setDouble(15, alw.get(i).getLat());
            preparedStatement.setString(16, alw.get(i).getWorkDttm());
            preparedStatement.setString(17, sessionId);
            
            //4. 쿼리실행
            affected += preparedStatement.executeUpdate();
            }
            connection.setAutoCommit(true);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(preparedStatement != null && !preparedStatement.isClosed()){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if(connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return affected;
	}

	public int sessionIdDelete(String sessionId) {
		
		String url = "jdbc:sqlite:C:\\Users\\dldud\\OneDrive\\바탕 화면\\제로베이스 백엔드 스쿨_4기_이영수_221010\\Project1-WorkSpace\\Project1\\src\\main\\webapp\\resources\\wifiDB.db";
	 	Connection connection =null;
        PreparedStatement preparedStatement =null;		 
        int affected = 0;
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url);
            String sql = "DELETE FROM SESSION_ID "
            		+ "WHERE ID = ?";
            
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, sessionId);

            //4. 쿼리실행
            affected = preparedStatement.executeUpdate();
            int affected2 = wifiInfoDelete(sessionId,connection);
            affected += affected2;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(preparedStatement != null && !preparedStatement.isClosed()){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if(connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return affected;
	}
	
	public int wifiInfoDelete(String sessionId, Connection connection) {
		
	    int affected = 0;
	    PreparedStatement preparedStatement =null;	
        try {
            
            String sql = "DELETE FROM WIFI_INFO "
            		+ "WHERE ID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, sessionId);

            //4. 쿼리실행
            affected = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(preparedStatement != null && !preparedStatement.isClosed()){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return affected;
	}

    public String sessionIdSelect(String sessionId){

		String url = "jdbc:sqlite:C:\\Users\\dldud\\OneDrive\\바탕 화면\\제로베이스 백엔드 스쿨_4기_이영수_221010\\Project1-WorkSpace\\Project1\\src\\main\\webapp\\resources\\wifiDB.db";
		 	String dbSessionId ="";
		 	Connection connection =null;
	        PreparedStatement preparedStatement =null;
	        ResultSet rs = null;

	        try {
	            Class.forName("org.sqlite.JDBC");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	        try {

	            connection = DriverManager.getConnection(url);

	            String sql = "SELECT ID FROM SESSION_ID WHERE ID = ?";
	            preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setString(1, sessionId);

	            //4. 쿼리실행
	            rs = preparedStatement.executeQuery();
	            
	            //5. 결과 수행
	            if(rs.next()){
	            	dbSessionId = rs.getString("ID");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if(rs != null &&!rs.isClosed()){
	                    rs.close();
	                }
	            } catch (SQLException e) {
	                throw new RuntimeException(e);
	            }
	            try {
	                if(preparedStatement != null && !preparedStatement.isClosed()){
	                    preparedStatement.close();
	                }
	            } catch (SQLException e) {
	                throw new RuntimeException(e);
	            }

	            try {
	                if(connection != null && !connection.isClosed()){
	                    connection.close();
	                }
	            } catch (SQLException e) {
	                throw new RuntimeException(e);
	            }
	        }
	        return dbSessionId;
	    }
    
	public ArrayList<WifiInfo> wifiInfoSelect(String sessionId,double uLat, double uLnt){

		String url = "jdbc:sqlite:C:\\Users\\dldud\\OneDrive\\바탕 화면\\제로베이스 백엔드 스쿨_4기_이영수_221010\\Project1-WorkSpace\\Project1\\src\\main\\webapp\\resources\\wifiDB.db";
			ArrayList<WifiInfo> alw = new ArrayList<WifiInfo>();
		 	Connection connection =null;
	        PreparedStatement preparedStatement =null;
	        ResultSet rs = null;

	        try {
	            Class.forName("org.sqlite.JDBC");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	        try {

	            connection = DriverManager.getConnection(url);

	            String sql = "SELECT (6371*acos(cos(radians(?))*cos(radians(wi.LAT))*cos(radians(wi.LNT)"
	            		+ "-radians(?))+sin(radians(?))*sin(radians(wi.LAT)))) AS '거리',* "
	            		+ "FROM WIFI_INFO wi "
	            		+ "WHERE ID = ? "
	            		+ "ORDER BY 거리 ASC limit 20";
	            preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setDouble(1, uLat);
	            preparedStatement.setDouble(2, uLnt);
	            preparedStatement.setDouble(3, uLat);
	            preparedStatement.setString(4, sessionId);

	            //4. 쿼리실행
	            rs = preparedStatement.executeQuery();
	            //5. 결과 수행
	            while (rs.next()){
	            	WifiInfo wi = new WifiInfo();
	            	wi.setDistance(Math.round((rs.getDouble("거리"))*10000)/10000.0);
	    			wi.setxSwifiMgrNo(rs.getString("X_SWIFI_MGR_NO"));
	    			wi.setxSwifiWrdifc(rs.getString("X_SWIFI_WRDOFC"));
	    			wi.setxSwifiMainNm(rs.getString("X_SWIFI_MAIN_NM"));
	    			wi.setxSwifiAdres1(rs.getString("X_SWIFI_ADRES1"));
	    			wi.setxSwifiAdres2(rs.getString("X_SWIFI_ADRES2"));
	    			wi.setxSwifiInstlFloor(rs.getString("X_SWIFI_INSTL_FLOOR"));
	    			wi.setxSwifiInstlTY(rs.getString("X_SWIFI_INSTL_TY"));
	    			wi.setxSwifiInstlMBY(rs.getString("X_SWIFI_INSTL_MBY"));
	    			wi.setxSwifiSvcSe(rs.getString("X_SWIFI_SVC_SE"));
	    			wi.setxSwifiCMCWR(rs.getString("X_SWIFI_CMCWR"));
	    			wi.setxSwifiCNSTCYear(rs.getString("X_SWIFI_CNSTC_YEAR"));
	    			wi.setxSwifiInOutDoor(rs.getString("X_SWIFI_INOUT_DOOR"));
	    			wi.setxSwifiREMARS3(rs.getString("X_SWIFI_REMARS3"));
	    			wi.setLnt(rs.getDouble("LNT"));
	    			wi.setLat(rs.getDouble("LAT"));
	    			wi.setWorkDttm(rs.getString("WORK_DTTM"));
	                alw.add(wi);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if(rs != null &&!rs.isClosed()){
	                    rs.close();
	                }
	            } catch (SQLException e) {
	                throw new RuntimeException(e);
	            }
	            try {
	                if(preparedStatement != null && !preparedStatement.isClosed()){
	                    preparedStatement.close();
	                }
	            } catch (SQLException e) {
	                throw new RuntimeException(e);
	            }

	            try {
	                if(connection != null && !connection.isClosed()){
	                    connection.close();
	                }
	            } catch (SQLException e) {
	                throw new RuntimeException(e);
	            }
	        }
	        return alw;
	    }

	public int localHistoryInsert(double ulat, double ulnt) {
		String url ="jdbc:sqlite:C:\\Users\\dldud\\OneDrive\\바탕 화면\\제로베이스 백엔드 스쿨_4기_이영수_221010\\Project1-WorkSpace\\Project1\\src\\main\\webapp\\resources\\wifiDB.db";
	 	Connection connection =null;
        PreparedStatement preparedStatement =null;		 
        int affected = 0;
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(url);
            String sql = "INSERT INTO LOCATION_HISTORY(LNT,LAT,CHECK_TIME) "
            		+ "VALUES(?,?,strftime('%Y-%m-%dT%H:%M:%S','now','localtime'))";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, ulnt);
            preparedStatement.setDouble(2, ulat);
            
            affected = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(preparedStatement != null && !preparedStatement.isClosed()){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if(connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return affected;
	}

	public ArrayList<LocalHistory> localHistorySelect(){
		String url = "jdbc:sqlite:C:\\Users\\dldud\\OneDrive\\바탕 화면\\제로베이스 백엔드 스쿨_4기_이영수_221010\\Project1-WorkSpace\\Project1\\src\\main\\webapp\\resources\\wifiDB.db";
		ArrayList<LocalHistory> alLH = new ArrayList<LocalHistory>();
	 	Connection connection =null;
        PreparedStatement preparedStatement =null;
        ResultSet rs = null;

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(url);
            String sql = "SELECT *"
            			+ "FROM LOCATION_HISTORY "
            			+ "ORDER BY P_NUM DESC LIMIT 20";
            preparedStatement = connection.prepareStatement(sql);

            //4. 쿼리실행
            rs = preparedStatement.executeQuery();
            //5. 결과 수행
            while (rs.next()){
            	LocalHistory lH = new LocalHistory();
            	lH.setpNum(rs.getInt("P_NUM"));
            	lH.setLat(rs.getDouble("LAT"));
            	lH.setLnt(rs.getDouble("LNT"));
            	lH.setCheckTime(rs.getString("CHECK_TIME"));
    			alLH.add(lH);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null &&!rs.isClosed()){
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if(preparedStatement != null && !preparedStatement.isClosed()){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if(connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return alLH;
	}
	
	public int LocalHistoryDelete(int pNum) {
		
		String url = "jdbc:sqlite:C:\\Users\\dldud\\OneDrive\\바탕 화면\\제로베이스 백엔드 스쿨_4기_이영수_221010\\Project1-WorkSpace\\Project1\\src\\main\\webapp\\resources\\wifiDB.db";
	 	Connection connection =null;
        PreparedStatement preparedStatement =null;		 
        int affected = 0;
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url);
            String sql = "DELETE FROM LOCATION_HISTORY "
            		+ "WHERE P_NUM = ?";
            
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, pNum);

            //4. 쿼리실행
            affected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(preparedStatement != null && !preparedStatement.isClosed()){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if(connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return affected;
	}
}
