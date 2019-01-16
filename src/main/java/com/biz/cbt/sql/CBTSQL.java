package com.biz.cbt.sql;

public class CBTSQL {
	
	public static final String CBT_ALL =
			" SELECT * FROM tbl_cbt ";
	
	public static final String CBT_FIND_NUM =
			" SELECT * FROM tbl_cbt WHERE cbt_quenum = #{cbt_quenum} ";
	
	public static final String CBT_INSERT =
			" INSERT INTO tbl_cbt(cbt_quenum, cbt_question, cbt_num1, cbt_num2, cbt_num3, cbt_num4)"
			+ " VALUES (#{cbt_quenum}, #{cbt_question}, #{cbt_num1}, #{cbt_num2}, #{cbt_num3}, #{cbt_num4}) ";
	
	public static final String CBT_UPDATE =
			" UPDATE tbl_cbt "
			+ " SET cbt_question =#{cbt_question} "
			+ " cbt_num1 = #{cbt_num1}, "
			+ " cbt_num2 = #{cbt_num2}, "
			+ " cbt_num3 = #{cbt_num3}, "
			+ " cbt_num4 = #{cbt_num4} "
			+ " WHERE cbt_quenum = #{cbt_quenum} ";
	
	
	public static final String CBT_DELETE =
			" DELETE tbl_cbt "
			+ " WHERE cbt_quenum = #{cbt_quenum} ";

}
