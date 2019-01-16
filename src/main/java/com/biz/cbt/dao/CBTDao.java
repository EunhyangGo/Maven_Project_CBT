package com.biz.cbt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.biz.cbt.sql.CBTSQL;
import com.biz.cbt.vo.CBTVO;

public interface CBTDao {
	
	@Select(CBTSQL.CBT_ALL)
	public List<CBTVO> selectAll();
	
	@Select(CBTSQL.CBT_FIND_NUM)
	public CBTVO findByNum(String cbt_quenum);
	
	@Insert(CBTSQL.CBT_INSERT)
	public int insert(CBTVO vo);
	
	@Update(CBTSQL.CBT_UPDATE)
	public int update(CBTVO vo);
	
	@Delete(CBTSQL.CBT_DELETE)
	public int delete(String cbt_quenum);
	

}
