package org.example;

import java.text.SimpleDateFormat;
import java.util.*;
public class RegistroAsistencia {

   static List<Persona> p= new ArrayList<>();
   static ArrayList<Persona> pp= new ArrayList<>();
    public static void main(String[] args) {
        registrarAsistencia();
        mostrarAsistencia();
    }
    public static void registrarAsistencia(){
        Date fechaReg = new Date();
        SimpleDateFormat fecha = new  SimpleDateFormat("dd 'de' MM 'del' yyyy", new Locale("es","PE"));
        String fechafor= fecha.format(fechaReg);
        Scanner cs=new Scanner(System.in);
        String exisAlumnos="";
        System.out.println("Registrar asistencia a curso POO");
        do{
            System.out.println("ingres nombre Estudiante:");
            String nombre=cs.nextLine();
            System.out.print("\n");
            System.out.println("ingrese P=Presente, F=Falta:");
            String estado=cs.nextLine();
            System.out.println(fecha.format(fechaReg));
            System.out.print("\n");
            p.add(new Persona(nombre, fechafor, estado, new Date()));
            System.out.println("existe más alumnos? S=SI, N=NO");
            exisAlumnos=cs.nextLine();
        }while(exisAlumnos.toUpperCase().equals("S"));
    }
    public static void mostrarAsistencia(){
        System.out.println("Alamcenado en una LIST:");
        for(Persona p:p){
            System.out.println(p.toString());
        }
    }


}
