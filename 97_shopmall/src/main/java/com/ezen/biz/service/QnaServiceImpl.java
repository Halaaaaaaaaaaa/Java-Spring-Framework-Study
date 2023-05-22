package com.ezen.biz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.biz.dao.QnaDAO;
import com.ezen.biz.dto.QnaVO;

@Service
public class QnaServiceImpl implements QnaService {
	
	@Autowired
	private QnaDAO qnaDao;

	@Override
	public List<QnaVO> getListQna(String id) {
		return qnaDao.getListQna(id);
	}

	@Override
	public QnaVO getQna(int qseq) {
		return qnaDao.getQna(qseq);
	}

	@Override
	public void insertQna(QnaVO vo) {
		qnaDao.insertQna(vo);
	}

	@Override
	public List<QnaVO> listAllQna() {
		return qnaDao.listAllQna();
	}

	@Override
	public void adminQnaDetail(QnaVO vo) {
		qnaDao.adminQnaDetail(vo);
	}

}
