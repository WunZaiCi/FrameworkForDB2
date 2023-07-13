package com.banma.annotation;

import java.lang.reflect.Field;

/**
 * 实现ORM框架中解析相关注解的工具类
 * 1.获取类上的@Table注解信息
 * 2.获取字段上的@Column注解信息
 * @author WHASSUPMAN
 *
 */
public class ORMAnnoHelper {
	
	/**
	 *  指定类上注入的表名
	 * @param beanCls
	 * @return
	 */
	public static String getTableName(Class<?> beanCls) {
		//通过Class获取类上的@Table
		Table tableAnno =beanCls.getAnnotation(Table.class);
		if(tableAnno==null) {
			//类上没有使用@Table注解
			//在此表示类的简称即为对应的表明
			return beanCls.getSimpleName().toLowerCase();
		}else {
			return tableAnno.value();//注解注入的表明
		}
	}
	
	/**
	 * 返回指定字段上的列名
	 * @param field
	 * @return
	 */
	public static String getColumnName(Field field) {
		//通过Class获取字段上的@Column
		Column columnAnno =field.getAnnotation(Column.class);
		
		if(columnAnno==null) {
			//字段上没有使用@Column注解
			//获取字段的名称作为列名
			return field.getName();
		}else {
			return columnAnno.value();//注解注入的表明
		}
	}
	
	
	/**
	 * 判断字段是否是主键
	 * @param field
	 * @return
	 */
	public static boolean isId(Field field) {
		//通过Class获取字段上的@Column
		Column columnAnno =field.getAnnotation(Column.class);
		
		if(columnAnno!=null) {
			//获取字段是主键字段
			return columnAnno.isId();
		}else {
			return false;
		}
	}
	
	
	/**
	 * 从类中查询主键的列
	 */
	public static Field findIdField(Class<?> cls) {
		for (Field f : cls.getDeclaredFields()) {
			if(isId(f)) {
				return f;
			}
		}
		return null;
	}
	
	
}
