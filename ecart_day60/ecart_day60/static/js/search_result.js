//############################################################
const msg = document.querySelector('#msg');

msg.style.left = window.innerWidth/2-150+'px';




//######################## All Products ################################


const search_key = document.querySelector('#search_key');

const blk1 = document.querySelector('#blk1');

const noprods = document.querySelector('#noprods');

const cart_count = document.querySelector('#cart_count');


const allProducts = async ()=>{
	const url = 'search.do?';
	const query = `search=${search_key.value}`;

	const response = await fetch(url+query);
	const data = await response.json();

	return data;
}; 

const addToCart = async (productId,quantity)=>{
	
	const url = 'addtocart.do?';
	const param = `product_id=${productId}&quantity=${quantity}`;

	const response = await fetch(url+param);
	const data = await response.json();

	return data;
};

const showProducts = (products)=>{	
	if(products.length>0){
		noprods.style.display = 'none';
		blk1.innerHTML = '<div id="allprods_ttl">All Products</div>';
		products.forEach((product)=>{
			blk1.innerHTML += `<div class="prodrec_">
								<div class="prodrec_rht">
									<div class="prorec_act">
										<span class="act_btn buynowbtn" data-bnb="${product.productId}">Buy Now</span>
										<span class="act_btn addtocartbtn" data-atcb="${product.productId}">Add To Cart</span>
										<select class="proqt">
											<option>1</option>
											<option>2</option>
											<option>3</option>
											<option>4</option>
											<option>5</option>
											<option>6</option>
											<option>7</option>
											<option>8</option>
											<option>9</option>
											<option>10</option>
										</select>
									</div>
									<div class="prodrec_ttl">
										<a href="viewproduct.do?product_id=${product.productId}">${product.productName}</a>
									</div>
									<div class="prodrec_otl">
										<span>Price:</span>
										<span class="price_">Rs. ${product.price*(100-product.discount)/100}</span>
										<s class="mrp_">(MRP Rs. ${product.price})</s>
										<span>Quantity:</span>
										<span class="qt_">${product.quantity}</span>
										<span>Sold:</span>
										<span class="sold_">${product.sold}</span>
									</div>									
								</div>
								<div class="prodrec_lft">
									<img src="product_image.do?product_id=${product.productId}" class="prodrec_img">
								</div>						
							</div>`;
		});

		const buynowbtns = Array.from(document.querySelectorAll('.buynowbtn'));
		const addtocartbtns = Array.from(document.querySelectorAll('.addtocartbtn'));
		const proqt = Array.from(document.querySelectorAll('.proqt'));

		addtocartbtns.forEach((atcb,index)=>{
			atcb.qt = proqt[index];
			atcb.addEventListener('click',(e)=>{
				//console.log(e.target.getAttribute('data-atcb'));
				let productId = e.target.getAttribute('data-atcb');
				addToCart(productId,e.target.qt.value)
					.then((data)=>{
						cart_count.innerHTML = data.productCount;
						msg.style.display = 'block';
						setTimeout(()=>{
							msg.style.display = 'none';
						},5000);
					})
					.catch((err)=>{
					
					});
			});
		});
	}else{
		noprods.style.display = 'block';
	}
};

window.addEventListener('load',(e)=>{
	allProducts()
		.then((data)=>{
			showProducts(data);
		})
		.catch((err)=>{});
});