package modelo;

import java.util.Random;
import org.apache.commons.math3.distribution.EnumeratedRealDistribution;
import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.distribution.TriangularDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;

public class Gerador {

	private final Random aleatorio;
	private final RealDistribution tec;
        private final RealDistribution tec2;
        private final RealDistribution ts;
        private final RealDistribution ts2;
        private final RealDistribution falhatec1;
        private final RealDistribution falhats1;
        private final RealDistribution falhatec2;
        private final RealDistribution falhats2;
        private  double chance;
    public RealDistribution getTec() {
        return tec;
    }

    public RealDistribution getTec2() {
        return tec2;
    }

    public RealDistribution getTs() {
        return ts;
    }

    public RealDistribution getTs2() {
        return ts2;
    }

	public Gerador(String tec,
			double parametro1, double parametro2, double parametro3,
                        String ts,
			double parametro12, double parametro11, double parametro10,
                        String tec2,
			double parametro13, double parametro14, double parametro15,
                        String ts2,
			double parametro18, double parametro17, double parametro16,
                        String falhatec1,
			double parametro31, double parametro32, double parametro33,
                        String falhats1,
			double parametro36, double parametro35, double parametro34,
                        String falhatec2,
			double parametro4, double parametro5, double parametro6,
                        String falhats2,
			double parametro21, double parametro20, double parametro19,
                        int chance) {
		super();
		aleatorio = new Random();
		this.tec = setFuncaoDeProbabilidade(
				tec, parametro1, parametro2, parametro3);
                this.tec2 = setFuncaoDeProbabilidade(
				tec2, parametro13, parametro14, parametro15);
                this.ts = setFuncaoDeProbabilidade(
				ts, parametro12, parametro11, parametro10);
                this.ts2 = setFuncaoDeProbabilidade(
				ts2, parametro18, parametro17, parametro16);
                this.falhatec1 = setFuncaoDeProbabilidade(
                        falhatec1, parametro31, parametro32, parametro33);
                this.falhatec2 = setFuncaoDeProbabilidade(
                        falhatec2, parametro4, parametro5, parametro6);
                this.falhats1 = setFuncaoDeProbabilidade(falhats1, parametro36, parametro35, parametro34);
                this.falhats2 = setFuncaoDeProbabilidade(falhats2, parametro21, parametro20, parametro19);
                this.chance = chance;
        }

	private RealDistribution setFuncaoDeProbabilidade(
			String funcaoDeProbabilidade, double parametro1, double parametro2,
			double parametro3) {

		double[] valores = { parametro1 };
		double[] probabilidades = { 1 };

		switch (funcaoDeProbabilidade) {
		case "Normal":
			return new NormalDistribution(parametro1, parametro2);
		case "Uniforme":
			return new UniformRealDistribution(parametro1, parametro2);
		case "Exponencial":
			return new ExponentialDistribution(parametro1);
		case "Triangular":
			return new TriangularDistribution(parametro1, parametro2,
					parametro3);
		default:
			return new EnumeratedRealDistribution(valores, probabilidades);
		}
	}

	public Entidade gerarEntidade(long tempo) {
                double qualEntidade = chance/100;
		double rand = Math.random();
                Entidade ent;
                if(rand < qualEntidade){
                    int duracao = (int) ts.sample();
                    ent = new Entidade(duracao, tempo, Tipo.TIPO1);
                }else{
                    int duracao = (int) ts2.sample();
                    ent = new Entidade(duracao,tempo,Tipo.TIPO2);
                }
                return ent;
	}
        
        public Entidade gerarFalha1(long tempo) {
                Entidade ent;
                int duracao = (int) falhats1.sample();
                ent = new Entidade(duracao, tempo, Tipo.TIPO1);
                
                return ent;
	}
  public Entidade gerarFalha2(long tempo) {
                Entidade ent;
                int duracao = (int) falhats2.sample();
                ent = new Entidade(duracao, tempo, Tipo.TIPO2);
                
                return ent;
	}
	public Chegada gerarProximaChegada(long tempo) {
		Entidade proximaChegadaC1;
		Entidade ent = gerarEntidade(tempo);
                Chegada c;
                if(ent.getTipo() == Tipo.TIPO1){
                    int tempoChegada = (int) tec.sample();
                    c = new Chegada(ent,(tempoChegada+tempo));
                    System.out.println("CHEGADA UM: "+tempoChegada+"+++++"+tempo);
                }else{
                    int tempoChegada = (int) tec2.sample();
                    c = new Chegada(ent,(tempoChegada+tempo));
                    System.out.println("CHEGADA DOIS: "+tempoChegada+"++++"+tempo);
                }
	return c;
	}
        
        public Falha gerarProximaFalha1(long tempo, ListaEncadeadaOrdenada<Evento> eventos) {
		Falha proximaFalha;
                Server s = Server.getInstance();
		Entidade ent = gerarFalha1(tempo);
                Falha c = null;
                if(ent.getTipo() == Tipo.TIPO1){
                    int tempoProxFalha = (int) falhatec1.sample();
                    int tempoFalha = (int)(tempoProxFalha+tempo);
                    c = new Falha(ent,tempoFalha);
                }
                
	return c;
	}
 public Falha gerarProximaFalha2(long tempo, ListaEncadeadaOrdenada<Evento> eventos) {
		Falha proximaFalha;
                Server s = Server.getInstance();
		Entidade ent = gerarFalha2(tempo);
                Falha c = null;
                if(ent.getTipo() == Tipo.TIPO2){
                    int tempoProxFalha = (int) falhatec2.sample();
                    c = new Falha(ent,(tempoProxFalha+tempo));
                }
                
	return c;
	}
    public Random getAleatorio() {
        return aleatorio;
    }

    public RealDistribution getDistribuicaoDuracaoChamada() {
        return tec;
    }


}
