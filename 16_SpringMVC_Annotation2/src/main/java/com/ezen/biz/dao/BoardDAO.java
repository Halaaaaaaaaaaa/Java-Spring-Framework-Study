package com.ezen.biz.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ezen.biz.dto.BoardVO;

@Repository("boardDAO")
public class BoardDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/* */
	private static final String BOARD_INSERT =
			"INSERT INTO board(seq, title, writer, content) " +
			"VALUES(board_seq.NEXTVAL, ?, ?, ?)"; 
	
	/* Ʈ����� ó�� �׽�Ʈ��
	private static final String BOARD_INSERT =
			"INSERT INTO board(seq, title, writer, content) " +
			"VALUES(?, ?, ?, ?)"; */
	private static final String BOARD_UPDATE =
			"UPDATE board SET title=?, writer=?, content=? " +
			"WHERE seq=?";
	private static final String DELETE_BOARD = 
			"DELETE board WHERE seq=?";
	private static final String GET_BOARD =
			"SELECT * FROM board WHERE seq=?";
	private static final String GET_BOARD_LIST =
			"SELECT * FROM board";
	//�Խñ� �˻� ��ȸ�� ���� SQL �߰�
	private static final String GET_BOARD_LIST_T =
			"SELECT * FROM board WHERE title LIKE '%'||?||'%'" + 
					"ORDER BY seq";
	private static final String GET_BOARD_LIST_C =
			"SELECT * FROM board WHERE content LIKE '%'||?||'%'" +
					"ORDER BY seq";
	
	// �Խñ� insert
	public void insertBoard(BoardVO board) {
		System.out.println("===> JDBC�� insertBoard() ó��");
		
		/**/
		jdbcTemplate.update(BOARD_INSERT, 
					board.getTitle(), board.getWriter(), board.getContent());
		
		/* Ʈ����� ó�� �׽�Ʈ�� 
		jdbcTemplate.update(BOARD_INSERT, 
				board.getSeq(), board.getTitle(), board.getWriter(), board.getContent());*/
		
	}

	
	// �Խñ� ����
	public void updateBoard(BoardVO board) {
		System.out.println("===> JDBC�� updateBoard() ó��");
		Object[] args = {board.getTitle(), board.getWriter(),
						board.getContent(), board.getSeq()};
		
		jdbcTemplate.update(BOARD_UPDATE, args);		
	}
	
	
	// �Խñ� ����
	public void deleteBoard(BoardVO board) {
		System.out.println("===> JDBC�� deleteBoard() ó��");
		Object[] args = {board.getSeq()};
		
		jdbcTemplate.update(DELETE_BOARD, args);
	}
	
	
	// �Խñ� �� ���� ��ȸ
	public BoardVO getBoard(BoardVO board) {
		System.out.println("===> JDBC�� getBoard() ó��");
		Object[] args = {board.getSeq()};
		
		return jdbcTemplate.queryForObject(GET_BOARD, args, new BoardRowMapper());
	}
	
	
	// �Խñ� ��� ��ȸ
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> JDBC�� getBoardList() ó��");		
		
		List<BoardVO> boardList = null;
		Object[] args = {vo.getSearchKeyword()};
		
		//�Խñ� �˻� ��ȸ
		if(vo.getSearchCondition().equals("TITLE")) {
			boardList = jdbcTemplate.query(GET_BOARD_LIST_T, args, new BoardRowMapper());
		} else if(vo.getSearchCondition().equals("CONTENT")) {
			boardList = jdbcTemplate.query(GET_BOARD_LIST_C, args, new BoardRowMapper());
		}
		return boardList;
	}
	
	
	class BoardRowMapper implements RowMapper<BoardVO> {

		@Override
		public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			BoardVO board = new BoardVO();
			
			board.setSeq(rs.getInt("seq"));
			board.setTitle(rs.getString("title"));
			board.setWriter(rs.getString("writer"));
			board.setContent(rs.getString("content"));
			board.setRegDate(rs.getDate("regDate"));
			board.setCnt(rs.getInt("cnt"));
			
			return board;
		}
		
	}
	
}





