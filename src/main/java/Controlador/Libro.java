package Controlador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.swing.JOptionPane;

import com.google.gson.Gson;

import Modelo.LibroDAO;
import Modelo.LibroDTO;

/**
 * Servlet implementation class Libro
 */
@WebServlet("/Libro")
@MultipartConfig
public class Libro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Libro() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		LibroDAO libDao= new LibroDAO(); 
		if(request.getParameter("registrar")!=null) {
		String codIsbn,titulo,editorial,autor;
		int paginas;
		codIsbn = request.getParameter("isbn");
		titulo = request.getParameter("titulo");
		editorial = request.getParameter("editorial");
		autor = request.getParameter("autor");
		paginas = Integer.parseInt(request.getParameter("paginas"));
		LibroDTO libDto = new LibroDTO(codIsbn,titulo,editorial,autor,paginas);
		if(libDao.Inserta_Libro(libDto)) {
			JOptionPane.showMessageDialog(null, "Libro Registrado Exitosamente.");
			response.sendRedirect("Libros.jsp?men=Libro Registrado Exitosamente.");
		}else {
			JOptionPane.showMessageDialog(null, "El libro no se Registro.");
			response.sendRedirect("Libros.jsp?men=El libro no se Registro.");
		}
		}
		
		if(request.getParameter("consultar")!=null) {
			 
			String codigo,titulo,editorial,autor;
			int paginas;
			codigo=request.getParameter("codigo");
			LibroDTO lib=libDao.Buscar_Libro(codigo);
			if(lib!=null) {
			codigo=lib.getISBN();
			titulo= lib.getTitulo();
			editorial = lib.getEditorial();
			autor = lib.getAutor();
			paginas = lib.getNo_paginas();
			response.sendRedirect("Libros.jsp?codigo="+codigo+"&&titulo="+titulo+"&&editorial="
			+editorial+"&&autor="+autor+"&&paginas="+paginas);
			}else
			{
				response.sendRedirect("Libros.jsp?men=El Libro no existe");
			}
	}
		
		if(request.getParameter("actualizar")!=null) {
		String codIsbn,titulo,editorial,autor;
		int paginas;
		codIsbn = request.getParameter("cod");
		titulo = request.getParameter("titulo");
		editorial = request.getParameter("editorial");
		autor = request.getParameter("autor");
		paginas = Integer.parseInt(request.getParameter("paginas"));
		LibroDTO libDto_Act = new LibroDTO(codIsbn,titulo,editorial,autor,paginas);
		if(libDao.Actualizar_Libro(libDto_Act)) {
			JOptionPane.showMessageDialog(null, "Libro se Actualizo Exitosamente.");
			response.sendRedirect("Libros.jsp?men=Libro Actualizado Exitosamente.");
		}else {
			JOptionPane.showMessageDialog(null, "El libro no se Modifico.");
			response.sendRedirect("Libros.jsp?men=El libro no se Modifico.");
		}
		}
		
		if(request.getParameter("eliminar")!=null) {
			String codIsbn;
			codIsbn = request.getParameter("cod");
			int op=JOptionPane.showConfirmDialog(null, "Desea Eliminar el Libro cod: "+codIsbn);
			if(op==0) {
			if(libDao.Eliminar_Libro(codIsbn)) {
				response.sendRedirect("Libros.jsp?men=Libro Eliminado");
			}else {
				response.sendRedirect("Libros.jsp?men=Libro no se elimino");
			}
			}else {
				response.sendRedirect("Libros.jsp");
			}
		}
		
		if(request.getParameter("cargar")!=null) {
			Part archivo=request.getPart("archivo");
			String nombre=request.getParameter("nombreArch");
			String Url=getServletContext().getRealPath("/Doc/");
			Url= Url.replace("Build\\","");
			JOptionPane.showMessageDialog(null, Url+nombre);
			//JOptionPane.showMessageDialog(null, archivo.getSubmittedFileName());
			//String Url="C:/Users/vivis/eclipse-workspace/Prestamos_31/src/main/webapp/Doc/";
			//Desde Mysql : show variables like 'secure_file_priv'
			//String Url="C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/";
			///JOptionPane.showMessageDialog(null, archivo.getContentType());
			if(archivo.getContentType().equals("application/vnd.ms-excel")) {
			try {
			InputStream file= archivo.getInputStream();	
			File copia = new File(Url+nombre+".csv");
			FileOutputStream escribir= new FileOutputStream(copia);
			int num=file.read();
			while(num != -1) {
				escribir.write(num);
				num=file.read();
			}
			escribir.close();
			file.close();
			JOptionPane.showMessageDialog(null, "Archivo Cargado Correctamente");
			if(libDao.Cargar_Libros(Url+nombre+".csv")) {
				response.sendRedirect("Libros.jsp?men=Libros Registrado Correctamente");
			}
			else {
				response.sendRedirect("Libros.jsp?men=Libros no se Registraron");
			}
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Error al cargar Archivo"+e);
			}
		}else {	response.sendRedirect("Libros.jsp?men=Formato de Archivo no permitido");}
		}
		
        //Formato JSON para cargar select, cuando cargue el jsp
		
		PrintWriter salida= response.getWriter();
		ArrayList<LibroDTO> lista=new ArrayList<>();
		lista=libDao.cargar_select();
		Gson datos= new Gson();
		salida.println(datos.toJson(lista));
		
	}
		}
		

