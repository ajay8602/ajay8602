const cart_box = document.querySelector('#cart_box');
const prod_recs = Array.from(document.querySelectorAll('.prod_rec'));

let pro_qts = Array.from(document.querySelectorAll('.pro_qt'));
let prcaftdis = Array.from(document.querySelectorAll('.prcaftdis'));
const ttl = document.querySelector('#ttl');

const cart_count = document.querySelector('#cart_count');
const empty = document.querySelector('#empty');

const cart_total_box = document.querySelector('#cart_total_box');
const button_box = document.querySelector('#button_box');

const checkout = document.querySelector('#checkout');

//################# Checkout ######################
checkout.addEventListener('click',()=>{

	window.location = 'all_addresses.do';
});




//################### Total Calculation ###############
let total = 0;
let itemCount = 0;
const calcTotal = ()=>{
	total = 0;
	itemCount = 0;

	pro_qts.forEach((sel,index)=>{
		itemCount += parseInt(sel.value);
		total += parseInt(prcaftdis[index].innerHTML) * parseInt(sel.value);
	});
	
	ttl.innerHTML = total;
	cart_count.innerHTML = itemCount;
	
	if(itemCount==0){
		empty.style.display = 'block';
		cart_total_box.style.display = 'none';
		button_box.style.display = 'none';
	}else{
		empty.style.display = 'none';
		cart_total_box.style.display = 'block';
		button_box.style.display = 'block';
	}
};

calcTotal();



//###################### Cart Update ######################

pro_qts.forEach((sel,index)=>{
	total += parseInt(prcaftdis[index].innerHTML) * parseInt(sel.value)

	sel.addEventListener('change',(e)=>{
		let proid = e.target.getAttribute('data-proqt');
		updateCart(proid,e.target.value)
			.then((data)=>{
				if(data.done=='true'){					
					calcTotal();
				}
			})
			.catch((err)=>{
			
			});
	});
});


const updateCart = async (proid,proqt)=>{
	const url = 'update_cart.do?';
	const params = `product_id=${proid}&quantity=${proqt}`;

	const response = await fetch(url+params);
	const data = await response.json();

	return data;
};




//############### Delete Record #############################
const del_box = Array.from(document.querySelectorAll('.del_box'));

const deleteCartRecord = async (productId)=>{
	const url = 'delete_cart_record.do?';
	const param = `product_id=${productId}`;

	const response = await fetch(url+param);
	const data = await response.json();

	return data;
};

del_box.forEach((del,index)=>{
	del.container_box = prod_recs[index];	

	del.addEventListener('click',(e)=>{
		let prodId = e.target.getAttribute('data-del');
		deleteCartRecord(prodId)
			.then((data)=>{
				if(data.done=='true'){						
					cart_box.removeChild(e.target.container_box);
					
					pro_qts = Array.from(document.querySelectorAll('.pro_qt'));
					prcaftdis = Array.from(document.querySelectorAll('.prcaftdis'));
										
					calcTotal();
				}
			})
			.catch((err)=>{
			
			});
	});
});