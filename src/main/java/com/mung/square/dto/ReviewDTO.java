package com.mung.square.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("review")
public class ReviewDTO {
	String reviewNo;
	String id;
	Date writeDate;
	String title;
	String content;
	String category;
	List<ReviewFileDTO> files;
}
