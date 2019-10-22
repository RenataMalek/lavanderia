package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Metodos {

	public static boolean verificarCPF(String CPF) {

		char n10, n11;
		int soma, aux1, aux2, numero, digito;

		soma = 0;
		digito = 10;
		for (aux1 = 0; aux1 < 9; aux1++) {
			numero = (int) (CPF.charAt(aux1) - 48);
			soma += (numero * digito);
			digito--;
		}

		aux2 = 11 - (soma % 11);
		if ((aux2 == 10) || (aux2) == 11)
			n10 = '0';
		else
			n10 = (char) (aux2 + 48);

		soma = 0;
		digito = 11;
		for (aux1 = 0; aux1 < 10; aux1++) {
			numero = (int) (CPF.charAt(aux1) - 48);
			soma += (numero * digito);
			digito--;
		}

		aux2 = 11 - (soma % 11);
		if ((aux2 == 10) || (aux2 == 11))
			n11 = '0';
		else
			n11 = (char) (aux2 + 48);

		if ((n10 == CPF.charAt(9)) && (n11 == CPF.charAt(10)))
			return (true);
		else
			return (false);

	}

	

	public GregorianCalendar verificarDataDevolucao(int tipoPedido, String dataColeta) throws ParseException {

		GregorianCalendar verData = new GregorianCalendar();
		Date data;
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
		data = formato.parse(dataColeta);
		
		verData.setTime(data);

		if (tipoPedido == 0) {
			verData.add(Calendar.DATE, +3);
			while (verData.get(Calendar.DAY_OF_WEEK) == 7 || verData.get(Calendar.DAY_OF_WEEK) == 1) {
				verData.add(Calendar.DATE, +1);
			}
		} else if (tipoPedido == 1) {
			verData.add(Calendar.DATE, +1);
			while (verData.get(Calendar.DAY_OF_WEEK) == 7 || verData.get(Calendar.DAY_OF_WEEK) == 1) {
				verData.add(Calendar.DATE, +1);
			}
		}

		return verData;

	}
}
