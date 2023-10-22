/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import modelos.Matriculas;
import java.util.List;
import modelos.*;

/**
 *
 * @author valer
 */
public interface IMatriculaDAO {
    public List<Matriculas> buscarMatriculas(Matriculas matricula);
    public List<Matriculas>obtener();
    public List<Alumnos> buscarAlumnos(Alumnos alumno);
    public List<Cursos> buscarCursos();
    public boolean grabarMatricula(String[] datosMatricula, String[] codigoCursos, String[] montos);

}
