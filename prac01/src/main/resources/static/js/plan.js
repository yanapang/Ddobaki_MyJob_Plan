var flowNameId = "";
var placeLat = 0;
var placeLng = 0;
var positions = [];
var i = 0;

$(function() {
	var user_num = $("#userNum").val(); // 실제 구현시 세션에 저장된 user_num 가져오기 

	var flowNumCnt = 0;
	var flowNameCnt = 0;
	var placeNumCnt = 0;
	var delBtnCnt = 0;
	var group_num;
	var planNumCnt = 0;
	var nxtPlanNum = 0;
	var nxtFlowNum = 0;

	//map 생성 
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = {
			center: new kakao.maps.LatLng(37.556317, 126.922713), // 지도의 중심좌표
			level: 3 // 지도의 확대 레벨
		};

	//지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
	var map = new kakao.maps.Map(mapContainer, mapOption);

	// 마커 이미지의 이미지 주소.
	//var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 	

	//장소의 위도 경도 가져오는 함수.
	function placeLatLng(placeNum) {
		$.ajax({
			url: "/getPlace",
			data: { place_num: placeNum },
			success: function(data) {
				//가져온값 변수에 저장;
				placeName = data.place_name;
				lat = data.place_lat;
				lng = data.place_lng;

				//카카오맵 연결을 위해 latlng 변수생성후, 새로운 LatLng 객체 생성.
				latlng = new kakao.maps.LatLng(lat, lng);

				//여러 값 저장을 위해 positions array에 저장.
				positions.push({ text: placeName, placenum: placeNum, latlng: latlng })

				//해당위치로 지도 이동 및 마커 표출 
				map.panTo(latlng);
				showMarker(latlng);

				console.log(positions);
			}
		})
	};

	function getNextPlanNum() {
		$.ajax({
			url: "/getNextPlanNum",
			success: function(data) {
				nxtPlanNum = data;
			}
		})
	}
	
	function getNextFlowNum(planGrpNum, planDate){
		$.ajax({
			url:"/getNextFlowNum",
			data:{plan_group_num:planGrpNum, plan_date:planDate},
			success: function(data){
				console.log(data);
				nxtFlowNum = data;
			}
		})
	}

	//카카오 맵 마커 생성 function
	function showMarker() {

		for (var i = 0; i < positions.length; i++) {
			//
			var imageSrc = '/images/paws.png', // 마커이미지 주소.
				imageSize = new kakao.maps.Size(24, 24); // 마커이미지의 크기.

			// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
			var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

			// 마커를 생성합니다
			var marker = new kakao.maps.Marker({
				map: map, // 마커를 표시할 지도
				position: positions[i].latlng, // 마커를 표시할 위치
				//title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
				image: markerImage // 마커 이미지 
			});
			
			markers.push(marker)
			console.log("markers:"+markers)
		}
	}

	// plan_group_num & title
	$("#btnGrpAdd").on("click", function() {
		$("#planList option:eq(0)").prop("selected", true);
		$("#planList").hide();
		$("#planText").show();
		$(this).hide();
		$("#btnGrpList").show();
		$.ajax({
			url: "/getNextGroupNum",
			success: function(data) {
				$("#planGrpNum").val(data);
				console.log("new Group num:" + data);
			}
		})
	});

	///////-----여기 내일 수정 해야함!!!
	//-- 새로운 여행 계획 제목 입력 후 버튼 클릭시
	// 여행 계획(plan_name) 이랑 plan_group_num 매칭해 저장 
	$("#btnGrpList").on("click", function() {
		if ($("#planText").val != null && $("#planText").text() != null) {
			console.log("New Group val :" + $("#planText").val()); //group title
			//console.log("New Group text :"+ $("#planText").text()); //nothing 
		}
		$("#planList").show();
		$("#planText").hide();
		$(this).hide();
		$("#btnGrpAdd").show();
	});

	$("#planList").on("change", function() {
		var planGrpNum = $("#planList option:selected").val();
		$("#planGrpNum").val(planGrpNum);
		$("#planText").val("");
		var text = $("#planList option:selected").text();
		$("#planText").val(text);
	});

	$("#placeList > li").on("click", function() {
		placeName = $(this).text();
		placeNum = $(this).val();
		$("#" + flowNameId).val(placeName);
		$("#" + flowNameId).next().val(placeNum);
		placeLatLng(placeNum);
	});

	getNextPlanNum();

	$("#AddPlan").on("click", function() {
		console.log("i:" + i);

		var inputPlanNum = $("<input name='list[" + i + "].plan_num'>").attr({
			id: "planNum" + planNumCnt++,
			type: "hidden"
		}).val(nxtPlanNum++);
		console.log(nxtPlanNum);

		var inputFlowNum = $("<input name='list[" + i + "].plan_flow_num' readonly>").attr({
			id: "flowNum" + flowNumCnt++,
			class: "form-control flowNum",
			style: "text-align:center; background:rgba(121,189,143,1);"
		}).val(nxtFlowNum++);
		console.log("nextFlowNum:"+nxtFlowNum);

		var inputFlowName = $("<input name='list[" + i + "].plan_flow_name' onclick='selectFlowName(this)'>").attr({
			id: "flowText" + flowNameCnt++,
			type: "text",
			class: "form-control flowText",
			style: "width: 70%"
		});

		var input_num = $("<input name='list[" + i + "].place_num'>").attr({
			type: "hidden"
		});

		var delBtn = $("<button onclick='del(this)'>x</button>").attr({
			id: "btnDel" + delBtnCnt++,
			type: "button",
			class: "btn btn-danger btnDel"
		});

		var str = $("<div></div>").attr({
			class: "input-group div mb-1"
		}).append(inputPlanNum, inputFlowNum, inputFlowName, input_num, delBtn);

		i++;
		$("#inputAppend").append(str);

	})//새로운 입력 박스 추가하는 function


	//날짜 선택시 ajax를 통해 해당 날짜 계획 불러오기!
	$("input[name=plan_date]").change(function() {
		flowNum = 1;
		plan_group_num = $("input[name=plan_group_num]").val();
		plan_date = $("input[name=plan_date]").val();
		
		getNextFlowNum(plan_group_num, plan_date);
		
		$("#inputAppend").empty();

		console.log("date_changed!");
		$.ajax({
			url: "/findByAll",
			data: { user_num: user_num, plan_group_num: plan_group_num, plan_date: plan_date },
			success: function(data) {

				console.log(data)

				for (let index in data) {

					let plan = data[index];
					console.log("i:" + i);
					console.log("Object.keys(plan):" + Object.keys(plan) + "\n");
					console.log("plan_flow_num:" + plan['plan_flow_num'])
					console.log("plan_flow_name:" + plan['plan_flow_name'])

					var inputPlanNum = $("<input name='list[" + i + "].plan_num'>").attr({
						id: "planNum" + planNumCnt++,
						class: "planNum",
						type: "hidden",
						value: plan['plan_num']
					})

					var inputFlowNum = $("<input name='list[" + i + "].plan_flow_num' readonly>").attr({
						id: "flowNum" + flowNumCnt++,
						class: "form-control flowNum",
						style: "text-align:center; background:rgba(121,189,143,1);",
						value: plan['plan_flow_num']
					})

					var inputFlowName = $("<input name='list[" + i + "].plan_flow_name' onclick='selectFlowName(this)'>").attr({
						id: "flowText" + flowNameCnt++,
						type: "text",
						class: "form-control flowText",
						style: "width: 70%",
						value: plan['plan_flow_name']
					});

					var input_num = $("<input name='list[" + i + "].place_num'>").attr({
						type: "hidden",
						value: plan.place['place_num']
					});

					var delBtn = $("<button onclick='del(this)'>x</button>").attr({
						id: "btnDel" + delBtnCnt++,
						type: "button",
						class: "btn btn-danger btnDel"
					});

					var str = $("<div></div>").attr({
						class: "input-group div mb-1"
					}).append(inputPlanNum, inputFlowNum, inputFlowName, input_num, delBtn);

					i++;
					$("#inputAppend").append(str);
					
					placeLatLng(plan.place['place_num'])

				}

				console.log("OK");
			}
		})//end ajax

	})//end onchange function

	$("#btnDelete").on("click", function() {
		var planDate = $("#planDate").val();
		var planGrpNum = $("#planGrpNum").val();
		console.log(planDate);
		console.log(planGrpNum);
		if (planDate != "" && planGrpNum != "") {
			$.ajax({
				url: "/deleteByPlanDate",
				data: { plan_date: planDate, plan_group_num: planGrpNum },
				success: function() {
					location.reload();
				}
			});
		}
	});

	$("#btnGrpDel").on("click", function() {
		var planGrpNum = $("#planGrpNum").val();
		if (planGrpNum != "") {
			$.ajax({
				url: "/deleteByGroupNum",
				data: { plan_group_num: planGrpNum },
				success: function() {
					location.reload();
				}
			})
		}

	});

})//end 전체 onload function

function del(id) {
	$(id).parent("div").remove();
	delPlanNum = $(id).prevAll(".planNum").val();
	if (delPlanNum != null) {
		$.ajax({
			url: "/deleteByPlanNum",
			data: { plan_num: delPlanNum },
			success: function() {
			}
		})
	}
	$(".flowNum").each(function(index) { // 중간 동선 삭제 시 flowNum 재 설정
    	var idx = index + 1;	
    	$(this).val(idx);
    	flowNum = idx +1;
	})
	
	console.log($("#inputAppend > input").val())
}

function selectFlowName(name) {
	flowNameId = name.id;
	console.log(name.id);
}
