package com.pozar;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * This is a sample class to launch a rule.
 */
public class DroolsPozar {

	public static final void main(String[] args) {
		try {
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("ksession-rules");

			kSession.fireAllRules();
			kSession.insert(new Straz());
			kSession.fireAllRules();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public static class Pomiar {
		private int temperatura;
		private boolean dym;
		
		public Pomiar(int temperatura, boolean dym) {
			this.temperatura = temperatura;
			this.dym = dym;
		}

		public boolean isDym() {
			return dym;
		}

		public void setDym(boolean dym) {
			this.dym = dym;
		}

		public int getTemperatura() {
			return temperatura;
		}

		public void setTemperatura(int temperatura) {
			this.temperatura = temperatura;
		}

	}

	public static class Pozar {
		private String message;
		public Pozar(String message){ 
			this.message = message;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
		
	}
	public static class Telefon {
		private int numer;
		
		public Telefon(int numer) {
			this.numer = numer;
		}

		public int getNumer() {
			return numer;
		}

		public void setNumer(int numer) {
			this.numer = numer;
		}
	}
	
	public static class Straz {
		
	}

}
