package com.sample;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.io.*;
/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {
	public static int number = 1;
    public static final void main(String[] args) {
        try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");
        	
            Klient klient = new Klient(true);
            kSession.insert(klient);
            Rezerwacja rez_1 = new Rezerwacja( klient, "11/11/2016", "12/11/2016", true, true);
            kSession.insert(rez_1);
            kSession.fireAllRules();
            
            Zwrot zwrot = new Zwrot(rez_1, "12/11/2016", 66, false, 0);
            kSession.insert(zwrot);
            kSession.fireAllRules();
            
            
            Klient klient1 = new Klient(false);
            kSession.insert(klient1);
            Rezerwacja rez1_1 = new Rezerwacja("B", klient1, "10/11/2016", "13/11/2016", false, true);
            Rezerwacja rez1_2 = new Rezerwacja("A", klient1, "11/11/2016", "14/11/2016", false, false);
            kSession.insert(rez1_1);
            kSession.fireAllRules();
            kSession.insert(rez1_2);
            kSession.fireAllRules();
            
            Zwrot zwrot1 = new Zwrot(rez1_1, "13/11/2016", 100, false, 0);
            kSession.insert(zwrot1);
            kSession.fireAllRules();
            
            
            Klient klient2 = new Klient(false);
            kSession.insert(klient2);
            Rezerwacja rez2_1 = new Rezerwacja("B", klient2, "11/11/2016", "16/11/2016", false, false);
            kSession.insert(rez2_1);
            kSession.fireAllRules();
            
            Zwrot zwrot2 = new Zwrot(rez2_1, "18/11/2016", 1000, true, 2);
            kSession.insert(zwrot2);
            kSession.fireAllRules();

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
    //Klient
    public static class Klient {
    	public String imie;
    	private ArrayList<Rezerwacja> rezerwacje = new ArrayList();
    	public boolean program;
    	
    	public Klient(boolean program) {
    		this.imie = "Klient_" + number;
    		this.program = program;
    		number++;
    	}
    }
    //Rezerwacja
    public static class Rezerwacja {
    	public int kosztDnia;
    	public Klient klient;
    	public LocalDate poczWyp;
    	public LocalDate konWyp;	
    	public Samochod samochod;
    	public String klasa;
    	public int koszt;
    	public boolean fotelik;
    	public boolean bagaznik;
    	public int liczbaDni;
    	
    	public Rezerwacja(Klient klient, String poczWyp, String konWyp, boolean fotelik, boolean bagaznik) {
    		this.klient = klient;
    		klient.rezerwacje.add(this);
    		this.fotelik = fotelik;
    		this.bagaznik = bagaznik;
    		try {
    			this.poczWyp = LocalDate.parse(poczWyp, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    			this.konWyp = LocalDate.parse(konWyp, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    		} catch (Exception e) {
    		}
    		this.liczbaDni = ileDni();
    	}
    	
    	public Rezerwacja(String klasa, Klient klient, String poczWyp, String konWyp, boolean fotelik, boolean bagaznik) {
    		this.klasa = klasa;
    		this.klient = klient;
    		this.fotelik = fotelik;
    		this.bagaznik = bagaznik;
    		try {
    			this.poczWyp = LocalDate.parse(poczWyp, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    			this.konWyp = LocalDate.parse(konWyp, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    		} catch (Exception e) {
    		}
    		this.liczbaDni = ileDni();
    		
    	}
    	public int ileDni() {
    		int x = (int) ChronoUnit.DAYS.between(poczWyp, konWyp) + 1;
    		return x;
    	}    	
    }
    //Zwrot
    public static class Zwrot {
    	public Rezerwacja rez;
    	public LocalDate dataZwr;
    	public int dystans;
    	public boolean zgubDowod;
    	public int liczbaZgubKolp;
    	public int koszt;
    	
    	public Zwrot(Rezerwacja rez, String data, int dystans, boolean zgubionyDowod, int liczbaZgubionychKolpakow) {
    		this.rez = rez;
    		this.dystans = dystans;
    		this.zgubDowod = zgubionyDowod;
    		this.liczbaZgubKolp = liczbaZgubionychKolpakow;
    		try {
    			this.dataZwr = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    		} catch (Exception e) {
    		}	
    	}
    	
    	public int ileDni(LocalDate poczatek, LocalDate koniec) {
    		int x = (int) ChronoUnit.DAYS.between(poczatek, koniec);
    		return x;
    	}
    }
    //Samochod
    public static class Samochod {
    	public String klasa;
    	public int cenaPodst;
    	public int cenaObn;
    	public ArrayList<LocalDate> poczWyp = new ArrayList();
    	public ArrayList<LocalDate> konWyp = new ArrayList();
    	
    	public Samochod(String klasa) {
    		this.klasa = klasa.toUpperCase();
    	}
		
		public boolean konflikt(LocalDate p, LocalDate k) {
			for(int i = 0; i < poczWyp.size(); i ++){
				if ((poczWyp.get(i).isBefore(p) && konWyp.get(i).isAfter(p)) || (poczWyp.get(i).isBefore(k) && konWyp.get(i).isAfter(k)))
					return true;
			}
			return false;
		}
    	
    	
    }

}
