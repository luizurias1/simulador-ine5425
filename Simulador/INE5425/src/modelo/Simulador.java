/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.SysexMessage;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import visao.Init;
import visao.TelaDeExecucao;

/**
 *
 * @author Luiz
 */
public class Simulador {
    static boolean passoApasso;
    static Relogio relogio;
    static Gerador gerador;
    static Estatistica estatistica;
    static ListaEncadeadaOrdenada<Evento> eventos;
    static String distribuicaoDuracaoTEC;
    static String distribuicaoTS;
    static String distribuicaoTEC2;
    static String distribuicaoTS2;
    static String falhaTEC;
    static String falhaTS;
    static String falhaTEC2;
    static String falhaTS2;
    static double parametro1;
    static double parametro2;
    static double parametro3;
    static double parametro12;
    static double parametro11;
    static double parametro10;
    static double parametro13;
    static double parametro14;
    static double parametro15;
    static double parametro16;
    static double parametro17;
    static double parametro18;
    static  double parametro31;
     static    double parametro32;
     static    double parametro33;
     static    double parametro36;
     static    double parametro35;
        static double parametro34;
        static double parametro4;
        static double parametro5;
        static double parametro6;
        static double parametro21;
        static double parametro20;
        static double parametro19;
    static int tempoSimulacao;
    static int chance;
    static Init telaInicial;
    static TelaDeExecucao telaDeExecucao;
    static boolean encerrar;
    static boolean pausado = false;
    	private static int numeroDePassosDeExecucao;


    static private void setarVariaveisVisao() {
        passoApasso = telaInicial.getPassoApasso();
        parametro1 = telaInicial.getParametro1();
        parametro2 = telaInicial.getParametro2();
        parametro3 = telaInicial.getParametro3();
        parametro12 = telaInicial.getParametro12();
        parametro11 = telaInicial.getParametro11();
        parametro10 = telaInicial.getParametro10();
        parametro13 = telaInicial.getParametro13();
        parametro14 = telaInicial.getParametro14();
        parametro15 = telaInicial.getParametro15();
        parametro16 = telaInicial.getParametro16();
        parametro17 = telaInicial.getParametro17();
        parametro18 = telaInicial.getParametro18();
        parametro31=telaInicial.getParametro31();
      parametro32=telaInicial.getParametro32();
     parametro33=telaInicial.getParametro33();
      parametro36=telaInicial.getParametro36();
      parametro35=telaInicial.getParametro35();
         parametro34=telaInicial.getParametro34();
        parametro4=telaInicial.getParametro4();
        parametro5=telaInicial.getParametro5();
        parametro6=telaInicial.getParametro6();
         parametro21=telaInicial.getParametro21();
        parametro20=telaInicial.getParametro20();
         parametro19=telaInicial.getParametro19();
        
        chance = telaInicial.getChance();
        tempoSimulacao = telaInicial.getTempoSimulacao();
        distribuicaoDuracaoTEC = telaInicial.getDistribuicaoDuracaoTEC();
        distribuicaoTEC2 = telaInicial.getDistribuicaoTEC2();
        distribuicaoTS = telaInicial.getDistribuicaoTS();
        distribuicaoTS2 = telaInicial.getDistribuicaoTS2();
        falhaTEC = telaInicial.getFalhaServidor1TEC();
      falhaTS= telaInicial.getFalhaServidor1TS();
      falhaTEC2= telaInicial.getFalhaServidor2TEC();
      falhaTS2= telaInicial.getFalhaServidor2TS();
      
        System.out.println("Parametro1 : "+parametro1);
        System.out.println("Parametro2 : "+parametro2);
        System.out.println("Parametro3 : "+parametro3);
        System.out.println("Parametro12 : "+parametro12);
        System.out.println("Parametro11 : "+parametro11);
        System.out.println("Parametro10 : "+parametro10);
        System.out.println("Parametro13 : "+parametro13);
        System.out.println("Parametro14 : "+parametro14);
        System.out.println("Parametro15 : "+parametro15);
        System.out.println("Parametro16 : "+parametro16);
        System.out.println("Parametro17 : "+parametro17);
        System.out.println("Parametro18 : "+parametro18);
        System.out.println("Parametro31 : "+parametro31);
        System.out.println("Parametro32 : "+parametro32);
        System.out.println("Parametro33 : "+parametro33);
        System.out.println("Parametro36 : "+parametro36);
        System.out.println("Parametro35 : "+parametro35);
        System.out.println("Parametro34 : "+parametro34);
        System.out.println("Parametro4 : "+parametro4);
        System.out.println("Parametro5 : "+parametro5);
        System.out.println("Parametro6 : "+parametro6);
        System.out.println("Parametro21 : "+parametro21);
        System.out.println("Parametro20 : "+parametro20);
        System.out.println("Parametro19 : "+parametro19);
        


    }
   
    public static void setup(){
        eventos = new ListaEncadeadaOrdenada();
	relogio = new Relogio();    
        estatistica = Estatistica.getInstance();
        gerador = new Gerador (distribuicaoDuracaoTEC,parametro1,parametro2,parametro3,
        distribuicaoTS,parametro12,parametro11,parametro10,distribuicaoTEC2,parametro13,parametro14,parametro15,
        distribuicaoTS2,parametro18,parametro17,parametro16,falhaTEC,parametro31,parametro32,parametro33,falhaTS,parametro36,parametro35,parametro34,
        falhaTEC2,parametro4,parametro5,parametro6,falhaTS2,parametro21,parametro20,parametro19,chance);
        eventos.adicionarOrdenado(gerador.gerarProximaChegada(relogio.getTempo()));
        eventos.adicionarOrdenado(gerador.gerarProximaFalha1(relogio.getTempo(),eventos));
        eventos.adicionarOrdenado(gerador.gerarProximaFalha2(relogio.getTempo(),eventos));
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        // Definir o tema nativo do sistema operacional
		String osName = System.getProperty("os.name").toLowerCase();
		try {
			if(osName.indexOf( "win" ) >= 0)
				UIManager.setLookAndFeel( "com.sun.java.swing.plaf.windows.WindowsLookAndFeel" );
			else if(osName.indexOf( "nux" ) >= 0)
				UIManager.setLookAndFeel( "com.sun.java.swing.plaf.gtk.GTKLookAndFeel" );
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
                
                telaInicial = new Init();
		telaInicial.setVisible(true);
		telaDeExecucao = new TelaDeExecucao();
		JButton botaoIniciar = telaInicial.getBotaoIniciar();
		botaoIniciar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
                            telaInicial.preprarVariaveis();
                            if(telaInicial.validarDistribuicao() && telaInicial.validarDistribuicao2() && telaInicial.validarDistribuicao3()
                                    && telaInicial.validarDistribuicao4() && telaInicial.validarDistribuicao5() && telaInicial.validarDistribuicao6()
                                    && telaInicial.validarDistribuicao7()
                                    && telaInicial.validarDistribuicao8() && telaInicial.validarChance()) {
                                telaInicial.setVisible(false);
                                
				telaDeExecucao.setVisible(true);
				new Thread(new Runnable() {
					@Override
					public void run() {
                                            try {
                                                iniciar();
                                            } catch (InterruptedException ex) {
                                                Logger.getLogger(Simulador.class.getName()).log(Level.SEVERE, null, ex);
                                            }
					}
				}).start();
                            }
			}
		});
        JButton botaoEncerrar = telaDeExecucao.getBotaoEncerrar();
		botaoEncerrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				pausado = true;
				encerrar = true;
			}
                        });
       JButton botaopausar = telaDeExecucao.getBotaoPausarResumir();
		botaopausar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
                            pausado = pausado==false;
			}
                        });
                JComboBox<?> comboBoxPassosDeExecucao = telaDeExecucao.getjComboBox1();
		comboBoxPassosDeExecucao.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				int indice = Integer.valueOf(telaDeExecucao.getjComboBox1()
						.getSelectedIndex());
				switch (indice) {
				case 0:
					numeroDePassosDeExecucao = 1;
					break;
				case 1:
					numeroDePassosDeExecucao = 3;
					break;

				case 2:
					numeroDePassosDeExecucao = 5;
					break;
				}
			}
                });
        }

    private static void iniciar() throws InterruptedException {
		setarVariaveisVisao();
		setup();
		loop();
		fim();
	}

	private static void fim() {

	}
   private static void avancarTempo() {
		relogio.avancarPara(eventos.getFirst().getTempo());
                long tempo = relogio.getTempo();
                System.out.println("TEMPO : "+tempo);
	} 
   private static void loop() throws InterruptedException {
		while (!encerrar) {
			while (!pausado) {
				if (tempoSimulacao <= relogio.getTempo()) {
					pausado = true;
					encerrar = true;
				}else{
                                    pausado = false;
                                    encerrar = false;
                                }
				int contador = 0;
                                if(passoApasso){
				try {
					Thread.sleep(1750);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					break;
				}
                                mostrarEstatisticas();
                                try {
					Thread.sleep(1750);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					break;
				}
                                }
                                avancarTempo();
				executarEvento();
				contador++;
                                if(passoApasso)
				mostrarEstatisticas();
			}
                        Thread.sleep(1000);
		}
                mostrarEstatisticas();
                JOptionPane.showMessageDialog(null,"Fim do tempo de simulação.\n Estatisticas finais na tela.");
	}
   private static void executarEvento() {
		Evento evento = eventos.pop();
		if (evento instanceof Chegada) {
                    if(evento.getEntidade().getTipo() == Tipo.TIPO1)
                   System.out.println("Chegada TIPO 1:"+evento.getTempo());
                    else
                   System.out.println("Chegada TIPO 2:"+evento.getTempo());
			eventos.adicionarOrdenado(gerador.gerarProximaChegada(relogio
					.getTempo()));
		}
                if(evento instanceof Falha){
                    Evento e = null;
                    if(evento.getEntidade().getTipo() == Tipo.TIPO1)
                     e = gerador.gerarProximaFalha1(relogio.getTempo(), eventos);
                    if(evento.getEntidade().getTipo() == Tipo.TIPO2)
                     e = gerador.gerarProximaFalha2(relogio.getTempo(),eventos);
                    if(e!= null)
                    eventos.adicionarOrdenado(e);
                }
                Evento novoEvento;
                if(evento instanceof Falha){
                    novoEvento = evento.executar(eventos);
                }else{
                    novoEvento = evento.executar();
                }
		if (novoEvento != null) {
			eventos.adicionarOrdenado(novoEvento);
		}
	}
   private static void mostrarEstatisticas() {
		telaDeExecucao.repaint();
		telaDeExecucao.getTaxaMediaDeOcupacaoDeC1().setText(
				String.valueOf(estatistica.getTempoFalha1()));
                telaDeExecucao.getTaxaMediaDeOcupacaoDeC2().setText(
				String.valueOf(estatistica.getTempoFalha2()));
		telaDeExecucao.getCampoTempoAtual().setText(
				String.valueOf(relogio.getTempo()));
		telaDeExecucao.getNumeroDeCanaisDeC1Ocupados().setText(
				String.valueOf(estatistica.getMediaTempoNaFila1()));
		telaDeExecucao.getNumeroDeCanaisDeC2Ocupados().setText(
				String.valueOf(estatistica.getMediaTempoNoSistema1()));
		telaDeExecucao.getNumeroChamadasCompletadas().setText(
				String.valueOf(estatistica.getMediaTempoNoSistema2()));
		telaDeExecucao.getNumeroChamadasNoSistema().setText(
				String.valueOf(estatistica.getContadorDeEntidadesNoSistema()));
		telaDeExecucao.getNumeroMaiorTempoChamada().setText(
				String.valueOf(estatistica.getContadorDeEntidadeTotal()));
		telaDeExecucao.getNumeroMenorTempoChamada().setText(
				String.valueOf(estatistica.getTrocarDe2Pro1()));
		telaDeExecucao.getNumeroPerdidasC1().setText(
				String.valueOf(estatistica.getQuantidadeDefalha1()));
		telaDeExecucao.getNumeroPerdidasC2().setText(
				String.valueOf(estatistica.getQuantidadeDefalha2()));
	//	telaDeExecucao.getNumeroPerdidasFA().setText(
	//			String.valueOf(estatistica.getChamadasSemSinal()));
		telaDeExecucao.getNumeroTempoMedioChamada().setText(
				String.valueOf(estatistica.getMediaTempoNaFila2()));
		telaDeExecucao.getNumeroMaiorChamadasNoSistema()
				.setText(String.valueOf(estatistica.getTrocarDe1Pro2()));
		try {
			atualizarEventosFuturos(1);
		} catch (NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
   private static void atualizarEventosFuturos(int contador) throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		//int contador = 1;
		//telaDeExecucao.getCampoTempoDoEventoFuturo1();
		for (Evento evento : eventos) {
                    String troca = "";
			Method metodoDoCampoTempo = telaDeExecucao.getClass().getMethod(
					"getCampoTempoDoEventoFuturo" + contador);
			JLabel labelTempo = (JLabel) metodoDoCampoTempo
					.invoke(telaDeExecucao);
			Method metodoTipoDoEvento = telaDeExecucao.getClass().getMethod(
					"getCampoTipoDoEvento" + contador);
			JLabel labelTipo = (JLabel) metodoTipoDoEvento
					.invoke(telaDeExecucao);
			labelTempo.setText(String.valueOf(evento.getTempo()));
                        if(evento.getEntidade().getTrocou() == true)
                            troca = " trocada";
			labelTipo.setText(evento.getClass().getSimpleName()+" "+evento.getEntidade().getTipo()+troca);
			if (contador == 13) {
				return;
			}
			contador++;
		}
		while (contador <= 13) {
			Method metodoDoCampoTempo = telaDeExecucao.getClass().getMethod(
					"getCampoTempoDoEventoFuturo" + contador);
			JLabel labelTempo = (JLabel) metodoDoCampoTempo
					.invoke(telaDeExecucao);
			Method metodoTipoDoEvento = telaDeExecucao.getClass().getMethod(
					"getCampoTipoDoEvento" + contador);
			JLabel labelTipo = (JLabel) metodoTipoDoEvento
					.invoke(telaDeExecucao);
			labelTempo.setText("---");
			labelTipo.setText("---");
			contador++;
		}
	}
}
