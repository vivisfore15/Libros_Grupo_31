$(document).ready(function(){
	

function listarLibros(){
	var men=document.getElementById("mensaje").value	
	$.ajax({
	type:"post",
	url:"Reportes",
	dataType:"json",
	data:{opcion:"libros",mensaje:men},
	success:function(result){
		console.log(result)
		var tabla=document.querySelector("#tabla")
		tabla.innerHTML=''
		tabla.innerHTML+=`<tr>
		<th>ISBN</th>
		<th>Titulo</th>
		<th>Editorial</th>
		<th>Autor</th>
		<th>Paginas</th>
		</tr>`
		for(let lib of result){
		tabla.innerHTML+=`<tr>
		<td>${lib.ISBN}</td>
		<td>${lib.Titulo}</td>
		<td>${lib.Editorial}</td>
		<td>${lib.Autor}</td>
		<td>${lib.No_paginas}</td>
		</tr>`
		}
	}
	})	
}

$('.listaLibros').on('click',function(){
	listarLibros();
})
})