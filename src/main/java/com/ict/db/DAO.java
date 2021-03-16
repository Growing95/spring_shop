package com.ict.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

public class DAO {
	private SqlSessionTemplate sqlSessionTemplate;
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	public List<VO> getList(String category){
		List<VO> list=null;
		list=sqlSessionTemplate.selectList("list", category);
		return list;
	}
	
	public MVO getLogIn(MVO mvo) {
		MVO m_vo=new MVO();
		m_vo=sqlSessionTemplate.selectOne("login", mvo);
		return m_vo;
	}
	public VO getOneList(String idx) {
		VO vo= new VO();
		vo=sqlSessionTemplate.selectOne("onelist", idx);
		return vo;
	}
	
	public int getProductInsert(VO vo) {
		int result = 0;
		result = sqlSessionTemplate.insert("product_add", vo);
		return result;
	}
	
	// id와 제품번호를 받아서 카트정보를 구하자 
	
	
	public int getCartInsert(CVO cvo) {
		int result = 0 ;
		result = sqlSessionTemplate.insert("cartInsert", cvo);
		return result;
	}
	public int getCartUpdate(CVO cvo) {
		int result = 0 ;
		result = sqlSessionTemplate.update("cartUpdate", cvo);
		return result;
	}
	
	public List<CVO> getAllCart(String id){
		List<CVO> clist = null;
		clist = sqlSessionTemplate.selectList("cartAll", id);
		return clist;
	}
	
	public int getCartAmountUpdate(CVO cvo) {
		int result = 0 ;
		result = sqlSessionTemplate.update("cartAmountUpdate", cvo);
		return result;
	}
	
	public int getCartDelete(CVO cvo) {
		int result = 0 ;
		result = sqlSessionTemplate.delete("cartDelete", cvo);
		return result;
	}
	// 해당 id를 가진 사람의 카트에 해당 제품이 있는지 검사
	public CVO getCartList(String id, String p_num) {
		CVO cvo= null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("p_num", p_num);
		cvo=sqlSessionTemplate.selectOne("selectcart",map);
		return cvo;
	}
	
}
