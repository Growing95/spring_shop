package com.ict.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ict.db.CVO;
import com.ict.db.DAO;
import com.ict.db.MVO;
import com.ict.db.VO;

@Controller
public class MyController {
	@Autowired
	private DAO dao;

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	@RequestMapping("index.do")
	public ModelAndView indexCommand(@ModelAttribute("category") String category) {
		return new ModelAndView("redirect:list.do");
	}

	@RequestMapping("list.do")
	public ModelAndView listCommand(HttpServletRequest request, HttpServletResponse response) {
		String category = request.getParameter("category");
		if (category == null || category == "") {
			category = "ele002";
		}
		List<VO> list = dao.getList(category);
		request.setAttribute("list", list);
		return new ModelAndView("product_list");

	}

	@RequestMapping("login_ok.do")
	public ModelAndView logInOKCommand(MVO m_vo, HttpServletRequest request) {
		MVO mvo = dao.getLogIn(m_vo);
		if (mvo == null) {
			request.getSession().setAttribute("login", "no");
		} else {
			request.getSession().setAttribute("login", "ok");
			request.getSession().setAttribute("mvo", mvo);
			if (mvo.getId().equals("admin") && mvo.getPw().equals("admin")) {
				request.getSession().setAttribute("login", "admin");
				return new ModelAndView("admin");
			}
		}
		return new ModelAndView("redirect:list.do");
	}

	@RequestMapping("content.do")
	public ModelAndView contentCommand(@RequestParam("idx") String idx) {
		ModelAndView mv = new ModelAndView("product_content");
		VO vo = dao.getOneList(idx);
		mv.addObject("vo", vo);
		return mv;
	}

	@RequestMapping("logout.do")
	public ModelAndView logoutCommand(HttpSession session) {
		session.invalidate();
		return new ModelAndView("redirect:list.do");
	}

	@RequestMapping("addcart.do")
	public ModelAndView addCartCommand(@ModelAttribute("idx") String idx, HttpSession session) {
		// id 구하기
		MVO mvo = (MVO) session.getAttribute("mvo");
		String id = mvo.getId();

		// idx를 이용해서 제품 정보 구하기
		VO vo = dao.getOneList(idx);
		// 해당 아이디를 가진 사람의 카트에 해당 제품이 있는지 없는지 검색
		CVO cvo = dao.getCartList(id, vo.getP_num());
		if (cvo == null) { // 현재 카트에 제품이 없다.(추가)
			CVO c_vo = new CVO();
			c_vo.setP_num(vo.getP_num());
			c_vo.setP_name(vo.getP_name());
			c_vo.setP_price(String.valueOf(vo.getP_price()));
			c_vo.setP_saleprice(String.valueOf(vo.getP_saleprice()));
			c_vo.setAmount(String.valueOf(1));
			c_vo.setId(id);

			int result = dao.getCartInsert(c_vo);
		} else { // 현재 카트에 제품이 있다.(제품 수량 증가)
			int result = dao.getCartUpdate(cvo);
		}
		return new ModelAndView("redirect:content.do");
	}

	@RequestMapping("viewcart.do")
	public ModelAndView showcartCommand(HttpSession session) {
		ModelAndView mv = new ModelAndView("viewcart");
		MVO mvo = (MVO) session.getAttribute("mvo");
		String id = mvo.getId();

		// 카트 테이블에서 해당 아이디가 가진 모든 목록 가져오기(장바구니)
		List<CVO> clist = dao.getAllCart(id);
		session.setAttribute("clist", clist);
		return mv;
	}

	@RequestMapping("edit.do")
	public ModelAndView editCommand(CVO cvo) {
		ModelAndView mv = new ModelAndView("redirect:viewcart.do");
		int result= dao.getCartAmountUpdate(cvo);
		return mv;
	}

	@RequestMapping("delete.do")
	public ModelAndView deleteCommand(CVO cvo) {
		ModelAndView mv = new ModelAndView("redirect:viewcart.do");
		int result=dao.getCartDelete(cvo);
		return mv;
	}

}
