package kr.or.connect.reservation.service;

import kr.or.connect.reservation.dto.DisplayInfo;
import kr.or.connect.reservation.dto.DisplayInfoImage;

public interface DisplayService {
	DisplayInfo getDisplayInfoUsingDisplayInfoId(Long displayInfoId);

	DisplayInfoImage getDisplayInfoImageUsingDisplayInfoId(Long displayInfoId);
}
