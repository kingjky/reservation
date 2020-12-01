package kr.or.connect.reservation.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.PromotionDao;
import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService {
	private final PromotionDao promotionDao;

	public PromotionServiceImpl(PromotionDao promotionDao) {
		this.promotionDao = promotionDao;
	}

	@Override
	public List<Promotion> getPromotions() {
		List<Promotion> promotions = promotionDao.selectAll();
		return promotions;
	}

}
