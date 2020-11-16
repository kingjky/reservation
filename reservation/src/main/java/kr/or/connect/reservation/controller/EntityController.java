package kr.or.connect.reservation.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class EntityController {
	public ResponseEntity<Map<String, Object>> handleSuccess(Map<String, Object> data) {
		return ResponseEntity.ok().body(data);
	}

	public ResponseEntity<Map<String, Object>> handleFail(String errorMessage, HttpStatus state) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("messsage", errorMessage);
		return new ResponseEntity<Map<String, Object>>(map, state);
	}
}
