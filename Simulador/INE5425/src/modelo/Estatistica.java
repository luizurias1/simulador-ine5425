package modelo;


public class Estatistica {

	// Bloco apenas contador
	private int contadorDeEntidadeTotal;
        private int contadorDeEntidadesNoSistema;
        private int contadorDeEntidades1;
        private int contadorDeEntidades2;
        private int inicioNoSistema1;
        private int inicioNoSistema2;
        private int quantidadeNaFila1;
        private int quantidadeNaFila2;
        private int tempoNaFila1;
         private int tempoNaFila2;
        private int tempoFalha1;
        private int tempoFalha2;
        private double mediaTempoNaFila1;
        private double mediaTempoNaFila2;
        private double mediaTempoNoSistema1;
        private double mediaTempoNoSistema2;
        private int trocarDe1Pro2;
        private int trocarDe2Pro1;
        private int quantidadeDefalha1;
        private int quantidadeDefalha2;
        
    public int getTrocarDe1Pro2() {
        return trocarDe1Pro2;
    }

    public void setTrocarDe1Pro2(int trocarDe1Pro2) {
        this.trocarDe1Pro2 = trocarDe1Pro2;
    }

    public int getTrocarDe2Pro1() {
        return trocarDe2Pro1;
    }

    public void setTrocarDe2Pro1(int trocarDe2Pro1) {
        this.trocarDe2Pro1 = trocarDe2Pro1;
    }

    public int getQuantidadeDefalha1() {
        return quantidadeDefalha1;
    }

    public void setQuantidadeDefalha1(int quantidadeDefalha1) {
        this.quantidadeDefalha1 = quantidadeDefalha1;
    }

    public int getQuantidadeDefalha2() {
        return quantidadeDefalha2;
    }

    public void setQuantidadeDefalha2(int quantidadeDefalha2) {
        this.quantidadeDefalha2 = quantidadeDefalha2;
    }


    public int getTempoFalha1() {
        return tempoFalha1;
    }

    public void setTempoFalha1(int tempoFalha1) {
        this.tempoFalha1 = tempoFalha1;
    }

    public int getTempoFalha2() {
        return tempoFalha2;
    }

    public void setTempoFalha2(int tempoFalha2) {
        this.tempoFalha2 = tempoFalha2;
    }

    public int getContadorDeEntidadeTotal() {
        return contadorDeEntidadeTotal = contadorDeEntidades1+contadorDeEntidades2;
    }

    public void setContadorDeEntidadeTotal(int contadorDeEntidadeTotal) {
        this.contadorDeEntidadeTotal = contadorDeEntidadeTotal;
    }

    public int getContadorDeEntidadesNoSistema() {
        return contadorDeEntidadesNoSistema;
    }

    public void setContadorDeEntidadesNoSistema(int contadorDeEntidadesNoSistema) {
        this.contadorDeEntidadesNoSistema = contadorDeEntidadesNoSistema;
    }

    public int getContadorDeEntidades1() {
        return contadorDeEntidades1;
    }

    public void setContadorDeEntidades1(int contadorDeEntidades1) {
        this.contadorDeEntidades1 = contadorDeEntidades1;
    }

    public int getContadorDeEntidades2() {
        return contadorDeEntidades2;
    }

    public void setContadorDeEntidades2(int contadorDeEntidades2) {
        this.contadorDeEntidades2 = contadorDeEntidades2;
    }




    public double getMediaTempoNoSistema1() {
        if(contadorDeEntidades1 == 0)
                return 0;
        return mediaTempoNoSistema1 = inicioNoSistema1/contadorDeEntidades1;
    }

    public double getMediaTempoNoSistema2() {
        if(contadorDeEntidades2 == 0)
                return 0;
        return mediaTempoNoSistema2 =  inicioNoSistema2/contadorDeEntidades2;
    }

	private static Estatistica instance;

	private Estatistica() {
          contadorDeEntidadeTotal = 0;
          contadorDeEntidadesNoSistema = 0;
          contadorDeEntidades1 = 0;
          contadorDeEntidades2 = 0;
          inicioNoSistema1 = 0;
          inicioNoSistema2 = 0;
          quantidadeNaFila1 = 0;
          tempoNaFila1 = 0;
          mediaTempoNaFila1 = 0;
          mediaTempoNoSistema1 = 0;
          mediaTempoNoSistema2 = 0;
	}

	public static Estatistica getInstance() {
		if (instance == null) {
			instance = new Estatistica();
		}
		return instance;
	}

   

	
        public void atualizarTempoSistema1(int tempo){
            this.inicioNoSistema1+=tempo;
            System.out.println("INICIO TEMPO NO SISTEMA: "+inicioNoSistema1);
        }
        public void inicioTempoFalha1(int tempo){
            this.tempoFalha1+=tempo;
        }

        public void inicioTempoFalha2(int tempo){
            this.tempoFalha2+=tempo;
        }

        public void atualizarTempoSistema2(int tempo){
            this.inicioNoSistema2+=tempo;
        }

        public void incrementarEntidadesTotais() {
		this.contadorDeEntidadeTotal++;
	}
        public void incrementarTroca1() {
		this.trocarDe1Pro2++;
	}
        public void incrementarTroca2() {
		this.trocarDe2Pro1++;
	}
        public void incrementarEntidades1() {
		this.contadorDeEntidades1++;
	}
        public void incrementarEntidades2() {
		this.contadorDeEntidades2++;
	}
        public void incrementarEntidadesNaFila1() {
		this.quantidadeNaFila1++;
	}
        public void incrementarEntidadesNaFila2() {
		this.quantidadeNaFila2++;
	}
        public void atualizartempoNaFila(int tempo) {
		this.tempoNaFila1+= tempo;
	}
        public void fimtempoNaFila(int tempo) {
		this.tempoNaFila1= tempo - tempoNaFila1;
                if(tempoNaFila1 < 0)
                    tempoNaFila1=-tempoNaFila1;
	}
        public void atualizartempoNaFila2(int tempo) {
		this.tempoNaFila2+= tempo;
	}
        public void fimtempoNaFila2(int tempo) {
		this.tempoNaFila2= tempo - tempoNaFila2;
                if(tempoNaFila2 < 0)
                    tempoNaFila2=-tempoNaFila2;
	}
	public void decrementarEntidadesTotais() {
		this.contadorDeEntidadeTotal--;
	}
        public void incrementarEntidadesNoSistema() {
		this.contadorDeEntidadesNoSistema++;
	}
        public void decrementarEntidadesNoSistema() {
		this.contadorDeEntidadesNoSistema--;
	}
	
        public double getMediaTempoNaFila1() {
            if(quantidadeNaFila1 == 0)
                return 0;
	return (mediaTempoNaFila1 = tempoNaFila1/quantidadeNaFila1);
	}
        public double getMediaTempoNaFila2() {
            if(quantidadeNaFila2 == 0)
                return 0;
	return (mediaTempoNaFila2 = tempoNaFila2/quantidadeNaFila2);
	}
	
    void incrementarQuantidadeFalha1() {
            this.quantidadeDefalha1++;
        }

    void incrementarQuantidadeFalha2() {
             this.quantidadeDefalha2++;  
    }

}
