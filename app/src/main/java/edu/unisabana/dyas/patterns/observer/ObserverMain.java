package edu.unisabana.dyas.patterns.observer;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import edu.unisabana.dyas.patterns.observer.impl.ConfigurationManager;
import edu.unisabana.dyas.patterns.observer.impl.observers.DateFormatObserver;
import edu.unisabana.dyas.patterns.observer.impl.observers.MoneyFormatObserver;

/**
 * @author 
 */
public class ObserverMain {

    public static void main(String[] args) {
        // Obtenemos la instancia única de ConfigurationManager
        ConfigurationManager conf = ConfigurationManager.getInstance();

        // Creamos los observadores que están interesados en los cambios de la configuración
        DateFormatObserver dateFormatObserver = new DateFormatObserver();
        MoneyFormatObserver moneyFormatObserver = new MoneyFormatObserver();

        // Registramos (suscribimos) los observadores en el observable
        conf.addObserver(dateFormatObserver);
        conf.addObserver(moneyFormatObserver);

        // Establecemos la configuración inicial (esto notificará de inmediato a los observadores)
        conf.setDefaultDateFormat(new SimpleDateFormat("yyyy/MM/dd"));
        conf.setMoneyFormat(new DecimalFormat("##.00"));
        System.out.println("Established configuration");

        System.out.println("");

        // Realizamos cambios en la configuración para ver cómo se notifica automáticamente
        conf.setDefaultDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
        conf.setMoneyFormat(new DecimalFormat("###,#00.00"));

        System.out.println("");

        // Otro cambio
        conf.setDefaultDateFormat(new SimpleDateFormat("MM/yyyy/dd"));
        conf.setMoneyFormat(new DecimalFormat("###,#00"));

        // Removemos los observadores
        conf.removeObserver(dateFormatObserver);
        conf.removeObserver(moneyFormatObserver);

        System.out.println("");

        // Al hacer un nuevo cambio, ya no hay observadores que reciban notificaciones
        conf.setDefaultDateFormat(new SimpleDateFormat("MM/yyyy"));
        conf.setMoneyFormat(new DecimalFormat("###,##0.00"));
    }
}