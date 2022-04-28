package org.example.unittests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.util.logging.Logger;

/**
 * El nuevo becario se ha encargado de programar una nueva calculadora para nuestro sistema de cobro en caja.
 * Antes de desplegarla en producción, nos han pedido verificar que cumple unos mínimos de calidad.
 * Debemos comprobar el correcto funcionamiento de la "calculadora segura" que nos ha proporcionado.
 * En caso de detectar comportamiento erróneo, arreglalo y comprueba que el test funciona correctamente.
 * En concreto, es necesario verificar la siguiente funcionalidad:
 * - En caso de intento de división entre 0, debe tirar ArithmeticException.
 * - Debe protegerse contra Overflows de forma correcta: por ejemplo la multiplicacion de dos numeros positivos nunca puede dar un numero negativo. En caso de overflow, tirar ArithmeticException.
 * - Debe generar numeros aleatorios de forma correcta, en el limite pedido
 * - Debe detectar correctamente cuando un numero es par y cuando un numero es impar
 * - Debe emitir mensajes de log si ha sido configurada para ello.
 */
public class SecureCalculatorTests {

    /**
     * Verify calculator is successfully created
     */
    @Test
    public void smokeTest(){
        SecureCalculator calculator = new SecureCalculator();
        Assertions.assertNotNull(calculator);
    }

    @Test
    public void isEvenTest(){
        SecureCalculator calculator = new SecureCalculator();
        for (int i = 0; i<100; i++){
            Assertions.assertTrue(calculator.isEven(i*2),"Testing "+i);
        }
    }

    @Test
    public void isOddTest(){
        SecureCalculator calculator = new SecureCalculator();
        for (int i = 1; i<100; i = i+2){
            Assertions.assertTrue(calculator.isOdd(i),"Testing "+i);
        }
    }

    @Test
    public void divideTest(){
        SecureCalculator calculator = new SecureCalculator();
        Assertions.assertThrows(ArithmeticException.class,()->calculator.divide(2,0));
    }

    @Test
    public void multiplyTest(){
        SecureCalculator calculator = new SecureCalculator();
        Assertions.assertThrows(ArithmeticException.class,()->calculator.multiply(Integer.MAX_VALUE,Integer.MAX_VALUE));
        Assertions.assertThrows(ArithmeticException.class,()->calculator.multiply(Integer.MIN_VALUE,Integer.MIN_VALUE));
        Assertions.assertThrows(ArithmeticException.class,()->calculator.multiply(Integer.MAX_VALUE,Integer.MIN_VALUE));
        Assertions.assertDoesNotThrow(()->calculator.multiply(5,5));
        Assertions.assertThrows(ArithmeticException.class,()->calculator.multiply(Integer.MAX_VALUE,2));
        Assertions.assertThrows(ArithmeticException.class,()->calculator.multiply(2,Integer.MIN_VALUE));
    }

    @Test
    public void randomTest(){
        SecureCalculator calculator = new SecureCalculator();
        Assertions.assertTrue(calculator.getRandomNumber(6)<=6);
        Assertions.assertTrue(calculator.getRandomNumber()<Integer.MAX_VALUE);
    }

    @Test
    public void modTest(){
        SecureCalculator calculator = new SecureCalculator();
        Assertions.assertThrows(ArithmeticException.class,()->calculator.mod(2,0));
    }

    @Mock
     Logger logger;

    @Test
    public void logTest(){
        logger = Logger.getLogger("MyLog");
        SecureCalculator calculator = new SecureCalculator(logger);
        calculator.multiply(1,2);
        BDDMockito.verify(logger).info(Mockito.anyString());

    }
}
