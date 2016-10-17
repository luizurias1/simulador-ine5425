/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Luiz
 */
public class Entidade {
    
        private long duracao;
	private long tempoEmQueSeRealizou;
        private Tipo tipo;
        private boolean fila = false;
        private boolean trocou = false;
    public Entidade(long duracao, long tempoEmQueSeRealizou, Tipo t) {
        this.duracao = duracao;
        this.tempoEmQueSeRealizou = tempoEmQueSeRealizou;
        this.tipo = t;
    }

    public boolean getFila() {
        return fila;
    }

    public boolean getTrocou() {
        return trocou;
    }

    public void setTrocou(boolean trocou) {
        this.trocou = trocou;
    }

    public void setFila(boolean fila) {
        this.fila = fila;
    }

    public long getDuracao() {
        return duracao;
    }

    public void setDuracao(long duracao) {
        this.duracao = duracao;
    }

    public long getTempoEmQueSeRealizou() {
        return tempoEmQueSeRealizou;
    }

    public void setTempoEmQueSeRealizou(long tempoEmQueSeRealizou) {
        this.tempoEmQueSeRealizou = tempoEmQueSeRealizou;
    }



    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
        
        
}
