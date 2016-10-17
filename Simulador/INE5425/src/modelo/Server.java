/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author Luiz
 */
public class Server   {
    
    EstadoServidor server1;
    EstadoServidor server2;
    private static Server instance;
    ArrayList<Entidade> fila2 = new ArrayList<Entidade>();
    ArrayList<Entidade> fila1 = new ArrayList<Entidade>();

    private Server(EstadoServidor server1, EstadoServidor server2) {
        this.server1 = server1;
        this.server2 = server2;
    }

  public static Server getInstance() {
		if (instance == null) {
			instance = new Server(EstadoServidor.LIVRE,EstadoServidor.LIVRE);
		}
         return instance;
	}

    public ArrayList<Entidade> getFila2() {
        return fila2;
    }

    public void setFila2(ArrayList<Entidade> fila2) {
        this.fila2 = fila2;
    }

    public ArrayList<Entidade> getFila1() {
        return fila1;
    }

    public void setFila1(ArrayList<Entidade> fila1) {
        this.fila1 = fila1;
    }
  
  public void ocuparServer1(){
      this.server1 = EstadoServidor.OCUPADO;
  }
   public void ocuparServer2(){
      this.server2 = EstadoServidor.OCUPADO;
  }
   public void desocuparServer1(){
      this.server1 = EstadoServidor.LIVRE;
  }
   public void desocuparServer2(){
      this.server2 = EstadoServidor.LIVRE;
  }

    public EstadoServidor getServer1() {
        return server1;
    }

    public void setServer1(EstadoServidor server1) {
        this.server1 = server1;
    }

    public EstadoServidor getServer2() {
        return server2;
    }

    public void setServer2(EstadoServidor server2) {
        this.server2 = server2;
    }
   
}
