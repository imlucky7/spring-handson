package com.findonnet.persistence;

import java.sql.Types;

import org.springframework.jdbc.core.support.JdbcDaoSupport;



/**
 * DAO class for persisting the sequence values.
 *
 */
public class MessageSequenceDAO extends JdbcDaoSupport {
	
	private static final String INSERT_SQL = " Insert into MSGSEQ (APPNAME, APPKEY, VALUE) VALUES ( ?, ?, ?) ";
	private static final String UPDATE_SQL = " Update MSGSEQ Set VALUE = ? Where APPNAME = ? AND APPKEY=? ";
	private static final int[] insertTypes = new int[] {Types.VARCHAR, Types.VARCHAR, Types.INTEGER};
	private static final int[] updateTypes = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR };

	public int insertSequence(String app, String appKey, int value) {
		Object[] params = new Object[] { app, appKey, value };
		return getJdbcTemplate().update(INSERT_SQL, params, insertTypes);
	}
	
	public int updateSequence(int seqNum, String application, String serverName) {
		Object[] params = new Object[] { seqNum, application, serverName };
		return getJdbcTemplate().update(UPDATE_SQL, params, updateTypes);
	}
	
}
