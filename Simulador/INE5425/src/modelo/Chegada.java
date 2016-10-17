package modelo;


public class Chegada extends Evento {

	public Chegada(Entidade chamada, long tempo) {
		super(chamada, tempo);
	}

	@Override
	public Evento executar() {
            Server serv = Server.getInstance();
            Evento evento = null;
            //servidor livre e tipo1 -> ocupa o servidor
            if(serv.getServer1() == EstadoServidor.LIVRE && this.getEntidade().getTipo() == Tipo.TIPO1){
		serv.ocuparServer1();
                evento = new Saida(this.getEntidade(),(this.getEntidade().getDuracao()+getTempo()));
                evento.getEntidade().setTempoEmQueSeRealizou(getTempo());
                System.out.println("Saida TIPO 1:"+(this.getEntidade().getDuracao()+getTempo()));
                 Estatistica.getInstance().incrementarEntidadesNoSistema();
                return evento;
            } if(serv.getServer1() == EstadoServidor.OCUPADO && this.getEntidade().getTipo() == Tipo.TIPO1){
                serv.fila1.add(getEntidade());
                this.getEntidade().setTempoEmQueSeRealizou(getTempo());
                this.getEntidade().setFila(true); 
                Estatistica.getInstance().incrementarEntidadesNoSistema();
                return evento;
            } if(serv.getServer2() == EstadoServidor.LIVRE && this.getEntidade().getTipo() == Tipo.TIPO2){
                serv.ocuparServer2();
                evento = new Saida(this.getEntidade(), (this.getEntidade().getDuracao()+getTempo()));
                evento.getEntidade().setTempoEmQueSeRealizou(getTempo());
                System.out.println("Saida TIPO 2:"+(this.getEntidade().getDuracao()+getTempo()));
                Estatistica.getInstance().incrementarEntidadesNoSistema();
                return evento;
            } if(serv.getServer2() == EstadoServidor.OCUPADO && this.getEntidade().getTipo() == Tipo.TIPO2){
                serv.fila2.add(getEntidade());
                this.getEntidade().setTempoEmQueSeRealizou(getTempo());
                this.getEntidade().setFila(true);
                Estatistica.getInstance().incrementarEntidadesNoSistema();
                return evento;
            }  if(serv.getServer1() == EstadoServidor.FALHA && this.getEntidade().getTipo() == Tipo.TIPO1){
                if(serv.getServer2()== EstadoServidor.LIVRE){
                    serv.ocuparServer2();
                    evento = new Saida(this.getEntidade(), (this.getEntidade().getDuracao()+getTempo()));
                    evento.getEntidade().setTempoEmQueSeRealizou(getTempo()); 
                    evento.getEntidade().setTrocou(true);
                    Estatistica.getInstance().incrementarTroca1();
                    Estatistica.getInstance().incrementarEntidadesNoSistema();
                    return evento;
                } if(serv.getServer2()== EstadoServidor.OCUPADO){
                    serv.fila2.add(getEntidade());
                    this.getEntidade().setTempoEmQueSeRealizou(getTempo());
                    this.getEntidade().setFila(true);
                    this.getEntidade().setTrocou(true);
                    Estatistica.getInstance().incrementarTroca1();
                    Estatistica.getInstance().incrementarEntidadesNoSistema();
                    return evento;
                } } if(serv.getServer2() == EstadoServidor.FALHA && this.getEntidade().getTipo() == Tipo.TIPO2){
                if(serv.getServer1()== EstadoServidor.LIVRE){
                    serv.ocuparServer1();
                    evento = new Saida(this.getEntidade(), (this.getEntidade().getDuracao()+getTempo()));
                    evento.getEntidade().setTempoEmQueSeRealizou(getTempo());
                    evento.getEntidade().setTrocou(true);
                    Estatistica.getInstance().incrementarTroca2();
                    Estatistica.getInstance().incrementarEntidadesNoSistema();
                    return evento;
                } if(serv.getServer1()== EstadoServidor.OCUPADO){
                    serv.fila2.add(getEntidade());
                    this.getEntidade().setTempoEmQueSeRealizou(getTempo());
                    this.getEntidade().setFila(true);  
                    this.getEntidade().setTrocou(true);
                    Estatistica.getInstance().incrementarTroca2();
                    Estatistica.getInstance().incrementarEntidadesNoSistema();
                    return evento;
                }
                }
            
	
       return evento;

        }
	// TODO: Pensar em como gerar as estatisticas


    @Override
    public Evento executar(ListaEncadeadaOrdenada<Evento> eventos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
