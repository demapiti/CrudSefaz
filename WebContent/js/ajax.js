function mostrarSaldo()
{
	var xmlHttp = new XMLHttpRequest();
	
	xmlHttp.onreadystatechange = function()
	{
		if(xmlHttp.readyState == 4 && xmlHttp.status == 200)
			{
				document.getElementById("menu").innerHTML = xmlHttp.responseText;
			}
	};
	
	xmlHttp.open("GET", "views/mostrarSaldo.jsp", true);
	xmlHttp.send();
}

function esconderSaldo()
{
	var xmlHttp = new XMLHttpRequest();
	
	xmlHttp.onreadystatechange = function()
	{
		if(xmlHttp.readyState == 4 && xmlHttp.status == 200)
			{
				document.getElementById("menu").innerHTML = xmlHttp.responseText;
			}
	};
	
	xmlHttp.open("GET", "views/esconderSaldo.jsp", true);
	xmlHttp.send();
}