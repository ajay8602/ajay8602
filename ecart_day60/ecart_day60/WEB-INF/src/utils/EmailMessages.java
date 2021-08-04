package utils;

public class EmailMessages{
	public static String getAccountActivationMail(String userName, String activationCode){
		String accountActivationMessage = 
					  "<!doctype html>"+
						"<html lang='en'>"+
						 "<head>"+
						  "<meta charset='UTF-8'>"+
					  "<style>"+
						"#container{"+
							"background-color: #eee;"+
							"height: 500px;"+
							"padding-top: 50px;"+
						"}"+

						"#header{"+
							"background-color: white;"+
							"padding-top: 20px;"+
							"padding-bottom: 20px;"+
							"text-align: center;"+
						"}"+

						"#logo{"+
							"height: 50px;"+
						"}"+

						"#msg{"+
							"font-family: arial;"+
							"font-size: 20px; "+
							"padding: 20px 50px;"+
						"}"+
					  "</style>"+
					"<title>Document</title>"+
					 "</head>"+
					 "<body>"+ 	
					  
					  "<div id='container'>"+
						"<div id='header'>"+
							"<img src='https://i1.wp.com/ecart.com.bd/wp-content/uploads/2020/06/ecart-copy-e1592487726745.png?fit=956%2C326&ssl=1' id='logo'>"+
						"</div>"+
						"<div id='msg'>"+
							"<h3><p>Welcome "+userName+"</p></h3>"+

							"<h3><p>Your Registration is successful...</p></h3>"+
							
							"<p>Click over the activation link to Activate your account:</p>"+

							"<a href='http://localhost:9090/ecart/activate.do?ukey="+userName+"&actCode="+activationCode+"'>Activate Account</a>"+
						"</div>"+
					  "</div>"+
					"</body>"+
				"</html>";

		return accountActivationMessage;
	}	
}