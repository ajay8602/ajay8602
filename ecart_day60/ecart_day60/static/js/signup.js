const form = document.querySelector('form');

const userName = form.username;
const email = form.email;
const password = form.password;
const repassword = form.repassword;

const error_message = document.querySelector('#error_message');
const form_box = document.querySelector('#form_box');
const button = document.querySelector('.button');

const err_arr = new Array(); 

//------------------
userName.onblur = checkUniqueKey;
email.onblur = checkUniqueKey;

let request;
let curFld;
function checkUniqueKey(){
	if(this.value.length!=0){
		curFld = this;

		request = new XMLHttpRequest();

		request.open('GET','unique_check.do?key='+this.value,true);
		request.onreadystatechange = uniqueCheckResult;
		request.send();
	}
}

let flag1=true,flag2=true;
function uniqueCheckResult(){
	if(request.readyState==4&&request.status==200){
		const resp = request.responseText;

		if(resp=='true'){
			console.log(1);
			error_message.style.display = 'block';
			let fld;
			if(curFld.id=='username'){
				fld = 'Username';
				flag1 = false;
			}else{
				fld = 'Email';
				flag2 = false;
			}
			error_message.innerHTML = `${fld} with the given input already exists`;			
		}else{
			console.log(2);
			if(curFld.id=='username'){
				//fld = 'Username';
				flag1 = true;
			}else{
				//fld = 'Email';
				flag2 = true;
			}
		}
	}
}
//------------------

userName.addEventListener('focus',()=>{
	userName.className = 'input_normal';
});

email.addEventListener('focus',()=>{
	email.className = 'input_normal';	
});

repassword.addEventListener('focus',()=>{
	repassword.className = 'input_normal';
	password.className = 'input_normal';
});

password.addEventListener('focus',()=>{
	password.className = 'input_normal';
	repassword.className = 'input_normal';
});

let i = 0;
form.addEventListener('submit',(e)=>{
	e.preventDefault();
	let flag = true; 
	
	const unamePattern = /^[a-zA-Z][a-zA-Z0-9]{4,29}$/;
	if(!unamePattern.test(userName.value)){
		flag = false;	
		userName.className = 'error';
		err_arr[i++] = 'Only alphabet and numeric characters allowed...!';
	}
		
	const emailPattern = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
	if(!emailPattern.test(email.value)){
		flag = false;
		email.className = 'error';
		err_arr[i++] = 'Invalid email!!';
	}
		
	if(password.value!=repassword.value){
		flag = false;
		password.className = 'error';
		repassword.className = 'error';
		err_arr[i++] = 'password and repassword must match!!';
	}
		
	if(flag&&flag1&&flag2)
		form.submit();
	else{
		error_message.style.display = 'block';
		err_arr.forEach((emsg)=>{
			let p = document.createElement('p');
			p.innerHTML = emsg;
			error_message.append(p);
		});
		form_box.style.marginTop = '10px';
	}
});

button.addEventListener('mouseover',()=>{
	i = 0;
	err_arr.length = 0;
	form_box.style.marginTop = '30px';
	error_message.style.display = 'none';
});