const product_id = document.querySelector('#product_id');
const all_pics_box = document.querySelector('#all_pics_box');

const pic_box = document.querySelector('#pic_box');

const getAllProductPics = async ()=>{
	const url = 'all_product_pics.do?';
	const params = `product_id=${product_id.value}`

	const response = await fetch(url+params);
	const data = await response.json();

	return data;
};

const showAllPics = (pics)=>{
	all_pics_box.innerHTML = '';

	let i = 0;
	pics.forEach((pic)=>{
		if(i==0){
			pic_box.innerHTML = `<img id="pic_focus" src="product_pic.do?product_pic=${pic.productPic}">`;
		}
		all_pics_box.innerHTML += `<img class="small_pic" src="product_pic.do?product_pic=${pic.productPic}">`;
	});

	const all_pics = document.querySelectorAll('.small_pic');
	const pics_arr = Array.from(all_pics);
	const pic_focus = document.querySelector('#pic_focus');

	pics_arr.forEach((pic)=>{
		pic.addEventListener('click',(e)=>{
			pic_focus.src = e.target.src;
		});
	});
};

const propt_tbl = document.querySelector('#propt_tbl');

let p = 0;
const showAllPoints = (points)=>{
	points.forEach((point)=>{
		let row = propt_tbl.insertRow(p++);
		let cell0 = row.insertCell(0);
		let cell1 = row.insertCell(1);
		cell0.innerHTML = point.pointHeader;
		cell1.innerHTML = point.pointValue;
	});
};

const getAllProductPoints = async ()=>{
	const url = 'all_product_points.do?';
	const param = `product_id=${product_id.value}`;

	const response = await fetch(url+param);
	const data = await response.json();

	return data;
};

window.addEventListener('load',()=>{
	getAllProductPics()
		.then((data)=>{
			showAllPics(data);
		})
		.catch((err)=>{
			console.log(err);
		});

	getAllProductPoints()
		.then((data)=>{
			showAllPoints(data);
		})
		.catch((err)=>{
		
		});
});




//#######################################

const allDtls = document.querySelectorAll('.odtl');

const detail_boxes = Array.from(allDtls);

//########################################

const allTabs = document.querySelectorAll('.desc');

const tabs = Array.from(allTabs);

let i = 0;
tabs.forEach((tab)=>{
	tab.dtl_box = detail_boxes[i++];

	tab.addEventListener('click',(e)=>{
		tabs.forEach((tb)=>{tb.className = 'desc desc_nsel';});

		detail_boxes.forEach((box)=>{box.className = 'odtl odtl_hide'})

		let t = e.target;
		if(t.classList.contains('desc_nsel')){
			t.className = 'desc desc_sel';
			t.dtl_box.className = 'odtl odtl_show';
		}else{
			t.className = 'desc desc_nsel';
			t.dtl_box.className = 'odtl odtl_hide';
		}
	});
});


//############################################


