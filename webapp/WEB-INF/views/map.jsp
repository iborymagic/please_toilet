<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>급  똥</title>
    <link rel="stylesheet" href="/app/resources/css/map.css?ver=1">
<script src="/app/resources/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(() => {
		$("#logoutBtn").on("click", () => {
	        location.href = "/app/logout";
	    });
	      
	    $("#homeBtn").on("click", () => {
	        location.href = "/app/home";
	    });
	    
	    $("#mypageBtn").on("click", () => {
	        location.href = "/app/mypage";
	    });
	});
</script>
</head>
<body>
<div id="container">
	<div class="left_container">
		<a id="title" href="/app">PLEASE TOILET</a>
		<div class="map_info_container">	
		</div>	
		<div class="login_bar">
		    <button id="mypageBtn" class="logBtn">마이페이지</button>
		    <button id="homeBtn" class="logBtn">홈</button>
		    <button id="logoutBtn" class="logBtn">로그아웃</button>
	    </div>
	</div>
	<div id="map_container">
		<div id="map" style="width:100%;height:350px;"></div>	
	</div>
</div>
</body>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f3f9b3f004153427009130bfbefa69c6"></script>
<script>
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = { 
	    center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	    level: 7 // 지도의 확대 레벨 
		}; 

	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	
	mapContainer.style.width = '100%';
	mapContainer.style.height = '98vh';
	map.relayout();
	
	let link;
	let list;
	// HTML5의 geolocation으로 사용할 수 있는지 확인합니다 
	if (navigator.geolocation) {
	    
	    // GeoLocation을 이용해서 접속 위치를 얻어옵니다
	    navigator.geolocation.getCurrentPosition(function(position) {
	        var lat = position.coords.latitude, // 위도
	            lon = position.coords.longitude; // 경도
	        
	        var locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
	            message = '<div class ="label"><span class="center">현재 위치</span></div>'; // 인포윈도우에 표시될 내용입니다
	            
            $.ajax({
                url: '/app/map',
                type: 'post',
                //contentType:'application/json; charset=utf-8',
                data: {latitude:lat, longitude:lon, range:<%=request.getParameter("range")%>},
                dataType:'json',
                success:(serverdata)=>{
                   list = serverdata;
                },
                error : function() {
                	console.log("error");
                },
                async : false
            });        
	            
	        // 마커와 인포윈도우를 표시합
	        
	        
	        displayMarker(locPosition, message);
	            
	      });
	    
	} else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
	    
	    var locPosition = new kakao.maps.LatLng(33.450701, 126.570667),    
	        message = 'geolocation을 사용할수 없어요..'
	        
	    displayMarker(locPosition, message);
	}
		
	// 지도에 마커와 인포윈도우를 표시하는 함수입니다
	function displayMarker(locPosition, message) {
	
	    // 마커를 생성합니다	    
	    var marker = new kakao.maps.Marker({  
	        map: map, 
	        position: locPosition
	    }); 
	    
	    var customOverlay = new kakao.maps.CustomOverlay({
	        position: locPosition,
	        content: message   
	    });
	    
	    customOverlay.setMap(map);
	    
	    // 지도 중심좌표를 접속위치로 변경합니다
	    map.setCenter(locPosition);      
	}
	
	function goreview(tno){
		location.href = "/app/review?tno=" + tno;
	}

	function findIt(){
	   var mc = document.querySelector('.map_info_container');
	   if (window.matchMedia("(max-width: 480px)").matches) {
		   for(var i=0;i<list.length;i++){
			   let fixedName = list[i].smallName;
			   if(fixedName.length > 6) {
				   fixedName = fixedName.slice(0, 6) + "...";
				   console.log(fixedName);
			   }
		       var str="";
		       let link= "https://map.kakao.com/link/to/"+list[i].smallName+","+list[i].latitude+","+list[i].longitude;
		       list[i].link=link;
		       str+=`<div class="map_info">`;
		       str+= `<h3>`;
		       str+=fixedName;
		       str+= `</h3>`;
		       str+=`<h5>`;
		       str+=list[i].bigName;
		       str+=`</h5>`;
		       str+=`<div class="buttons">`;
		       str+=`<input type="button" class="paths" value="길찾기">`;
		       str+=`<input type="button" class="reviews" value="리뷰" id="review" onclick="goreview();">`;
		       str+=`</div>`;
		       str+=`</div>`;
		       mc.innerHTML+=str;
		    }
	   } else {
	    for(var i=0;i<list.length;i++){
	       var str="";
	       let link= "https://map.kakao.com/link/to/"+list[i].smallName+","+list[i].latitude+","+list[i].longitude;
	       list[i].link=link;
	       str+=`<div class="map_info">`;
	       str+= `<h3>`;
	       str+=list[i].smallName;
	       str+= `</h3>`;
	       str+=`<h5>`;
	       str+=list[i].bigName;
	       str+=`</h5>`;
	       str+=`<div class="buttons">`;
	       str+=`<input type="button" class="paths" value="길찾기로 연결">`;
	       str+=`<input type="button" class="reviews" value="리뷰 보기" id="review" onclick="goreview();">`;
	       str+=`</div>`;
	       str+=`</div>`;
	       mc.innerHTML+=str;
	    }		   
	   }
	}
	
	setTimeout(() => {
		findIt();
		
		const buttons = Array.from(document.getElementsByClassName('paths'));
		const buttons2 = Array.from(document.getElementsByClassName('reviews'));
		for(let i = 0; i < list.length; i++) {			
			buttons[i].addEventListener('click', function() {
				findmap(list[i]);
			});
			buttons2[i].addEventListener('click', function() {
				goreview(list[i].tno);
			});
		}
	}, 1000);
		
	setTimeout(() => {
		const positions = [];
		
		for(let i = 0; i < list.length; i++) {
			positions[i] = {
					title : list[i].smallName,
					latlng : new kakao.maps.LatLng(list[i].latitude, list[i].longitude)
			};
		}
		
		const imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
		const cards = Array.from(document.getElementsByClassName("map_info"));
				
		for (let i = 0; i < positions.length; i++) {
		    // 마커 이미지의 이미지 크기 입니다
		    let imageSize = new kakao.maps.Size(24, 35); 
		    
		    // 마커 이미지를 생성합니다    
		    let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
		    
		    // 마커를 생성합니다
		    let marker = new kakao.maps.Marker({
		        map: map, // 마커를 표시할 지도
		        position: positions[i].latlng, // 마커를 표시할 위치
		        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
		        image : markerImage // 마커 이미지 
		    });
		    
		    marker.setZIndex(2);
		    
			let message = '<div class ="label"><span class="center">' + positions[i].title + '</span></div>';
		    
		    const customOverlay = new kakao.maps.CustomOverlay({
		        position: positions[i].latlng,
		        content: message
		    });
		    
		    customOverlay.setZIndex(1);

		    		    		    
		    cards[i].addEventListener('mouseover', function() {
		    	customOverlay.setMap(map);
		    });
		    
		    cards[i].addEventListener('mouseout', function() {
		    	customOverlay.setMap(null);
		    });
		    
		    kakao.maps.event.addListener(marker, 'mouseover', function() {
		    	customOverlay.setMap(map);
		  	});
		    
		    kakao.maps.event.addListener(marker, 'mouseout', function() {
		    	customOverlay.setMap(null);
		  	});
		}		
	}, 1500);
	
	function findmap(obj){
	   window.open(obj.link);
	   $.ajax({
           url: '/app/mypage/usedlog',
           type: 'post',
           //contentType:'application/json; charset=utf-8',
           data: {
		       "tno" : obj.tno
           },
           dataType:'json',
           success:(serverdata)=>{
              list = serverdata;
           },
           error : function() {
           	console.log("error");
           }
       });        
	}
</script>
</html>