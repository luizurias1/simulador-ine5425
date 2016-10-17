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
public class Falha extends Evento {

  
    public Falha(Entidade chamada, long tempo) {
		super(chamada, tempo);
	}
    
    @Override
    public Evento executar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      
    }

    @Override
    public Evento executar(ListaEncadeadaOrdenada<Evento> eventos) {
         Evento e = null;
        Server s = Server.getInstance();
        ListaEncadeadaOrdenada<Evento> temp = (ListaEncadeadaOrdenada<Evento>) eventos.clone();
        if(this.getEntidade().getTipo() == Tipo.TIPO1){
            s.setServer1(EstadoServidor.FALHA);
            int tempoFalhaConsertada = (int)(this.getEntidade().getDuracao()+this.getTempo());
            this.getEntidade().setTempoEmQueSeRealizou(tempo);
            e = new FalhaConsertada(this.getEntidade(),tempoFalhaConsertada);
              for(Evento eventos1 : temp){
                        if(eventos1.getClass().getSimpleName().equalsIgnoreCase("Saida")){
                          if(eventos1.getEntidade().getTipo() == Tipo.TIPO1){
                            if(eventos1.getTempo() < tempoFalhaConsertada){
                             int tempoQueFalta = (int)(eventos1.getTempo()-tempo);
                             Evento novaSaida = new Saida(eventos1.getEntidade(),tempoFalhaConsertada+tempoQueFalta);
                             eventos.adicionarOrdenado(novaSaida);
                             eventos.remove(eventos1);
                            }
                           }
                        }
                    }
           return e;
        } else if(this.getEntidade().getTipo() == Tipo.TIPO2){
            s.setServer2(EstadoServidor.FALHA);
            int tempoFalhaConsertada = (int)(this.getEntidade().getDuracao()+this.getTempo());
            e = new FalhaConsertada(this.getEntidade(),this.getEntidade().getDuracao()+this.getTempo());
            this.getEntidade().setTempoEmQueSeRealizou(tempo);
                for(Evento eventos1 : temp){
                        if(eventos1.getClass().getSimpleName().equalsIgnoreCase("Saida")){
                           if(eventos1.getEntidade().getTipo() == Tipo.TIPO2){
                             if(eventos1.getTempo() < tempoFalhaConsertada){
                             int tempoQueFalta = (int)(tempo - eventos1.getTempo());
                             Evento novaSaida = new Saida(eventos1.getEntidade(),tempoFalhaConsertada+tempoQueFalta);
                             eventos.adicionarOrdenado(novaSaida);
                             eventos.remove(eventos1);
                            }
                            }
                        }
                    }
                return e;
        }
                
    
        return e;    
}
}


