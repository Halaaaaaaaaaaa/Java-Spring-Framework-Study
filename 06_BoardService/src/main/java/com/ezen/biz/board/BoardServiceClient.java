package com.ezen.biz.board;

import java.util.*;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.ezen.biz.dto.BoardVO;
import com.ezen.biz.service.BoardService;

public class BoardServiceClient {
	
	private static BoardService boardService;

	//@Autowired
	public static void main(String[] args) {
		
		//spring container ����
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		
		//boardService ��ü ���� ��û
		//�Ʒ��� ������ @Autowired �� ��� ó��
		boardService = (BoardService)container.getBean("boardService");
		
		//�Խñ� ���
		//�����̳ʷκ��� ���弭�� �����ͼ� ��� - BoardService boardService ���� ���� ����
		BoardVO board = new BoardVO();
			board.setTitle("�Խñ� ����");
			board.setWriter("�輼��");
			board.setContent("������ ���� �Խñ� �Դϴ�.");
		
		//�Խñ� �Է� �׽�Ʈ
		boardService.insertBoard(board);
			
		
		//�Խñ� ��� ��ȸ
		List<BoardVO> boardList = boardService.getBoardList();

		System.out.println("<<< �Խñ� ��� >>>");
		for(BoardVO vo : boardList) {
			System.out.println(vo);
		}
			
		// �Խñ� �� ��ȸ
		BoardVO vo1 = new BoardVO();
		vo1.setSeq(3);
		System.out.println("<<< �Խñ� ����ȸ >>>");
		System.out.println(boardService.getBoard(vo1));
			
		// �Խñ� ����
		BoardVO vo2 = new BoardVO();
			vo2.setSeq(4);
			vo2.setTitle("");
			vo2.setWriter("");
			vo2.setContent("");
			System.out.println("<<< �Խñ� ���� >>>");
		boardService.updateBoard(vo2);
			
		// �Խñ� ����
		BoardVO vo3 = new BoardVO();
		vo3.setSeq(4);
		System.out.println("<<< �Խñ� ���� >>>");
		boardService.deleteBoard(vo3);
		
		container.close();

	}

}