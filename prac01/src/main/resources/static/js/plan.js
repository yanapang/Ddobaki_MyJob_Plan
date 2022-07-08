/*  			var btn1 = $('<button </button>').attr({
 				id: "btnPlace",
 				class: "btn btn-secondary btn-sm dropdown-toggle",
 				type: "button",
////////-----------------
 			}); */
 			
 			
/*  			var btn1 = $('<button data-bs-toggle="dropdown" aria-expanded="false">place</button>').attr({
 				class: "",
 				id: "btnPlace",
 				type: "button",
 			}); */
 			/* btn1 = $('<button data-bs-auto-close="true" >place</button>').attr({ */
	btn1 = $('<button >place</button>').attr({
		class: "btn btn-default dropdown-toggle",
		id: "btnPlace"+cnt,
		type: "button",
	}).css({
		class: "dropdown-toggle",
		"data-bs-toggle":"dropdown",
		"data-bs-auto-close":"true",
		"aria-expanded": "false"});
	
	var ul1 = btn1.append($('<ul id="placeList"></ul>').css("dropdown-menu"));
	
	$( btn1 ).on("click", function(){

		var li1 = ul1.empty();
		$.each(placeAll, function(key, value){
			
			var addPlace = $("<li>"+value.place_name+"</li>").attr({
				class: "dropdown-item",
				value: value.place_num
			});
			
			ul1.append(addPlace);
			
			$(addPlace).on("click", function(){
				var thisPName = value.place_name;
				var thisPNum = value.place_num;
				var lat = value.place_lat;
				var lng = value.place_lng;
	 			console.log("장소명: "+value.place_name + ", 장소번호: "+ value.place_num+", lat: "+ lat +", lng: "+lng);
				$(this).parents(".flowText").attr({
					value: thisPName
				});
				cnt++;
			})
		})
		
	})//btn1 끝
	
	var btn2 = $("<button>찜</button>").attr({
		id: "btnDibs",
		class: "btn btn-secondary btn-sm dropdown-toggle",
		type: "button",
	}).append();
	var btn3 = $("<button>예약</button>").attr({
		id: "btnRsv",
		class: "btn btn-secondary btn-sm dropdown-toggle",
		type: "button",
	}).append();
	var btn4 = $("<button>X</button>").attr({
		id: "btnDel",
		class: "btn btn-secondary btn-sm",
		type: "button", 
		
	})
	
	$(btn1).on("click", "li", function(){
 			console.log(li1);
 			var text = $(this).text();
 			console.log(text);
 			$("#flowText").attr({value:text});
 		});
 		
 	$("#dibsList > li").on("click", function(){
 			var text = $(this).text();
 			$("#flowText").attr({value:text});
 		});
 		
 		$("#rsvList > li").on("click", function(){
 			var text = $(this).text();
 			$("#flowText").attr({value:text});
 		});