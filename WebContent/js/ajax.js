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

function inserirTelefone2()
{
	var xmlHttp = new XMLHttpRequest();
	
	xmlHttp.onreadystatechange = function()
	{
		if(xmlHttp.readyState == 4 && xmlHttp.status == 200)
			{
				document.getElementById("telefone2").innerHTML = xmlHttp.responseText;
			}
	};
	
	xmlHttp.open("GET", "views/inserirTelefone.jsp", true);
	xmlHttp.send();
}