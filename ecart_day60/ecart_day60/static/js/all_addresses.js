//#################### address form ##############
const form = document.forms[0];

const name = form.name;
const address = form.address;
//const city_id = form.city_id;
const pin = form.pin;

const addNewAddress = async ()=>{	
	const url = 'all_addresses.do?';
	const params = `name=${name.value}&address=${address.value}&city_id=${city_id.value}&pin=${pin.value}`;

	const response = await fetch(url+params,{method:'POST'});
	const data = await response.json();
	
	return data;
}; 

form.addEventListener('submit',(e)=>{
	e.preventDefault();

	addNewAddress()
		.then((data)=>{
			if(data.done==1){
				window.location = 'all_addresses.do';
			}
		})
		.catch((err)=>{
			console.log('++++++',err);
		});
});



//#################### Tabs ###########################
const record_box = document.querySelector('#record_box_container');
const form_box = document.querySelector('#form_box_container');
const addnew = document.querySelector('#addnew');

const adrtabs = Array.from(document.querySelectorAll('.adrtab'));

adrtabs.forEach((tab)=>{
	tab.addEventListener('click',(e)=>{
		record_box.style.display = 'block';
		form_box.style.display = 'none';
	});
});

addnew.addEventListener('click',()=>{
	record_box.style.display = 'none';
	form_box.style.display = 'block';
});