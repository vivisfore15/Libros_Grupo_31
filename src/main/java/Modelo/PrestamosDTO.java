package Modelo;

public class PrestamosDTO {

	private int Id_prestamo;
	private String Estudiante;
	private String Libro;
	private String Fecha_Prestamo;
	private String Fecha_Devolucion;
	private int Sancion;
	
	
	public PrestamosDTO(String estudiante, String libro, String fecha_Prestamo) {
		Estudiante = estudiante;
		Libro = libro;
		Fecha_Prestamo = fecha_Prestamo;
	}


	public int getId_prestamo() {
		return Id_prestamo;
	}


	public void setId_prestamo(int id_prestamo) {
		Id_prestamo = id_prestamo;
	}


	public String getEstudiante() {
		return Estudiante;
	}


	public void setEstudiante(String estudiante) {
		Estudiante = estudiante;
	}


	public String getLibro() {
		return Libro;
	}


	public void setLibro(String libro) {
		Libro = libro;
	}


	public String getFecha_Prestamo() {
		return Fecha_Prestamo;
	}


	public void setFecha_Prestamo(String fecha_Prestamo) {
		Fecha_Prestamo = fecha_Prestamo;
	}


	public String getFecha_Devolucion() {
		return Fecha_Devolucion;
	}


	public void setFecha_Devolucion(String fecha_Devolucion) {
		Fecha_Devolucion = fecha_Devolucion;
	}


	public int getSancion() {
		return Sancion;
	}


	public void setSancion(int sancion) {
		Sancion = sancion;
	}
	
	
	
	
	
}
