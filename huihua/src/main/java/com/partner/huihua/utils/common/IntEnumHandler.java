package com.partner.huihua.utils.common;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.partner.huihua.utils.base.MyBatisEnum;
import com.partner.huihua.utils.exception.HuiHuaException;



/**
 * mybatis 数字与枚举之间相互转换的类型处理器
 * 使用此类对实体中的枚举属性进行转换, 枚举必须实现
 * com.partner.huihua.utils.base.MyBatisEnum接口
 * 并且请在枚举中定义静态方法 forValue(int) 
 * 用来根据int值转换成枚举值
 * @author rainyhao
 * @since 2015-3-10 上午11:40:54
 */
@SuppressWarnings({"unchecked"})
public class IntEnumHandler<T extends Enum<T>> extends BaseTypeHandler<T> {
	
	/**
	 * 具体枚举类型
	 */
	private Class<T> type;
	
	public IntEnumHandler(Class<T> type) {
		if (type == null) throw new IllegalArgumentException("Type argument cannot be null");
	    this.type = type;
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
		 ps.setInt(i, ((MyBatisEnum)parameter).getValue());
	}

	@Override
	public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
		int i = rs.getInt(columnName);
		if (rs.wasNull()) {
			return null;
		} else {
			try {
				return (T)type.getMethod("forValue", int.class).invoke(type, i);
			} catch (Exception e) {
				e.printStackTrace();
				throw new HuiHuaException("未找到值所对应的枚举类型, 值: " + i + "类型: " + type.getName(), e);
			}
		}
	}

	@Override
	public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		int i = rs.getInt(columnIndex);
		if (rs.wasNull()) {
			return null;
		} else {
			try {
				return (T)type.getMethod("forValue", int.class).invoke(type, i);
			} catch (Exception e) {
				throw new HuiHuaException("未找到值所对应的枚举类型, 值: " + i + "类型: " + type.getName(), e);
			}
		}
	}

	@Override
	public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		int i = cs.getInt(columnIndex);
		if (cs.wasNull()) {
			return null;
		} else {
			try {
				return (T)type.getMethod("forValue", int.class).invoke(type, i);
			} catch (Exception e) {
				throw new HuiHuaException("未找到值所对应的枚举类型, 值: " + i + "类型: " + type.getName(), e);
			}
		}
	}

}
