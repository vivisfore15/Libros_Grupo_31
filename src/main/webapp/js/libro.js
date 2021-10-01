$(document).ready(function(){
	

cargaLibros();

function cargaLibros(){

$.ajax({
 type:"post",
 url:"Libro",
 dataType:"json",
 data:{op:"select"},
 success: function(result){
	console.log(result);
	let datos=document.querySelector(".listaLibros")
	console.log(datos)
	datos.innerHTML=''
	for(let i of result){
		datos.innerHTML+=`<option value='${i.ISBN}'>${i.Titulo}</option>`;
	}
}	
	
});	
}	

});