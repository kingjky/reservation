package kr.or.connect.reservation.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.reservation.dto.CommentImage;
import kr.or.connect.reservation.service.CommentService;

@Controller
public class FileController {
	private final CommentService commentService;

	public FileController(CommentService commentService) {
		this.commentService = commentService;
	}

	@GetMapping("/download")
	public void download(
		@RequestParam("fileName") String fileName,
		@RequestParam(name = "contentType", required = false) String contentType,
		HttpServletResponse response) {
		String saveFileName = "c:/tmp/" + fileName;

		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Type", contentType);
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");

		try (
			FileInputStream fis = new FileInputStream(saveFileName);
			OutputStream out = response.getOutputStream();) {
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while ((readCount = fis.read(buffer)) != -1) {
				out.write(buffer, 0, readCount);
			}
		} catch (Exception ex) {
			throw new RuntimeException("file Load Error" + fileName);
		}
	}

	@GetMapping("/download/{imageId}")
	public void getReviewImage(
		@PathVariable("imageId") Long imageId,
		HttpServletResponse response) throws IOException {
		CommentImage commentImage = commentService.getCommentImageUsingImageId(imageId);

		String fileName = commentImage.getSaveFileName();
		String contentType = commentImage.getContentType();
		String url = "../download?fileName=" + fileName + "&contentType=" + contentType;

		response.sendRedirect(url);
	}
}
