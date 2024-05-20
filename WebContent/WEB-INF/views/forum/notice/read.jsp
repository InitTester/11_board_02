<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String ctx = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">

    <!-- viewport meta -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="MartPlace - Complete Online Multipurpose Marketplace HTML Template">
    <meta name="keywords" content="marketplace, easy digital download, digital product, digital, html5">

    <title>포트폴리오</title>

    <!-- inject:css -->
    <link rel="stylesheet" href="<%=ctx%>/assest/template/css/animate.css">
    <link rel="stylesheet" href="<%=ctx%>/assest/template/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=ctx%>/assest/template/css/fontello.css">
    <link rel="stylesheet" href="<%=ctx%>/assest/template/css/jquery-ui.css">
    <link rel="stylesheet" href="<%=ctx%>/assest/template/css/lnr-icon.css">
    <link rel="stylesheet" href="<%=ctx%>/assest/template/css/owl.carousel.css">
    <link rel="stylesheet" href="<%=ctx%>/assest/template/css/slick.css">
    <link rel="stylesheet" href="<%=ctx%>/assest/template/css/trumbowyg.min.css">
    <link rel="stylesheet" href="<%=ctx%>/assest/template/css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="<%=ctx%>/assest/template/css/style.css">
	<link rel="stylesheet" href="<%=ctx%>/assest/template/css/trumbowyg.min.css">
    <!-- endinject -->

    <!-- Favicon -->
    <link rel="icon" type="image/png" sizes="16x16" href="<%=ctx%>/assest/template/images/favicon.png">    
</head>

<body class="preload home1 mutlti-vendor">
    <!--================================
            START DASHBOARD AREA
    =================================-->
    <section class="support_threads_area section--padding2">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="forum_detail_area ">
                    
                        <div class="cardify forum--issue">
                            <div class="title_vote clearfix">
                              <h3>${board.title}</h3>

								<!-- 게시글에 대한 좋아요/ 싫어요 이벤트 css 위치 
									 클릭 시 함수를 호출 하도록 onClick 이벤트를 연결
									 필수 값인 게시글번호와 게시글 타입을 매개변수로 전달
								-->
								
								 <div class="vote">
								    <a href="#" id='cThumbUpAnchor' data-isLike ='Y' data-thumb ='Up' onClick="javascript:thumbVote(${board.boardSeq}, ${board.boardTypeSeq}, this);" class="${isLike eq 'Y'? 'active':'' }" >
								        <span class="lnr lnr-thumbs-up"></span>
								    </a>
								    <a href="#" id='!' data-isLike ='N' data-thumb ='Down' onClick="javascript:thumbVote(${board.boardSeq}, ${board.boardTypeSeq}, this);" class="${isLike eq 'N'? 'active':'' }">
								        <span class="lnr lnr-thumbs-down"></span>
								    </a>
								</div>
                                <!-- end .vote -->
                                
                            </div>
                            <!-- end .title_vote -->
                            
                            <div class="suppot_query_tag">
                                <img class="poster_avatar" src="<%=ctx%>/assest/template/images/support_avat1.png" alt="Support Avatar"> 
                                ${board.regMemberId}
                                <span>${board.regDtm}</span>
                                
                        		<!-- 수정버튼 -->	                               
                                <a href="<c:url value='/forum/notice/modifyPage.do?boardSeq=${board.boardSeq}&boardTypeSeq=${board.boardTypeSeq}'/>" >수정</a>
                        		<!-- 삭제버튼 -->
                        		<a href="#" onClick="javascript:deleteClick(${board.boardSeq}, ${board.boardTypeSeq});">삭제</a>	                               
                                <%-- <a href="<c:url value='/forum/notice/delete.do?boardSeq=${board.boardSeq}&boardTypeSeq=${board.boardTypeSeq}'/>" >삭제</a> --%>
                                
                            </div>
                            <p style="margin-bottom: 0; margin-top: 19px;">
                            	${board.content}</p>
                            	
                        </div>
                        <!-- end .forum_issue -->

                        <div class="forum--replays cardify">
                            <div class="area_title">
                                <h4>1 Replies</h4>
                            </div>
                            <!-- end .area_title -->

                            <div class="forum_single_reply">
                                <div class="reply_content">
                                    <div class="name_vote">
                                        <div class="pull-left">
                                            <h4>AazzTech
                                                <span>staff</span>
                                            </h4>
                                            <p>Answered 3 days ago</p>
                                        </div>
                                        <!-- end .pull-left -->

                                        <div class="vote">
                                            <a href="#" class="active">
                                                <span class="lnr lnr-thumbs-up"></span>
                                            </a>
                                            <a href="#" class="">
                                                <span class="lnr lnr-thumbs-down"></span>
                                            </a>
                                        </div>
                                        
                                    </div>
                                    <!-- end .vote -->
                                    <p>Nunc placerat mi id nisi interdum mollis. Praesent pharetra, justo ut sceleris que the
                                        mattis, leo quam aliquet congue placerat mi id nisi interdum mollis. </p>
                                </div>
                                <!-- end .reply_content -->
                            </div>
                            <!-- end .forum_single_reply -->

                            <div class="comment-form-area">
                                <h4>Leave a comment</h4>
                                <!-- comment reply -->
                                <div class="media comment-form support__comment">
                                    <div class="media-left">
                                        <a href="#">
                                            <img class="media-object" src="<%=ctx%>/assest/template/images/m7.png" alt="Commentator Avatar">
                                        </a>
                                    </div>
                                    <div class="media-body">
                                        <form action="#" class="comment-reply-form">
                                            <div id="trumbowyg-demo"></div>
                                            <button class="btn btn--sm btn--round">Post Comment</button>
                                        </form>
                                    </div>
                                </div>
                                <!-- comment reply -->
                            </div>
                        </div>
                        <!-- end .forum_replays -->
                    </div>
                    <!-- end .forum_detail_area -->
                </div>
                <!-- end .col-md-12 -->            
            </div>
            <!-- end .row -->
        </div>
        <!-- end .container -->
    </section>
    <!--================================
            END DASHBOARD AREA
    =================================-->
   	<!--//////////////////// JS GOES HERE ////////////////-->
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA0C5etf1GVmL_ldVAichWwFFVcDfa1y_c"></script>
    <!-- inject:js -->
    <script src="<%=ctx%>/assest/template/js/vendor/jquery/jquery-1.12.3.js"></script>
    <script src="<%=ctx%>/assest/template/js/vendor/jquery/popper.min.js"></script>
    <script src="<%=ctx%>/assest/template/js/vendor/jquery/uikit.min.js"></script>
    <script src="<%=ctx%>/assest/template/js/vendor/bootstrap.min.js"></script>
    <script src="<%=ctx%>/assest/template/js/vendor/chart.bundle.min.js"></script>
    <script src="<%=ctx%>/assest/template/js/vendor/grid.min.js"></script>
    <script src="<%=ctx%>/assest/template/js/vendor/jquery-ui.min.js"></script>
    <script src="<%=ctx%>/assest/template/js/vendor/jquery.barrating.min.js"></script>
    <script src="<%=ctx%>/assest/template/js/vendor/jquery.countdown.min.js"></script>
    <script src="<%=ctx%>/assest/template/js/vendor/jquery.counterup.min.js"></script>
    <script src="<%=ctx%>/assest/template/js/vendor/jquery.easing1.3.js"></script>
    <script src="<%=ctx%>/assest/template/js/vendor/owl.carousel.min.js"></script>
    <script src="<%=ctx%>/assest/template/js/vendor/slick.min.js"></script>
    <script src="<%=ctx%>/assest/template/js/vendor/tether.min.js"></script>
    <script src="<%=ctx%>/assest/template/js/vendor/trumbowyg.min.js"></script>
    <script src="<%=ctx%>/assest/template/js/vendor/waypoints.min.js"></script>
    <script src="<%=ctx%>/assest/template/js/dashboard.js"></script>
    <script src="<%=ctx%>/assest/template/js/main.js"></script>
    <script src="<%=ctx%>/assest/template/js/map.js"></script>
    <!-- endinject -->
    
   	<script type="text/javascript">
		var ctx = '<%= request.getContextPath() %>';
	</script>	
	<script src="<%=ctx%>/assest/js/page.js"></script>
	
    <script src="<%=ctx%>/assest/template/js/vendor/trumbowyg.min.js"></script>
    <script src="<%=ctx%>/assest/template/js/vendor/trumbowyg/ko.js"></script>
    <script type="text/javascript">
	    $('#trumbowyg-demo').trumbowyg({
	        lang: 'kr'
	    });
	    
	    function thumbVote(boardSeq, boardTypeSeq, elem) {
	    	
	    	let url = '<%=ctx%>/forum/notice/vote.do?';
	    	url += 'boardSeq='+boardSeq
	    	url += '&boardTypeSeq='+boardTypeSeq
	    	url += '&isLike=' + elem.getAttribute("data-isLike")
	    	url += '&thumb=' + elem.getAttribute("data-thumb"); 
	    	
	    	$.ajax({
	    		// 타입 (get, post, put 등등)    
	    		type : 'get',           
	    		// 요청할 서버url
	    		url : url,
	    		// Http header
	    		headers : {
	    			"Content-Type" : "application/json"
	    			/* "accept" : "application/jsoin" */
	    		},
	    		/* dataType : 'text', */
	    		// 결과 성공 콜백함수 
	    		success : function(result) {
    				
    				console.log("result : " + result);
    				
/*     				result : 0, 삭제
    				result : 1, 추가
    				result : 2, 수정 */

	      			// jQuery 이용해 이벤트 후처리
	      			
	    			if (result == 0) {
		      			$('a#cThumb'+elem.getAttribute("data-thumb")+'Anchor').removeClass('active');	    				
	    			}else if(result == 1){
	    				$('a#cThumb'+elem.getAttribute("data-thumb")+'Anchor').addClass('active');
	    			} else if(result ==2){
	    				$('a#cThumb'+!elem.getAttribute("data-thumb")+'Anchor').removeClass('active');
	    				$('a#cThumb'+elem.getAttribute("data-thumb")+'Anchor').addClass('active');
	    			}
	    		},
	    		// 결과 에러 콜백함수
	    		error : function(request, status, error) {
	    			console.log(error)
	    		}
	    	});
	    }
	 
    	window.onload=function(){
    		var code = '${code}';
    		var msg = '${msg}';
    		
   			if(msg != null && msg != "" ) {
   				
   				console.log(ctx);
   				/* window.location.href = ctx; */
   				alert(msg);
   			}
   			
    /* 		var urlParams = new URLSearchParams(window.location.search);
    		var msg2 = urlParams.get('msg');
    		
    		if(msg2){
    			alert(msg2);
    		} */
    	}
    	
    	function deleteClick(boardSeq, boardTypeSeq) {
    		var result = confirm("정말 현재 게시글을 삭제 하시겠습니까?");

    		let url = '<%=ctx%>/forum/notice/delete.do?';
		    	url += 'boardSeq='+boardSeq
		    	url += '&boardTypeSeq='+boardTypeSeq;
		    	
    		if(result){
    			
    			$.ajax({
    	            type: 'get',
    	            url: url,
    	            headers: {
    	                "Accept": "application/json",  // 요청에 대한 Accept 헤더를 설정
    	                "Content-Type": "application/json"
    	    		},
    	    		// 결과 성공 콜백함수 
    	    		success : function(response) {   
    	    			var page = response.page;
    	    			
    	    			/* alert(page); */
	    				location.href='<%=ctx%>'+page;
        				/* /* alert(response.msg); */ */        	
    	    		},
    	    		// 결과 에러 콜백함수
    	    		error : function(request, status, error) {
    	    			console.log(error)
    	    		}
    	    	});
    		}else{
    			/* alert('cancel'); */
    		}
    	}
    	
	</script>
</body>

</html>
	