package modelo;

import modelo.Entidade;
import modelo.Evento;

public abstract class Evento implements Comparable<Evento> {

	private Entidade entidade;
	protected long tempo;
	public Evento(Entidade chamada, long tempo) {
		this.entidade = chamada;
		this.tempo = tempo;
	}
	public Entidade getEntidade() {
		return entidade;
	}

	public long getTempo() {
		return tempo;
	}

	@Override
	public int compareTo(Evento o) {
		long outroTempo = o.getTempo();
		if (tempo < outroTempo) {
			return -1;
		} else if (tempo == outroTempo) {
			return 0;
		} else {
			return 1;
		}
	}

	public abstract Evento executar();
        public abstract Evento executar(ListaEncadeadaOrdenada<Evento> eventos);
}
