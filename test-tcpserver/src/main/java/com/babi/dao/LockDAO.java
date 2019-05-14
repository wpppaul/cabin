package com.babi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import com.babi.common.Params;
import com.babi.domain.Blood;
import com.babi.domain.Exercise;
import com.babi.domain.Weight;
import redis.clients.jedis.Jedis;

/**
 * 
 * @author LiWenhui
 * @Date 2018.03.19
 * @author GuoLiangyun
 * @Date 2018.03.23
 * @Date 2018.05.20
 * @Date 2018.06.23
 */
public class LockDAO extends BaseDAO {

	public LockDAO() {
		super(Params.DB_DRIVER, Params.DB_URL, Params.DB_USERNAME, Params.DB_PASSWORD);
		// this.getConnection();
	}

	/*
	 * 加载车锁列表至Redis
	 * 
	 * @return
	 * 
	 * @author GuoLiangyun
	 * 
	 * @Date 2018.05.20
	 */
	public void loadLockList() throws Exception {
		Jedis jedis = null;
		String Id = null;
		String door = null;
		String light = null;
		String color = null;
		String aqi = null;
		String temperature = null;
		String emergency_stop = null;
		String emergency_call = null;
		String CABINKEY=null;

		String sql = String.format("SELECT * FROM `cabins`");
		ResultSet rs = super.exectueQuery(sql);
		try {
			while (rs.next()) {
				Id = rs.getString("id");
				door = rs.getString("door");
				temperature = rs.getString("temperature");
				light = rs.getString("light");
				color = rs.getString("color");
				emergency_stop = rs.getString("emergency_stop");
				emergency_call = rs.getString("emergency_call");
				aqi = rs.getString("aqi");
				
				if (null != Id && 0 != Id.length()) {
					jedis = JedisUtils.getJedis();
					CABINKEY="CABIN:"+Id;
					jedis.hset(CABINKEY, "DOOR", door);
					jedis.hset(CABINKEY, "AQI", aqi);
					jedis.hset(CABINKEY, "TEMPERATURE", temperature);
					jedis.hset(CABINKEY, "LIGHT", light);
					jedis.hset(CABINKEY, "COLOR", color);
					jedis.hset(CABINKEY, "EMGSTOP", emergency_stop);
					jedis.hset(CABINKEY, "EMGCALL", emergency_call);
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			super.closeConnection();
		}
	}
	/*
	 * 补充加载车锁至Redis
	 * 
	 * @return
	 * 
	 * @author GuoLiangyun
	 * 
	 * @Date 2018.06.23
	 */
	public void reLoadLockToList(String cabinid) throws Exception {
		Jedis jedis = null;
		String strID = null;
		String door = null;
		String light = null;
		String color = null;
		String aqi = null;
		String temperature = null;
		String emergency_stop = null;
		String emergency_call = null;
		String CABINKEY=null;

		String sql = String.format("SELECT * FROM `cabins` WHERE id='%s'", cabinid);
		ResultSet rs = super.exectueQuery(sql);
		try {
			while (rs.next()) {
				strID = rs.getString("id");
				door = rs.getString("door");
				temperature = rs.getString("temperature");
				light = rs.getString("light");
				color = rs.getString("color");
				emergency_stop = rs.getString("emergency_stop");
				emergency_call = rs.getString("emergency_call");
				aqi = rs.getString("aqi");
				
				if (null != strID && 0 != strID.length()) {
					CABINKEY="CABIN:"+strID;
					jedis = JedisUtils.getJedis();
					jedis.hset(CABINKEY, "DOOR", door);
					jedis.hset(CABINKEY, "AQI", aqi);
					jedis.hset(CABINKEY, "TEMPERATURE", temperature);
					jedis.hset(CABINKEY, "LIGHT", light);
					jedis.hset(CABINKEY, "COLOR", color);
					jedis.hset(CABINKEY, "EMGSTOP", emergency_stop);
					jedis.hset(CABINKEY, "EMGCALL", emergency_call);
					jedis.close();
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			super.closeConnection();
		}
	}

	/*
	 * 更新仓的状态 (状态定义参见LOCKSTATUS)
	 * 
	 * @param ID
	 * 
	 * @param Status
	 * 
	 * @return
	 */
	public boolean updateLockProperties(String surfaceName,String ID, String index,String door) throws Exception {
		boolean flag = false;
		String sql = String.format("UPDATE %s SET %s ='%s' WHERE id='%s'",surfaceName,index,door,ID);
		try {
			flag = super.executeByBoolean(sql);
		} catch (Exception e) {
			throw e;
		}finally {
			super.closeConnection();
		}
		return flag;
	}
	
	public Integer insertExercise(Exercise exercise) throws Exception {
		Integer flag = null;
		String sql = "INSERT INTO exercises (cabin_id,distance,calorie,duration,maxspd) VALUES(?,?,?,?,?)";
		PreparedStatement ps = super.getPreparedStatement(sql);
		try {
			ps.setString(1, exercise.getCabinId());
			ps.setFloat(2, exercise.getDistance());
			ps.setFloat(3, exercise.getCalorie());
			ps.setFloat(4, exercise.getDuration());
			ps.setFloat(5, exercise.getMaxspeed());
			flag = ps.executeUpdate();
		} catch (Exception e) {
			throw e;
		}finally {
			super.closeConnection();
		}
		return flag;
	}
	
	public void updateExercise(Exercise exercise) throws Exception {
		Integer flag = null;
		String sql = "UPDATE exercises SET distance=?,calorie = ?,duration = ?,maxspd = ? WHERE cabin_id = ?";
		PreparedStatement ps = super.getPreparedStatement(sql);
		try {
			ps.setFloat(1, exercise.getDistance());
			ps.setFloat(2, exercise.getCalorie());
			ps.setFloat(3, exercise.getDuration());
			ps.setFloat(4, exercise.getMaxspeed());
			ps.setString(5, exercise.getCabinId());
			flag = ps.executeUpdate();
		} catch (Exception e) {
			throw e;
		}finally {
			super.closeConnection();
		}
	}
	
	public Integer insertBlood(Blood blood) throws Exception {
		Integer flag = null;
		String sql = "INSERT INTO bloods (systolic,diastolic,pulse,taken,cabin_id) VALUES(?,?,?,?,?)";
		PreparedStatement ps = super.getPreparedStatement(sql);
		try {
			ps.setInt(1, blood.getSystolic());
			ps.setInt(2, blood.getDiastolic());
			ps.setInt(3, blood.getPulse());
			ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			ps.setString(5, blood.getCabinId());
			flag = ps.executeUpdate();
		} catch (Exception e) {
			throw e;
		}finally {
			super.closeConnection();
		}
		return flag;
	}
	public Integer insertWeight(Weight weight) throws Exception {
		Integer flag = null;
		String sql = "INSERT INTO weights (res,weight,taken,cabin_id) VALUES(?,?,?,?)";
		PreparedStatement ps = super.getPreparedStatement(sql);
		try {
			ps.setFloat(1, weight.getRes());
			ps.setFloat(2, weight.getWeight());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.setString(4, weight.getCabinId());
			flag = ps.executeUpdate();
		} catch (Exception e) {
			throw e;
		}finally {
			super.closeConnection();
		}
		return flag;
	}
	
	public boolean queryExercise(String cabinId) throws Exception {
		boolean flag = false;
		String sql = String.format("select id from exercises WHERE cabin_id='%s'",cabinId);
		try {
			ResultSet  rs = super.getPreparedStatement(sql).executeQuery();
			if(rs.next()) {
				flag = true;
				return flag;
			}
		} catch (Exception e) {
			throw e;
		}finally {
			super.closeConnection();
		}
		return flag;
	}
	
	public Exercise findExerciseByCabinId(String cabinId) throws Exception {
		String sql = String.format("select * from exercises WHERE cabin_id='%s'",cabinId);
		try {
			ResultSet  rs = super.getPreparedStatement(sql).executeQuery();
			if(rs.next()) {
				Exercise exercise = new Exercise();
				int id = rs.getInt("id");
				exercise.setExerciseId(id);
				String cabin_id = rs.getString("cabin_id");
				exercise.setCabinId(cabin_id);			
				Float distance = rs.getFloat("distance");
				exercise.setDistance(distance);
				Float calorie = rs.getFloat("calorie");
				exercise.setCalorie(calorie);
				Float duration = rs.getFloat("duration");
				exercise.setDuration(duration);
				return exercise;
			}
		} catch (Exception e) {
			throw e;
		}finally {
			super.closeConnection();
		}
		return new Exercise();
	}

}
