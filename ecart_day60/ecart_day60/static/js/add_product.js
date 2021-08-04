const form = document.forms;

const product_id_field = document.querySelector("#product_id");

const steps = document.querySelectorAll('.step_box');


const step_list = document.querySelectorAll("#step_list li");
const steps_li = Array.from(step_list);
const product_titles = document.querySelectorAll('.product_title');

const add_more_link = document.querySelector('#add_more_link');
const product_point_record = document.querySelector('#product_point_record');

const add_file = document.querySelector('#add_file');
const upload_field_box = document.querySelector('#upload_field_box');
let n = 1;
add_file.addEventListener('click',()=>{
	let uprow = upload_field_box.insertRow(n++);
	let rcell = uprow.insertCell(0);
	rcell.className = 'lft';
	rcell.innerHTML = `${n}.`;
	rcell = uprow.insertCell(1);	
	rcell.className = 'rht';
	let fup = document.createElement('input');
	fup.type = 'file';
	fup.name = `product_pic_${n}`;
	fup.className = 'input_normal';
	rcell.append(fup);
});


let row = 1;
add_more_link.addEventListener('click',()=>{
	const nrow = product_point_record.insertRow(row++);

	const cell1 = nrow.insertCell(0);
	const cell2 = nrow.insertCell(1);
	const cell3 = nrow.insertCell(2);

	cell1.innerHTML = `${row}.`;
	cell1.className = 'block1';

	const input_field = document.createElement('input');
	input_field.className = 'input_normal point_ttl';
	cell2.append(input_field);
	cell2.className = 'block2';
	
	cell3.innerHTML = '<textarea class="point_txt" rows="3" cols="22"></textarea>';
	cell3.className = 'block3';
});

//############### +++++++++++++++++++++ #######################
const saveProductNextStep = async (request) => {	

	const response = await fetch(request,{method:'post'});
	const data = await response.json();

	return data;
};
//############### +++++++++++++++++++++ #######################

//##################### 7. Product Points #####################

form[6].addEventListener('submit',(e)=>{
	e.preventDefault();

	const url = 'save_product_points.do?';

	const point_ttls = Array.from(document.querySelectorAll('.point_ttl'));
	const point_txts = Array.from(document.querySelectorAll('.point_txt'));
	
	let query = '';
	let i = 0;
	point_ttls.forEach((point_ttl)=>{
		if(i==0)
			query += `p_ttl=${encodeURIComponent(point_ttl.value)}`;
		else
			query += `&p_ttl=${encodeURIComponent(point_ttl.value)}`;
		i++;
	});

	point_txts.forEach((point_txt)=>{
		query += `&p_val=${encodeURIComponent(point_txt.value)}`;
	});

	saveProductNextStep(url+query)
		.then((data)=>{
			if(data.resp==1){
				steps[6].className = 'hide step_box';
				steps[7].className = 'show step_box';	
				steps_li[7].className = 'sel';
			}else{
			
			}
		})
		.catch((err)=>{
			console.log(err);
		});
});


//##################### 6. Payment Details #####################
form[5].addEventListener('submit',(e)=>{
	e.preventDefault();
	
	const url = 'save_payment_details.do?';
	const query = `payment_details=${form[5].payment_details.value}`;

	saveProductNextStep(url+query)
		.then((data)=>{
			if(data.resp==1){
				steps[5].className = 'hide step_box';
				steps[6].className = 'show step_box';	
				steps_li[6].className = 'sel';
			}else if(data.resp==0){
			
			}else{
				window.location = 'signin.do';
			}
		})
		.catch((err)=>{
			console.log(err);
		});
});


//##################### 5. Shipment Details #####################
form[4].addEventListener('submit',(e)=>{
	e.preventDefault();

	const url = 'save_shipment_details.do?';
	const query = `shipment_details=${form[4].shipment_details.value}`;
	
	saveProductNextStep(url+query)
		.then((data)=>{
			if(data.resp===1){
				steps[4].className = 'hide step_box';
				steps[5].className = 'show step_box';	
				steps_li[5].className = 'sel';
			}else if(data.resp===0){
			
			}else{
				window.location = 'signin.do';
			}
		}).catch((err)=>{
			console.log(err);
		});
});



//##################### 4. Returning Policy #####################
form[3].addEventListener('submit',(e)=>{
	e.preventDefault();
	
	const url = 'save_returning_policy.do?';
	const query = `returning_policy=${form[3].returning_policy.value}`;

	saveProductNextStep(url+query)
		.then((data)=>{
			if(data.resp===1){
				steps[3].className = 'hide step_box';
				steps[4].className = 'show step_box';	
				steps_li[4].className = 'sel';
			}else if(data.resp===0){
			
			}else{
				window.location = 'signin.do';
			}
		}).catch((err)=>{
			console.log(err);
		});
});

//##################### 3. Save Warranty #####################
form[2].addEventListener('submit',(e)=>{
	e.preventDefault();
	
	const url = 'save_warranty.do?';
	const query = `warranty=${form[2].warranty.value}`;

	saveProductNextStep(url+query)
		.then((data)=>{
			if(data.resp===1){
				steps[2].className = 'hide step_box';
				steps[3].className = 'show step_box';	
				steps_li[3].className = 'sel';
			}else if(data.resp===0){
			
			}else{
				window.location = 'signin.do';
			}
		}).catch((err)=>{
			console.log(err);
		});
});


//##################### 2. Save Description #####################
form[1].addEventListener('submit',(e)=>{
	e.preventDefault();
	
	const url = 'save_product_desc.do?';
	const query = `description=${form[1].description.value}&product_id=${product_id_field.value}`;

	saveProductNextStep(url+query)
		.then((data)=>{
			if(data.resp===1){
				steps[1].className = 'hide step_box';
				steps[2].className = 'show step_box';	
				steps_li[2].className = 'sel';
			}else if(data.resp===0){
			
			}else{
				window.location = 'signin.do';
			}		
		}).catch((err)=>{
			console.log(err);
		});
});


//##################### 1. Save Product ##########################
form[0].addEventListener('submit',(e)=>{
	e.preventDefault();

	const url = 'new_product.do?';
	const query = `categor_id=${form[0].categor_id.value}&product_name=${encodeURIComponent(form[0].product_name.value)}&quantity=${form[0].quantity.value}&price=${form[0].price.value}&discount=${form[0].discount.value}`;
	
	saveProductNextStep(url+query)
		.then((data)=>{
			if(data.resp==0){
				window.location = 'signin.do';				
			}else{
				if(data.productId){					
					product_id_field.value = data.productId;
					steps[0].className = 'hide step_box';
					steps[1].className = 'show step_box';	
					steps_li[1].className = 'sel';

					const pro_titles = Array.from(product_titles);
					pro_titles.forEach((title)=>{
						title.innerHTML = data.productName;
					});
				}
			}
		}).catch((err)=>{
			console.log(err);
		});
});

