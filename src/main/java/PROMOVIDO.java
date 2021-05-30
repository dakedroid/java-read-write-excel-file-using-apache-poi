/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dakedroid
 */
public class PROMOVIDO {
    
   String nombres;
   String paterno;
   String materno;
   String seccion;

    public PROMOVIDO(String nombres, String paterno, String materno, String seccion) {
        this.nombres = nombres;
        this.paterno = paterno;
        this.materno = materno;
        this.seccion = seccion;
    }

   
    public PROMOVIDO() {
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }
   
   
   
    
}
