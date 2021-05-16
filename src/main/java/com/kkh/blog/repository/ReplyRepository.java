package com.kkh.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.kkh.blog.dto.ReplyRequestDto;
import com.kkh.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{
	
	/*
	 * @Modifying 
	 * @Query(
	 * value="INSERT INTO reply(userId,boardId,content,createDate) VALUES(?1,?2,?3,NOW())"
	 * ,nativeQuery = true) int nativeSave(int userId, int boardId, String content);
	 */
}
