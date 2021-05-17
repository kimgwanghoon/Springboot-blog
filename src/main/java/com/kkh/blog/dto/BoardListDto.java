package com.kkh.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardListDto {

	private int id;
	private String title;
	private String content;
	private int count;
}
