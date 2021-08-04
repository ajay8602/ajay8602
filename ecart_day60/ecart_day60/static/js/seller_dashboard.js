//####################### Seller Action (Tabs) ######################
const slr_act_tab = document.querySelectorAll('.slr_act_tab');
const blk_ = document.querySelectorAll('.blk_');

const actTabs = Array.from(slr_act_tab);
const tabBlocks = Array.from(blk_);

let i = 0;
actTabs.forEach((tab)=>{
	tab.blk = tabBlocks[i++];

	tab.addEventListener('click',(e)=>{
		actTabs.forEach((tb)=>{ tb.className = 'slr_act_tab act_tab_dactv'; });
		e.target.className = 'slr_act_tab act_tab_actv';

		tabBlocks.forEach((block)=>{ block.className = 'blk_ blk_hide'; });
		e.target.blk.className = 'blk_';
	});

});


//######################## All Products ################################
const slr_act1 = document.querySelector('#slr_act1');

const slrid_ = document.querySelector('#slrid_');

const blk1 = document.querySelector('#blk1');

const noprods = document.querySelector('#noprods');


const allProducts = async ()=>{
	const url = 'allproducts.do?';
	const query = `seller_id=${slrid_.value}`;

	const response = await fetch(url+query);
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
										<a href="editproduct.do?product_id=${product.productId}"><img src="static/images/edit.png" class="prorec_ico"></a>
										<a href="deleteproduct.do?product_id=${product.productId}"><img src="static/images/del.png" class="prorec_ico"></a>
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
	}else{
		noprods.style.display = 'block';
	}
};

slr_act1.addEventListener('click',()=>{
	allProducts()
		.then((data)=>{
			showProducts(data);
		})
		.catch((err)=>{});
});

allProducts()
	.then((data)=>{
		showProducts(data);
	})
	.catch((err)=>{});