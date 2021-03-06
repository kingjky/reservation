<%@ page import="org.springframework.web.context.request.RequestScope"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="utf-8">
	<meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
	<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
	<title>네이버 예약</title>
	<link href="./css/style.css" rel="stylesheet">
	<link href="./css/containerVisual.css" rel="stylesheet">
	<link rel="icon" href="./download?fileName=img/favicon.ico">
	<style>
		.container_visual {
			height: 414px;
		}
	</style>
</head>

<body>
	<div id="container">
		<div class="ct main">
			<div class="section_visual">
				<header>
					<h1 class="logo">
						<a href="./" class="lnk_logo" title="네이버">
							<span class="spr_bi ico_n_logo">네이버</span>
						</a>
						<a href="./" class="lnk_logo" title="예약">
							<span class="spr_bi ico_bk_logo">예약</span>
						</a>
					</h1>
					<c:choose>
						<c:when test="${sessionScope.login == null}">
							<a href="bookinglogin" class="btn_my">
								<span class="viewReservation" title="예약확인">예약확인</span>
							</a>
						</c:when>
						<c:otherwise>
							<a href="myreservation" class="btn_my">
								<span class="viewReservation" title="예약내역">${sessionScope.login}</span>
							</a>
						</c:otherwise>
					</c:choose>
				</header>
				<div class="pagination">
					<div class="bg_pagination"></div>
					<div class="figure_pagination">
						<span class="num">0</span>
						<span class="num off">/
							<span>0</span>
						</span>
					</div>
				</div>
				<div class="group_visual">
					<div class="container_visual" style="width: 414px;">
						<ul class="visual_img detail_swipe shifting">
						</ul>
					</div>
					<div class="prev btn">
						<div class="prev_inn">
							<a class="btn_prev" title="이전">
								<i class="spr_book2 ico_arr6_lt off"></i>
							</a>
						</div>
					</div>
					<div class="nxt btn">
						<div class="nxt_inn">
							<a class="btn_nxt" title="다음">
								<i class="spr_book2 ico_arr6_rt"></i>
							</a>
						</div>
					</div>
				</div>
			</div>
			<div class="section_store_details">
				<div class="store_details close3">
					<p class="dsc"></p>
				</div>
				<a class="bk_more _open">
					<span class="bk_more_txt">펼쳐보기</span>
					<i class="fn fn-down2"></i>
				</a>
				<a class="bk_more _close" style="display: none;">
					<span class="bk_more_txt">접기</span>
					<i class="fn fn-up2"></i>
				</a>
			</div>
			<div class="section_event">
				<div class="event_info_box">
					<div class="event_info_tit">
						<h4 class="in_tit">
							<i class="spr_book ico_evt"></i> <span>이벤트 정보</span>
						</h4>
					</div>
					<div class="event_info">
						<div class="in_dsc">
							[네이버예약 특별할인]<br>R석 50%, S석 60% 할인
						</div>
					</div>
				</div>
			</div>
			<div class="section_btn">
				<a>
					<button type="button" class="bk_btn">
						<i class="fn fn-nbooking-calender2"></i>예매하기
					</button>
				</a>
			</div>
			<div class="section_review_list">
				<div class="review_box">
					<h3 class="title_h3">예매자 한줄평</h3>
					<div class="short_review_area">
						<div class="grade_area">
							<span class="graph_mask">
								<em class="graph_value" style="width: 0%;"></em>
							</span>
							<strong class="text_value">
								<span class="average">0.0</span>
								<em class="total">5.0</em>
							</strong>
							<span class="join_count"><em class="green">0</em>건 등록</span>
						</div>
						<ul class="list_short_review">
						</ul>
					</div>
					<p class="guide">
						<i class="spr_book2 ico_bell"></i>
						<span>네이버 예약을 통해 실제 방문한 이용자가 남긴 평가입니다.</span>
					</p>
				</div>
				<a class="btn_review_more hide">
					<span>예매자 한줄평 더보기</span>
					<i class="fn fn-forward1"></i>
				</a>
			</div>
			<div class="section_info_tab">
			</div>
		</div>
	</div>
	<footer>
		<div class="gototop">
			<a href="#" class="lnk_top"> <span class="lnk_top_text">TOP</span>
			</a>
		</div>
		<div class="footer">
			<p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및
				환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
			<span class="copyright">© NAVER Corp.</span>
		</div>
	</footer>
	<div id="photoviwer"></div>
	<script type="myTemplate" id="reviewTemplate">
		<li class="list_item">
			<div>
				<div class="review_area">
					<div class="thumb_area">
						{{#if commentImages}}
							{{#each commentImages}}
								<a href="#" class="thumb" title="이미지 크게 보기">
									<img width="90" height="90"
										class="img_vertical_top"
										src="./download?fileName={{saveFileName}}&contentType={{contentType}}"
										alt="리뷰이미지">
								</a>
								<span class="img_count" style="display: none;">1</span>
							{{/each}}
						{{/if}}
					</div>
					<h4 class="resoc_name">
						{{#getProductDescription}}
						{{/getProductDescription}}
					</h4>
					<p class="review">{{comment}}</p>
				</div>
				<div class="info_area">
					<div class="review_info">
						<span class="grade">
							{{#getFormatScore score}}
							{{/getFormatScore}}
						</span>
						<span class="name"> 
							{{#getFormatEmail reservationEmail}}
							{{/getFormatEmail}}
						</span>
						<span class="date">
							{{#getFormatDate reservationDate}}
							{{/getFormatDate}}
							 방문
						</span>
					</div>
				</div>
			</div>
		</li>
	</script>
	<script type="myTemplate" id="imageTemplate">
		<li class="item" style="width: 414px;">
			<img alt="{{fileName}}" class="img_thumb" src="./download?fileName={{saveFileName}}&contentType={{contentType}}">
			<span class="img_bg"></span>
			<div class="visual_txt">
				<div class="visual_txt_inn">
					<h2 class="visual_txt_tit">
						<span></span>
					</h2>
					<p class="visual_txt_dsc"></p>
				</div>
			</div>
		</li>
	</script>
	<script type="myTemplate" id="infoTabTemplate">
		<ul class="info_tab_lst">
			<li class="item _detail">
				<a class="anchor active"> <span>상세정보</span></a>
			</li>
			<li class="item _path">
				<a class="anchor"> <span>오시는길</span></a>
			</li>
		</ul>
		<div class="detail_area_wrap">
			<div class="detail_area">
				<div class="detail_info">
					<h3 class="blind">상세정보</h3>
					<ul class="detail_info_group">
						<li class="detail_info_lst"><strong class="in_tit">[소개]</strong>
							<p class="in_dsc">{{productContent}}</p>
						</li>
						<li class="detail_info_lst"><strong class="in_tit">[공지사항]</strong>
							<ul class="in_img_group">
								<li class="in_img_lst"><img alt="" class="img_thumb"
										src="https://ssl.phinf.net/naverbooking/20170131_238/14858250829398Pnx6_JPEG/%B0%F8%C1%F6%BB%E7%C7%D7.jpg?type=a1000">
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="detail_location hide">
			<div class="box_store_info no_topline">
				<a class="store_location" title="지도웹으로 연결">
					<img class="store_map img_thumb" alt="map" src="./download?fileName={{#getSaveFileName}}{{/getSaveFileName}}&contentType={{#getContentType}}{{/getContentType}}">
					<span class="img_border"></span>
					<span class="btn_map"><i class="spr_book2 ico_mapview"></i></span>
				</a>
				<h3 class="store_name">{{productDescription}}</h3>
				<div class="store_info">
					<div class="store_addr_wrap">
						<span class="fn fn-pin2"></span>
						<p class="store_addr_bold">{{placeStreet}}</p>
						<p class="store_addr">
							<span class="addr_old">지번</span>
							<span class="addr_old_detail">{{placeLot}}</span>
						</p>
						<p class="store_addr addr_detail">{{placeName}}</p>
					</div>
					<div class="lst_store_info_wrap">
						<ul class="lst_store_info">
							<li class="item">
								<span class="item_lt">
									<i class="fn fn-call2"></i><span class="sr_only">전화번호</span>
								</span>
								<span class="item_rt">
									<a href="tel:{{telephone}}" class="store_tel">{{telephone}}</a>
								</span>
							</li>
						</ul>
					</div>
				</div>
				<div class="bottom_common_path column2">
					<a class="btn_path">
						<i class="fn fn-path-find2"></i>
						<span>길찾기</span>
					</a>
					<a class="btn_navigation before">
						<i class="fn fn-navigation2"></i>
						<span>내비게이션</span>
					</a>
				</div>
			</div>
		</div>
	</script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="module" src="./js/views/detail.js"></script>
</body>

</html>