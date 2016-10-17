/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author luiz
 */
public class FalhaConsertada extends Evento {

    
     public FalhaConsertada(Entidade chamada, long tempo) {
		super(chamada, tempo);
	}
     
    @Override
    public Evento executar() {
        Evento e = null;
        Server s = Server.getInstance();
        if(tempo < Simulador.tempoSimulacao){
        if(this.getEntidade().getTipo() == Tipo.TIPO1){
            s.setServer1(EstadoServidor.LIVRE);
        Estatistica.getInstance().inicioTempoFalha1((int)(tempo-this.getEntidade().getTempoEmQueSeRealizou()));
        Estatistica.getInstance().incrementarQuantidadeFalha1();
        }else if(this.getEntidade().getTipo() == Tipo.TIPO2){
            s.setServer2(EstadoServidor.LIVRE);
        Estatistica.getInstance().inicioTempoFalha2((int)(tempo-this.getEntidade().getTempoEmQueSeRealizou()));
        Estatistica.getInstance().incrementarQuantidadeFalha2();

        }
        }
        return e;    
    }

    @Override
    public Evento executar(ListaEncadeadaOrdenada<Evento> eventos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
