package modelo;

public class Saida extends Evento {

	public Saida(Entidade chamada, long tempo) {
		super(chamada, tempo);
	}

	@Override
	public Evento executar() {
                Server serv = Server.getInstance();
                Evento evento = null;
                //saída do tipo 1 com ninguem na fila -> desocupa servidor
              if(this.getTempo() < Simulador.tempoSimulacao){  
                if(this.getEntidade().getTipo() == Tipo.TIPO1 && serv.getFila1().isEmpty() && this.getEntidade().getTrocou() == false){
                    serv.desocuparServer1();
                    coletarEstatisticas1();                 
                } else if(this.getEntidade().getTipo() == Tipo.TIPO1 && !serv.getFila1().isEmpty() && this.getEntidade().getTrocou() == false){
                    Entidade ent = serv.fila1.remove(0);
                    evento = new Saida(ent,(ent.getDuracao()+getTempo()));
                    System.out.println("Saida TIPO 1 FILA:"+(ent.getDuracao()+getTempo()));
                    coletarEstatisticas1();                    
                } else if(this.getEntidade().getTipo() == Tipo.TIPO2 && serv.getFila2().isEmpty() && this.getEntidade().getTrocou() == false){
                   serv.desocuparServer2();
                   coletarEstatisticas2();
                } else if(this.getEntidade().getTipo() == Tipo.TIPO2 && !serv.getFila2().isEmpty() && this.getEntidade().getTrocou() == false){
                    Entidade ent = serv.fila2.remove(0);
                    evento = new Saida(ent,(ent.getDuracao()+getTempo()));
                    System.out.println("Saida TIPO 2 FILA:"+(ent.getDuracao()+getTempo()));
                    coletarEstatisticas2();
                } else if (this.getEntidade().getTipo() == Tipo.TIPO1 && serv.getFila2().isEmpty() && this.getEntidade().getTrocou() == true){
	           serv.desocuparServer2();
                    coletarEstatisticas1();
                } else if (this.getEntidade().getTipo() == Tipo.TIPO1 && !serv.getFila2().isEmpty() && this.getEntidade().getTrocou() == true){
                    Entidade ent = serv.fila2.remove(0);
                    evento = new Saida(ent,(ent.getDuracao()+getTempo()));
                    coletarEstatisticas1();
                } else if (this.getEntidade().getTipo() == Tipo.TIPO2 && serv.getFila1().isEmpty() && this.getEntidade().getTrocou() == true){
                    serv.desocuparServer1();
                    coletarEstatisticas2();
                }  else if (this.getEntidade().getTipo() == Tipo.TIPO2 && !serv.getFila1().isEmpty() && this.getEntidade().getTrocou() == true){
                    Entidade ent = serv.fila1.remove(0);
                    evento = new Saida(ent,(ent.getDuracao()+getTempo()));
                    coletarEstatisticas2();
              }
              }      
		return evento;
	}
        
        public void coletarEstatisticas1() {
            Estatistica.getInstance().decrementarEntidadesNoSistema();
            Estatistica.getInstance().incrementarEntidades1();
            int tempoNaFila = 0;
            if(this.getEntidade().getFila() == true){
            Estatistica.getInstance().incrementarEntidadesNaFila1();
             tempoNaFila =(int) ((this.getTempo()-this.getEntidade().getDuracao())-(this.getEntidade().getTempoEmQueSeRealizou()));
            Estatistica.getInstance().atualizartempoNaFila(tempoNaFila);
            
            }
            int tempoNoSistema = (int)(this.getTempo()-this.getEntidade().getTempoEmQueSeRealizou());
            Estatistica.getInstance().atualizarTempoSistema1(tempoNoSistema);
            
        }
        
        public void coletarEstatisticas2() {
            Estatistica.getInstance().decrementarEntidadesNoSistema();
            Estatistica.getInstance().incrementarEntidades2();
            int tempoNaFila = 0;
            if(this.getEntidade().getFila() == true){
            Estatistica.getInstance().incrementarEntidadesNaFila2();
             tempoNaFila =(int) ((this.getTempo()-this.getEntidade().getDuracao())-(this.getEntidade().getTempoEmQueSeRealizou()));
            Estatistica.getInstance().atualizartempoNaFila2(tempoNaFila);
            }
            int tempoNoSistema = (int)(this.getTempo()-this.getEntidade().getTempoEmQueSeRealizou());
            Estatistica.getInstance().atualizarTempoSistema2(tempoNoSistema);
        }


    @Override
    public Evento executar(ListaEncadeadaOrdenada<Evento> eventos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}



