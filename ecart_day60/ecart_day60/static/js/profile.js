const form = document.forms[0];
const otp_box_wrapper = document.querySelector('#otp_box_wrapper');
const otp_box = document.querySelector('#otp_box');
const close = document.querySelector('#close');
const loader = document.querySelector('#loader');

close.addEventListener('click', () => {
	otp_box_wrapper.style.display = 'none'; 
	otp_box.style.display = 'none'; 
});

const sendOTP = async () => {
	const mobile = form.mobile.value.trim();
	
	const data = await fetch('sendotp.do?mobile='+mobile);
	//const data = await response.json();

	return data;
};

const updateUI = () => {
	otp_box.style.display = 'block';
	loader.style.display = 'none';
};

form.addEventListener('submit',(e) => {
	if(form.otp.value.trim().length==6){
		console.log('$$$$$$$$$');
	}else{
		e.preventDefault();
		form.otp.value = '';
		sendOTP()
			.then((data)=>{
				console.log(data);
				updateUI();
			})
			.catch((err)=>{
				console.log(err)
			});	
		
		otp_box_wrapper.style.display = 'block';
		loader.style.display = 'block';
	}
});