package kr.or.connect.reservation.service;

import kr.or.connect.reservation.dto.DisplayInfo;
import kr.or.connect.reservation.dto.DisplayInfoImage;

public interface DisplayService {
	public DisplayInfo getDisplayInfoUsingDisplayInfoId(Integer displayInfoId);
	public DisplayInfoImage getDisplayInfoImageUsingDisplayInfoId(Integer displayInfoId);
}
