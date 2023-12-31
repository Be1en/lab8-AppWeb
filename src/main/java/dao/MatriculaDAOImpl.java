/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import modelos.Matriculas;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import modelos.*;
import java.util.Date;
/**
 *
 * @author valer
 */
public class MatriculaDAOImpl implements IMatriculaDAO{
   
    @Override
    public List<Matriculas> buscarMatriculas(Matriculas matricula){
        Connection co=null;
        Statement stm=null;
        ResultSet rs=null;
        String sql="SELECT m.codigo, m.fecha, m.nro_doc, m.total, m.estado, " +
                "(SELECT nombre FROM alumnos WHERE codigo = m.codigo_alumno) AS nombre_alumno " +
                    "FROM matriculas m " +
                    "WHERE m.nro_doc LIKE '%" + matricula.getNro_doc() + "%' ";
        List<Matriculas> listaMatriculas= new ArrayList<Matriculas>();
        try{
                Conexion con= new Conexion();
                co= con.Conectar();
                stm=co.createStatement();
                rs= stm.executeQuery(sql);
                while(rs.next()){
                    Matriculas nmatricula= new Matriculas();
                    nmatricula.setCodigo(rs.getInt(1));
                    nmatricula.setFecha(rs.getDate(2));
                    nmatricula.setNro_doc(rs.getString(3));
                    nmatricula.setNombreAlumno(rs.getString("nombre_alumno"));
                    nmatricula.setTotal(rs.getDouble(4));
                    nmatricula.setEstado(rs.getString(5));
                    listaMatriculas.add(nmatricula);
                }
                stm.close();
                rs.close();
                co.close();
        }catch(SQLException e){
                System.out.println("Error:Clase MatriculaDaoImpl,"
                    + "buscarMatriculas");
        }
        return listaMatriculas;
    }
    
    
    
     @Override
    public List<Matriculas> obtener(){
        Connection co=null;
        Statement stm=null;
        ResultSet rs=null;
        String sql = "SELECT m.codigo, m.fecha, m.nro_doc, m.total, m.estado, (SELECT nombre FROM alumnos WHERE codigo = m.codigo_alumno) "
                + "AS nombre_alumno FROM matriculas m ORDER BY m.codigo;";

        List<Matriculas> listaMatriculas= new ArrayList<Matriculas>();
        try{
            Conexion con = new Conexion();
            co=con.Conectar();
            stm=co.createStatement();
            rs=stm.executeQuery(sql);
            while(rs.next()){
                Matriculas matricula=new Matriculas();
                matricula.setCodigo(rs.getInt(1));
                matricula.setFecha(rs.getDate(2));
                matricula.setNro_doc(rs.getString(3));
                matricula.setNombreAlumno(rs.getString("nombre_alumno"));
                matricula.setTotal(rs.getDouble(4));
                matricula.setEstado(rs.getString(5));
                listaMatriculas.add(matricula);
            }
            stm.close();
            rs.close();
            co.close();
        } catch(SQLException e){
            System.out.println("Error:Clase MatriculaDaoImpl,"
                    + "método obtener");
        }
        return listaMatriculas;
    }
    
    
    @Override
    public List<Alumnos> buscarAlumnos(Alumnos alumno) {
        Connection co =null;
        Statement stm= null;
        ResultSet rs=null;
        String sql="SELECT * FROM alumnos WHERE nombre LIKE'%" + alumno.getNombre() + "%'";
        List<Alumnos> listaAlumnos= new ArrayList<Alumnos>();

        try {			
                Conexion con = new Conexion();
                co=con.Conectar();
                stm=co.createStatement();
                rs=stm.executeQuery(sql);
                while (rs.next()) {
                        Alumnos nalumno=new Alumnos();
                        nalumno.setCodigo(rs.getInt(1));
                        nalumno.setNombre(rs.getString(2));
                        nalumno.setDireccion(rs.getString(3));
                        nalumno.setEmail(rs.getString(4));
                        nalumno.setTelefono(rs.getString(5));
                        nalumno.setCelular(rs.getString(6));
                        nalumno.setSexo(rs.getString(7));
                        nalumno.setFec_nac(rs.getDate(8));
                        nalumno.setEstado(rs.getString(9));
                        listaAlumnos.add(nalumno);
                }
                stm.close();
                rs.close();
                co.close();
        } catch (SQLException e) {
                System.out.println("Error:Clase MatriculaDaoImpl,"
                        + "método buscarAlumnos");
        }
        return listaAlumnos;
    }
    
    @Override
    public List<Cursos> buscarCursos() {
        Connection co =null;
        Statement stm= null;
        ResultSet rs=null;
        String sql="SELECT * FROM cursos ORDER BY codigo";
        List<Cursos> listaCursos= new ArrayList<Cursos>();

        try {			
                Conexion con = new Conexion();
                co=con.Conectar();
                stm=co.createStatement();
                rs=stm.executeQuery(sql);
                while (rs.next()) {
                        Cursos curso=new Cursos();
                        curso.setCodigo(rs.getInt(1));
                        curso.setNombre(rs.getString(2));
                        curso.setCosto(rs.getInt(3));
                        curso.setFec_ini(rs.getDate(4));
                        curso.setFec_fin(rs.getDate(5));
                        curso.setDuracion(rs.getInt(6));
                        curso.setSesiones(rs.getInt(7));
                        curso.setCapacidad(rs.getInt(8));
                        curso.setInscritos(rs.getInt(9));
                        curso.setEstado(rs.getString(10));
                        listaCursos.add(curso);
                }
                stm.close();
                rs.close();
                co.close();
        } catch (SQLException e) {
                System.out.println("Error:Clase MatriculaDaoImpl,"
                        + "método obtener");
        }
        return listaCursos;
    }
    
    public String getFecha() {
        Calendar calendar = new GregorianCalendar();
        Date date = calendar.getTime();
        DateFormat formato = new SimpleDateFormat( "yyyy-MM-dd" );
        return formato.format( date ) ;
    }
    @Override
        public boolean grabarMatricula(String[] datosMatricula, String[] codigoCursos, String[] montos) {
            Conexion co = new Conexion();
            String xcodm = co.generarCodigo("matriculas","codigo");
            boolean registrar = true;
            Statement stm= null;
            Connection con=null;      

            String sql = "insert into matriculas (codigo,fecha,nro_doc," +
                     "codigo_alumno,total,estado) values (?,?,?,?,?,'A') ";
            String xfech = this.getFecha();
            String xndoc = datosMatricula[0];
            String xcoda = datosMatricula[1];
            String xtota = datosMatricula[2];

            try {			    
                con=co.Conectar();
                stm= con.createStatement();
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, xcodm);
                ps.setString(2, xfech);
                ps.setString(3, xndoc);
                ps.setString(4, xcoda);
                ps.setString(5, xtota);
                ps.executeUpdate();

                for( int xc=0 ; xc < codigoCursos.length ; xc++ ){
                    String xcodCurso = codigoCursos[xc];
                    this.grabarNuevoDetalle(con,xcodm,xcodCurso,montos[xc]);
                }


                stm.close();
                con.close();
            } catch (SQLException e) {
                    System.out.println("Error: Clase MatriculaDaoImpl, "
                            + "método grabarMatricula");
                    e.printStackTrace();
                    return false;
            }
            return registrar;
        }

        public void grabarNuevoDetalle( Connection xcon,String xcodm, String xcodc,String xmonto ) 
            throws SQLException {
            String sql = "insert into detalles (codigo_matricula,codigo_curso," +
                         "monto,asistencias,nota,estado) values (?,?,?,0,0,'A') ";

            PreparedStatement ps = xcon.prepareStatement(sql);
            ps.setString(1, xcodm);
            ps.setString(2, xcodc);
            ps.setString(3, xmonto);
            ps.executeUpdate();

            // actualizar nro de inscritos en curso
            sql = "update cursos set inscritos=inscritos+1 where codigo=?";
            PreparedStatement psc = xcon.prepareStatement(sql);
            psc.setString(1, xcodc);
            psc.executeUpdate();
        }


}
