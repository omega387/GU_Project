 
function LoginValodation()
{
const email=document.getElementById("email").value;
 const password=document.getElementById("password").value;
 if(email==="" || !email.includes("@"))
	{
		alert("Invalid email!");
	}
if(password==="")
	{
		alert("Invalid password!");
	}
}