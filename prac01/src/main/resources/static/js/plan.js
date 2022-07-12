var flowNum = 1;
var flowNameId = "";
var placeLat=0;
var placeLng=0;
var positions = [];
	
$(function(){
	var user_num = $("#userNum").val(); // 실제 구현시 세션에 저장된 user_num 가져오기
	var plan_num = $("#plannum").val(); //플랜넘버 타임리프로 가져온값 가져옴
	console.log("plan_num: "+plan_num);
	var flowNumCnt =0;
	var flowNameCnt =0;
	var placeNumCnt =0;
	var delBtnCnt =0;
	var group_num;
	
	//map 생성 
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = { 
		    center: new kakao.maps.LatLng(37.556317, 126.922713), // 지도의 중심좌표
		    level: 3 // 지도의 확대 레벨
		};

	//지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
	var map = new kakao.maps.Map(mapContainer, mapOption);
		
	// 마커 이미지의 이미지 주소.
	var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 	
		
	//장소의 위도 경도 가져오는 함수.
	function placeLatLng(placeNum){
		$.ajax({
			url:"/getPlace",
			data:{place_num:placeNum},
			success:function(data){
				//가져온값 변수에 저장;
				placeName = data.place_name;
				lat = data.place_lat;
				lng = data.place_lng;
				
				//카카오맵 연결을 위해 latlng 변수생성후, 새로운 LatLng 객체 생성.
				latlng = new kakao.maps.LatLng(lat, lng);
				
				//여러 값 저장을 위해 positions array에 저장.
				positions.push({title:placeName, placenum:placeNum, latlng : latlng})
				
				//해당위치로 지도 이동 및 마커 표출 
				map.panTo(latlng);
				showMarker(latlng);
				
				console.log(latlng);
			}		 		
		})
	};
	
	//카카오 맵 마커 생성 function
 	function showMarker(){
		
		for (var i = 0; i < positions.length; i++) {
		 	
		    // 마커 이미지의 이미지 크기 입니다
		    var imageSize = new kakao.maps.Size(24, 35); 
		    
		    // 마커 이미지를 생성합니다    
		    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
		    
		    // 마커를 생성합니다
		    var marker = new kakao.maps.Marker({
		        map: map, // 마커를 표시할 지도
		        position: positions[i].latlng, // 마커를 표시할 위치
		        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
		        image : markerImage // 마커 이미지 
		    });
		}
	}
	
	// plan_group_num & title
	$("#btnGrpAdd").on("click", function(){
		$("#planList option:eq(0)").prop("selected", true);
		$("#planList").hide();
		$("#planText").show();
		$(this).hide();
		$("#btnGrpList").show();
		$.ajax({
			url:"/getNextGroupNum",
			success:function(data){
				$("#planGrpNum").attr('value',data);
			}
		})
	});
	
	$("#btnGrpList").on("click", function(){
		$("#planList").show();
		$("#planText").hide();
		$(this).hide();
		$("#btnGrpAdd").show();
	});

	$("#planList").on("change", function(){
		var plGrpnum = $("#planList option:selected").val();
		$("#planGrpNum").val(plGrpnum);
		$("#planText").val("");
		var text = $("#planList option:selected").text();
		$("#planText").val(text);
	});
	
	$("#placeList > li").on("click", function(){
		placeName = $(this).text();
		placeNum = $(this).val();
		$("#"+flowNameId).attr({value:placeName});
		$("#"+flowNameId).next().val(placeNum);
		placeLatLng(placeNum);
	});
	
	$("#AddPlan").on("click", function(){
		
		var inputFlowNum = $("<div name='plan_flow_num'></div>").attr({
			id:"flowNum"+flowNumCnt++,
			class: "form-control flowNum",
			style: "text-align:center"
		}).html(flowNum++)
		
		var inputFlowName = $("<input name='plan_flow_name' onclick='selectFlowName(this)'>").attr({
			id: "flowText"+flowNameCnt++,
			type: "text",
			class :"form-control flowText",
			style: "width: 70%"
		});
 			
		var input_num = $("<input name='place.place_num'>").attr({
			type: "hidden",
			//현재 선택된 장소의 경도 위도 가져오는 함수 실행.
		});
 
		var delBtn = $("<button onclick='del(this)'>x</button>").attr({
			id:"btnDel"+delBtnCnt++,
			type:"button",
			class:"btn btn-dark btnDel"
		});
 			
		var str = $("<div></div>").attr({
			name: "plan_name",
			class: "input-group div mb-1"
		}).append(inputFlowNum,inputFlowName,input_num,delBtn);

 			
		$("#inputAppend").append(str);
 			
	})
 		
	//동선입력에 변경이 생기면 새로 번호 부여 및 지도 새로 표시
	$("#inputAppend").on("change", function(){
		
	})
	
})
 	
function del(id){
	 $(id).parent("div").remove();
	 flowNum--;
}

function selectFlowName(name){
	flowNameId = name.id;
	console.log(name.id);
}
