package Controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.LibroDTO;

/**
 * Servlet implementation class Estudiante
 */
@WebServlet("/Estudiante")
public class Estudiante extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Estudiante() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("registrar") != null) {
			String codIsbn, titulo, editorial, autor;
			int paginas;
			
			codIsbn = request.getParameter("isbn");
			titulo = request.getParameter("titulo");
			editorial = request.getParameter("editorial");
			autor = request.getParameter("autor");
			paginas = Integer.parseInt(request.getParameter("paginas"));
			
			LibroDTO libDto = new LibroDTO(codIsbn, titulo, editorial, autor, paginas);
			if(libDao.Inserta_Libro(libDto)) {
				// JOptionPane.showMessageDialog(null, "Libro Registrado Exitosamente.");
				response.sendRedirect("Libros.jsp?men=Libro Registrado Exitosamente.");
			} else {
				// JOptionPane.showMessageDialog(null, "El libro no se registro.");
				response.sendRedirect("Libros.jsp?men=El libro no se registro.");
			}
		}
	}

}
