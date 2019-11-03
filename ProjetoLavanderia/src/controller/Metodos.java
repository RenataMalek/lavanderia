package controller;


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

}
