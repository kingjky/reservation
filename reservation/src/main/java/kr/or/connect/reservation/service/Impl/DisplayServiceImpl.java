package kr.or.connect.reservation.service.Impl;

import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.DisplayInfoDao;
import kr.or.connect.reservation.dao.DisplayInfoImageDao;
import kr.or.connect.reservation.dto.DisplayInfo;
import kr.or.connect.reservation.dto.DisplayInfoImage;
import kr.or.connect.reservation.service.DisplayService;

@Service
public class DisplayServiceImpl implements DisplayService {
	private final DisplayInfoDao displayInfoDao;
	private final DisplayInfoImageDao displayInfoImageDao;

	public DisplayServiceImpl(DisplayInfoDao displayInfoDao, DisplayInfoImageDao displayInfoImageDao) {
		this.displayInfoDao = displayInfoDao;
		this.displayInfoImageDao = displayInfoImageDao;
	}

	@Override
	public DisplayInfo getDisplayInfoUsingDisplayInfoId(Long displayInfoId) {
		DisplayInfo displayInfo = displayInfoDao.selectUsingDisplayInfoId(displayInfoId);
		return displayInfo;
	}

	@Override
	public DisplayInfoImage getDisplayInfoImageUsingDisplayInfoId(Long displayInfoId) {
		DisplayInfoImage displayInfoImage = displayInfoImageDao.selectUsingDisplayInfoId(displayInfoId);
		return displayInfoImage;
	}

}
